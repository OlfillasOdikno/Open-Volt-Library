package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVPolyhedron implements RvStruct {

	private int type;
	private int surface;

	private RVFace[] planes = new RVFace[5];
	private RVBoundingBox bbox;

	private boolean hasFive;

	@Override
	public void encode(ByteBuffer buf) {
		buf.putInt(type);
		buf.putInt(surface);

		for (int i = 0; i < planes.length; i++) {
			planes[i].encode(buf);
		}
		bbox.encode(buf);
	}

	@Override
	public void decode(ByteBuffer buf) {
		type = buf.getInt();
		surface = buf.getInt();
		mode();
		for (int i = 0; i < planes.length; i++) {
			RVFace plane = new RVFace();
			plane.decode(buf);
			planes[i] = plane;
		}
		bbox = new RVBoundingBox();
		bbox.decode(buf);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSurface() {
		return surface;
	}

	public void setSurface(int surface) {
		this.surface = surface;
	}

	public RVFace[] getPlanes() {
		return planes;
	}

	public void setPlanes(RVFace[] planes) {
		this.planes = planes;
	}

	public RVBoundingBox getBbox() {
		return bbox;
	}

	public void setBbox(RVBoundingBox bbox) {
		this.bbox = bbox;
	}

	public boolean isHasFive() {
		return hasFive;
	}

	public void setHasFive(boolean hasFive) {
		this.hasFive = hasFive;
	}

	public void mode() {
		hasFive = isBitSet(0);
	}

	public boolean isBitSet(int index) {
		return (((type >>> index) & 1) != 0);
	}

	@Override
	public int getNumBytes() {
		int ret = 2*4+bbox.getNumBytes();
		if (planes.length > 0 && planes[0] != null) {
			ret += planes.length * planes[0].getNumBytes();
		}
		return ret;
	}
}
