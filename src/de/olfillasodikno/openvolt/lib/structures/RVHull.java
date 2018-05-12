package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVHull implements RvStruct {

	private short convexHullCount;

	private RVConvexHull[] convexHulls;
	private RVInterior interior;

	@Override
	public void encode(ByteBuffer buf) {

		buf.putShort(convexHullCount);

		for (RVConvexHull cHull : convexHulls) {
			cHull.encode(buf);
		}

		interior.encode(buf);
	}

	@Override
	public void decode(ByteBuffer buf) {
		convexHullCount = buf.getShort();

		convexHulls = new RVConvexHull[convexHullCount];
		for (int i = 0; i < convexHulls.length; i++) {
			convexHulls[i] = new RVConvexHull();
			convexHulls[i].decode(buf);
		}

		interior = new RVInterior();
		interior.decode(buf);
	}

	public short getConvexHullCount() {
		return convexHullCount;
	}

	public void setConvexHullCount(short convexHullCount) {
		this.convexHullCount = convexHullCount;
	}

	public RVConvexHull[] getConvexHulls() {
		return convexHulls;
	}

	public void setConvexHulls(RVConvexHull[] convexHulls) {
		this.convexHulls = convexHulls;
	}

	public RVInterior getInterior() {
		return interior;
	}

	public void setInterior(RVInterior interior) {
		this.interior = interior;
	}

	@Override
	public int getNumBytes() {
		int ret = 2+interior.getNumBytes();
//		if (convexHullCount > 0 && convexHulls[0] != null) {
//			ret += convexHullCount * convexHulls[0].getNumBytes();
//		}
		for(int i = 0; i< convexHulls.length; i++) {
			ret += convexHulls[i].getNumBytes();
		}
		return ret;
	}

}
