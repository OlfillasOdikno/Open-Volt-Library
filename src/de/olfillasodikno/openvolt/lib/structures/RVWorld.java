package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVWorld implements RvStruct {

	private int meshCount;
	private RVMesh[] meshes;

	private int bigcubeCount;
	private RVBigCube[] bcube;

	private int animationCount;
	private RVTexAnimation[] anim;

	private int[] envColors;

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

		for (int colors : envColors) {
			buf.putInt(colors);
		}
	}

	public void decode(ByteBuffer buf) {
		int envCount = 0;
		meshCount = buf.getInt();
		meshes = new RVMesh[meshCount];
		for (int i = 0; i < meshes.length; i++) {
			meshes[i] = new RVMesh();
			meshes[i].decode(buf);
			RVPolygon[] polygons = meshes[i].getBody().getPolygons();
			for (RVPolygon polygon : polygons) {
				if (polygon.isBitSet(11)) {
					envCount++;
				}
			}
		}
		if (buf.remaining() == 0) {
			return;
		}
		bigcubeCount = buf.getInt();
		bcube = new RVBigCube[bigcubeCount];
		for (int i = 0; i < bcube.length; i++) {
			bcube[i] = new RVBigCube();
			bcube[i].decode(buf);
		}
		if (buf.remaining() == 0) {
			return;
		}
		animationCount = buf.getInt();
		anim = new RVTexAnimation[animationCount];
		for (int i = 0; i < anim.length; i++) {
			anim[i] = new RVTexAnimation();
			anim[i].decode(buf);
		}
		envColors = new int[envCount];
		for (int i = 0; i < envCount; i++) {
			envColors[i]=buf.getInt();
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



	public int[] getEnvColors() {
		return envColors;
	}

	public void setEnvColors(int[] envColors) {
		this.envColors = envColors;
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

	@Override
	public int getNumBytes() {
		int ret = 4 * 4 + envColors.length *4;
		for (int i = 0; i < meshCount; i++) {
			ret += meshes[i].getNumBytes();
		}
		for (int i = 0; i < bigcubeCount; i++) {
			ret += bcube[i].getNumBytes();
		}
		for (int i = 0; i < animationCount; i++) {
			ret += anim[i].getNumBytes();
		}
		return ret;
	}

}
