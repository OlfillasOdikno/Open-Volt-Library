package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVVectorI extends RvStruct {

	private int x;
	private int y;
	private int z;

	@Override
	public void encode(ByteBuffer buf) {
		buf.putInt(x);
		buf.putInt(y);
		buf.putInt(z);
	}

	@Override
	public void decode(ByteBuffer buf) {
		x = buf.getInt();
		y = buf.getInt();
		z = buf.getInt();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

}
