package de.olfillasodikno.openvolt.lib.structures;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RVParameters {
	private static final char CONTEXT_BEGIN_TOKEN = '{';
	private static final char CONTEXT_END_TOKEN = '}';
	private static final char COMMENT_TOKEN = ';';

	private static final char STRING_TOKEN = '"';
	
	private final Context root;
	
	public RVParameters(Context root) {
		this.root = root;
	}
	
	public Context getRoot() {
		return root;
	}

	public static RVParameters fromFile(File f) throws IOException {

		List<String> lines = Files.readAllLines(f.toPath());

		RawContext fileContext = new RawContext(null);
		parseContext(lines, fileContext);
		Context build = fileContext.build(null);
		RVParameters param = new RVParameters(build.getSubcontexts().get(""));
		return param;
	}

	private static void parseContext(List<String> lines, RawContext ctx) {

		RawContext currentContext = ctx;

		for (String line : lines) {
			ArrayList<TypeObject<?>> types = currentContext.getTypes();

			char[] chars = line.toCharArray();
			int lastParsed = -1;

			int startString = 0;

			boolean inString = false;

			for (int i = 0; i < line.length(); i++) {
				char c = chars[i];
				if (c == COMMENT_TOKEN && !inString) {
					TypeObject<String> object = new TypeObject<String>(Type.COMMENT,
							line.substring(i, line.length() - 1));
					types.add(object);
					break;
				} else if (c == CONTEXT_BEGIN_TOKEN && !inString) {
					RawContext context = new RawContext(currentContext);
					TypeObject<RawContext> object = new TypeObject<RawContext>(Type.CONTEXT, context);
					types.add(object);
					lastParsed = i;
					currentContext = context;
				} else if (c == CONTEXT_END_TOKEN && !inString) {
					currentContext = currentContext.getParent();
					lastParsed = i;
					if (currentContext == null) {
						return;
					}
				} else if (c == STRING_TOKEN) {
					inString = !inString;
					if (!inString) {
						TypeObject<String> object = new TypeObject<String>(Type.STRING, line.substring(startString, i));
						types.add(object);
						lastParsed = i;
					} else {
						startString = i + 1;
					}
				} else if (c == (char) 0x20 || c == (char) 0x09 || i == line.length() - 1) {
					String substring = line.substring(lastParsed + 1, i + 1).trim();
					if (substring.isEmpty()) {
						continue;
					}
					TypeObject<?> object = null;
					if (!substring.contains(".")) {
						try {
							int x = Integer.parseInt(substring);
							object = new TypeObject<Integer>(Type.INT, x);
						} catch (NumberFormatException e) {
						}
					} else {
						try {
							float x = Float.parseFloat(substring);
							object = new TypeObject<Float>(Type.FLOAT, x);
						} catch (NumberFormatException e) {
						}
					}
					if (substring.equalsIgnoreCase("true") || substring.equalsIgnoreCase("false")) {
						boolean x = substring.equalsIgnoreCase("true");
						object = new TypeObject<Boolean>(Type.BOOLEAN, x);
					}

					if (object == null) {
						object = new TypeObject<String>(Type.NAME, substring);
					}
					types.add(object);
					lastParsed = i;
				}
			}
		}
	}

	private static enum Type {
		NAME, STRING, FLOAT, INT, BOOLEAN, COMMENT, CONTEXT
	}

	private static class TypeObject<V> {
		private final Type type;
		private final V object;

		public TypeObject(Type type, V object) {
			this.type = type;
			this.object = object;
		}

		public Type getType() {
			return type;
		}

		public V getObject() {
			return object;
		}
	}

	private static class RawContext {

		private final ArrayList<TypeObject<?>> types;

		private final RawContext parent;

		public RawContext(RawContext parent) {
			types = new ArrayList<>();
			this.parent = parent;
		}

		public ArrayList<TypeObject<?>> getTypes() {
			return types;
		}

		public RawContext getParent() {
			return parent;
		}

		public Context build(String name) {
			Context current;
			current = new Context(name);
			int lastName = 0;
			for (int i = 0; i < types.size(); i++) {
				TypeObject<?> type = types.get(i);
				if (type.getType() == Type.CONTEXT) {
					RawContext raw = (RawContext) types.get(i).getObject();
					StringBuilder sb = new StringBuilder();
					for (int j = lastName; j < i; j++) {
						sb.append(types.get(j).getObject());
						if (j != i - 1) {
							sb.append("_");
						}
					}
					String ctxName = sb.toString();
					current.getSubcontexts().put(ctxName, raw.build(ctxName));
				} else if ((type.getType() == Type.NAME || i == types.size()-1) && name != null) {
					TypeObject<?> descriptor = types.get(lastName);
					ArrayList<Object> values = new ArrayList<>();
					for (int j = lastName + 1; j < i; j++) {
						TypeObject<?> obj = types.get(j);
						if(obj.getType() == Type.COMMENT) {
							continue;
						}
						values.add(obj.getObject());
					}
					String descriptorName = (String) descriptor.object;
					if(!current.getObjects().containsKey(descriptorName))
					{
						current.getObjects().put(descriptorName, new ArrayList<>());						
					}
					current.getObjects().get(descriptorName).add(values);
					lastName = i;
				}
			}

			return current;
		}
	}

	public static class Context {
		private final HashMap<String, Context> subcontexts;

		private final HashMap<String, ArrayList<ArrayList<Object>>> objects;

		private final String name;

		public Context(String name) {
			subcontexts = new HashMap<>();
			objects = new HashMap<>();
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public HashMap<String, ArrayList<ArrayList<Object>>> getObjects() {
			return objects;
		}

		public HashMap<String, Context> getSubcontexts() {
			return subcontexts;
		}
	}

	public static void main(String[] args) throws IOException {
		File test = new File("parameters.txt");
		RVParameters param = RVParameters.fromFile(test);
		System.out.println(param.getRoot().getObjects().get("MODEL").get(1));
	}
}
