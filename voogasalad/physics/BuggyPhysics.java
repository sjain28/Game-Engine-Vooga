package physics;

import gameengine.Sprite;
import tools.Acceleration;
import tools.Position;
import tools.Velocity;
import voogasalad.physics.IPhysicsEngine;

/**
 * @author Hunter Lee
 *
 */
public class BuggyPhysics implements IPhysicsEngine {

	/* (non-Javadoc)
	 * @see physics.IPhysicsEngine#setPosition(gameengine.Sprite, tools.Position)
	 */
	@Override
	public void setPosition(Sprite sprite, Position newPosition) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see physics.IPhysicsEngine#setVelocity(gameengine.Sprite, tools.Velocity)
	 */
	@Override
	public void setVelocity(Sprite sprite, Velocity newVelocity) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see physics.IPhysicsEngine#accelerate(gameengine.Sprite, tools.Acceleration)
	 */
	@Override
	public void accelerate(Sprite sprite, Acceleration change) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see physics.IPhysicsEngine#elasticBounceY(gameengine.Sprite, java.lang.Double)
	 */
	@Override
	public void elasticBounceY(Sprite sprite, Double bounceCoefficient) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see physics.IPhysicsEngine#friction(gameengine.Sprite, java.lang.Double)
	 */
	@Override
	public void friction(Sprite sprite, Double frictionCoefficeint) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see physics.IPhysicsEngine#jump(gameengine.Sprite, java.lang.Double)
	 */
	@Override
	public void jump(Sprite sprite, Double jumpMagnitude) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see physics.IPhysicsEngine#gravity(gameengine.Sprite, java.lang.Double)
	 */
	@Override
	public void gravity(Sprite sprite, Double gravityAcceleration) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see physics.IPhysicsEngine#translateX(gameengine.Sprite, java.lang.Double)
	 */
	@Override
	public void translateX(Sprite sprite, Double change) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see physics.IPhysicsEngine#checkCollisionY(gameengine.Sprite, gameengine.Sprite)
	 */
	@Override
	public int checkCollisionY(Sprite spriteA, Sprite spriteB) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see physics.IPhysicsEngine#checkCollisionX(gameengine.Sprite, gameengine.Sprite)
	 */
	@Override
	public int checkCollisionX(Sprite spriteA, Sprite spriteB) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see physics.IPhysicsEngine#inelasticBounceX(gameengine.Sprite, java.lang.Double)
	 */
	@Override
	public void inelasticBounceX(Sprite sprite, Double bounceCoefficient) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see physics.IPhysicsEngine#elasticBounceX(gameengine.Sprite, java.lang.Double)
	 */
	@Override
	public void elasticBounceX(Sprite sprite, Double bounceCoefficient) {
		// TODO Auto-generated method stub

	}

}
