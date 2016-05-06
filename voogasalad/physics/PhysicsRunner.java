/**
 * This is entire file is part of my masterpiece.
 * @author HarryGuo
 * 
 * I decided to make these changes because there were some code smells 
 * in this class: unused code and repeat code. I fixed these smells along 
 * with changing the interface to match the changes in this class and 
 * vice versa. I also decided to put the following variables
 * into a resource file because I think something that can be extended on in
 * the future is allowing the user to define the type of physics they want.
 * For example, if they wanted a "moon-like" physics rather than this "Standard 
 * Physics" they could just load that resource file with the following parameters 
 * and the rest of the game would work accordingly. I renamed this class Physics
 * Runner for that reason and created an enum class Physics Type to help determine
 * the type, and then they can pick accordingly. I renamed this class to be Physics 
 * Runner just 
 * 
 */

package physics;

import gameengine.Sprite;
import javafx.geometry.Bounds;

/**
 * Standard Physics Module
 * A set of methods that provide physics-related functionalities
 * 
 * @author Hunter, Michael
 *
 */
public class PhysicsRunner implements IPhysicsEngine {
	
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
		bounceHelper(sprite);
		sprite.getVelocity().setX(0);

	}
	
	/**
	 * Sets X velocity to the opposite direction with bounceCoefficient applied
	 * @param sprite
	 * @param bounceCoefficient
	 */
	@Override
	public void elasticBounceX(Sprite sprite, Double bounceCoefficient) {
		bounceHelper(sprite);
		sprite.getVelocity().setX(-1 * sprite.getVelocity().getX() * bounceCoefficient);
	}

	
	private void bounceHelper(Sprite sprite) {
		if (sprite.getVelocity().getX() > 0) {
			sprite.getPosition().setX(sprite.getPosition().getX() - LIFT);
		}
		else {
			sprite.getPosition().setX(sprite.getPosition().getX() + LIFT);
		}
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

}
