/**
 * This is entire file is part of my masterpiece.
 * @author HarryGuo
 * 
 * I decided to make these changes because there were some code smells 
 * with a subclass (Standard Physics). I fixed those smells along 
 * with changing this interface to match the changes in that class and 
 * vice versa.
 * 
 */

package physics;

import gameengine.Sprite;



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

}
