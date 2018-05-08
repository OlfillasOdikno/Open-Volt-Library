package de.olfillasodikno.openvolt.lib.structures.parameters;

import de.olfillasodikno.openvolt.lib.structures.RVVectorF;
import de.olfillasodikno.openvolt.lib.structures.RVVectorI;

public class RVCarParameters extends RVParameters {

	@Param("Name")
	private String name;

	@Param("MODEL")
	private String[] models;

	@Param("TPAGE")
	private String textureFile;
	@Param("COLL")
	private String collisionFile;

	@Param("EnvRGB")
	private RVVectorI envRGB;

	@Param("BestTime")
	private boolean frontendBestTime;

	@Param("Selectable")
	private boolean frontendSelectable;

	@Param("Class")
	private int frontendEngineType;

	@Param("Obtain")
	private int frontendObtainMethod;

	@Param("Rating")
	private int frontendSkillLevel;

	@Param("TopEnd")
	private float frontendTopSpeed;

	@Param("Acc")
	private float frontendAcceleration;

	@Param("Weight")
	private float frontendWeight;

	@Param("Handling")
	private float frontendHandling;

	@Param("Trans")
	private int frontendTransmissionType;

	@Param("MaxRevs")
	private float frontendMaxRevs;

	@Param("SteerRate")
	private float steerRate;

	@Param("SteerMod")
	private float steerMod;

	@Param("EngineRate")
	private float engineRate;

	@Param("TopSpeed")
	private float topSpeed;

	@Param("DownForceMod")
	private float downForceMod;

	@Param("CoM")
	private RVVectorF centerOfMass;

	@Param("Weapon")
	private RVVectorF weaponOffset;

	@Param(value = "BODY", type = ParamType.CONTEXT)
	private BodyDetails body;

	@Param(value = "WHEEL", type = ParamType.CONTEXT)
	private WheelDetails[] wheels;

	@Param(value = "SPRING", type = ParamType.CONTEXT)
	private SpringDetails[] springs;

	@Param(value = "PIN", type = ParamType.CONTEXT)
	private PinDetails[] pins;

	@Param(value = "AXLE", type = ParamType.CONTEXT)
	private AxleDetails[] axles;

	@Param(value = "SPINNER", type = ParamType.CONTEXT)
	private SpinnerDetails spinner;

	@Param(value = "AERIAL", type = ParamType.CONTEXT)
	private AerialDetails aerial;

	@Param(value = "AI", type = ParamType.CONTEXT)
	private AIDetails ai;

	public RVCarParameters(Context root) {
		super(root);
	}

	public static class BodyDetails {
		@Param("ModelNum")
		private int modelNum;

		@Param("Offset")
		private RVVectorF offset;

		@Param("Mass")
		private float mass;

		// TODO: Inertia

		@Param("Gravity")
		private int gravity;

		@Param("Hardness")
		private float hardness;

		@Param("Resistance")
		private float airLinearResistance;

		@Param("AngRes")
		private float airAngularResistance;

		@Param("ResMod")
		private float airScaleResistance;

		@Param("Grip")
		private float grip;

		@Param("StaticFriction")
		private float staticFriction;

		@Param("KineticFriction")
		private float kineticFriction;

		public RVVectorF getOffset() {
			return offset;
		}

		public void setOffset(RVVectorF offset) {
			this.offset = offset;
		}

		public float getMass() {
			return mass;
		}

		public void setMass(float mass) {
			this.mass = mass;
		}

		public int getGravity() {
			return gravity;
		}

		public void setGravity(int gravity) {
			this.gravity = gravity;
		}

		public float getHardness() {
			return hardness;
		}

		public void setHardness(float hardness) {
			this.hardness = hardness;
		}

		public float getGrip() {
			return grip;
		}

		public void setGrip(float grip) {
			this.grip = grip;
		}

		public int getModelNum() {
			return modelNum;
		}

		public void setModelNum(int modelNum) {
			this.modelNum = modelNum;
		}

		public float getAirLinearResistance() {
			return airLinearResistance;
		}

		public void setAirLinearResistance(float airLinearResistance) {
			this.airLinearResistance = airLinearResistance;
		}

		public float getAirAngularResistance() {
			return airAngularResistance;
		}

		public void setAirAngularResistance(float airAngularResistance) {
			this.airAngularResistance = airAngularResistance;
		}

		public float getAirScaleResistance() {
			return airScaleResistance;
		}

		public void setAirScaleResistance(float airScaleResistance) {
			this.airScaleResistance = airScaleResistance;
		}

		public float getStaticFriction() {
			return staticFriction;
		}

		public void setStaticFriction(float staticFriction) {
			this.staticFriction = staticFriction;
		}

		public float getKineticFriction() {
			return kineticFriction;
		}

		public void setKineticFriction(float kineticFriction) {
			this.kineticFriction = kineticFriction;
		}

	}

	public static class WheelDetails {
		@Param("ModelNum")
		private int modelNum;

		@Param("Offset1")
		private RVVectorF offset1;

		@Param("Offset2")
		private RVVectorF offset2;

		@Param("IsPresent")
		private boolean present;

		@Param("IsPowered")
		private boolean powered;

		@Param("IsTurnable")
		private boolean turnable;

		@Param("SteerRatio")
		private float steerRatio;

		@Param("EngineRatio")
		private float engineRatio;

		@Param("Radius")
		private float radius;

		@Param("Mass")
		private float mass;

		@Param("Gravity")
		private float gravity;

		@Param("MaxPos")
		private float maxPos;

		@Param("SkidWidth")
		private float skidWidth;

		@Param("ToeIn")
		private float toeIn;

		@Param("AxleFriction")
		private float axleFriction;

		@Param("Grip")
		private float grip;

		@Param("StaticFriction")
		private float staticFriction;

		@Param("KineticFriction")
		private float kineticFriction;

		public int getModelNum() {
			return modelNum;
		}

		public void setModelNum(int modelNum) {
			this.modelNum = modelNum;
		}

		public RVVectorF getOffset1() {
			return offset1;
		}

		public void setOffset1(RVVectorF offset1) {
			this.offset1 = offset1;
		}

		public RVVectorF getOffset2() {
			return offset2;
		}

		public void setOffset2(RVVectorF offset2) {
			this.offset2 = offset2;
		}

		public boolean isPresent() {
			return present;
		}

		public void setPresent(boolean present) {
			this.present = present;
		}

		public boolean isPowered() {
			return powered;
		}

		public void setPowered(boolean powered) {
			this.powered = powered;
		}

		public boolean isTurnable() {
			return turnable;
		}

		public void setTurnable(boolean turnable) {
			this.turnable = turnable;
		}

		public float getSteerRatio() {
			return steerRatio;
		}

		public void setSteerRatio(float steerRatio) {
			this.steerRatio = steerRatio;
		}

		public float getEngineRatio() {
			return engineRatio;
		}

		public void setEngineRatio(float engineRatio) {
			this.engineRatio = engineRatio;
		}

		public float getRadius() {
			return radius;
		}

		public void setRadius(float radius) {
			this.radius = radius;
		}

		public float getMass() {
			return mass;
		}

		public void setMass(float mass) {
			this.mass = mass;
		}

		public float getGravity() {
			return gravity;
		}

		public void setGravity(float gravity) {
			this.gravity = gravity;
		}

		public float getMaxPos() {
			return maxPos;
		}

		public void setMaxPos(float maxPos) {
			this.maxPos = maxPos;
		}

		public float getSkidWidth() {
			return skidWidth;
		}

		public void setSkidWidth(float skidWidth) {
			this.skidWidth = skidWidth;
		}

		public float getToeIn() {
			return toeIn;
		}

		public void setToeIn(float toeIn) {
			this.toeIn = toeIn;
		}

		public float getAxleFriction() {
			return axleFriction;
		}

		public void setAxleFriction(float axleFriction) {
			this.axleFriction = axleFriction;
		}

		public float getGrip() {
			return grip;
		}

		public void setGrip(float grip) {
			this.grip = grip;
		}

		public float getStaticFriction() {
			return staticFriction;
		}

		public void setStaticFriction(float staticFriction) {
			this.staticFriction = staticFriction;
		}

		public float getKineticFriction() {
			return kineticFriction;
		}

		public void setKineticFriction(float kineticFriction) {
			this.kineticFriction = kineticFriction;
		}

	}

	public static class SpringDetails {
		@Param("ModelNum")
		private int modelNum;

		@Param("Offset")
		private RVVectorF offset;

		@Param("Length")
		private float length;

		@Param("Stiffness")
		private float stiffness;

		@Param("Damping")
		private float damping;

		@Param("Restitution")
		private float restitution;

		public int getModelNum() {
			return modelNum;
		}

		public void setModelNum(int modelNum) {
			this.modelNum = modelNum;
		}

		public RVVectorF getOffset() {
			return offset;
		}

		public void setOffset(RVVectorF offset) {
			this.offset = offset;
		}

		public float getLength() {
			return length;
		}

		public void setLength(float length) {
			this.length = length;
		}

		public float getStiffness() {
			return stiffness;
		}

		public void setStiffness(float stiffness) {
			this.stiffness = stiffness;
		}

		public float getDamping() {
			return damping;
		}

		public void setDamping(float damping) {
			this.damping = damping;
		}

		public float getRestitution() {
			return restitution;
		}

		public void setRestitution(float restitution) {
			this.restitution = restitution;
		}

	}

	public static class PinDetails {
		@Param("ModelNum")
		private int modelNum;

		@Param("Offset")
		private RVVectorF offset;

		@Param("Length")
		private float length;

		public int getModelNum() {
			return modelNum;
		}

		public void setModelNum(int modelNum) {
			this.modelNum = modelNum;
		}

		public RVVectorF getOffset() {
			return offset;
		}

		public void setOffset(RVVectorF offset) {
			this.offset = offset;
		}

		public float getLength() {
			return length;
		}

		public void setLength(float length) {
			this.length = length;
		}

	}

	public static class AxleDetails {
		@Param("ModelNum")
		private int modelNum;

		@Param("Offset")
		private RVVectorF offset;

		@Param("Length")
		private float length;

		public int getModelNum() {
			return modelNum;
		}

		public void setModelNum(int modelNum) {
			this.modelNum = modelNum;
		}

		public RVVectorF getOffset() {
			return offset;
		}

		public void setOffset(RVVectorF offset) {
			this.offset = offset;
		}

		public float getLength() {
			return length;
		}

		public void setLength(float length) {
			this.length = length;
		}

	}

	public static class SpinnerDetails {
		@Param("ModelNum")
		private int modelNum;

		@Param("Offset")
		private RVVectorF offset;

		@Param("Axis")
		private RVVectorF axis;

		@Param("AngVel")
		private float angularVelocity;

		public RVVectorF getOffset() {
			return offset;
		}

		public void setOffset(RVVectorF offset) {
			this.offset = offset;
		}

		public RVVectorF getAxis() {
			return axis;
		}

		public void setAxis(RVVectorF axis) {
			this.axis = axis;
		}

		public int getModelNum() {
			return modelNum;
		}

		public void setModelNum(int modelNum) {
			this.modelNum = modelNum;
		}

		public float getAngularVelocity() {
			return angularVelocity;
		}

		public void setAngularVelocity(float angularVelocity) {
			this.angularVelocity = angularVelocity;
		}

	}

	public static class AerialDetails {

		@Param("SecModelNum")
		private int secModelNum;

		@Param("TopModelNum")
		private int topModelNum;

		@Param("Offset")
		private RVVectorF offset;

		@Param("Direction")
		private RVVectorF direction;

		@Param("Length")
		private float length;

		@Param("Stiffness")
		private float stiffness;

		@Param("Damping")
		private float damping;

		public int getSecModelNum() {
			return secModelNum;
		}

		public void setSecModelNum(int secModelNum) {
			this.secModelNum = secModelNum;
		}

		public int getTopModelNum() {
			return topModelNum;
		}

		public void setTopModelNum(int topModelNum) {
			this.topModelNum = topModelNum;
		}

		public RVVectorF getOffset() {
			return offset;
		}

		public void setOffset(RVVectorF offset) {
			this.offset = offset;
		}

		public RVVectorF getDirection() {
			return direction;
		}

		public void setDirection(RVVectorF direction) {
			this.direction = direction;
		}

		public float getLength() {
			return length;
		}

		public void setLength(float length) {
			this.length = length;
		}

		public float getStiffness() {
			return stiffness;
		}

		public void setStiffness(float stiffness) {
			this.stiffness = stiffness;
		}

		public float getDamping() {
			return damping;
		}

		public void setDamping(float damping) {
			this.damping = damping;
		}

	}

	public static class AIDetails {

		@Param("UnderThresh")
		private float underThresh;

		@Param("UnderRange")
		private float underRange;

		@Param("UnderFront")
		private float underFront;

		@Param("UnderRear")
		private float underRear;

		@Param("UnderMax")
		private float underMax;

		@Param("OverThresh")
		private float overThresh;

		@Param("OverRange")
		private float overRange;

		@Param("OverMax")
		private float overMax;

		@Param("OverAccThresh")
		private float overAccelerationThresh;

		@Param("OverAccRange")
		private float overAccelerationRange;

		@Param("PickupBias")
		private int pickupBias;

		@Param("BlockBias")
		private int blockBias;

		@Param("OvertakeBias")
		private int overtakeBias;

		@Param("Suspension")
		private int suspension;

		@Param("Aggression")
		private int aggression;



		public float getUnderThresh() {
			return underThresh;
		}

		public void setUnderThresh(float underThresh) {
			this.underThresh = underThresh;
		}

		public float getUnderRange() {
			return underRange;
		}

		public void setUnderRange(float underRange) {
			this.underRange = underRange;
		}

		public float getUnderFront() {
			return underFront;
		}

		public void setUnderFront(float underFront) {
			this.underFront = underFront;
		}

		public float getUnderRear() {
			return underRear;
		}

		public void setUnderRear(float underRear) {
			this.underRear = underRear;
		}

		public float getUnderMax() {
			return underMax;
		}

		public void setUnderMax(float underMax) {
			this.underMax = underMax;
		}

		public float getOverThresh() {
			return overThresh;
		}

		public void setOverThresh(float overThresh) {
			this.overThresh = overThresh;
		}

		public float getOverRange() {
			return overRange;
		}

		public void setOverRange(float overRange) {
			this.overRange = overRange;
		}

		public float getOverMax() {
			return overMax;
		}

		public void setOverMax(float overMax) {
			this.overMax = overMax;
		}

		public float getOverAccelerationThresh() {
			return overAccelerationThresh;
		}

		public void setOverAccelerationThresh(float overAccelerationThresh) {
			this.overAccelerationThresh = overAccelerationThresh;
		}

		public float getOverAccelerationRange() {
			return overAccelerationRange;
		}

		public void setOverAccelerationRange(float overAccelerationRange) {
			this.overAccelerationRange = overAccelerationRange;
		}

		public int getPickupBias() {
			return pickupBias;
		}

		public void setPickupBias(int pickupBias) {
			this.pickupBias = pickupBias;
		}

		public int getBlockBias() {
			return blockBias;
		}

		public void setBlockBias(int blockBias) {
			this.blockBias = blockBias;
		}

		public int getOvertakeBias() {
			return overtakeBias;
		}

		public void setOvertakeBias(int overtakeBias) {
			this.overtakeBias = overtakeBias;
		}

		public int getSuspension() {
			return suspension;
		}

		public void setSuspension(int suspension) {
			this.suspension = suspension;
		}

		public int getAggression() {
			return aggression;
		}

		public void setAggression(int aggression) {
			this.aggression = aggression;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getModels() {
		return models;
	}

	public void setModels(String[] models) {
		this.models = models;
	}

	public String getTextureFile() {
		return textureFile;
	}

	public void setTextureFile(String textureFile) {
		this.textureFile = textureFile;
	}

	public String getCollisionFile() {
		return collisionFile;
	}

	public void setCollisionFile(String collisionFile) {
		this.collisionFile = collisionFile;
	}

	public RVVectorI getEnvRGB() {
		return envRGB;
	}

	public void setEnvRGB(RVVectorI envRGB) {
		this.envRGB = envRGB;
	}

	public boolean isFrontendBestTime() {
		return frontendBestTime;
	}

	public void setFrontendBestTime(boolean frontendBestTime) {
		this.frontendBestTime = frontendBestTime;
	}

	public boolean isFrontendSelectable() {
		return frontendSelectable;
	}

	public void setFrontendSelectable(boolean frontendSelectable) {
		this.frontendSelectable = frontendSelectable;
	}

	public int getFrontendEngineType() {
		return frontendEngineType;
	}

	public void setFrontendEngineType(int frontendEngineType) {
		this.frontendEngineType = frontendEngineType;
	}

	public int getFrontendObtainMethod() {
		return frontendObtainMethod;
	}

	public void setFrontendObtainMethod(int frontendObtainMethod) {
		this.frontendObtainMethod = frontendObtainMethod;
	}

	public int getFrontendSkillLevel() {
		return frontendSkillLevel;
	}

	public void setFrontendSkillLevel(int frontendSkillLevel) {
		this.frontendSkillLevel = frontendSkillLevel;
	}

	public float getFrontendTopSpeed() {
		return frontendTopSpeed;
	}

	public void setFrontendTopSpeed(float frontendTopSpeed) {
		this.frontendTopSpeed = frontendTopSpeed;
	}

	public float getFrontendAcceleration() {
		return frontendAcceleration;
	}

	public void setFrontendAcceleration(float frontendAcceleration) {
		this.frontendAcceleration = frontendAcceleration;
	}

	public float getFrontendWeight() {
		return frontendWeight;
	}

	public void setFrontendWeight(float frontendWeight) {
		this.frontendWeight = frontendWeight;
	}

	public float getFrontendHandling() {
		return frontendHandling;
	}

	public void setFrontendHandling(float frontendHandling) {
		this.frontendHandling = frontendHandling;
	}

	public int getFrontendTransmissionType() {
		return frontendTransmissionType;
	}

	public void setFrontendTransmissionType(int frontendTransmissionType) {
		this.frontendTransmissionType = frontendTransmissionType;
	}

	public float getFrontendMaxRevs() {
		return frontendMaxRevs;
	}

	public void setFrontendMaxRevs(float frontendMaxRevs) {
		this.frontendMaxRevs = frontendMaxRevs;
	}

	public float getSteerRate() {
		return steerRate;
	}

	public void setSteerRate(float steerRate) {
		this.steerRate = steerRate;
	}

	public float getSteerMod() {
		return steerMod;
	}

	public void setSteerMod(float steerMod) {
		this.steerMod = steerMod;
	}

	public float getEngineRate() {
		return engineRate;
	}

	public void setEngineRate(float engineRate) {
		this.engineRate = engineRate;
	}

	public float getTopSpeed() {
		return topSpeed;
	}

	public void setTopSpeed(float topSpeed) {
		this.topSpeed = topSpeed;
	}

	public float getDownForceMod() {
		return downForceMod;
	}

	public void setDownForceMod(float downForceMod) {
		this.downForceMod = downForceMod;
	}

	public RVVectorF getCenterOfMass() {
		return centerOfMass;
	}

	public void setCenterOfMass(RVVectorF centerOfMass) {
		this.centerOfMass = centerOfMass;
	}

	public RVVectorF getWeaponOffset() {
		return weaponOffset;
	}

	public void setWeaponOffset(RVVectorF weaponOffset) {
		this.weaponOffset = weaponOffset;
	}

	public BodyDetails getBody() {
		return body;
	}

	public void setBody(BodyDetails body) {
		this.body = body;
	}

	public WheelDetails[] getWheels() {
		return wheels;
	}

	public void setWheels(WheelDetails[] wheels) {
		this.wheels = wheels;
	}

	public SpringDetails[] getSprings() {
		return springs;
	}

	public void setSprings(SpringDetails[] springs) {
		this.springs = springs;
	}

	public PinDetails[] getPins() {
		return pins;
	}

	public void setPins(PinDetails[] pins) {
		this.pins = pins;
	}

	public AxleDetails[] getAxles() {
		return axles;
	}

	public void setAxles(AxleDetails[] axles) {
		this.axles = axles;
	}

	public SpinnerDetails getSpinner() {
		return spinner;
	}

	public void setSpinner(SpinnerDetails spinner) {
		this.spinner = spinner;
	}

	public AerialDetails getAerial() {
		return aerial;
	}

	public void setAerial(AerialDetails aerial) {
		this.aerial = aerial;
	}

	public AIDetails getAi() {
		return ai;
	}

	public void setAi(AIDetails ai) {
		this.ai = ai;
	}
	
}
