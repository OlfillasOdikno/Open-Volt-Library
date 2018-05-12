package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVBoundingBox implements RvStruct  {

	private float xMin;
	private float xMax;
	private float yMin;
	private float yMax;
	private float zMin;
	private float zMax;

	@Override
	public void encode(ByteBuffer buf) {
		buf.putFloat(xMin);
		buf.putFloat(xMax);
		buf.putFloat(yMin);
		buf.putFloat(yMax);
		buf.putFloat(zMin);
		buf.putFloat(zMax);
	}

	@Override
	public void decode(ByteBuffer buf) {
		xMin = buf.getFloat();
		xMax = buf.getFloat();
		yMin = buf.getFloat();
		yMax = buf.getFloat();
		zMin = buf.getFloat();
		zMax = buf.getFloat();
	}

	public float getxMin() {
		return xMin;
	}

	public void setxMin(float xMin) {
		this.xMin = xMin;
	}

	public float getxMax() {
		return xMax;
	}

	public void setxMax(float xMax) {
		this.xMax = xMax;
	}

	public float getyMin() {
		return yMin;
	}

	public void setyMin(float yMin) {
		this.yMin = yMin;
	}

	public float getyMax() {
		return yMax;
	}

	public void setyMax(float yMax) {
		this.yMax = yMax;
	}

	public float getzMin() {
		return zMin;
	}

	public void setzMin(float zMin) {
		this.zMin = zMin;
	}

	public float getzMax() {
		return zMax;
	}

	public void setzMax(float zMax) {
		this.zMax = zMax;
	}

	@Override
	public int getNumBytes() {
		return 6*4;
	}

}
