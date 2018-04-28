package de.olfillasodikno.openvolt.lib.utils;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;

import de.olfillasodikno.openvolt.lib.structures.RVMeshBody;
import de.olfillasodikno.openvolt.lib.structures.RVWorld;

public class RVReader {
	
	public static RVWorld worldFromFile(File f) throws IOException {
		ByteBuffer buf = ByteBuffer.wrap(Files.readAllBytes(f.toPath()));
		buf.order(ByteOrder.LITTLE_ENDIAN);
		RVWorld ret = new RVWorld();
		ret.decode(buf);
		return ret;
	}
	
	public static RVMeshBody prmFromFile(File f) throws IOException {
		ByteBuffer buf = ByteBuffer.wrap(Files.readAllBytes(f.toPath()));
		buf.order(ByteOrder.LITTLE_ENDIAN);
		RVMeshBody ret = new RVMeshBody();
		ret.decode(buf);
		return ret;
	}
}
