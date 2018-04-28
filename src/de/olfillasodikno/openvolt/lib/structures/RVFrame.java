package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVFrame extends RvStruct {

	private int texture;
	private float delay;
	private RVUV[] uvs = new RVUV[4];

	@Override
	public void encode(ByteBuffer buf) {
		buf.putInt(texture);
		buf.putFloat(delay);
		for (RVUV uv : uvs) {
			uv.encode(buf);
		}
	}

	@Override
	public void decode(ByteBuffer buf) {
		texture = buf.getInt();
		delay = buf.getFloat();
		for (int i = 0; i < uvs.length; i++) {
			RVUV uv = new RVUV();
			uv.decode(buf);
			uvs[i] = uv;
		}
	}

	public int getTexture() {
		return texture;
	}

	public void setTexture(int texture) {
		this.texture = texture;
	}

	public float getDelay() {
		return delay;
	}

	public void setDelay(float delay) {
		this.delay = delay;
	}

	public RVUV[] getUvs() {
		return uvs;
	}

	public void setUvs(RVUV[] uvs) {
		this.uvs = uvs;
	}

}
