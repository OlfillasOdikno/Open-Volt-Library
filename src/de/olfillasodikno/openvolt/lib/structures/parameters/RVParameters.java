package de.olfillasodikno.openvolt.lib.structures.parameters;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
					substring = substring.replace(",", "");
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
			int lastName = 1;
			for (int i = 0; i < types.size(); i++) {
				TypeObject<?> type = types.get(i);
				if (type.getType() == Type.CONTEXT) {
					RawContext raw = (RawContext) types.get(i).getObject();
					StringBuilder sb = new StringBuilder();
					for (int j = lastName; j < i; j++) {
						sb.append(types.get(j).getObject());
						if (j != i - 1) {
							sb.append(" ");
						}
					}
					String ctxName = sb.toString();
					current.getSubcontexts().put(ctxName, raw.build(ctxName));
				} else if ((type.getType() == Type.NAME || i == types.size() - 1) && name != null) {
					TypeObject<?> descriptor = types.get(lastName);
					if (descriptor.type != Type.NAME) {
						lastName = i;
						continue;
					}
					String descriptorName = (String) descriptor.object;
					ArrayList<Object> values = new ArrayList<>();
					for (int j = lastName + 1; j < i; j++) {
						TypeObject<?> obj = types.get(j);
						if (obj.getType() == Type.COMMENT) {
							continue;
						}
						values.add(obj.getObject());
					}
					if (!current.getObjects().containsKey(descriptorName)) {
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

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	protected @interface Param {
		String value();
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	protected @interface Con {
		String value();
	}

	private static final HashMap<Class<?>, ParamWrapper<?>> wrappers = new HashMap<>();

	public static void registerWrapper(ParamWrapper<?> wrapper) {
		wrappers.put(wrapper.getWrapping(), wrapper);
	}

	static {
		registerWrapper(new RVVectorFWrapper());
		registerWrapper(new RVVectorIWrapper());
	}

	public void decode() {
		decodeContext(getRoot(), getClass(), this);
	}

	private void decodeContext(Context current, Class<?> clazz, Object instance) {
		HashMap<String, Field> params = new HashMap<>();
		for (Field f : clazz.getDeclaredFields()) {
			if (f.isAnnotationPresent(Param.class)) {
				Param p = f.getAnnotation(Param.class);
				params.put(p.value(), f);
			}
		}
		HashMap<String, ArrayList<ArrayList<Object>>> objects = current.getObjects();
		for (Map.Entry<String, ArrayList<ArrayList<Object>>> entry : objects.entrySet()) {
			String key = entry.getKey();
			if (!params.containsKey(key) || entry.getValue().isEmpty()) {
				continue;
			}
			Field f = params.get(key);
			boolean primitive = f.getType().isPrimitive();
			boolean array = f.getType().isArray();
			if (array) {
				ArrayList<ArrayList<Object>> vals = entry.getValue();
				Object[] arr = (Object[]) Array.newInstance(f.getType().getComponentType(), vals.size());
				for (int i = 0; i < arr.length; i++) {
					ArrayList<Object> values = vals.get(i);
					if (values.size() < 2) {
						continue;
					}
					Object idx = values.remove(0);
					if (!(idx instanceof Integer) || (int) idx >= arr.length) {
						continue;
					}
					Object val = values.get(0);
					Class<?> type = f.getType().getComponentType();
					if (wrappers.containsKey(type)) {
						val = wrappers.get(type).wrap(values);
						if (val == null) {
							System.err.println("Failed to wrap: " + key);
							continue;
						}
					}
					if (!(val.getClass().equals(type))) {
						continue;
					}
					arr[(int) idx] = val;
				}
				try {
					f.setAccessible(true);
					f.set(instance, arr);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			} else {
				ArrayList<Object> vals = entry.getValue().get(0);
				setField(key, f, instance, primitive, vals);
			}
		}
		HashMap<String, Context> subcontexts = current.getSubcontexts();
		for (Map.Entry<String, Context> entry : subcontexts.entrySet()) {
			String key = entry.getKey();
			if (!params.containsKey(key)) {
				continue;
			}
			try {
				Field f = params.get(key);
				Object o = f.getType().newInstance();
				decodeContext(entry.getValue(), f.getType(), o);
				f.setAccessible(true);
				f.set(instance, o);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
				continue;
			}
		}
	}

	private static void setField(String key, Field f, Object instance, boolean primitive, ArrayList<Object> vals) {
		if (vals.isEmpty()) {
			return;
		}
		if (primitive) {
			Object val = vals.get(0);
			Class<?> type = f.getType();
			try {
				f.setAccessible(true);
				if (type.equals(boolean.class)) {
					if (!(val instanceof Boolean)) {
						System.err.println("Invalid type for: " + key + " val: " + val);
						return;
					}
					f.setBoolean(instance, (boolean) val);
				} else if (type.equals(int.class)) {
					if (!(val instanceof Integer)) {
						System.err.println("Invalid type for: " + key + " val: " + val);
						return;
					}
					f.setInt(instance, (int) val);
				} else if (type.equals(float.class)) {
					if (!(val instanceof Float) && !(val instanceof Integer)) {
						System.err.println("Invalid type for: " + key + " val: " + val);
						return;
					}
					f.setFloat(instance, (float) val);
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		} else {
			Object val = vals.get(0);
			Class<?> type = f.getType();
			if (wrappers.containsKey(type)) {
				val = wrappers.get(type).wrap(vals);
				if (val == null) {
					System.err.println("Failed to wrap: " + key+" val: "+val);
					return;
				}
			}
			if (!(val.getClass().equals(type))) {
				return;
			}
			try {
				f.setAccessible(true);
				f.set(instance, val);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		File test = new File("parameters.txt");
		RVCarParameters param = new RVCarParameters(RVParameters.fromFile(test).getRoot());
		param.decode();
		System.out.println("Name: "+param.getName());
	}
}
