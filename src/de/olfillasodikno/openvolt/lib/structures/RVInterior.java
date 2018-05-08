package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVInterior implements RvStruct {

	private short sphereCount;
	private RVSphere[] spheres;

	@Override
	public void encode(ByteBuffer buf) {
		buf.putShort(sphereCount);
		for (RVSphere s : spheres) {
			s.encode(buf);
		}
	}

	@Override
	public void decode(ByteBuffer buf) {
		sphereCount = buf.getShort();
		spheres = new RVSphere[sphereCount];
		for (int i = 0; i < spheres.length; i++) {
			spheres[i] = new RVSphere();
			spheres[i].decode(buf);
		}
	}

	public short getSphereCount() {
		return sphereCount;
	}

	public void setSphereCount(short sphereCount) {
		this.sphereCount = sphereCount;
	}

	public RVSphere[] getSpheres() {
		return spheres;
	}

	public void setSpheres(RVSphere[] spheres) {
		this.spheres = spheres;
	}

}
