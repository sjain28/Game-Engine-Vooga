/**
 * 
 */
package physics;

import gameengine.Sprite;

/**
 * Basic physics engine
 * 
 * @author Hunter Lee
 *
 */
@Deprecated
public class BasicPhysics implements IPhysics {

	/**
	 * Basic Physics in a single time step
	 * 
	 */
	
	/**
	 * Translates X position by amount
	 * 
	 * @param sprite
	 * @param amount
	 */
	public void translateX(Sprite sprite, Double amount) {
		sprite.getPosition().addX(amount);
	}
	
	/**
	 * Translates Y position by amount
	 * 
	 * @param sprite
	 * @param amount
	 */
	public void translateY(Sprite sprite, Double amount) {
		sprite.getPosition().addY(amount);

	}
	
	/**
	 * Increase X velocity by amount
	 * 
	 * @param sprite
	 * @param amount
	 */
	public void accelerateX(Sprite sprite, Double amount) {
		sprite.getVelocity().addX(amount);

	}
	
	/**
	 * Increase Y velocity by amount
	 * 
	 * @param sprite
	 * @param amount
	 */
	public void accelerateY(Sprite sprite, Double amount) {
		sprite.getVelocity().addY(amount);

	}
	
	/**
	 * Complicated Physics involving multiple time steps
	 * 
	 */
	
	/**
	 * Jump: add magnitude to the current velocity
	 * 
	 * @param sprite
	 * @param magnitude
	 */
	public void jump(Sprite sprite, Double magnitude) {
		accelerateY(sprite, magnitude);
	}	
	
	/**
	 * Gravity: add negative magnitude to the current velocity
	 * 
	 * @param sprite
	 * @param gravitationalConstant
	 */
	public void gravity(Sprite sprite, Double gravitationalConstant) {
		accelerateY(sprite, -1 * gravitationalConstant);
	}
	
	/**
	 * Currently wrong
	 * 
	 * @param sprite
	 * @param bounceCoefficient
	 */
	public void bounce(Sprite sprite, Double bounceCoefficient) {
		sprite.getVelocity().setX(-1 * bounceCoefficient * sprite.getVelocity().getX());
		sprite.getVelocity().setY(-1 * bounceCoefficient * sprite.getVelocity().getY());
	}

}
