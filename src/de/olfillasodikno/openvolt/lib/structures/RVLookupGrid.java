package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVLookupGrid implements RvStruct {

	private float x0;
	private float z0;

	private float xSize;
	private float zSize;

	private float rasterSize;

	private RVLookupList[][] lists;

	@Override
	public void encode(ByteBuffer buf) {
		buf.putFloat(x0);
		buf.putFloat(z0);

		buf.putFloat(xSize);
		buf.putFloat(zSize);

		buf.putFloat(rasterSize);

		for (int i = 0; i < zSize; i++) {
			for (int j = 0; j < xSize; j++) {
				lists[i][j].encode(buf);
			}
		}
	}

	@Override
	public void decode(ByteBuffer buf) {
		x0 = buf.getFloat();
		z0 = buf.getFloat();

		xSize = buf.getFloat();
		zSize = buf.getFloat();

		rasterSize = buf.getFloat();

		lists = new RVLookupList[(int) zSize][(int) xSize];
		for (int i = 0; i < zSize; i++) {
			for (int j = 0; j < xSize; j++) {
				RVLookupList list = new RVLookupList();
				list.decode(buf);
				lists[i][j] = list;
			}
		}
	}

	public float getX0() {
		return x0;
	}

	public void setX0(float x0) {
		this.x0 = x0;
	}

	public float getZ0() {
		return z0;
	}

	public void setZ0(float z0) {
		this.z0 = z0;
	}

	public float getxSize() {
		return xSize;
	}

	public void setxSize(float xSize) {
		this.xSize = xSize;
	}

	public float getzSize() {
		return zSize;
	}

	public void setzSize(float zSize) {
		this.zSize = zSize;
	}

	public float getRasterSize() {
		return rasterSize;
	}

	public void setRasterSize(float rasterSize) {
		this.rasterSize = rasterSize;
	}

	public RVLookupList[][] getLists() {
		return lists;
	}

	public void setLists(RVLookupList[][] lists) {
		this.lists = lists;
	}

	@Override
	public int getNumBytes() {
		int ret = 5*4;
		for(int i = 0; i< zSize; i++) {
			for(int j = 0; j< xSize; j++) {
				ret += lists[i][j].getNumBytes();
			}
		}
		return ret;
	}

}
