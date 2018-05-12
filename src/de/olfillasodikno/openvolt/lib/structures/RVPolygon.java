package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVPolygon  implements RvStruct  {

	private short type;
	private short texture;
	private short[] vertexIndices = new short[4];
	private int[] colors = new int[4];
	private RVUV[] texcoord = new RVUV[4];

	private boolean quadratic;
	private boolean doubleSided;
	private boolean translucent;
	private boolean additive;
	private boolean enableEnvMapping;

	@Override
	public void encode(ByteBuffer buf) {
		buf.putShort(type);
		buf.putShort(texture);
		for (short s : vertexIndices) {
			buf.putShort(s);
		}
		for (int i : colors) {
			buf.putInt(i);
		}
		for (RVUV uv : texcoord) {
			uv.encode(buf);
		}

	}

	@Override
	public void decode(ByteBuffer buf) {
		type = buf.getShort();
		texture = buf.getShort();
		for (int i = 0; i < vertexIndices.length; i++) {
			vertexIndices[i] = buf.getShort();
		}

		for (int i = 0; i < colors.length; i++) {
			colors[i] = buf.getInt();
		}

		for (int i = 0; i < texcoord.length; i++) {
			RVUV uv = new RVUV();
			uv.decode(buf);
			texcoord[i] = uv;
		}
		mode();
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public short getTexture() {
		return texture;
	}

	public void setTexture(short texture) {
		this.texture = texture;
	}

	
	public int[] getColors() {
		return colors;
	}

	public void setColors(int[] colors) {
		this.colors = colors;
	}

	public RVUV[] getTexcoord() {
		return texcoord;
	}

	public void setTexcoord(RVUV[] texcoord) {
		this.texcoord = texcoord;
	}

	public boolean isQuadratic() {
		return quadratic;
	}

	public void setQuadratic(boolean quadratic) {
		this.quadratic = quadratic;
	}


	public boolean isTranslucent() {
		return translucent;
	}

	public void setTranslucent(boolean translucent) {
		this.translucent = translucent;
	}

	public boolean isAdditive() {
		return additive;
	}

	public void setAdditive(boolean additive) {
		this.additive = additive;
	}

	public short[] getVertexIndices() {
		return vertexIndices;
	}

	public void setVertexIndices(short[] vertexIndices) {
		this.vertexIndices = vertexIndices;
	}

	public boolean isDoubleSided() {
		return doubleSided;
	}

	public void setDoubleSided(boolean doubleSided) {
		this.doubleSided = doubleSided;
	}

	public boolean isEnableEnvMapping() {
		return enableEnvMapping;
	}

	public void setEnableEnvMapping(boolean enableEnvMapping) {
		this.enableEnvMapping = enableEnvMapping;
	}

	public void mode(){
		quadratic = isBitSet(0);
		doubleSided = isBitSet(1);
		translucent = isBitSet(2);
		additive = isBitSet(8);
		enableEnvMapping = isBitSet(11);
	}
	
	public boolean isBitSet(int index) {
		return (((type >>> index) & 1) != 0);
	}

	@Override
	public int getNumBytes() {
		int ret = 2*2+vertexIndices.length*2+colors.length*4;
		if(texcoord.length>0 && texcoord[0] != null) {
			ret += texcoord.length*texcoord[0].getNumBytes();
		}
		return ret;
	}

}
