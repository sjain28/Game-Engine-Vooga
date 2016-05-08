// This entire file is part of my masterpiece.
// Michael Kuryshev (myk3)
/**
 * Minor other edits were made (the frame rate is finally 60 fps and large games run without lag!!!
 * but I can't play other people's games when pulling from the master branch, so...), but beyond those
 * minor edits in this commit, my coding masterpiece IS THIS CLASS.
 * 
 * Choosing this class to fix up was an easy decision as my vision of it was taken away
 * and modified in a way that ultimately hurt our team, so making edits to clean up and fix
 * this code felt very good to me. Unfortunately, while choosing this class was easy, bringing
 * the class back to the form I liked did not entirely go my way. I made a class called GeneralOperations
 * which was meant to handle all changes to sprites that physics classes would ultimately call, but there were to 
 * many dependencies in sprite and authoring to make that possible within my time frame. That all said, 
 * I do believe this piece of code is certainly improved to a good state now and am happy to present it
 * as my coding masterpiece. 
 * 
 * Regarding changes, first and foremost I removed the seemingly arbitrary constants for velocity
 * and jump factor and replaced them with the frame rate. From this, any changes to the frame rate 
 * would equally impact all physics operations and leave game players unaffected by those changes 
 * (except for the actual frame rate change). 
 * Further, if-trees were cleaned up to remove redundant logic and one method even became entirely 
 * redundant from this rework (stopBounceX(), now deprecated). This simply shortened up methods to make the physics
 * operations as simple as possible. In addition to simplifying the logic, I also clarified the names 
 * of a few of the methods now that I understand how the reworkers intended them to be.
 * 
 * In terms of why I believe this code is good on a scale beyond just a few method if-trees a names, 
 * I believe this physics module can be extended or replaced very easily for other users.
 * Any class implementing the IPhysicsEngine interface is set to create methods of its own choosing
 * that users in authoring can then easily select from to create whackier or better versions versions
 * of this current engine. Beyond replacability, coders can add newer, more complex methods to individual 
 * classes implementing the interface and through that, can combine the base methods being overridden to
 * create much more complicated physics effects, but most importantly, because, the interface sets up users to 
 * have all of the methods to handle games and basic simulations, the physics engine can be kept simple
 * knowing that users can create the more complex physics methods in the authoring environment itself. As
 * a result, we are safe in leaving a user with just motion, jumping, collision checking, and bouncing 
 * mechanics on a basic level. Ultimately, while the number of methods in this physics module are limited,
 * we understood and reworked them  knowing that the final product itself would let users create abstractions
 * out of these methods to create their own more complicated results.
 * 
 * Looking back at my original game physics operations, I can say that this has come a long way.
 * While the first game was limited to a single set of physics operations under strict values...
 * this implementation sets users up to...
 * * easily add new sets of physics classes implementing IPhysicsEngine (such as reverse physics)
 * * handle different frame rates all modified from a single location, no back channel dependencies
 * * have the base tools for physics operations, if they want something more complex, they can just call a combination of what is here
 * * no hard code for minimal 'forgot to change' bugs and no purely duplicated code. The two lines starting 
 * 	 the each of the check collision methods are the same but the way they are used is different and just
 *   prevent overly long lines from preventing user readability.
 * * In general, I believe this code follows the SOLID principle which we discussed a few weeks ago.
 * 
 * Two last warnings! I've pulled the recent master push Hunter made which had the games and a few final 
 * edits the team made in final debugging, but I can no longer run past games... I doubt you'll really
 * grade the design of this masterpiece based on running games, but if you do want to test the physics
 * or similar such and such, then you may have to make your own game. Sorry about that.
 * Also, if the old games do work, then understand that now that all operations run off of the normal frame rate,
 * the rates at which operations happen will be messed up from what they were.
 * 
 * That all said, this is my masterpiece and more specifically, this is my last sentence written 
 * for this class, so all that is left to say is thank you.
 */
package physics;

import player.gamerunner.GameRunner;
import gameengine.Sprite;
import javafx.geometry.Bounds;
import tools.Acceleration;
import tools.Position;
import tools.Velocity;

/**
 * Standard Physics Module
 * A set of methods that provide physics-related functionalities for
 * a game that seeks to have more 'realistic' 2D physics.
 * 
 * @author Hunter, Michael
 *
 */
public class StandardPhysics implements IPhysicsEngine {
	
	private static final double COLLISION_SHIFT = 0.1;
	private static final double ERROR_MARGIN = 0.01;
	private static final double COLLISION_CHECK = 1;
	
	private static final double FRAMES_PER_SECOND = GameRunner.FRAME_RATE;
	private static final double FRAME_RATE = 1/FRAMES_PER_SECOND;
	
	/**
	 * Checks whether a double is same as numToCompare
	 * @param number
	 * @return
	 */
	private boolean isCloseEnoughTo(Double number, int numToCompare) {
		return Math.abs(number.doubleValue()) - numToCompare < ERROR_MARGIN;
	}
	
	/**
	 * Gives the sprite a horizontal momentum
	 */
	@Override
	public void translateX(Sprite sprite, Double change) {
		sprite.getVelocity().setX(change * FRAME_RATE);
	}

	/**
	 * Gives the sprite a vertical momentum
	 */
	@Override
	public void translateY(Sprite sprite, Double change) {
		sprite.getVelocity().setY(change * FRAME_RATE);
	}

	/**
	 * Checks if the collision is happening horizontally
	 * Does not return a boolean because vertical collisions are called 
	 * similarily in other methods and if users so choose to have different 
	 * effects on each side of collision versus not colliding, we are left with 
	 * 3 types which leaves us with a ternary operator or more safely, using ints.
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

		if (boundA.intersects(boundB) && (diffRight < COLLISION_CHECK || diffLeft < COLLISION_CHECK)) {
				return 1;
		}
		return 0;
	}
	
	/**
	 * Checks if the collision is happening vertically
	 * Return differently for above/below collisions in case
	 * users want to have different effects for each type
	 * We only need to check one side to know if the other was
	 * collided with though.
	 * @param spriteA
	 * @param spriteB
	 * @return -1 if A is below B, 1 if A is above B, and 0 if no collision
	 */
	@Override
	public int checkCollisionY(Sprite spriteA, Sprite spriteB) {
		Bounds boundA = spriteA.getImage().getBoundsInParent();
		Bounds boundB = spriteB.getImage().getBoundsInParent();
		double diffTop = Math.abs(boundA.getMinY() - boundB.getMaxY());

		if (!boundA.intersects(boundB)) {
			return 0;
		}
		
		if (diffTop < COLLISION_CHECK) {
			return -1;
		} else {
			return 1;
		} 
	}
	
	/**
	 * Sets X velocity to the opposite direction with bounceCoefficient applied
	 * or stop motion if colliding at close to zero speed
	 * @param sprite
	 * @param bounceCoefficient
	 */
	@Override
	public void elasticBounceX(Sprite sprite, Double bounceCoefficient) {
		if (isCloseEnoughTo(sprite.getVelocity().getX(), 0)) {
			//if any going from left, bounce back a git right and vice-versa
			if (sprite.getVelocity().getX() > 0) {
				sprite.getPosition().setX(sprite.getPosition().getX() - COLLISION_SHIFT);
			} else {
				sprite.getPosition().setX(sprite.getPosition().getX() + COLLISION_SHIFT);
			}
			sprite.getVelocity().setX(0.0);
		} 
		sprite.getVelocity().setX(-1 * sprite.getVelocity().getX() * bounceCoefficient);
	}
	
	/**
	 * Elastic bounce in Y direction
	 */
	@Override
	public void elasticBounceY(Sprite sprite, Double bounceCoefficient) {
		if (isCloseEnoughTo(sprite.getVelocity().getY(), 0)) {
			sprite.getVelocity().setY(0.0);
			sprite.getPosition().setY(sprite.getPosition().getY() - COLLISION_SHIFT);
		} else {
			sprite.getVelocity().setY(-1 * sprite.getVelocity().getY() * bounceCoefficient);
		}
	}
	
	/**
	 * Applies the effect of gravity by giving it a downward acceleration
	 */
	@Override 
	public void gravity(Sprite sprite, Double gravityMagnitude) {		
		sprite.getVelocity().setY(sprite.getVelocity().getY() + gravityMagnitude * FRAME_RATE);
	}
	
	/**
	 * Jump method that allows the main character to gain upward velocity
	 * if they are not already moving vertically
	 * False jump possible if not colliding with other objects still...
	 */
	@Override
	public void jump(Sprite sprite, Double jumpMagnitude) {
        if (isCloseEnoughTo(sprite.getVelocity().getY(), 0)) {
			sprite.getVelocity().setY(-jumpMagnitude * FRAME_RATE);
		}
	}

	@Override
	@Deprecated
	public void friction(Sprite sprite, Double frictionCoefficient) {
		Acceleration curr = new Acceleration(sprite.getVelocity().getX(), sprite.getVelocity().getY());
		curr.setX(curr.getX()*frictionCoefficient);
		curr.setY(curr.getY()*frictionCoefficient);
		accelerate(sprite, curr);
	}
	
	@Override
	@Deprecated
	public void accelerate(Sprite sprite, Acceleration change) {
		sprite.getVelocity().setX(sprite.getVelocity().getX() + change.getX() * FRAME_RATE);
		sprite.getVelocity().setY(sprite.getVelocity().getY() + change.getY() * FRAME_RATE);
	}
	
	@Override
	@Deprecated
	public void setPosition(Sprite sprite, Position newPosition) {	
		sprite.setPosition(newPosition);
	}

	@Override
	@Deprecated
	public void setVelocity(Sprite sprite, Velocity newVelocity) {		
		sprite.setVelocity(newVelocity);
	}
	
	/**
	 * Sets X velocity to zero (stops the movement) when bounceX occurs
	 * @param sprite
	 * @param bounceCoefficient
	 */
	@Override
	@Deprecated
	public void stopBounceX(Sprite sprite, Double bounceCoefficient) {
		if (sprite.getVelocity().getX() > 0) {
			sprite.getPosition().setX(sprite.getPosition().getX() - COLLISION_SHIFT);
		} else {
			sprite.getPosition().setX(sprite.getPosition().getX() + COLLISION_SHIFT);
		}
		sprite.getVelocity().setX(0);
	}
}
