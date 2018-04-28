package de.olfillasodikno.openvolt.lib.structures;

import java.nio.ByteBuffer;

public class RVPolygon extends RvStruct {

	private short type;
	private short texture;
	private short[] vertex_indices = new short[4];
	private int[] colors = new int[4];
	private RVUV[] texcoord = new RVUV[4];

	private boolean quadratic;
	private boolean double_sided;
	private boolean translucent;
	private boolean additive;
	private boolean enable_env_mapping;

	@Override
	public void encode(ByteBuffer buf) {
		buf.putShort(type);
		buf.putShort(texture);
		for (short s : vertex_indices) {
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
		for (int i = 0; i < vertex_indices.length; i++) {
			vertex_indices[i] = buf.getShort();
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

	public short[] getVertex_indices() {
		return vertex_indices;
	}

	public void setVertex_indices(short[] vertex_indices) {
		this.vertex_indices = vertex_indices;
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

	public boolean isDouble_sided() {
		return double_sided;
	}

	public void setDouble_sided(boolean double_sided) {
		this.double_sided = double_sided;
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

	public boolean isEnable_env_mapping() {
		return enable_env_mapping;
	}

	public void setEnable_env_mapping(boolean enable_env_mapping) {
		this.enable_env_mapping = enable_env_mapping;
	}

	public void mode(){
		quadratic = isBitSet(0);
		double_sided = isBitSet(1);
		translucent = isBitSet(2);
		additive = isBitSet(8);
		enable_env_mapping = isBitSet(11);
	}
	
	public boolean isBitSet(int index) {
		return (((type >>> index) & 1) != 0);
	}

}
