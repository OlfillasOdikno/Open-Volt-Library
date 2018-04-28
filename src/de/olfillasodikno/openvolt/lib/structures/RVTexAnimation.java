package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVTexAnimation extends RvStruct {

	private int frame_count;
	private RVFrame[] frames;

	@Override
	public void encode(ByteBuffer buf) {
		buf.putInt(frame_count);
		for(RVFrame frame : frames) {
			frame.encode(buf);
		}
	}

	@Override
	public void decode(ByteBuffer buf) {
		frame_count = buf.getInt();
		frames = new RVFrame[frame_count];
		for (int i = 0; i < frames.length; i++) {
			RVFrame frame = new RVFrame();
			frame.decode(buf);
			frames[i] = frame;
		}
	}

	public int getFrame_count() {
		return frame_count;
	}

	public void setFrame_count(int frame_count) {
		this.frame_count = frame_count;
	}

	public RVFrame[] getFrames() {
		return frames;
	}

	public void setFrames(RVFrame[] frames) {
		this.frames = frames;
	}

}
