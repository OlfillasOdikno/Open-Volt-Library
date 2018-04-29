package de.olfillasodikno.openvolt.lib.structures.parameters;

import java.util.ArrayList;

public abstract class ParamWrapper<V> {
	
	private final Class<?> wrapping;
	
	public ParamWrapper(Class<?> wrapping) {
		this.wrapping = wrapping;
	}
	
	public Class<?> getWrapping() {
		return wrapping;
	}

	protected abstract boolean wrap(ArrayList<Object> data, V in);
	
	public V wrap(ArrayList<Object> data) {
		try {
			@SuppressWarnings("unchecked")
			V o = (V) wrapping.newInstance();
			if(wrap(data, o)) {
				return o;				
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
