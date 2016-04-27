package physics;

import gameengine.Sprite;
import tools.Acceleration;
import tools.Position;
import tools.Velocity;

public interface IPhysicsEngine {

	void translateX(Sprite sprite, Double change);	
	
	void translateY(Sprite sprite, Double change);
	
	int checkCollisionX(Sprite spriteA, Sprite spriteB);

	int checkCollisionY(Sprite spriteA, Sprite spriteB);
	
	void inelasticBounceX(Sprite sprite, Double bounceCoefficient);
	
	void elasticBounceX(Sprite sprite, Double bounceCoefficient);
	
	/**
	 * Given a velocity modifying coefficient, reflect a sprite's velocity
	 * direction and set the magnitude to change by a factor of bounceCoefficient
	 * 
	 * @param sprite
	 * @param bounceCoefficient
	 */
	void elasticBounceY(Sprite sprite, Double bounceCoefficient);
	
	/**
	 * Given a gravitational source (ground), produce an acceleration vector
	 * in the direction of the source to which the sprite can accelerate()
	 * towards
	 * 
	 * @param sprite
	 * @param gravityAcceleration
	 */
	void gravity(Sprite sprite, Double gravityAcceleration);
	
	/**
	 * Produce an upwards velocity to set the sprite to increase by
	 * 
	 * @param sprite
	 * @param jumpMagnitude
	 */
	void jump(Sprite sprite, Double jumpMagnitude);

	/**
	 * Set a sprite's position to a new vector
	 * 
	 * @param sprite
	 * @param newPosition
	 */
	@Deprecated
	void setPosition(Sprite sprite, Position newPosition);
	
	/**
	 * Set a sprite's velocity to a new vector
	 * 
	 * @param sprite
	 * @param newVelocity
	 */
	@Deprecated
	void setVelocity(Sprite sprite, Velocity newVelocity);
	
	/**
	 * Take in a sprite's velocity and add acceleration per frame
	 * 
	 * @param sprite
	 * @param change
	 */
	@Deprecated
	void accelerate(Sprite sprite, Acceleration change);
	
	/**
	 * Produce an acceleration vector in the same direction per sprite motion
	 * and call accelerate() to modify its velocity per the coefficient's factor.
	 * 
	 * @param sprite
	 * @param frictionCoefficeint
	 */
	@Deprecated
	void friction(Sprite sprite, Double frictionCoefficeint);
}
