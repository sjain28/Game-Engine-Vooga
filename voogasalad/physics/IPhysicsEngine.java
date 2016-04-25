package physics;

import gameengine.Sprite;
import tools.Acceleration;
import tools.Position;
import tools.Velocity;

public interface IPhysicsEngine {


	/**
	 * Set a sprite's position to a new vector
	 * 
	 * @param sprite
	 * @param newPosition
	 */
	void setPosition(Sprite sprite, Position newPosition);


	/**
	 * Set a sprite's velocity to a new vector
	 * 
	 * @param sprite
	 * @param newVelocity
	 */
	void setVelocity(Sprite sprite, Velocity newVelocity);



	/**
	 * Take in a sprite's velocity and add acceleration per frame
	 * 
	 * @param sprite
	 * @param change
	 */
	void accelerate(Sprite sprite, Acceleration change);

	/**
	 * Given a velocity modifying coefficient, reflect a sprite's velocity
	 * direction and set the magnitude to change by a factor of bounceCoefficient
	 * 
	 * @param sprite
	 * @param bounceCoefficient
	 */
	void elasticBounceY(Sprite sprite, Double bounceCoefficient);

	/**
	 * Produce an acceleration vector in the same direction per sprite motion
	 * and call accelerate() to modify its velocity per the coefficient's factor.
	 * 
	 * @param sprite
	 * @param frictionCoefficeint
	 */
	void friction(Sprite sprite, Double frictionCoefficeint);

	/**
	 * Produce an upwards velocity to set the sprite to increase by
	 * 
	 * @param sprite
	 * @param jumpMagnitude
	 */
	void jump(Sprite sprite, Double jumpMagnitude);

	/**
	 * Given a gravitational source (ground), produce an acceleration vector
	 * in the direction of the source to which the sprite can accelerate()
	 * towards
	 * 
	 * @param sprite
	 * @param gravityAcceleration
	 */
	void gravity(Sprite sprite, Double gravityAcceleration);

	void translateX(Sprite sprite, Double change);	
	
	int checkCollisionY(Sprite spriteA, Sprite spriteB);
	
	int checkCollisionX(Sprite spriteA, Sprite spriteB);


}
