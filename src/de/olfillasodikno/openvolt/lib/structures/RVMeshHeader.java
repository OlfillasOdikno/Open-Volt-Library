package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVMeshHeader  implements RvStruct  {

	private RVSphere boundBall;

	private RVBoundingBox bbox;

	@Override
	public void encode(ByteBuffer buf) {
		boundBall.encode(buf);

		bbox.encode(buf);
	}

	@Override
	public void decode(ByteBuffer buf) {
		boundBall = new RVSphere();
		boundBall.decode(buf);

		bbox = new RVBoundingBox();
		bbox.decode(buf);

	}

	public RVBoundingBox getBbox() {
		return bbox;
	}

	public void setBbox(RVBoundingBox bbox) {
		this.bbox = bbox;
	}

	public RVSphere getBoundBall() {
		return boundBall;
	}

	public void setBoundBall(RVSphere boundBall) {
		this.boundBall = boundBall;
	}

	@Override
	public int getNumBytes() {
		return boundBall.getNumBytes()+bbox.getNumBytes();
	}



}
