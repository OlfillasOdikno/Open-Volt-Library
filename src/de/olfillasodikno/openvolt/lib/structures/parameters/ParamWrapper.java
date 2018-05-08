package de.olfillasodikno.openvolt.lib.structures.parameters;

import java.util.List;

public abstract class ParamWrapper<V> {
	
	private final Class<?> wrapping;
	
	public ParamWrapper(Class<?> wrapping) {
		this.wrapping = wrapping;
	}
	
	public Class<?> getWrapping() {
		return wrapping;
	}

	protected abstract boolean wrap(List<Object> data, V in);
	
	public V wrap(List<Object> data) {
		try {
			@SuppressWarnings("unchecked")
			V o = (V) wrapping.newInstance();
			if(wrap(data, o)) {
				return o;				
			}
		} catch (InstantiationException | IllegalAccessException e) {
			return null;
		}
		return null;
	}
}
