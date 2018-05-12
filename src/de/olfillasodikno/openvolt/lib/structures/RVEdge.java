package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVEdge  implements RvStruct  {

	private short a;
	private short b;

	@Override
	public void encode(ByteBuffer buf) {
		buf.putShort(a);
		buf.putShort(b);
	}

	@Override
	public void decode(ByteBuffer buf) {
		a = buf.getShort();
		b = buf.getShort();
	}

	public short getA() {
		return a;
	}

	public void setA(short a) {
		this.a = a;
	}

	public short getB() {
		return b;
	}

	public void setB(short b) {
		this.b = b;
	}

	@Override
	public int getNumBytes() {
		return 2*2;
	}

}
