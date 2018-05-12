package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVLookupList implements RvStruct {

	private int indicesCount;
	private int[] polyhedronIndices;
	
	@Override
	public void encode(ByteBuffer buf) {
		buf.putInt(indicesCount);
		for(int i = 0; i < indicesCount; i++) {
			buf.putInt(polyhedronIndices[i]);
		}
	}

	@Override
	public void decode(ByteBuffer buf) {
		indicesCount = buf.getInt();
		polyhedronIndices = new int[indicesCount];
		for(int i = 0; i < indicesCount; i++) {
			polyhedronIndices[i]=buf.getInt();
		}
	}

	public int getIndicesCount() {
		return indicesCount;
	}

	public void setIndicesCount(int indicesCount) {
		this.indicesCount = indicesCount;
	}

	public int[] getPolyhedronIndices() {
		return polyhedronIndices;
	}

	public void setPolyhedronIndices(int[] polyhedronIndices) {
		this.polyhedronIndices = polyhedronIndices;
	}

	@Override
	public int getNumBytes() {
		return 4+indicesCount*4;
	}
	
	
}
