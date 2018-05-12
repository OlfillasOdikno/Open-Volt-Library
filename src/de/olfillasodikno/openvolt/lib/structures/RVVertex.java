package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVVertex  implements RvStruct  {

	private RVVectorF position;
	private RVVectorF normal;

	@Override
	public void encode(ByteBuffer buf) {
		position.encode(buf);
		normal.encode(buf);
	}

	@Override
	public void decode(ByteBuffer buf) {
		position = new RVVectorF();
		position.decode(buf);
		normal = new RVVectorF();
		normal.decode(buf);
	}

	public RVVectorF getPosition() {
		return position;
	}

	public void setPosition(RVVectorF position) {
		this.position = position;
	}

	public RVVectorF getNormal() {
		return normal;
	}

	public void setNormal(RVVectorF normal) {
		this.normal = normal;
	}

	@Override
	public int getNumBytes() {
		return position.getNumBytes()+normal.getNumBytes();
	}
}
