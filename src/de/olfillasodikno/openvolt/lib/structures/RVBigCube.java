package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVBigCube implements RvStruct {

	private RVVectorF center;

	private float size;

	private int meshCount;

	private int[] meshIndices;

	@Override
	public void encode(ByteBuffer buf) {
		center.encode(buf);
		buf.putFloat(size);
		buf.putInt(meshCount);
		for (int i : meshIndices) {
			buf.putInt(i);
		}
	}

	@Override
	public void decode(ByteBuffer buf) {
		center = new RVVectorF();
		center.decode(buf);
		size = buf.getFloat();
		meshCount = buf.getInt();
		meshIndices = new int[meshCount];
		for (int i = 0; i < meshCount; i++) {
			meshIndices[i] = buf.getInt();
		}
	}

	public RVVectorF getCenter() {
		return center;
	}

	public void setCenter(RVVectorF center) {
		this.center = center;
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public int getMeshCount() {
		return meshCount;
	}

	public void setMeshCount(int meshCount) {
		this.meshCount = meshCount;
	}

	public int[] getMeshIndices() {
		return meshIndices;
	}

	public void setMeshIndices(int[] meshIndices) {
		this.meshIndices = meshIndices;
	}

}
