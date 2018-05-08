package de.olfillasodikno.openvolt.lib.structures.parameters;

import java.util.List;

import de.olfillasodikno.openvolt.lib.structures.RVVectorI;


public class RVVectorIWrapper extends ParamWrapper<RVVectorI>{

	public RVVectorIWrapper() {
		super(RVVectorI.class);
	}

	@Override
	public boolean wrap(List<Object> data, RVVectorI in) {
		if(data.size()!=3) {
			return false;
		}
		Object x = data.get(0);
		Object y = data.get(1);
		Object z = data.get(2);
		if(!(x instanceof Integer && y instanceof Integer && z instanceof Integer)) {
			return false;
		}
		in.setX((int) x);
		in.setY((int) y);
		in.setZ((int) z);
		return true;
	}

}
