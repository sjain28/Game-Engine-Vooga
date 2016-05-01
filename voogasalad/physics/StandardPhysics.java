package physics;

import gameengine.Sprite;
import javafx.geometry.Bounds;
import tools.Acceleration;
import tools.Position;
import tools.Velocity;

/**
 * Standard Physics Module
 * A set of methods that provide physics-related functionalities
 * 
 * @author Hunter, Michael
 *
 */
public class StandardPhysics implements IPhysicsEngine {
	
	public static final double REDUCE_FACTOR = 0.1;
	private static final double VELOCITY_FACTOR = 0.00001;
	private static final double LIFT = 0.1;
	private static final double ERROR = 0.01;
	private static final double JUMP_FACTOR = 0.05;
	private static final double COLLISION_CHECK = 1;
	
	/**
	 * Checks whether a double is same as numToCompare
	 * @param number
	 * @return
	 */
	private boolean isThisNumber(Double number, int numToCompare) {
		return Math.abs(number.doubleValue()) - numToCompare < ERROR;
	}
	
	/**
	 * Gives the sprite a horizontal momentum
	 */
	@Override
	public void translateX(Sprite sprite, Double change) {
		sprite.getVelocity().setX(change * REDUCE_FACTOR);
	}

	/**
	 * Gives the sprite a vertical momentum
	 */
	@Override
	public void translateY(Sprite sprite, Double change) {
		sprite.getVelocity().setY(change * REDUCE_FACTOR);
	}

	/**
	 * Checks if the collision is happening horizontally
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

		if (boundA.intersects(boundB)) {
			if (diffRight < COLLISION_CHECK || diffLeft < COLLISION_CHECK) {
				return 1;
			} else {
				return 0;	
			}
		}
		return 0;
	}
	
	/**
	 * Checks if the collision is happening vertically
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

		if (boundA.intersects(boundB)) {
			if (diffTop < COLLISION_CHECK) {
				return -1;
			} else if (diffBottom < COLLISION_CHECK) {
				return 1;
			} 
			return 0;
		} 
		return 0;
	}
	
	/**
	 * Sets X velocity to zero (stops the movement) when bounceX occurs
	 * @param sprite
	 * @param bounceCoefficient
	 */
	@Override
	public void inelasticBounceX(Sprite sprite, Double bounceCoefficient) {
		if (sprite.getVelocity().getX() > 0) {
			sprite.getPosition().setX(sprite.getPosition().getX() - LIFT);
		}
		else {
			sprite.getPosition().setX(sprite.getPosition().getX() + LIFT);
		}
		sprite.getVelocity().setX(0);

	}
	
	/**
	 * Sets X velocity to the opposite direction with bounceCoefficient applied
	 * @param sprite
	 * @param bounceCoefficient
	 */
	@Override
	public void elasticBounceX(Sprite sprite, Double bounceCoefficient) {
		if (sprite.getVelocity().getX() > 0) {
			sprite.getPosition().setX(sprite.getPosition().getX() - LIFT);
		}
		else {
			sprite.getPosition().setX(sprite.getPosition().getX() + LIFT);
		}
		sprite.getVelocity().setX(-1 * sprite.getVelocity().getX() * bounceCoefficient);
	}
	
	/**
	 * Elastic bounce in Y direction
	 */
	@Override
	public void elasticBounceY(Sprite sprite, Double bounceCoefficient) {
		// If sprite's velocity is negligible and not 0 (at start, velocity is 0!)
		if (sprite.getVelocity().getY() < LIFT && sprite.getVelocity().getY() != 0.0) {
			sprite.getVelocity().setY(0.0);
			sprite.getPosition().setY(sprite.getPosition().getY() - LIFT);
		}
		else {
			sprite.getVelocity().setY(-1 * sprite.getVelocity().getY() * bounceCoefficient);
		}
	}
	
	/**
	 * Applies the effect of gravity by giving it a downward force
	 */
	@Override 
	public void gravity(Sprite sprite, Double gravityMagnitude) {
		Double mass = (((Double) sprite.getPropertiesMap().get("Mass").getValue()).isInfinite()) 
				? 0d : (Double) sprite.getPropertiesMap().get("Mass").getValue();
		
		sprite.getVelocity().setY(sprite.getVelocity().getY() + mass * gravityMagnitude * VELOCITY_FACTOR);
	}
	
	/**
	 * Jump method that allows the main character to gain upward velocity
	 */
	@Override
	public void jump(Sprite sprite, Double jumpMagnitude) {
        if (isThisNumber(sprite.getVelocity().getY(), 0)) {
			sprite.getVelocity().setY(sprite.getVelocity().getY() - jumpMagnitude * JUMP_FACTOR);
		}
	}

	@Override
	public void friction(Sprite sprite, Double frictionCoefficient) {
		Acceleration curr = new Acceleration(sprite.getVelocity().getX(), sprite.getVelocity().getY());
		curr.setX(curr.getX()*frictionCoefficient);
		curr.setY(curr.getY()*frictionCoefficient);
		accelerate(sprite, curr);
	}
	@Override
	public void accelerate(Sprite sprite, Acceleration change) {
		sprite.getVelocity().setX(sprite.getVelocity().getX() + change.getX() * VELOCITY_FACTOR);
		sprite.getVelocity().setY(sprite.getVelocity().getY() + change.getY() * VELOCITY_FACTOR);

	}
	@Override
	public void setPosition(Sprite sprite, Position newPosition) {		
	}

	@Override
	public void setVelocity(Sprite sprite, Velocity newVelocity) {		
	}
}
