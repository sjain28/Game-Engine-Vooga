package physics;

import java.util.concurrent.TimeUnit;

import gameengine.Sprite;
import tools.Acceleration;
import tools.Position;
import tools.Velocity;

/**
 * Standard Physics Module
 * 
 * A set of methods that provide physics-related functionalities
 * 
 * @author Michael, Hunter
 *
 */
public class StandardPhysics implements IPhysicsEngine{
	
	public static final double REDUCE_FACTOR = 0.05;
	
	
	/**
	 * Default constructor for Standard Physics module
	 * 
	 */
	public StandardPhysics() {

	}
	
	
	@Deprecated
	private double myFrameTime;

	@Deprecated
	public StandardPhysics(double frameTime){	
//		this.frameTime = (float) FixedStepLoopWithInterpolation.FRAME_TIME;
		myFrameTime = frameTime;
	}
	
//	@Override
//	public void translateX(Sprite sprite, Double change) {
//		sprite.getVelocity().setX(change * REDUCE_FACTOR);
//		
//	}
	
	@Override
	public void translateX(Sprite sprite, Double change) {
		//sprite.getPosition().addX(change * 10);
		sprite.getVelocity().setX(change * REDUCE_FACTOR);
	}
//	
	public void translateY(Sprite sprite, Double change) {
		sprite.getPosition().addY(change);
	}
		
	@Override
	public void setPosition(Sprite sprite, Position newPosition) {
		sprite.setPosition(newPosition);
	}


	public void setVelocity(Sprite sprite, Velocity newVelocity) {
		sprite.setVelocity(newVelocity);
	}


	@Override
	public void bounce(Sprite sprite, Double bounceCoefficient) {
		Velocity curr = sprite.getVelocity();
		curr.setX(-1*curr.getX()*bounceCoefficient);
		curr.setY(-1*curr.getY()*bounceCoefficient);
	}

	@Override
	public void friction(Sprite sprite, Double frictionCoefficient) {
		Acceleration curr = new Acceleration(sprite.getVelocity().getX(), sprite.getVelocity().getY());
		curr.setX(curr.getX()*frictionCoefficient);
		curr.setY(curr.getY()*frictionCoefficient);
		accelerate(sprite, curr);
	}

	@Override
	public void jump(Sprite sprite, Double jumpMagnitude) {
		Velocity jumpVelocity = new Velocity(0, jumpMagnitude);
		setVelocity(sprite, jumpVelocity);
	}

	@Override 
	public void gravity(Sprite sprite, Double gravityMagnitude) {
		Double mass = (((Double) sprite.getPropertiesMap().get("Mass").getValue()).isInfinite()) ? 0d : (Double) sprite.getPropertiesMap().get("Mass").getValue();
//		sprite.getVelocity().setX(sprite.getVelocity().getX() + 0.0001);
		sprite.getVelocity().setY(sprite.getVelocity().getY() + gravityMagnitude * 0.000001);
		
//		Acceleration gravityAcceleration = new Acceleration(0, mass * gravityMagnitude/Math.pow(10, 9));
//		accelerate(sprite, gravityAcceleration);
	}

	@Override
	public void accelerate(Sprite sprite, Acceleration change) {
		// TODO Auto-generated method stub
		sprite.getVelocity().setX(sprite.getVelocity().getX() + change.getX() * REDUCE_FACTOR);
		sprite.getVelocity().setY(sprite.getVelocity().getY() + change.getY() * REDUCE_FACTOR);

	}

}
