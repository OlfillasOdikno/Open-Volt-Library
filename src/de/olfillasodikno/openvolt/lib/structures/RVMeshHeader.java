package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVMeshHeader extends RvStruct {

	private RVSphere bound_ball;

	private RVBoundingBox bbox;

	@Override
	public void encode(ByteBuffer buf) {
		bound_ball.encode(buf);

		bbox.encode(buf);
	}

	@Override
	public void decode(ByteBuffer buf) {
		bound_ball = new RVSphere();
		bound_ball.decode(buf);

		bbox = new RVBoundingBox();
		bbox.decode(buf);

	}

	public RVBoundingBox getBbox() {
		return bbox;
	}

	public void setBbox(RVBoundingBox bbox) {
		this.bbox = bbox;
	}

	public RVSphere getBound_ball() {
		return bound_ball;
	}

	public void setBound_ball(RVSphere bound_ball) {
		this.bound_ball = bound_ball;
	}

}
