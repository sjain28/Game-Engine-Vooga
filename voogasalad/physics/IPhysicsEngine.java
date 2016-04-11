package physics;

import gameengine.Sprite;
import tools.Acceleration;
import tools.Position;
import tools.Velocity;
import tools.interfaces.VoogaData;

public interface IPhysicsEngine {

	// TODO decide on methods for the physics engine, here are a few ideas to
	// start
	void translate(Sprite sprite, Velocity change);
	
	void setPosition(Sprite sprite, Position newPosition);
	
	void addPosition(Sprite sprite, Position addedPosition);
	
	void setVelocity(Sprite sprite, Velocity newVelocity);
	
	void addVelocity(Sprite sprite, Velocity addedVelocity);
	
	void accelerate(Sprite sprite, Acceleration change);
	
	void bounce(Sprite sprite, double bounceCoefficient);
	
	void friction(Sprite sprite, double frictionCoefficeint);
	
	void jump(Sprite sprite, double jumpMagnitude);
	
	void gravity(Sprite sprite, double gravityAcceleration);
	
	void interpolatePositions(float alpha);	
}
