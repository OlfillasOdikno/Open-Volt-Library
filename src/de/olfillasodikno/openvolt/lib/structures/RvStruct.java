package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public interface RvStruct {
	
	public void encode(ByteBuffer buf);
	public void decode(ByteBuffer buf);
	
}
