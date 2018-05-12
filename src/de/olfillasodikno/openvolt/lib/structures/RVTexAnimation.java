package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVTexAnimation implements RvStruct {

	private int frameCount;
	private RVFrame[] frames;

	@Override
	public void encode(ByteBuffer buf) {
		buf.putInt(frameCount);
		for (RVFrame frame : frames) {
			frame.encode(buf);
		}
	}

	@Override
	public void decode(ByteBuffer buf) {
		frameCount = buf.getInt();
		frames = new RVFrame[frameCount];
		for (int i = 0; i < frames.length; i++) {
			RVFrame frame = new RVFrame();
			frame.decode(buf);
			frames[i] = frame;
		}
	}

	public int getFrameCount() {
		return frameCount;
	}

	public void setFrameCount(int frameCount) {
		this.frameCount = frameCount;
	}

	public RVFrame[] getFrames() {
		return frames;
	}

	public void setFrames(RVFrame[] frames) {
		this.frames = frames;
	}

	@Override
	public int getNumBytes() {
		int ret = 4;
		if (frameCount> 0 && frames[0] != null) {
			ret += frameCount * frames[0].getNumBytes();
		}
		return ret;
	}

}
