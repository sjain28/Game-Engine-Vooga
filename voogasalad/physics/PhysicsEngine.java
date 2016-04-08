package physics;

import tools.Acceleration;
import tools.Position;
import tools.Velocity;
import tools.interfaces.VoogaData;

public interface PhysicsEngine {

	// TODO decide on methods for the physics engine, here are a few ideas to
	// start
	void translate(VoogaData sprite, Velocity change);
	
	void setPosition(VoogaData sprite, Position newPosition);
	
	void addPosition(VoogaData sprite, Position addedPosition);
	
	void setVelocity(VoogaData sprite, Velocity newVelocity);
	
	void addVelocity(VoogaData sprite, Velocity addedVelocity);
	
	void accelerate(VoogaData sprite, Acceleration change);
	
	void bounce(VoogaData sprite, double bounceCoefficient);
	
	void friction(VoogaData sprite, double frictionCoefficeint);
	
	void jump(VoogaData sprite, double jumpMagnitude);
	
	void interpolatePositions(float alpha);	
}
