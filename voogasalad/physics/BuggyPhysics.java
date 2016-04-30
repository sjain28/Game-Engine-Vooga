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
 * @author  Hunter
 *
 */
public class BuggyPhysics implements IPhysicsEngine {
	
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
	}
	

	public void translateXwithKeyEvent(Sprite sprite, Double change, KeyEvent event) {
		sprite.getVelocity().setX(change * REDUCE_FACTOR);
		if (event.isConsumed()) {
			sprite.getVelocity().setX(0);
		}
	}
	
	public void translateY(Sprite sprite, Double change) {
		sprite.getVelocity().setY(change * REDUCE_FACTOR);
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
		if (sprite.getVelocity().getY() < 0.1 && sprite.getVelocity().getY() != 0.0) {
			sprite.getVelocity().setY(0.0);
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
		if (isThisNumber(sprite.getVelocity().getAngleDegree(), 90) || isThisNumber(sprite.getVelocity().getAngleDegree(), 270)) {
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

		if (boundA.intersects(boundB) && (checkOverlapY(spriteA, spriteB) != 0)) {
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
		
		if (boundA.intersects(boundB)) {
			if (checkOverlapX(spriteA, spriteB, false) == 1) {
				if (checkOverlapY(spriteA, spriteB) == 1) {
					return -1;
				}
				else if (checkOverlapY(spriteA, spriteB) == 2) {
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
	        if (boundA.getMinX() <= boundB.getMinX() && (atRightBorder) ) {
	        	return 1;
	        }
	        if (boundA.getMinX() > boundB.getMinX() && (atLeftBorder)) {
	        		return 1;
	        }
	        return 0;
	}

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

		if (boundA.getMinY() <= boundB.getMinY() && (atBottomBorder)) {
			return 2;
		}

		if (boundA.getMinY() > boundB.getMinY() && (atTopBorder)) {
			return 1;
		}
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
		if (isThisNumber(sprite.getVelocity().getY(), 0)) {
			sprite.getVelocity().setY(sprite.getVelocity().getY() - jumpMagnitude * JUMP_FACTOR);
		}
	}

	/**
	 * Applies the effect of gravity by giving it a downward force
	 * 
	 */
	@Override 
	public void gravity(Sprite sprite, Double gravityMagnitude) {

		Double mass = (((Double) sprite.getPropertiesMap().get("Mass").getValue()).isInfinite()) 
				? 0d : (Double) sprite.getPropertiesMap().get("Mass").getValue();
		
		sprite.getVelocity().setY(sprite.getVelocity().getY() + 
				mass * gravityMagnitude * VELOCITY_FACTOR);
	}

	@Override
	public void accelerate(Sprite sprite, Acceleration change) {
		sprite.getVelocity().setX(sprite.getVelocity().getX() + change.getX() * VELOCITY_FACTOR);
		sprite.getVelocity().setY(sprite.getVelocity().getY() + change.getY() * VELOCITY_FACTOR);

	}

}
