package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVVertex extends RvStruct {

	private RVVector position;
	private RVVector normal;

	@Override
	public void encode(ByteBuffer buf) {
		position.encode(buf);
		normal.encode(buf);
	}

	@Override
	public void decode(ByteBuffer buf) {
		position = new RVVector();
		position.decode(buf);
		normal = new RVVector();
		normal.decode(buf);
	}

	public RVVector getPosition() {
		return position;
	}

	public void setPosition(RVVector position) {
		this.position = position;
	}

	public RVVector getNormal() {
		return normal;
	}

	public void setNormal(RVVector normal) {
		this.normal = normal;
	}
}
