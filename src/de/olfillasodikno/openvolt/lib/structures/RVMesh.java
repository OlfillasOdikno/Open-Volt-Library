package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVMesh  implements RvStruct  {

	private RVMeshHeader header;
	private RVMeshBody body;

	@Override
	public void encode(ByteBuffer buf) {
		header.encode(buf);
		body.encode(buf);
	}

	@Override
	public void decode(ByteBuffer buf) {
		header = new RVMeshHeader();
		header.decode(buf);
		body = new RVMeshBody();
		body.decode(buf);
	}

	public RVMeshHeader getHeader() {
		return header;
	}

	public void setHeader(RVMeshHeader header) {
		this.header = header;
	}

	public RVMeshBody getBody() {
		return body;
	}

	public void setBody(RVMeshBody body) {
		this.body = body;
	}

	@Override
	public int getNumBytes() {
		return header.getNumBytes()+body.getNumBytes();
	}

}
