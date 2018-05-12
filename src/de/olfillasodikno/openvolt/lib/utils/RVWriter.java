package de.olfillasodikno.openvolt.lib.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;

import de.olfillasodikno.openvolt.lib.structures.RVHull;
import de.olfillasodikno.openvolt.lib.structures.RVMeshBody;
import de.olfillasodikno.openvolt.lib.structures.RVWorld;
import de.olfillasodikno.openvolt.lib.structures.RVWorldNCP;

public class RVWriter {

	private RVWriter() {
	}

	public static void worldToFile(RVWorld world, File f) throws IOException {
		ByteBuffer buf = ByteBuffer.allocate(world.getNumBytes());
		buf.order(ByteOrder.LITTLE_ENDIAN);
		world.encode(buf);
		buf.flip();
		toFile(buf,f);
	}

	public static void prmToFile(RVMeshBody meshBody, File f) throws IOException {
		ByteBuffer buf = ByteBuffer.allocate(meshBody.getNumBytes());
		buf.order(ByteOrder.LITTLE_ENDIAN);
		meshBody.encode(buf);
		buf.flip();
		toFile(buf,f);
	}

	public static void hullToFile(RVHull hull, File f) throws IOException {
		ByteBuffer buf = ByteBuffer.allocate(hull.getNumBytes());
		buf.order(ByteOrder.LITTLE_ENDIAN);
		hull.encode(buf);
		buf.flip();
		toFile(buf,f);
	}

	public static void worldNCPToFile(RVWorldNCP worldNCP, File f) throws IOException {
		ByteBuffer buf = ByteBuffer.allocate(worldNCP.getNumBytes());
		buf.order(ByteOrder.LITTLE_ENDIAN);
		worldNCP.encode(buf);
		buf.flip();
		toFile(buf,f);
	}
	
	
	private static void toFile(ByteBuffer buf, File f) throws IOException {
		try (FileOutputStream fos = new FileOutputStream(f, false)) {
			FileChannel channel = fos.getChannel();
			channel.write(buf);
			channel.close();
		}
	}

}
