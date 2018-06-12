package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVOffsetsF implements RvStruct {

	private float[] offsets;

	@Override
	public void encode(ByteBuffer buf) {
		buf.putInt(offsets.length);
		for (float f : offsets) {
			buf.putFloat(f);
		}
	}

	@Override
	public void decode(ByteBuffer buf) {
		int length = buf.getInt();
		offsets = new float[length];
		for (int i = 0; i < length; i++) {
			offsets[i] = buf.getFloat();
		}
	}

	public float[] getOffsets() {
		return offsets;
	}
	
	public void setOffsets(float[] offsets) {
		this.offsets = offsets;
	}

	@Override
	public int getNumBytes() {
		return offsets.length * 4;
	}

}
