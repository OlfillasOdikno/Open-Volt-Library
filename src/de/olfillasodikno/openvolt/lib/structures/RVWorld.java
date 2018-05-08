package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVWorld implements RvStruct {

	private int meshCount;
	private RVMesh[] meshes;

	private int bigcubeCount;
	private RVBigCube[] bcube;

	private int animationCount;
	private RVTexAnimation[] anim;

	public void encode(ByteBuffer buf) {
		buf.putInt(meshCount);
		for (RVMesh mesh : meshes) {
			mesh.encode(buf);
		}

		buf.putInt(bigcubeCount);
		for (RVBigCube cube : bcube) {
			cube.encode(buf);
		}

		buf.putInt(animationCount);
		for (RVTexAnimation a : anim) {
			a.encode(buf);
		}
	}

	public void decode(ByteBuffer buf) {
		meshCount = buf.getInt();
		meshes = new RVMesh[meshCount];
		for (int i = 0; i < meshes.length; i++) {
			meshes[i] = new RVMesh();
			meshes[i].decode(buf);
		}
		if(buf.remaining() == 0) {
			return;
		}
		bigcubeCount = buf.getInt();
		bcube = new RVBigCube[bigcubeCount];
		for (int i = 0; i < bcube.length; i++) {
			bcube[i] = new RVBigCube();
			bcube[i].decode(buf);
		}
		if(buf.remaining() == 0) {
			return;
		}
		animationCount = buf.getInt();
		anim = new RVTexAnimation[animationCount];
		for (int i = 0; i < anim.length; i++) {
			anim[i] = new RVTexAnimation();
			anim[i].decode(buf);
		}
	}

	public int getMeshCount() {
		return meshCount;
	}

	public void setMeshCount(int meshCount) {
		this.meshCount = meshCount;
	}

	public RVMesh[] getMeshes() {
		return meshes;
	}

	public void setMeshes(RVMesh[] meshes) {
		this.meshes = meshes;
	}

	public int getBigcubeCount() {
		return bigcubeCount;
	}

	public void setBigcubeCount(int bigcubeCount) {
		this.bigcubeCount = bigcubeCount;
	}

	public RVBigCube[] getBcube() {
		return bcube;
	}

	public void setBcube(RVBigCube[] bcube) {
		this.bcube = bcube;
	}

	public int getAnimationCount() {
		return animationCount;
	}

	public void setAnimationCount(int animationCount) {
		this.animationCount = animationCount;
	}

	public RVTexAnimation[] getAnim() {
		return anim;
	}

	public void setAnim(RVTexAnimation[] anim) {
		this.anim = anim;
	}


}
