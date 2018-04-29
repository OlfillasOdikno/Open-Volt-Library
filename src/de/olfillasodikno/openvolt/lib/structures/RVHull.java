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

}
