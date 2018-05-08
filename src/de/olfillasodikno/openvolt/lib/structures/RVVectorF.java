package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVVectorF  implements RvStruct  {

	private float x;
	private float y;
	private float z;

	@Override
	public void encode(ByteBuffer buf) {
		buf.putFloat(x);
		buf.putFloat(y);
		buf.putFloat(z);
	}

	@Override
	public void decode(ByteBuffer buf) {
		x = buf.getFloat();
		y = buf.getFloat();
		z = buf.getFloat();
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

}
