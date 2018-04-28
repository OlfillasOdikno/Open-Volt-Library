package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVMeshBody extends RvStruct {

	private short polygon_count;
	private short vertex_count;

	private RVPolygon[] polygons;
	private RVVertex[] vertices;

	@Override
	public void encode(ByteBuffer buf) {

		buf.putShort(polygon_count);
		buf.putShort(vertex_count);

		for (RVPolygon poly : polygons) {
			poly.encode(buf);
		}

		for (RVVertex v : vertices) {
			v.encode(buf);
		}
	}

	@Override
	public void decode(ByteBuffer buf) {
		polygon_count = buf.getShort();
		vertex_count = buf.getShort();

		polygons = new RVPolygon[polygon_count];
		for (int i = 0; i < polygons.length; i++) {
			polygons[i] = new RVPolygon();
			polygons[i].decode(buf);
		}

		vertices = new RVVertex[vertex_count];

		for (int i = 0; i < vertices.length; i++) {
			vertices[i] = new RVVertex();
			vertices[i].decode(buf);
		}
	}

	public short getPolygon_count() {
		return polygon_count;
	}

	public void setPolygon_count(short polygon_count) {
		this.polygon_count = polygon_count;
	}

	public short getVertex_count() {
		return vertex_count;
	}

	public void setVertex_count(short vertex_count) {
		this.vertex_count = vertex_count;
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
