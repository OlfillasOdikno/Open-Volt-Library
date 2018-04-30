package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVHull extends RvStruct {

	private short convex_hull_count;

	private RVConvexHull[] convex_hulls;
	private RVInterior interior;

	@Override
	public void encode(ByteBuffer buf) {

		buf.putShort(convex_hull_count);

		for (RVConvexHull cHull : convex_hulls) {
			cHull.encode(buf);
		}

		interior.encode(buf);
	}

	@Override
	public void decode(ByteBuffer buf) {
		convex_hull_count = buf.getShort();

		convex_hulls = new RVConvexHull[convex_hull_count];
		for (int i = 0; i < convex_hulls.length; i++) {
			convex_hulls[i] = new RVConvexHull();
			convex_hulls[i].decode(buf);
		}

		interior = new RVInterior();
		interior.decode(buf);
	}

	public short getConvex_hull_count() {
		return convex_hull_count;
	}

	public void setConvex_hull_count(short convex_hull_count) {
		this.convex_hull_count = convex_hull_count;
	}

	public RVConvexHull[] getConvex_hulls() {
		return convex_hulls;
	}

	public void setConvex_hulls(RVConvexHull[] convex_hulls) {
		this.convex_hulls = convex_hulls;
	}

	public RVInterior getInterior() {
		return interior;
	}

	public void setInterior(RVInterior interior) {
		this.interior = interior;
	}

}
