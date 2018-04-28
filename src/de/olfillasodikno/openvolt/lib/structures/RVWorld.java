package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVWorld extends RvStruct {

	private int mesh_count;
	private RVMesh[] meshes;

	private int bigcube_count;
	private RVBigCube[] bcube;

	private int animation_count;
	private RVTexAnimation[] anim;

	@Override
	public void encode(ByteBuffer buf) {
		buf.putInt(mesh_count);
		for (RVMesh mesh : meshes) {
			mesh.encode(buf);
		}

		buf.putInt(bigcube_count);
		for (RVBigCube cube : bcube) {
			cube.encode(buf);
		}

		buf.putInt(animation_count);
		for (RVTexAnimation a : anim) {
			a.encode(buf);
		}
	}

	@Override
	public void decode(ByteBuffer buf) {
		mesh_count = buf.getInt();
		meshes = new RVMesh[mesh_count];
		for (int i = 0; i < meshes.length; i++) {
			meshes[i] = new RVMesh();
			meshes[i].decode(buf);
		}
		if(buf.remaining() == 0) {
			return;
		}
		bigcube_count = buf.getInt();
		bcube = new RVBigCube[bigcube_count];
		for (int i = 0; i < bcube.length; i++) {
			bcube[i] = new RVBigCube();
			bcube[i].decode(buf);
		}
		if(buf.remaining() == 0) {
			return;
		}
		animation_count = buf.getInt();
		anim = new RVTexAnimation[animation_count];
		for (int i = 0; i < anim.length; i++) {
			anim[i] = new RVTexAnimation();
			anim[i].decode(buf);
		}
	}

	public int getMesh_count() {
		return mesh_count;
	}

	public void setMesh_count(int mesh_count) {
		this.mesh_count = mesh_count;
	}

	public RVMesh[] getMeshes() {
		return meshes;
	}

	public void setMeshes(RVMesh[] meshes) {
		this.meshes = meshes;
	}

	public int getBigcube_count() {
		return bigcube_count;
	}

	public void setBigcube_count(int bigcube_count) {
		this.bigcube_count = bigcube_count;
	}

	public RVBigCube[] getBcube() {
		return bcube;
	}

	public void setBcube(RVBigCube[] bcube) {
		this.bcube = bcube;
	}

	public int getAnimation_count() {
		return animation_count;
	}

	public void setAnimation_count(int animation_count) {
		this.animation_count = animation_count;
	}

	public RVTexAnimation[] getAnim() {
		return anim;
	}

	public void setAnim(RVTexAnimation[] anim) {
		this.anim = anim;
	}

}
