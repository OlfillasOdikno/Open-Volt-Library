package de.olfillasodikno.openvolt.lib.structures.parameters;

import de.olfillasodikno.openvolt.lib.structures.RVVectorF;
import de.olfillasodikno.openvolt.lib.structures.RVVectorI;

public class RVCarParameters extends RVParameters {

	@Param("Name")
	private String name;

	@Param("MODEL")
	private String[] models;

	@Param("TPAGE")
	private String texture_file;
	@Param("COLL")
	private String collision_file;

	@Param("EnvRGB")
	private RVVectorI env_rgb;

	@Param("BestTime")
	private boolean frontend_bestTime;

	@Param("Selectable")
	private boolean frontend_selectable;

	@Param("Class")
	private int frontend_engine_type;

	@Param("Obtain")
	private int frontend_obtain_method;

	@Param("Rating")
	private int frontend_skill_level;

	@Param("TopEnd")
	private float frontend_top_speed;

	@Param("Acc")
	private float frontend_acceleration;

	@Param("Weight")
	private float frontend_weight;

	@Param("Handling")
	private float frontend_handling;

	@Param("Trans")
	private int frontend_transmission_type;

	@Param("MaxRevs")
	private float frontend_max_revs;

	@Param("SteerRate")
	private float steer_rate;

	@Param("SteerMod")
	private float steer_mod;

	@Param("EngineRate")
	private float engine_rate;

	@Param("TopSpeed")
	private float top_speed;

	@Param("DownForceMod")
	private float down_force_mod;

	@Param("CoM")
	private RVVectorF center_of_mass;

	@Param("Weapon")
	private RVVectorF weapon_offset;

	@Param("BODY")
	private BodyDetails body;

	@Param("WHEEL 0")
	private WheelDetails wheel_0;

	@Param("WHEEL 1")
	private WheelDetails wheel_1;

	@Param("WHEEL 2")
	private WheelDetails wheel_2;

	@Param("WHEEL 3")
	private WheelDetails wheel_3;

	@Param("SPRING 0")
	private SpringDetails spring_0;

	@Param("SPRING 1")
	private SpringDetails spring_1;

	@Param("SPRING 2")
	private SpringDetails spring_2;

	@Param("SPRING 3")
	private SpringDetails spring_3;

	@Param("PIN 0")
	private PinDetails pin_0;

	@Param("PIN 1")
	private PinDetails pin_1;

	@Param("PIN 2")
	private PinDetails pin_2;

	@Param("PIN 3")
	private PinDetails pin_3;

	@Param("AXLE 0")
	private AxleDetails axle_0;

	@Param("AXLE 1")
	private AxleDetails axle_1;

	@Param("AXLE 2")
	private AxleDetails axle_2;

	@Param("AXLE 3")
	private AxleDetails axle_3;

	@Param("SPINNER")
	private SpinnerDetails spinner;

	@Param("AERIAL")
	private AerialDetails aerial;

	@Param("AI")
	private AIDetails ai;

	public RVCarParameters(Context root) {
		super(root);
	}

	public static class BodyDetails {
		@Param("ModelNum")
		private int model_num;

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
		private float air_linear_resistance;

		@Param("AngRes")
		private float air_angular_resistance;

		@Param("ResMod")
		private float air_scale_resistance;

		@Param("Grip")
		private float grip;

		@Param("StaticFriction")
		private float static_friction;

		@Param("KineticFriction")
		private float kinetic_friction;

		public int getModel_num() {
			return model_num;
		}

		public void setModel_num(int model_num) {
			this.model_num = model_num;
		}

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

		public float getAir_linear_resistance() {
			return air_linear_resistance;
		}

		public void setAir_linear_resistance(float air_linear_resistance) {
			this.air_linear_resistance = air_linear_resistance;
		}

		public float getAir_angular_resistance() {
			return air_angular_resistance;
		}

		public void setAir_angular_resistance(float air_angular_resistance) {
			this.air_angular_resistance = air_angular_resistance;
		}

		public float getAir_scale_resistance() {
			return air_scale_resistance;
		}

		public void setAir_scale_resistance(float air_scale_resistance) {
			this.air_scale_resistance = air_scale_resistance;
		}

		public float getGrip() {
			return grip;
		}

		public void setGrip(float grip) {
			this.grip = grip;
		}

		public float getStatic_friction() {
			return static_friction;
		}

		public void setStatic_friction(float static_friction) {
			this.static_friction = static_friction;
		}

		public float getKinetic_friction() {
			return kinetic_friction;
		}

		public void setKinetic_friction(float kinetic_friction) {
			this.kinetic_friction = kinetic_friction;
		}

	}

	public static class WheelDetails {
		@Param("ModelNum")
		private int model_num;

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
		private float steer_ratio;

		@Param("EngineRatio")
		private float engine_ratio;

		@Param("Radius")
		private float radius;

		@Param("Mass")
		private float mass;

		@Param("Gravity")
		private float gravity;

		@Param("MaxPos")
		private float max_pos;

		@Param("SkidWidth")
		private float skid_width;

		@Param("ToeIn")
		private float toe_in;

		@Param("AxleFriction")
		private float axle_friction;

		@Param("Grip")
		private float grip;

		@Param("StaticFriction")
		private float static_friction;

		@Param("KineticFriction")
		private float kinetic_friction;

		public int getModel_num() {
			return model_num;
		}

		public void setModel_num(int model_num) {
			this.model_num = model_num;
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

		public float getSteer_ratio() {
			return steer_ratio;
		}

		public void setSteer_ratio(float steer_ratio) {
			this.steer_ratio = steer_ratio;
		}

		public float getEngine_ratio() {
			return engine_ratio;
		}

		public void setEngine_ratio(float engine_ratio) {
			this.engine_ratio = engine_ratio;
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

		public float getMax_pos() {
			return max_pos;
		}

		public void setMax_pos(float max_pos) {
			this.max_pos = max_pos;
		}

		public float getSkid_width() {
			return skid_width;
		}

		public void setSkid_width(float skid_width) {
			this.skid_width = skid_width;
		}

		public float getToe_in() {
			return toe_in;
		}

		public void setToe_in(float toe_in) {
			this.toe_in = toe_in;
		}

		public float getAxle_friction() {
			return axle_friction;
		}

		public void setAxle_friction(float axle_friction) {
			this.axle_friction = axle_friction;
		}

		public float getGrip() {
			return grip;
		}

		public void setGrip(float grip) {
			this.grip = grip;
		}

		public float getStatic_friction() {
			return static_friction;
		}

		public void setStatic_friction(float static_friction) {
			this.static_friction = static_friction;
		}

		public float getKinetic_friction() {
			return kinetic_friction;
		}

		public void setKinetic_friction(float kinetic_friction) {
			this.kinetic_friction = kinetic_friction;
		}

	}

	public static class SpringDetails {
		@Param("ModelNum")
		private int model_num;

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

		public int getModel_num() {
			return model_num;
		}

		public void setModel_num(int model_num) {
			this.model_num = model_num;
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
		private int model_num;

		@Param("Offset")
		private RVVectorF offset;

		@Param("Length")
		private float length;

		public int getModel_num() {
			return model_num;
		}

		public void setModel_num(int model_num) {
			this.model_num = model_num;
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
		private int model_num;

		@Param("Offset")
		private RVVectorF offset;

		@Param("Length")
		private float length;

		public int getModel_num() {
			return model_num;
		}

		public void setModel_num(int model_num) {
			this.model_num = model_num;
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
		private int model_num;

		@Param("Offset")
		private RVVectorF offset;

		@Param("Axis")
		private RVVectorF axis;

		@Param("AngVel")
		private float angular_velocity;

		public int getModel_num() {
			return model_num;
		}

		public void setModel_num(int model_num) {
			this.model_num = model_num;
		}

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

		public float getAngular_velocity() {
			return angular_velocity;
		}

		public void setAngular_velocity(float angular_velocity) {
			this.angular_velocity = angular_velocity;
		}

	}

	public static class AerialDetails {

		@Param("SecModelNum")
		private int sec_model_num;

		@Param("TopModelNum")
		private int top_model_num;

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

		public int getSec_model_num() {
			return sec_model_num;
		}

		public void setSec_model_num(int sec_model_num) {
			this.sec_model_num = sec_model_num;
		}

		public int getTop_model_num() {
			return top_model_num;
		}

		public void setTop_model_num(int top_model_num) {
			this.top_model_num = top_model_num;
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
		private float under_thresh;

		@Param("UnderRange")
		private float under_range;

		@Param("UnderFront")
		private float under_front;

		@Param("UnderRear")
		private float under_rear;

		@Param("UnderMax")
		private float under_max;

		@Param("OverThresh")
		private float over_thresh;

		@Param("OverRange")
		private float over_range;

		@Param("OverMax")
		private float over_max;

		@Param("OverAccThresh")
		private float over_acceleration_thresh;

		@Param("OverAccRange")
		private float over_acceleration_range;

		@Param("PickupBias")
		private int pickup_bias;

		@Param("BlockBias")
		private int block_bias;

		@Param("OvertakeBias")
		private int overtake_bias;

		@Param("Suspension")
		private int suspension;

		@Param("Aggression")
		private int aggression;

		public float getUnder_thresh() {
			return under_thresh;
		}

		public void setUnder_thresh(float under_thresh) {
			this.under_thresh = under_thresh;
		}

		public float getUnder_range() {
			return under_range;
		}

		public void setUnder_range(float under_range) {
			this.under_range = under_range;
		}

		public float getUnder_front() {
			return under_front;
		}

		public void setUnder_front(float under_front) {
			this.under_front = under_front;
		}

		public float getUnder_rear() {
			return under_rear;
		}

		public void setUnder_rear(float under_rear) {
			this.under_rear = under_rear;
		}

		public float getUnder_max() {
			return under_max;
		}

		public void setUnder_max(float under_max) {
			this.under_max = under_max;
		}

		public float getOver_thresh() {
			return over_thresh;
		}

		public void setOver_thresh(float over_thresh) {
			this.over_thresh = over_thresh;
		}

		public float getOver_range() {
			return over_range;
		}

		public void setOver_range(float over_range) {
			this.over_range = over_range;
		}

		public float getOver_max() {
			return over_max;
		}

		public void setOver_max(float over_max) {
			this.over_max = over_max;
		}

		public float getOver_acceleration_thresh() {
			return over_acceleration_thresh;
		}

		public void setOver_acceleration_thresh(float over_acceleration_thresh) {
			this.over_acceleration_thresh = over_acceleration_thresh;
		}

		public float getOver_acceleration_range() {
			return over_acceleration_range;
		}

		public void setOver_acceleration_range(float over_acceleration_range) {
			this.over_acceleration_range = over_acceleration_range;
		}

		public int getPickup_bias() {
			return pickup_bias;
		}

		public void setPickup_bias(int pickup_bias) {
			this.pickup_bias = pickup_bias;
		}

		public int getBlock_bias() {
			return block_bias;
		}

		public void setBlock_bias(int block_bias) {
			this.block_bias = block_bias;
		}

		public int getOvertake_bias() {
			return overtake_bias;
		}

		public void setOvertake_bias(int overtake_bias) {
			this.overtake_bias = overtake_bias;
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

	public String getTexture_file() {
		return texture_file;
	}

	public void setTexture_file(String texture_file) {
		this.texture_file = texture_file;
	}

	public String getCollision_file() {
		return collision_file;
	}

	public void setCollision_file(String collision_file) {
		this.collision_file = collision_file;
	}

	public RVVectorI getEnv_rgb() {
		return env_rgb;
	}

	public void setEnv_rgb(RVVectorI env_rgb) {
		this.env_rgb = env_rgb;
	}

	public boolean isFrontend_bestTime() {
		return frontend_bestTime;
	}

	public void setFrontend_bestTime(boolean frontend_bestTime) {
		this.frontend_bestTime = frontend_bestTime;
	}

	public boolean isFrontend_selectable() {
		return frontend_selectable;
	}

	public void setFrontend_selectable(boolean frontend_selectable) {
		this.frontend_selectable = frontend_selectable;
	}

	public int getFrontend_engine_type() {
		return frontend_engine_type;
	}

	public void setFrontend_engine_type(int frontend_engine_type) {
		this.frontend_engine_type = frontend_engine_type;
	}

	public int getFrontend_obtain_method() {
		return frontend_obtain_method;
	}

	public void setFrontend_obtain_method(int frontend_obtain_method) {
		this.frontend_obtain_method = frontend_obtain_method;
	}

	public int getFrontend_skill_level() {
		return frontend_skill_level;
	}

	public void setFrontend_skill_level(int frontend_skill_level) {
		this.frontend_skill_level = frontend_skill_level;
	}

	public float getFrontend_top_speed() {
		return frontend_top_speed;
	}

	public void setFrontend_top_speed(float frontend_top_speed) {
		this.frontend_top_speed = frontend_top_speed;
	}

	public float getFrontend_acceleration() {
		return frontend_acceleration;
	}

	public void setFrontend_acceleration(float frontend_acceleration) {
		this.frontend_acceleration = frontend_acceleration;
	}

	public float getFrontend_weight() {
		return frontend_weight;
	}

	public void setFrontend_weight(float frontend_weight) {
		this.frontend_weight = frontend_weight;
	}

	public float getFrontend_handling() {
		return frontend_handling;
	}

	public void setFrontend_handling(float frontend_handling) {
		this.frontend_handling = frontend_handling;
	}

	public int getFrontend_transmission_type() {
		return frontend_transmission_type;
	}

	public void setFrontend_transmission_type(int frontend_transmission_type) {
		this.frontend_transmission_type = frontend_transmission_type;
	}

	public float getFrontend_max_revs() {
		return frontend_max_revs;
	}

	public void setFrontend_max_revs(float frontend_max_revs) {
		this.frontend_max_revs = frontend_max_revs;
	}

	public float getSteer_rate() {
		return steer_rate;
	}

	public void setSteer_rate(float steer_rate) {
		this.steer_rate = steer_rate;
	}

	public float getSteer_mod() {
		return steer_mod;
	}

	public void setSteer_mod(float steer_mod) {
		this.steer_mod = steer_mod;
	}

	public float getEngine_rate() {
		return engine_rate;
	}

	public void setEngine_rate(float engine_rate) {
		this.engine_rate = engine_rate;
	}

	public float getTop_speed() {
		return top_speed;
	}

	public void setTop_speed(float top_speed) {
		this.top_speed = top_speed;
	}

	public float getDown_force_mod() {
		return down_force_mod;
	}

	public void setDown_force_mod(float down_force_mod) {
		this.down_force_mod = down_force_mod;
	}

	public RVVectorF getCenter_of_mass() {
		return center_of_mass;
	}

	public void setCenter_of_mass(RVVectorF center_of_mass) {
		this.center_of_mass = center_of_mass;
	}

	public RVVectorF getWeapon_offset() {
		return weapon_offset;
	}

	public void setWeapon_offset(RVVectorF weapon_offset) {
		this.weapon_offset = weapon_offset;
	}

	public BodyDetails getBody() {
		return body;
	}

	public void setBody(BodyDetails body) {
		this.body = body;
	}

	public WheelDetails getWheel_0() {
		return wheel_0;
	}

	public void setWheel_0(WheelDetails wheel_0) {
		this.wheel_0 = wheel_0;
	}

	public WheelDetails getWheel_1() {
		return wheel_1;
	}

	public void setWheel_1(WheelDetails wheel_1) {
		this.wheel_1 = wheel_1;
	}

	public WheelDetails getWheel_2() {
		return wheel_2;
	}

	public void setWheel_2(WheelDetails wheel_2) {
		this.wheel_2 = wheel_2;
	}

	public WheelDetails getWheel_3() {
		return wheel_3;
	}

	public void setWheel_3(WheelDetails wheel_3) {
		this.wheel_3 = wheel_3;
	}

	public SpringDetails getSpring_0() {
		return spring_0;
	}

	public void setSpring_0(SpringDetails spring_0) {
		this.spring_0 = spring_0;
	}

	public SpringDetails getSpring_1() {
		return spring_1;
	}

	public void setSpring_1(SpringDetails spring_1) {
		this.spring_1 = spring_1;
	}

	public SpringDetails getSpring_2() {
		return spring_2;
	}

	public void setSpring_2(SpringDetails spring_2) {
		this.spring_2 = spring_2;
	}

	public SpringDetails getSpring_3() {
		return spring_3;
	}

	public void setSpring_3(SpringDetails spring_3) {
		this.spring_3 = spring_3;
	}

	public PinDetails getPin_0() {
		return pin_0;
	}

	public void setPin_0(PinDetails pin_0) {
		this.pin_0 = pin_0;
	}

	public PinDetails getPin_1() {
		return pin_1;
	}

	public void setPin_1(PinDetails pin_1) {
		this.pin_1 = pin_1;
	}

	public PinDetails getPin_2() {
		return pin_2;
	}

	public void setPin_2(PinDetails pin_2) {
		this.pin_2 = pin_2;
	}

	public PinDetails getPin_3() {
		return pin_3;
	}

	public void setPin_3(PinDetails pin_3) {
		this.pin_3 = pin_3;
	}

	public AxleDetails getAxle_0() {
		return axle_0;
	}

	public void setAxle_0(AxleDetails axle_0) {
		this.axle_0 = axle_0;
	}

	public AxleDetails getAxle_1() {
		return axle_1;
	}

	public void setAxle_1(AxleDetails axle_1) {
		this.axle_1 = axle_1;
	}

	public AxleDetails getAxle_2() {
		return axle_2;
	}

	public void setAxle_2(AxleDetails axle_2) {
		this.axle_2 = axle_2;
	}

	public AxleDetails getAxle_3() {
		return axle_3;
	}

	public void setAxle_3(AxleDetails axle_3) {
		this.axle_3 = axle_3;
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
