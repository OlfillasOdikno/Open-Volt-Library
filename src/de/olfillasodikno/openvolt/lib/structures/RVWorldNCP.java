package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVWorldNCP implements RvStruct {

	private short polyhedronCount;

	private RVPolyhedron[] polyhedra;

	private RVLookupGrid lookup;

	@Override
	public void encode(ByteBuffer buf) {
		buf.putShort(polyhedronCount);
		for(int i = 0; i< polyhedronCount; i++) {
			polyhedra[i].encode(buf);
		}
		lookup.encode(buf);
	}

	@Override
	public void decode(ByteBuffer buf) {
		polyhedronCount = buf.getShort();
		polyhedra = new RVPolyhedron[polyhedronCount];
		for (int i = 0; i < polyhedra.length; i++) {
			RVPolyhedron poly = new RVPolyhedron();
			poly.decode(buf);
			polyhedra[i] = poly;
		}
		lookup = new RVLookupGrid();
		lookup.decode(buf);
	}

	public short getPolyhedronCount() {
		return polyhedronCount;
	}

	public void setPolyhedronCount(short polyhedronCount) {
		this.polyhedronCount = polyhedronCount;
	}

	public RVPolyhedron[] getPolyhedra() {
		return polyhedra;
	}

	public void setPolyhedra(RVPolyhedron[] polyhedra) {
		this.polyhedra = polyhedra;
	}

	public RVLookupGrid getLookup() {
		return lookup;
	}

	public void setLookup(RVLookupGrid lookup) {
		this.lookup = lookup;
	}

	@Override
	public int getNumBytes() {
		int ret = 2+lookup.getNumBytes();
		for(int i = 0; i< polyhedra.length; i++) {
			ret+=polyhedra[i].getNumBytes();
		}
		return ret;
	}
}
