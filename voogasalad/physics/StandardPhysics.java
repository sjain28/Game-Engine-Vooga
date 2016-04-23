package physics;

import gameengine.Sprite;
import javafx.geometry.Bounds;
import javafx.scene.input.KeyEvent;
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
	
	private static final double REDUCE_FACTOR = 0.1;
	private static final double VELOCITY_FACTOR = 0.00001;
	private static final double LIFT = 0.00001;
	private static final double ERROR = 0.01;
	private static final double JUMP_FACTOR = 0.05;
	
	
	/**
	 * Default constructor for Standard Physics module
	 * 
	 */
	public StandardPhysics() {

	}
	
	/**
	 * Checks whether a double is zero
	 * 
	 * @param number
	 * @return
	 */
	private boolean isZero(Double number) {
		return Math.abs(number.doubleValue()) < ERROR;
	}

	
	@Deprecated
	private double myFrameTime;

	@Deprecated
	public StandardPhysics(double frameTime){	
//		this.frameTime = (float) FixedStepLoopWithInterpolation.FRAME_TIME;
		myFrameTime = frameTime;
	}
	
	@Override
	public void translateX(Sprite sprite, Double change) {
		sprite.getVelocity().setX(change * REDUCE_FACTOR);
		//System.out.println("Translate: my velocity is: " + sprite.getVelocity().getX());
	}
	

	public void translateXwithKeyEvent(Sprite sprite, Double change, KeyEvent event) {
		sprite.getVelocity().setX(change * REDUCE_FACTOR);
		if (event.isConsumed()) {
			sprite.getVelocity().setX(0);
		}
		//System.out.println("Translate: my velocity is: " + sprite.getVelocity().getX());
	}
	
//	@Override
//	public void translateX(Sprite sprite, Double change) {
//		sprite.getPosition().addX(change* 5);
//		
//	}
	
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

	/**
	 * Elastic bounce in Y direction
	 * 
	 */
	@Override
	public void bounce(Sprite sprite, Double bounceCoefficient) {
		//System.out.println("Bounce is called");
		
		// If sprite's velocity is negligible and not 0 (at start, velocity is 0!)
		if (sprite.getVelocity().getY() < 0.1 && sprite.getVelocity().getY() != 0.0) {
			// Set velocity to 0--stop the bounce
			sprite.getVelocity().setY(0.0);
			// Set the Y position to a little higher so there is no collision
			sprite.getPosition().setY(sprite.getPosition().getY() - 0.1);
		}
		
		else {
			
			//sprite.getImage().getBoundsInParent().
			//sprite.getVelocity().setX(-1 * sprite.getVelocity().getX() * bounceCoefficient);
			sprite.getVelocity().setY(-1 * sprite.getVelocity().getY() * bounceCoefficient);
		}

	}
	
	/**
	 * Sets X velocity to the opposite direction with bounceCoefficient applied
	 * 
	 * @param sprite
	 * @param bounceCoefficient
	 */
	public void elasticBounceX(Sprite sprite, Double bounceCoefficient) {
		sprite.getVelocity().setX(-1 * sprite.getVelocity().getX() * bounceCoefficient);
	}
	
	/**
	 * Sets X velocity to zero (stops the movement)
	 * 
	 * @param sprite
	 * @param bounceCoefficient
	 */
	public void inelasticBounceX(Sprite sprite, Double bounceCoefficient) {
		sprite.getVelocity().setX(0);
	}
	
	/**
	 * Checks if the collision is happening horizontally
	 * 
	 * @param spriteA
	 * @param spriteB
	 * @return
	 */
	public boolean checkCollisionX(Sprite spriteA, Sprite spriteB) {
		Bounds boundA = spriteA.getImage().getBoundsInLocal();
		Bounds boundB = spriteB.getImage().getBoundsInLocal();
        boolean atRightBorder = boundA.getMaxX() >= boundB.getMaxX();
        boolean atLeftBorder = boundA.getMinX() <= boundB.getMinX();
        
        if (atRightBorder || atLeftBorder) {
        	return true;
        }
        else {
        	return false;
        }
	}
	
	/**
	 * Checks if the collision is happening vertically
	 * 
	 * @param spriteA
	 * @param spriteB
	 * @return
	 */
	public boolean checkCollisionY(Sprite spriteA, Sprite spriteB) {
		Bounds boundA = spriteA.getImage().getBoundsInLocal();
		Bounds boundB = spriteB.getImage().getBoundsInLocal();
        boolean atTopBorder = boundA.getMaxY() >= boundB.getMaxY();
        boolean atBottomBorder = boundA.getMinY() <= boundB.getMinY();
        
        if (atTopBorder || atBottomBorder) {
        	return true;
        }
        else {
        	return false;
        }
	}

	@Override
	public void friction(Sprite sprite, Double frictionCoefficient) {
		Acceleration curr = new Acceleration(sprite.getVelocity().getX(), sprite.getVelocity().getY());
		curr.setX(curr.getX()*frictionCoefficient);
		curr.setY(curr.getY()*frictionCoefficient);
		accelerate(sprite, curr);
	}

	/**
	 * Jump method that allows the main character to gain upward velocity
	 * 
	 */
	@Override
	public void jump(Sprite sprite, Double jumpMagnitude) {
		
		//System.out.println("When Jump is called, this is sprite's y velocity: " + sprite.getVelocity().getY());
		//System.out.println("And this is the result of isZero check: " + isZero(sprite.getVelocity().getY()));
		// Check if the main character is on the ground, not in the air to be able to jump
		if (isZero(sprite.getVelocity().getY())) {
			// Apply change to the velocity so that the character has upward velocity
			sprite.getVelocity().setY(sprite.getVelocity().getY() - jumpMagnitude * JUMP_FACTOR);
		}
	}

	/**
	 * Applies the effect of gravity by giving it a downward force
	 * 
	 */
	@Override 
	public void gravity(Sprite sprite, Double gravityMagnitude) {
		//System.out.println("Gravity is called");
		
		//if (onGround) return;
		
		//if (isZero(sprite.getVelocity().getY())) return;
		// Get mass value of the sprite
		Double mass = (((Double) sprite.getPropertiesMap().get("Mass").getValue()).isInfinite()) 
				? 0d : (Double) sprite.getPropertiesMap().get("Mass").getValue();
		
		// Apply velocity change to the sprite's velocity
		sprite.getVelocity().setY(sprite.getVelocity().getY() + 
				mass * gravityMagnitude * VELOCITY_FACTOR);
	}

	@Override
	public void accelerate(Sprite sprite, Acceleration change) {
		sprite.getVelocity().setX(sprite.getVelocity().getX() + change.getX() * VELOCITY_FACTOR);
		sprite.getVelocity().setY(sprite.getVelocity().getY() + change.getY() * VELOCITY_FACTOR);

	}

}
