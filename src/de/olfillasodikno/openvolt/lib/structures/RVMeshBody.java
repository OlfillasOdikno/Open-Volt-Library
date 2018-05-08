package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVMeshBody implements RvStruct {

	private short polygonCount;
	private short vertexCount;

	private RVPolygon[] polygons;
	private RVVertex[] vertices;

	@Override
	public void encode(ByteBuffer buf) {

		buf.putShort(polygonCount);
		buf.putShort(vertexCount);

		for (RVPolygon poly : polygons) {
			poly.encode(buf);
		}

		for (RVVertex v : vertices) {
			v.encode(buf);
		}
	}

	@Override
	public void decode(ByteBuffer buf) {
		polygonCount = buf.getShort();
		vertexCount = buf.getShort();

		polygons = new RVPolygon[polygonCount];
		for (int i = 0; i < polygons.length; i++) {
			polygons[i] = new RVPolygon();
			polygons[i].decode(buf);
		}

		vertices = new RVVertex[vertexCount];

		for (int i = 0; i < vertices.length; i++) {
			vertices[i] = new RVVertex();
			vertices[i].decode(buf);
		}
	}

	public short getPolygonCount() {
		return polygonCount;
	}

	public void setPolygonCount(short polygonCount) {
		this.polygonCount = polygonCount;
	}

	public short getVertexCount() {
		return vertexCount;
	}

	public void setVertexCount(short vertexCount) {
		this.vertexCount = vertexCount;
	}

	public RVPolygon[] getPolygons() {
		return polygons;
	}

	public void setPolygons(RVPolygon[] polygons) {
		this.polygons = polygons;
	}

	public RVVertex[] getVertices() {
		return vertices;
	}

	public void setVertices(RVVertex[] vertices) {
		this.vertices = vertices;
	}

}
