package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVConvexHull extends RvStruct {

	private short point_count;
	private short edge_count;
	private short face_count;

	private RVBoundingBox bbox;

	private RVVectorF offset; // Relative to center of mass

	private RVVectorF[] points;
	private RVEdge[] edges;
	private RVFace[] faces;

	@Override
	public void encode(ByteBuffer buf) {
		buf.putShort(point_count);
		buf.putShort(edge_count);
		buf.putShort(face_count);
		bbox.encode(buf);
		offset.encode(buf);

		for (RVVectorF point : points) {
			point.encode(buf);
		}

		for (RVEdge edge : edges) {
			edge.encode(buf);
		}

		for (RVFace face : faces) {
			face.encode(buf);
		}
	}

	@Override
	public void decode(ByteBuffer buf) {
		
		point_count = buf.getShort();
		edge_count = buf.getShort();
		face_count = buf.getShort();
		
		bbox = new RVBoundingBox();
		bbox.decode(buf);
		
		offset = new RVVectorF();
		offset.decode(buf);

		points = new RVVectorF[point_count];

		for (int i = 0; i < points.length; i++) {
			points[i] = new RVVectorF();
			points[i].decode(buf);
		}
		
		edges = new RVEdge[edge_count];

		for (int i = 0; i < edges.length; i++) {
			edges[i] = new RVEdge();
			edges[i].decode(buf);
		}
		
		faces = new RVFace[face_count];

		for (int i = 0; i < faces.length; i++) {
			faces[i] = new RVFace();
			faces[i].decode(buf);
		}
	}

	public short getPoint_count() {
		return point_count;
	}

	public void setPoint_count(short point_count) {
		this.point_count = point_count;
	}

	public short getEdge_count() {
		return edge_count;
	}

	public void setEdge_count(short edge_count) {
		this.edge_count = edge_count;
	}

	public short getFace_count() {
		return face_count;
	}

	public void setFace_count(short face_count) {
		this.face_count = face_count;
	}

	public RVBoundingBox getBbox() {
		return bbox;
	}

	public void setBbox(RVBoundingBox bbox) {
		this.bbox = bbox;
	}

	public RVVectorF getOffset() {
		return offset;
	}

	public void setOffset(RVVectorF offset) {
		this.offset = offset;
	}

	public RVVectorF[] getPoints() {
		return points;
	}

	public void setPoints(RVVectorF[] points) {
		this.points = points;
	}

	public RVEdge[] getEdges() {
		return edges;
	}

	public void setEdges(RVEdge[] edges) {
		this.edges = edges;
	}

	public RVFace[] getFaces() {
		return faces;
	}

	public void setFaces(RVFace[] faces) {
		this.faces = faces;
	}
}
