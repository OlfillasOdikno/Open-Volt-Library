package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVUV extends RvStruct {

	private float u;
	private float v;

	@Override
	public void encode(ByteBuffer buf) {
		buf.putFloat(u);
		buf.putFloat(v);
	}

	@Override
	public void decode(ByteBuffer buf) {
		u = buf.getFloat();
		v = buf.getFloat();
	}

	public float getU() {
		return u;
	}

	public void setU(float u) {
		this.u = u;
	}

	public float getV() {
		return v;
	}

	public void setV(float v) {
		this.v = v;
	}

}
