package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVInterior extends RvStruct {

	private short sphere_count;
	private RVSphere[] spheres;

	@Override
	public void encode(ByteBuffer buf) {
		buf.putShort(sphere_count);
		for (RVSphere s : spheres) {
			s.encode(buf);
		}
	}

	@Override
	public void decode(ByteBuffer buf) {
		sphere_count = buf.getShort();
		spheres = new RVSphere[sphere_count];
		for (int i = 0; i < spheres.length; i++) {
			spheres[i] = new RVSphere();
			spheres[i].decode(buf);
		}
	}

	public short getSphere_count() {
		return sphere_count;
	}

	public void setSphere_count(short sphere_count) {
		this.sphere_count = sphere_count;
	}

	public RVSphere[] getSpheres() {
		return spheres;
	}

	public void setSpheres(RVSphere[] spheres) {
		this.spheres = spheres;
	}

}
