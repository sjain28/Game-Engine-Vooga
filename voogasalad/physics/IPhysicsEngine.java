package physics;

import gameengine.Sprite;
import tools.Acceleration;
import tools.Position;
import tools.Velocity;

public interface IPhysicsEngine {

	/**
	 * Take in a sprite's position and add velocity per frame
	 * 
	 * @param sprite
	 * @param change
	 */
	void translate(Sprite sprite, Velocity change);

	/**
	 * Set a sprite's position to a new vector
	 * 
	 * @param sprite
	 * @param newPosition
	 */
	void setPosition(Sprite sprite, Position newPosition);

	/**
	 * Take in a sprite's position and add a position vector directly to it
	 * 
	 * @param sprite
	 * @param addedPosition
	 */
	void addPosition(Sprite sprite, Position addedPosition);

	/**
	 * Set a sprite's velocity to a new vector
	 * 
	 * @param sprite
	 * @param newVelocity
	 */
	void setVelocity(Sprite sprite, Velocity newVelocity);

	/**
	 * Take in a sprite's position and add velocity vector directly to it
	 * 
	 * @param sprite
	 * @param addedVelocity
	 */
	void addVelocity(Sprite sprite, Velocity addedVelocity);

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
	void bounce(Sprite sprite, Double bounceCoefficient);

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

	/**
	 * Given enough time in the frame for physics operations, look at the next frame 
	 * and prepare for operations
	 * 
	 * @param alpha
	 */
	void interpolatePositions(float alpha);	
}
