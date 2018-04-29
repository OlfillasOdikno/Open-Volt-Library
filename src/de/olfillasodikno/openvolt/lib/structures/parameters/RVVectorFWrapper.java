package de.olfillasodikno.openvolt.lib.structures.parameters;

import java.util.ArrayList;

import de.olfillasodikno.openvolt.lib.structures.RVVectorF;


public class RVVectorFWrapper extends ParamWrapper<RVVectorF>{

	public RVVectorFWrapper() {
		super(RVVectorF.class);
	}

	@Override
	public boolean wrap(ArrayList<Object> data, RVVectorF in) {
		if(data.size()!=3) {
			return false;
		}
		Object x = data.get(0);
		Object y = data.get(1);
		Object z = data.get(2);
		if(!(x instanceof Float && y instanceof Float && z instanceof Float)) {
			return false;
		}
		in.setX((float) x);
		in.setY((float) y);
		in.setZ((float) z);
		return true;
	}

}
