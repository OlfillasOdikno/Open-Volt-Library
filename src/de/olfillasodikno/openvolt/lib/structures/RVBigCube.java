package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVBigCube extends RvStruct {

	private RVVectorF center;

	private float size;

	private int mesh_count;

	private int[] mesh_indices;

	@Override
	public void encode(ByteBuffer buf) {
		center.encode(buf);
		buf.putFloat(size);
		buf.putInt(mesh_count);
		for(int i : mesh_indices) {
			buf.putInt(i);
		}
	}

	@Override
	public void decode(ByteBuffer buf) {
		center = new RVVectorF();
		center.decode(buf);
		size = buf.getFloat();
		mesh_count = buf.getInt();
		mesh_indices = new int[mesh_count];
		for (int i = 0; i < mesh_count; i++) {
			mesh_indices[i] = buf.getInt();
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

	public int getMesh_count() {
		return mesh_count;
	}

	public void setMesh_count(int mesh_count) {
		this.mesh_count = mesh_count;
	}

	public int[] getMesh_indices() {
		return mesh_indices;
	}

	public void setMesh_indices(int[] mesh_indices) {
		this.mesh_indices = mesh_indices;
	}

}
