package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public abstract class RvStruct {
	
	public abstract void encode(ByteBuffer buf);
	public abstract void decode(ByteBuffer buf);
	
}
