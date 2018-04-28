package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVMeshHeader extends RvStruct{

	private RVVector bound_ball_center;
	private float bound_ball_radius;

	private RVBoundingBox bbox;
	
	@Override
	public void encode(ByteBuffer buf) {
		bound_ball_center.encode(buf);
		buf.putFloat(bound_ball_radius);

		bbox.encode(buf);
	}

	@Override
	public void decode(ByteBuffer buf) {
		bound_ball_center = new RVVector();
		bound_ball_center.decode(buf);

		bound_ball_radius = buf.getFloat();

		bbox = new RVBoundingBox();
		bbox.decode(buf);

	}

	public RVVector getBound_ball_center() {
		return bound_ball_center;
	}

	public void setBound_ball_center(RVVector bound_ball_center) {
		this.bound_ball_center = bound_ball_center;
	}

	public float getBound_ball_radius() {
		return bound_ball_radius;
	}

	public void setBound_ball_radius(float bound_ball_radius) {
		this.bound_ball_radius = bound_ball_radius;
	}

	public RVBoundingBox getBbox() {
		return bbox;
	}

	public void setBbox(RVBoundingBox bbox) {
		this.bbox = bbox;
	}
}
