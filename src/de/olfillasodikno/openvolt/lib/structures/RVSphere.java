package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVSphere implements RvStruct  {

	private RVVectorF center;
	private float radius;

	@Override
	public void encode(ByteBuffer buf) {
		center.encode(buf);
		buf.putFloat(radius);
	}

	@Override
	public void decode(ByteBuffer buf) {
		center = new RVVectorF();
		center.decode(buf);
		radius = buf.getFloat();
	}

	public RVVectorF getCenter() {
		return center;
	}

	public void setCenter(RVVectorF center) {
		this.center = center;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	@Override
	public int getNumBytes() {
		return 4+center.getNumBytes();
	}
}
