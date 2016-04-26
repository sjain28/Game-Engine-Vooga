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
	private static final double COLLISION_CHECK = 1;
	
	
	/**
	 * Checks whether a double is same as numToCompare
	 * 
	 * @param number
	 * @return
	 */
	private boolean isThisNumber(Double number, int numToCompare) {
		return Math.abs(number.doubleValue()) - numToCompare < ERROR;
	}
	
	@Override
	public void translateX(Sprite sprite, Double change) {
		sprite.getVelocity().setX(change * REDUCE_FACTOR);
		//System.out.println("Translate: my velocity is: " + sprite.getVelocity().getX());
	}

	@Override
	public void translateY(Sprite sprite, Double change) {
		sprite.getVelocity().setY(change * REDUCE_FACTOR);
	}

	/**
	 * Elastic bounce in Y direction
	 * 
	 */
	@Override
	public void elasticBounceY(Sprite sprite, Double bounceCoefficient) {
		//System.out.println("Bounce is called");

		// If sprite's velocity is negligible and not 0 (at start, velocity is 0!)
		if (sprite.getVelocity().getY() < 0.1 && sprite.getVelocity().getY() != 0.0) {
			// Set velocity to 0--stop the bounce
			sprite.getVelocity().setY(0.0);
			// Set the Y position to a little higher so there is no collision
			sprite.getPosition().setY(sprite.getPosition().getY() - LIFT);
		}
		else {
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
		if (sprite.getVelocity().getX() > 0) {
			// Set the X position to a little to the left so there is no collision
			sprite.getPosition().setX(sprite.getPosition().getX() - LIFT);
		}
		else {
			// Set the X position to a little to the right so there is no collision
			sprite.getPosition().setX(sprite.getPosition().getX() + LIFT);
		}
		sprite.getVelocity().setX(-1 * sprite.getVelocity().getX() * bounceCoefficient);
	}


	// If sprite's velocity is negligible and not 0 (at start, velocity is 0!)

//if (sprite.getVelocity().getX() < 0.1 && isThisNumber(sprite.getVelocity().getX(), 0)) {
//
//	if (sprite.getVelocity().getX() > 0) {
//		// Set the X position to a little to the left so there is no collision
//		sprite.getPosition().setX(sprite.getPosition().getX() - LIFT);
//	}
//	else {
//		// Set the X position to a little to the right so there is no collision
//		sprite.getPosition().setX(sprite.getPosition().getX() + LIFT);
//	}
	/**
	 * Sets X velocity to zero (stops the movement)
	 * 
	 * @param sprite
	 * @param bounceCoefficient
	 */
	@Override
	public void inelasticBounceX(Sprite sprite, Double bounceCoefficient) {
		if (sprite.getVelocity().getX() > 0) {
			// Set the X position to a little to the left so there is no collision
			sprite.getPosition().setX(sprite.getPosition().getX() - LIFT);
		}
		else {
			// Set the X position to a little to the right so there is no collision
			sprite.getPosition().setX(sprite.getPosition().getX() + LIFT);
		}
		sprite.getVelocity().setX(0);

	}
	
//	if (isThisNumber(sprite.getVelocity().getAngleDegree(), 90) || isThisNumber(sprite.getVelocity().getAngleDegree(), 270)) {
//	}
//	else if (sprite.getVelocity().getAngleDegree() > 180 && sprite.getVelocity().getAngleDegree() < 360) {
//		//sprite.getPosition().setX(sprite.getPosition().getX() + LIFT);
//	}
//	else {
//		//sprite.getPosition().setX(sprite.getPosition().getX() - LIFT);
//	}
	
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

		double diffRight = Math.abs(boundB.getMinX() - boundA.getMaxX());
		double diffLeft = Math.abs(boundA.getMinX() - boundB.getMaxX());
		
		// Collision means they intersect
		if (boundA.intersects(boundB)) {
//			System.out.println("intersecting");
//			System.out.println(checkOverlapY(spriteA,spriteB));
//			System.out.println();
		//	if (checkOverlapY(spriteA, spriteB) != 0 && checkOverlapX(spriteA, spriteB, true) == 0) {
			//if (diffRight < LIFT || diffLeft < LIFT) {
			if (diffRight < COLLISION_CHECK || diffLeft < COLLISION_CHECK) {
				return 1;
			}
			else {
				return 0;	
			}
		}
		return 0;
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

		double diffTop = Math.abs(boundA.getMinY() - boundB.getMaxY());
		double diffBottom = Math.abs(boundB.getMinY() - boundA.getMaxY());

	//	System.out.println("DiffBottom " + diffBottom);
		
		// Collision means they intersect
		if (boundA.intersects(boundB)) {

			if (diffTop < COLLISION_CHECK) {
				return -1;
			}
			else if (diffBottom < COLLISION_CHECK) {
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

	@Override
	public void setPosition(Sprite sprite, Position newPosition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVelocity(Sprite sprite, Velocity newVelocity) {
		// TODO Auto-generated method stub
		
	}

}
