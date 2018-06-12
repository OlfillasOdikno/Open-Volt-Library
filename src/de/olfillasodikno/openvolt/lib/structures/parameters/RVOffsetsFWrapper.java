package de.olfillasodikno.openvolt.lib.structures.parameters;

import java.util.ArrayList;
import java.util.List;

import de.olfillasodikno.openvolt.lib.structures.RVOffsetsF;

public class RVOffsetsFWrapper extends ParamWrapper<RVOffsetsF> {

	public RVOffsetsFWrapper() {
		super(RVOffsetsF.class);
	}

	@Override
	public boolean wrap(List<Object> data, RVOffsetsF in) {
		int size = data.size();
		System.out.println(size);
		ArrayList<Float> offsets = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			Object x = data.get(i);
			if (x instanceof Integer) {
				x = ((Integer) x).floatValue();
			}
			if (!(x instanceof Float)) {
				continue;
			}
			offsets.add((float) x);
		}
		float[] arrOffsets = new float[offsets.size()];
		for (int i = 0; i < offsets.size(); i++) {
			arrOffsets[i] = offsets.get(i);
		}
		in.setOffsets(arrOffsets);
		return true;
	}

}
