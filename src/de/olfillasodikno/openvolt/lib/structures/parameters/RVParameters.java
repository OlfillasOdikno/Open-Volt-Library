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
import java.util.logging.Level;
import java.util.logging.Logger;

public class RVParameters {
	private static final char CONTEXT_BEGIN_TOKEN = '{';
	private static final char CONTEXT_END_TOKEN = '}';
	private static final char COMMENT_TOKEN = ';';

	private static final char STRING_TOKEN = '"';

	private static final char RV_12_TOKEN = ')';

	protected static final Logger logger = Logger.getLogger(RVParameters.class.getName());

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
		return new RVParameters(build.getSubcontexts().get("").get(0));
	}

	private static void parseContext(List<String> lines, RawContext ctx) {

		RawContext currentContext = ctx;

		for (String line : lines) {
			currentContext = parseLine(line, currentContext);
			if (currentContext == null) {
				return;
			}
		}
	}

	private static RawContext parseLine(String line, RawContext currentContext) {
		List<TypeObject<?>> types = currentContext.getTypes();

		char[] chars = line.toCharArray();
		int lastParsed = -1;

		int startString = 0;

		boolean inString = false;

		boolean newParams = false;

		for (int i = 0; i < line.length(); i++) {
			char c = chars[i];
			if (c == COMMENT_TOKEN && !inString) {
				if (i < line.length() - 1 && chars[i + 1] == RV_12_TOKEN) {
					i++;
					newParams = true;
					lastParsed = i;
				} else {
					TypeObject<String> object = new TypeObject<>(Type.COMMENT, line.substring(i, line.length() - 1));
					types.add(object);
				}
			} else if (c == CONTEXT_BEGIN_TOKEN && !inString) {
				RawContext context = new RawContext(currentContext);
				TypeObject<RawContext> object = new TypeObject<>(Type.CONTEXT, context);
				if(newParams) {
					object.setNew(true);
				}
				types.add(object);
				lastParsed = i;
				currentContext = context;
			} else if (c == CONTEXT_END_TOKEN && !inString) {
				currentContext = currentContext.getParent();
				lastParsed = i;
				if (currentContext == null) {
					return null;
				}
			} else if (c == STRING_TOKEN) {
				inString = !inString;
				if (!inString) {
					TypeObject<String> object = new TypeObject<>(Type.STRING, line.substring(startString, i));
					if(newParams) {
						object.setNew(true);
					}
					types.add(object);
					lastParsed = i;
				} else {
					startString = i + 1;
				}
			} else if (c == (char) 0x20 || c == (char) 0x09 || i == line.length() - 1) {
				TypeObject<?> o = parseObject(line, lastParsed, i);
				if (o != null) {
					if(newParams) {
						o.setNew(true);
					}
					types.add(o);
				}
				lastParsed = i;
			}
		}
		return currentContext;
	}

	private static TypeObject<?> parseObject(String line, int lastParsed, int i) {
		String substring = line.substring(lastParsed + 1, i + 1).trim();
		substring = substring.replace(",", "");
		if (substring.isEmpty()) {
			return null;
		}
		TypeObject<?> object = null;
		if (!substring.contains(".")) {
			try {
				int x = Integer.parseInt(substring);
				object = new TypeObject<>(Type.INT, x);
			} catch (NumberFormatException e) {
				// Nothing to do here
			}
		} else {
			try {
				float x = Float.parseFloat(substring);
				object = new TypeObject<>(Type.FLOAT, x);
			} catch (NumberFormatException e) {
				// Nothing to do here
			}
		}
		if (substring.equalsIgnoreCase("true") || substring.equalsIgnoreCase("false")) {
			boolean x = substring.equalsIgnoreCase("true");
			object = new TypeObject<>(Type.BOOLEAN, x);
		}
		if (object == null) {
			object = new TypeObject<>(Type.NAME, substring);
		}
		return object;
	}

	private enum Type {
		NAME, STRING, FLOAT, INT, BOOLEAN, COMMENT, CONTEXT
	}

	private static class TypeObject<V> {
		
		private boolean isNew;
		
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
		
		public void setNew(boolean isNew) {
			this.isNew = isNew;
		}
		
		public boolean isNew() {
			return isNew;
		}
	}

	private static class RawContext {

		private final ArrayList<TypeObject<?>> types;

		private final RawContext parent;

		public RawContext(RawContext parent) {
			types = new ArrayList<>();
			this.parent = parent;
		}

		public List<TypeObject<?>> getTypes() {
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
					RawContext raw = (RawContext) type.getObject();
					ArrayList<TypeObject<?>> typeObjects = new ArrayList<>();
					for (int j = lastName; j < i; j++) {
						typeObjects.add(types.get(j));
					}
					String ctxName = "";
					if (!typeObjects.isEmpty()) {
						ctxName = (String) typeObjects.get(0).object;
					}
					if (!current.getSubcontexts().containsKey(ctxName)) {
						current.getSubcontexts().put(ctxName, new HashMap<>());
					}

					int key = 0;
					if (typeObjects.size() > 1 && typeObjects.get(1).type == Type.INT) {
						key = (int) typeObjects.get(1).object;
					}

					current.getSubcontexts().get(ctxName).put(key, raw.build(ctxName));
				} else if ((type.getType() == Type.NAME || i == types.size() - 1) && name != null) {
					TypeObject<?> descriptor = types.get(lastName);
					if (descriptor.type != Type.NAME) {
						lastName = i;
						continue;
					}
					String descriptorName = (String) descriptor.object;
					ArrayList<Object> values = new ArrayList<>();
					if(i == types.size() - 1) {
						i++;
					}
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
		private final HashMap<String, Map<Integer, Context>> subcontexts;

		private final HashMap<String, List<List<Object>>> objects;

		private final String name;

		public Context(String name) {
			subcontexts = new HashMap<>();
			objects = new HashMap<>();
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public Map<String, List<List<Object>>> getObjects() {
			return objects;
		}

		public Map<String, Map<Integer, Context>> getSubcontexts() {
			return subcontexts;
		}
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	protected @interface Param {
		String value();

		ParamType type() default ParamType.NORMAL;
	}

	public enum ParamType {
		NORMAL, CONTEXT;
	}

	private static final HashMap<Class<?>, ParamWrapper<?>> wrappers = new HashMap<>();

	public static void registerWrapper(ParamWrapper<?> wrapper) {
		wrappers.put(wrapper.getWrapping(), wrapper);
	}

	static {
		registerWrapper(new RVVectorFWrapper());
		registerWrapper(new RVVectorIWrapper());
		registerWrapper(new RVOffsetsFWrapper());
	}

	public void decode() {
		decodeContext(getRoot(), getClass(), this);
	}

	private static void decodeContext(Context current, Class<?> clazz, Object instance) {
		Map<String, Field> params = new HashMap<>();
		Map<String, Field> contexts = new HashMap<>();
		while(clazz != RVParameters.class && clazz != null) {
			for (Field f : clazz.getDeclaredFields()) {
				if (!f.isAnnotationPresent(Param.class)) {
					continue;
				}
				Param p = f.getAnnotation(Param.class);
				if (p.type() == ParamType.NORMAL) {
					params.put(p.value(), f);
				} else if (p.type() == ParamType.CONTEXT) {
					contexts.put(p.value(), f);
				}
			}
			clazz = clazz.getSuperclass();
		}
		Map<String, List<List<Object>>> objects = current.getObjects();
		objects.forEach((key, vals) -> {
			if (!params.containsKey(key) || vals.isEmpty()) {
				return;
			}
			Field f = params.get(key);
			decodeObject(key, vals, f, instance);
		});
		Map<String, Map<Integer, Context>> subcontexts = current.getSubcontexts();
		subcontexts.forEach((key, ctx) -> {
			if (!contexts.containsKey(key) || ctx.isEmpty()) {
				return;
			}
			Field f = contexts.get(key);
			decodeSubcontext(ctx, f, instance);
		});
	}

	private static void decodeObject(String key, List<List<Object>> vals, Field f, Object instance) {
		boolean primitive = f.getType().isPrimitive();
		boolean array = f.getType().isArray();
		if (array) {
			if (!setArrayField(f, instance, vals)) {
				logger.log(Level.SEVERE, "Failed to wrap: {0}", key);
			}
		} else {
			setField(key, f, instance, primitive, vals.get(0));
		}
	}

	private static void decodeSubcontext(Map<Integer, Context> ctx, Field f, Object instance) {
		if (f.getType().isArray()) {
			int max = 0;
			for (Integer i : ctx.keySet()) {
				max = Math.max(max, i);
			}
			Object[] arr = (Object[]) Array.newInstance(f.getType().getComponentType(), max + 1);
			ctx.forEach((k, v) -> {
				try {
					Object o = f.getType().getComponentType().newInstance();
					decodeContext(v, f.getType().getComponentType(), o);
					arr[k] = o;
				} catch (InstantiationException | IllegalAccessException e) {
					logger.log(Level.SEVERE, e.getMessage(), e.getCause());
				}
			});
			setField(f, instance, arr);
		} else {
			Context con = ctx.values().iterator().next();
			try {
				Object o = f.getType().newInstance();
				decodeContext(con, f.getType(), o);
				setField(f, instance, o);
			} catch (InstantiationException | IllegalAccessException e) {
				logger.log(Level.SEVERE, e.getMessage(), e.getCause());
			}
		}
	}

	private static boolean setArrayField(Field f, Object instance, List<List<Object>> vals) {
		Object[] arr = (Object[]) Array.newInstance(f.getType().getComponentType(), vals.size());
		for (int i = 0; i < arr.length; i++) {
			List<Object> values = vals.get(i);
			Object idx = null;
			if (values.size() >= 2) {
				idx = values.remove(0);
			}
			if (idx == null || !(idx instanceof Integer) || (int) idx >= arr.length) {
				continue;
			}
			Object val = values.get(0);
			Class<?> type = f.getType().getComponentType();
			if (wrappers.containsKey(type)) {
				val = wrappers.get(type).wrap(values);
				if (val == null || !(val.getClass().equals(type))) {
					return false;
				} else {
					arr[(int) idx] = val;
				}
			} else {
				arr[(int) idx] = val;
			}
		}
		try {
			f.setAccessible(true);
			f.set(instance, arr);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			logger.log(Level.SEVERE, e.getMessage(), e.getCause());
			return false;
		}
		return true;
	}

	private static void setField(String key, Field f, Object instance, boolean primitive, List<Object> vals) {
		if (vals.isEmpty()) {
			return;
		}
		if (primitive) {
			Object val = vals.get(0);
			if (!setPrimitiveField(f, instance, val)) {
				String msg = "Invalid type for: " + key + " val: " + val;
				logger.warning(msg);
			}
		} else {
			if (!setField(f, instance, vals)) {
				String msg = "Failed to wrap: " + key + " vals: " + vals;
				logger.warning(msg);
			}
		}
	}

	private static boolean setField(Field f, Object instance, Object val) {
		f.setAccessible(true);
		try {
			f.set(instance, val);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			logger.log(Level.SEVERE, e.getMessage(), e.getCause());
			return false;
		}
		return true;
	}

	private static boolean setField(Field f, Object instance, List<Object> vals) {
		Object val = vals.get(0);
		Class<?> type = f.getType();
		if (wrappers.containsKey(type)) {
			val = wrappers.get(type).wrap(vals);
		}
		if (val == null || !(val.getClass().equals(type))) {
			return false;
		}
		return setField(f, instance, val);
	}

	private static boolean setPrimitiveField(Field f, Object instance, Object val) {
		Class<?> type = f.getType();
		try {
			f.setAccessible(true);
			if (type.equals(boolean.class) && val instanceof Boolean) {
				f.setBoolean(instance, (boolean) val);
			} else if (type.equals(int.class) && val instanceof Integer) {
				f.setInt(instance, (int) val);
			} else if (type.equals(float.class) && ((val instanceof Float) || (val instanceof Integer))) {
				f.setFloat(instance, (float) val);
			} else {
				return false;
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			logger.log(Level.SEVERE, e.getMessage(), e.getCause());
			return false;
		}
		return true;
	}
}
