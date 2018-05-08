package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVConvexHull implements RvStruct  {

	private short pointCount;
	private short edgeCount;
	private short faceCount;

	private RVBoundingBox bbox;

	private RVVectorF offset; // Relative to center of mass

	private RVVectorF[] points;
	private RVEdge[] edges;
	private RVFace[] faces;

	@Override
	public void encode(ByteBuffer buf) {
		buf.putShort(pointCount);
		buf.putShort(edgeCount);
		buf.putShort(faceCount);
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
		
		pointCount = buf.getShort();
		edgeCount = buf.getShort();
		faceCount = buf.getShort();
		
		bbox = new RVBoundingBox();
		bbox.decode(buf);
		
		offset = new RVVectorF();
		offset.decode(buf);

		points = new RVVectorF[pointCount];

		for (int i = 0; i < points.length; i++) {
			points[i] = new RVVectorF();
			points[i].decode(buf);
		}
		
		edges = new RVEdge[edgeCount];

		for (int i = 0; i < edges.length; i++) {
			edges[i] = new RVEdge();
			edges[i].decode(buf);
		}
		
		faces = new RVFace[faceCount];

		for (int i = 0; i < faces.length; i++) {
			faces[i] = new RVFace();
			faces[i].decode(buf);
		}
	}

	

	public short getPointCount() {
		return pointCount;
	}

	public void setPointCount(short pointCount) {
		this.pointCount = pointCount;
	}

	public short getEdgeCount() {
		return edgeCount;
	}

	public void setEdgeCount(short edgeCount) {
		this.edgeCount = edgeCount;
	}

	public short getFaceCount() {
		return faceCount;
	}

	public void setFaceCount(short faceCount) {
		this.faceCount = faceCount;
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
