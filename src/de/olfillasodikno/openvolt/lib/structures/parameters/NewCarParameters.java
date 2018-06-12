package de.olfillasodikno.openvolt.lib.structures.parameters;

import de.olfillasodikno.openvolt.lib.structures.RVOffsetsF;
import de.olfillasodikno.openvolt.lib.structures.RVVectorF;

public class NewCarParameters extends RVCarParameters {

	public NewCarParameters(Context root) {
		super(root);
	}
	
	@Param("TCARBOX")
	private String carBoxFile;
	
	@Param("SFXENGINE")
	private String engineSFXFile;
	
	@Param("TSHADOW")
	private String shadowTextureFile;
	
	@Param("SHADOWINDEX")
	private int shadowIndex;
	
	@Param("SHADOWTABLE")
	private RVOffsetsF shadowTable;
	
	@Param("Statistics")
	private boolean statistics;
	
	@Param("Camber")
	private int camber;
	
	@Param("Flippable")
	private boolean flippable;
	
	@Param("Flying")
	private boolean flying;
	
	@Param("ClothFx")
	private boolean clothfx;
	
	@Param("Type")
	private int type;
	
	@Param("Trans")
	private RVVectorF translationMax;
	
	@Param("TransVel")
	private float velocityFactor;
	
	@Param("CPUSelectable")
	private boolean cpuSelectable;
	
	@Param(value = "CAMATTACHED", type = ParamType.CONTEXT)
	private CameraDetails camDetails;
	
	public static class CameraDetails {
		@Param("HoodOffset")
		private RVVectorF hoodOffset;
		
		@Param("HoodLook")
		private float hoodLook;
		
		@Param("RearOffset")
		private RVVectorF rearOffset;
		
		public RVVectorF getHoodOffset() {
			return hoodOffset;
		}

		public void setHoodOffset(RVVectorF hoodOffset) {
			this.hoodOffset = hoodOffset;
		}

		public float getHoodLook() {
			return hoodLook;
		}

		public void setHoodLook(float hoodLook) {
			this.hoodLook = hoodLook;
		}

		public RVVectorF getRearOffset() {
			return rearOffset;
		}

		public void setRearOffset(RVVectorF rearOffset) {
			this.rearOffset = rearOffset;
		}

		public float getRearLook() {
			return rearLook;
		}

		public void setRearLook(float rearLook) {
			this.rearLook = rearLook;
		}

		@Param("RearLook")
		private float rearLook;
	}

	public CameraDetails getCamDetails() {
		return camDetails;
	}

	public void setCamDetails(CameraDetails camDetails) {
		this.camDetails = camDetails;
	}

	public String getCarBoxFile() {
		return carBoxFile;
	}

	public void setCarBoxFile(String carBoxFile) {
		this.carBoxFile = carBoxFile;
	}

	public String getEngineSFXFile() {
		return engineSFXFile;
	}

	public void setEngineSFXFile(String engineSFXFile) {
		this.engineSFXFile = engineSFXFile;
	}

	public String getShadowTextureFile() {
		return shadowTextureFile;
	}

	public void setShadowTextureFile(String shadowTextureFile) {
		this.shadowTextureFile = shadowTextureFile;
	}

	public int getShadowIndex() {
		return shadowIndex;
	}

	public void setShadowIndex(int shadowIndex) {
		this.shadowIndex = shadowIndex;
	}

	public RVOffsetsF getShadowTable() {
		return shadowTable;
	}

	public void setShadowTable(RVOffsetsF shadowTable) {
		this.shadowTable = shadowTable;
	}

	public boolean isStatistics() {
		return statistics;
	}

	public void setStatistics(boolean statistics) {
		this.statistics = statistics;
	}

	public int getCamber() {
		return camber;
	}

	public void setCamber(int camber) {
		this.camber = camber;
	}

	public boolean isFlippable() {
		return flippable;
	}

	public void setFlippable(boolean flippable) {
		this.flippable = flippable;
	}

	public boolean isFlying() {
		return flying;
	}

	public void setFlying(boolean flying) {
		this.flying = flying;
	}

	public boolean isClothfx() {
		return clothfx;
	}

	public void setClothfx(boolean clothfx) {
		this.clothfx = clothfx;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public RVVectorF getTranslationMax() {
		return translationMax;
	}

	public void setTranslationMax(RVVectorF translationMax) {
		this.translationMax = translationMax;
	}

	public float getVelocityFactor() {
		return velocityFactor;
	}

	public void setVelocityFactor(float velocityFactor) {
		this.velocityFactor = velocityFactor;
	}

	public boolean isCpuSelectable() {
		return cpuSelectable;
	}

	public void setCpuSelectable(boolean cpuSelectable) {
		this.cpuSelectable = cpuSelectable;
	}
	
}
