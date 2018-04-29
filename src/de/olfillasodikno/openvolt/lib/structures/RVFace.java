package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVFace extends RvStruct {

	private RVVectorF normal;
	private float distance;

	@Override
	public void encode(ByteBuffer buf) {
		normal.encode(buf);
		buf.putFloat(distance);
	}

	@Override
	public void decode(ByteBuffer buf) {
		normal = new RVVectorF();
		normal.decode(buf);
		distance = buf.getFloat();
	}

	public RVVectorF getNormal() {
		return normal;
	}

	public void setNormal(RVVectorF normal) {
		this.normal = normal;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

}
