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
public class StandardPhysics implements IPhysicsEngine {
	
	private static final double REDUCE_FACTOR = 0.1;
	private static final double VELOCITY_FACTOR = 0.00001;
	private static final double LIFT = 0.1;
	private static final double ERROR = 0.01;
	private static final double JUMP_FACTOR = 0.05;
	private static final double PADDING = 0.1;
	
	
	/**
	 * Default constructor for Standard Physics module
	 * 
	 */
	public StandardPhysics() {

	}
	
	/**
	 * Checks whether a double is same as numToCompare
	 * 
	 * @param number
	 * @return
	 */
	private boolean isThisNumber(Double number, int numToCompare) {
		return Math.abs(number.doubleValue()) - numToCompare < ERROR;
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
	public void elasticBounceY(Sprite sprite, Double bounceCoefficient) {
		System.out.println("Bounce is called");
		
		// If sprite's velocity is negligible and not 0 (at start, velocity is 0!)
		if (sprite.getVelocity().getY() < 0.1 && sprite.getVelocity().getY() != 0.0) {
			// Set velocity to 0--stop the bounce
			sprite.getVelocity().setY(0.0);
			// Set the Y position to a little higher so there is no collision
			sprite.getPosition().setY(sprite.getPosition().getY() - LIFT);
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
	@Override
	public void elasticBounceX(Sprite sprite, Double bounceCoefficient) {
		sprite.getVelocity().setX(-1 * sprite.getVelocity().getX() * bounceCoefficient);
	}
	
	/**
	 * Sets X velocity to zero (stops the movement)
	 * 
	 * @param sprite
	 * @param bounceCoefficient
	 */
	@Override
	public void inelasticBounceX(Sprite sprite, Double bounceCoefficient) {
		// If velocity is going left
		System.out.println("Velocity angle: " + sprite.getVelocity().getAngleDegree());
		System.out.println("Velocity x: " + sprite.getVelocity().getX() + " Velocity y: " + sprite.getVelocity().getY());
		if (isThisNumber(sprite.getVelocity().getAngleDegree(), 90) || isThisNumber(sprite.getVelocity().getAngleDegree(), 270)) {
		}
		else if (sprite.getVelocity().getAngleDegree() > 180 && sprite.getVelocity().getAngleDegree() < 360) {
			//sprite.getPosition().setX(sprite.getPosition().getX() + LIFT);
		}
		else {
			//sprite.getPosition().setX(sprite.getPosition().getX() - LIFT);
		}
		sprite.getVelocity().setX(0);

	}
	
	/**
	 * Checks if the collision is happening horizontally
	 * 
	 * @param spriteA
	 * @param spriteB
	 * @return 1 if there is a collision, 0 if no collision
	 */
	@Override
	public int checkCollisionX(Sprite spriteA, Sprite spriteB) {
		Bounds boundA = spriteA.getImage().getBoundsInParent();
		Bounds boundB = spriteB.getImage().getBoundsInParent();
//		if (boundA.intersects(boundB)) {
//			if (checkOverlapY(spriteA, spriteB) != 0 && checkOverlapX(spriteA, spriteB, true) == 0) {
//				return 1;
//			}
//		}
//		return 0;
		
		//if (checkCollisionY(spriteA, spriteB) == 0) {
//		System.out.println("Sprite A: " + spriteA.getName() + " and spriteB is: " + spriteB.getName());
//		System.out.println("This is overlap tag: " + checkOverlapY(spriteA, spriteB));
		System.out.println("CheckOverlapX: " + checkOverlapX(spriteA, spriteB, false));
		System.out.println("CheckOverlapY: " + checkOverlapY(spriteA, spriteB));
		
		if (boundA.intersects(boundB)) {
		
		if (checkOverlapY(spriteA, spriteB) != 0) {
			if (checkOverlapX(spriteA, spriteB, true) == 1) {
				return 1;
			}
			else {
				return 0;
			}
		}
		else {
			return 0;
		}
		
		}
		return 0;

		//---------------------
//		Bounds boundA = spriteA.getImage().getBoundsInParent();
//		Bounds boundB = spriteB.getImage().getBoundsInParent();
//        boolean atRightBorder = boundA.getMaxX() >= boundB.getMinX();
//        boolean atLeftBorder = boundA.getMinX() <= boundB.getMaxX();
//        
//        // If Y collision is happening, return false
//        if (checkCollisionY(spriteA, spriteB) == 1 || checkCollisionY(spriteA, spriteB) == -1) {
//        	return 0;
//        }
//        
//        if (atRightBorder || atLeftBorder) {
//        	return 1;
//        }
//        else {
//        	return 0;
//        }
	}
	
	/**
	 * Checks if the collision is happening vertically
	 * 
	 * @param spriteA
	 * @param spriteB
	 * @return -1 if A is below B, 1 if A is above B, and 0 if no collision
	 */
	@Override
	public int checkCollisionY(Sprite spriteA, Sprite spriteB) {
		
		Bounds boundA = spriteA.getImage().getBoundsInParent();
		Bounds boundB = spriteB.getImage().getBoundsInParent();
//		if (boundA.intersects(boundB)) {
//			if (checkOverlapX(spriteA, spriteB) == 1) {
//				if (checkOverlapY(spriteA, spriteB) == )
//			}
//		}
		System.out.println("CheckOverlapX: " + checkOverlapX(spriteA, spriteB, false));
		System.out.println("CheckOverlapY: " + checkOverlapY(spriteA, spriteB));

		//System.out.println("Sprite A: " + spriteA.getName() + " and spriteB is: " + spriteB.getName());
		//if (boundA.intersects(boundB)) {
		
//		if (boundA.intersects(boundB)) {
//			System.out.println("we intersected in checkCollisionY");
//			System.out.println("checkOverlapX value: " + checkOverlapX(spriteA, spriteB, false));
//			System.out.println("checkOverlapY value: " + checkOverlapY(spriteA, spriteB));
//			spriteA.getVelocity().setY(0);
//		}
		
		if (boundA.intersects(boundB)) {
			if (checkOverlapX(spriteA, spriteB, false) == 1) {
				if (checkOverlapY(spriteA, spriteB) == 1) {
					return -1;
				}
				if (checkOverlapY(spriteA, spriteB) == 2) {
					return 1;
				}
				else {
					return 0;
				}
			} else {
				return 0;
			}
		}
		return 0;
//		}
//		return 0;
		
		
//		Bounds boundA = spriteA.getImage().getBoundsInParent();
//		Bounds boundB = spriteB.getImage().getBoundsInParent();
//		
//		// CollisionX and collisionY are checked mutually exclusively
//        boolean atRightBorder = boundA.getMaxX() >= boundB.getMinX();
//        boolean atLeftBorder = boundA.getMinX() <= boundB.getMaxX();
//        // X collision happening--return false
//        if (atRightBorder || atLeftBorder) {
//        	return 0;
//        }
//		
//		//The following assumes JavaFX Pane style coordinates
//		boolean atTopBorder = boundA.getMinY() <= boundB.getMaxY();
//		boolean atBottomBorder = boundA.getMaxY() >= boundB.getMinY();
//
//		//		// The following assumes the Cartesian coordinates
//		//        boolean atTopBorder = boundA.getMaxY() >= boundB.getMinY();
//		//        boolean atBottomBorder = boundA.getMinY() <= boundB.getMaxY();
//
//        if (atTopBorder) {
//        	return 1;
//        }
//        else if (atBottomBorder) {
//        	return -1;
//        }
//        else {
//        	return 0;
//        }
	}

	/**
	 * Check overlap for X
	 * @param spriteA
	 * @param spriteB
	 * @return 0 if there is no overlap, 1 if X overlap, 
	 * -1 if Y overlap at Top (A is below), -2 if Y overlap at Bottom (A is above)
	 */
	private int checkOverlapX(Sprite spriteA, Sprite spriteB, boolean pad) {
		Bounds boundA = spriteA.getImage().getBoundsInParent();
		Bounds boundB = spriteB.getImage().getBoundsInParent();
		boolean atRightBorder;
		boolean atLeftBorder;
		if (pad) {
	        atRightBorder = boundA.getMaxX() >= boundB.getMinX() - PADDING;
	        atLeftBorder = boundA.getMinX() <= boundB.getMaxX() + PADDING;
		}
		else {
	        atRightBorder = boundA.getMaxX() >= boundB.getMinX();
	        atLeftBorder = boundA.getMinX() <= boundB.getMaxX();
		}

        //if(boundA.intersects(boundB)){ 
	        // If A left B right
	        if (boundA.getMinX() <= boundB.getMinX()) {
	        	if (atRightBorder) {
	        		return 1;
	        	}
	        }
	        // If A right B left
	        if (boundA.getMinX() > boundB.getMinX()) {
	        	if (atLeftBorder) {
	        		return 1;
	        	}
	        }
	        return 0;
	}
        //return 0;

//        if (atRightBorder || atLeftBorder) {
//        	return 1;
//        } else {
//        	return 0;
//        }
//	}

	/**
	 * Check overlap for Y
	 * 
	 * @param spriteA
	 * @param spriteB
	 * @return 1 if Y overlap at Top (A is below), 2 if Y overlap at Bottom (A is above)
	 * 0 if no overlap
	 */
	private int checkOverlapY(Sprite spriteA, Sprite spriteB) {
		Bounds boundA = spriteA.getImage().getBoundsInParent();
		Bounds boundB = spriteB.getImage().getBoundsInParent();
		boolean atTopBorder = boundA.getMinY() <= boundB.getMaxY();
		boolean atBottomBorder = boundA.getMaxY() >= boundB.getMinY();
//boundA.getM
//		if (boundA.intersects(boundB)){
		//If A is above B
		//System.out.println(boundA.getMaxX() + " " + boundB.getMinY());
		if (boundA.getMinY() <= boundB.getMinY()) {
			if (atBottomBorder) {
				return 2;
			} 
		}
		// If A is below B..
		if (boundA.getMinY() > boundB.getMinY()) {
			if (atTopBorder) {
				return 1;
			}
		}
		
//		}
		return 0;
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
		if (isThisNumber(sprite.getVelocity().getY(), 0)) {
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
