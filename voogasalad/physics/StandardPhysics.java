package physics;

import gameengine.Sprite;
import tools.Acceleration;
import tools.Position;
import tools.Velocity;

public class StandardPhysics implements IPhysicsEngine{
	//TODO find which methods overlap in code and reduce the bulk from that
	private double frameTime;
	
	public StandardPhysics(double frameTime){
		this.frameTime = (float) frameTime;
	}
	
	@Override
	public void translate(Sprite sprite, Velocity change) {
		double newX = gradualChange(sprite.getPosition().getX(), change.getX());
		double newY = gradualChange(sprite.getPosition().getY(), change.getY());
		//TODO sprite.setPosition(Position.setXY(newX, newY));
	}

	@Override
	public void setPosition(Sprite sprite, Position newPosition) {
		sprite.setPosition(newPosition);
	}

	@Override
	public void addPosition(Sprite sprite, Position addedPosition) {
		double newX = immediateChange(sprite.getPosition().getX(), addedPosition.getX());
		double newY = immediateChange(sprite.getPosition().getY(), addedPosition.getY());
		//TODO sprite.setPosition(Position.setXY(newX, newY));
	}

	@Override
	public void accelerate(Sprite sprite, Acceleration change) {
		double newXVel = gradualChange(sprite.getVelocity().getX(), change.getX());
		double newYVel = gradualChange(sprite.getVelocity().getY(), change.getY());
		//TODO sprite.setVelocity(Velocty.setXY(newXVel, newYVel));
	}
	
	@Override
	public void setVelocity(Sprite sprite, Velocity newVelocity) {
		sprite.setVelocity(newVelocity);
	}

	@Override
	public void addVelocity(Sprite sprite, Velocity addedVelocity) {
		double newXVel = immediateChange(sprite.getVelocity().getX(), addedVelocity.getX());
		double newYVel = immediateChange(sprite.getVelocity().getY(), addedVelocity.getY());
		//TODO sprite.setVelocity(Velocity.setXY(newXVel, newYVel));
	}

	@Override
	public void bounce(Sprite sprite, double bounceCoefficient) {
		//TODO get direction of sprite direction
		//create velocity to be ratio of velocity in opposite directions
		//set velocity to replace the current
		
		//Velocity curr = sprite.getVelocity();
		//curr.setX(-1*curr.getX()*bounceCoefficient);
		//curr.setX(-1*curr.getY()*bounceCoefficient);
	}

	@Override
	public void friction(Sprite sprite, double frictionCoefficeint) {
		//TODO get direction of sprite motion
		//find effective acceleration in x and y and create accel vector
		//then just accelerate
		
		//Acceleration curr = sprite.getVelocity();
		//curr.setX(curr.getX()*frictionCoefficient);
		//curr.setY(curr.getY()*frictionCoefficient);
		//accelerate(sprite, curr);
	}

	@Override
	public void jump(Sprite sprite, double jumpMagnitude) {
		//TODO handle whether this will be up or down with negative sign
		//TODO by our setup, this may have to be addVelocity instead
		Velocity jumpVelocity = new Velocity(0, jumpMagnitude);
		addVelocity(sprite, jumpVelocity);
		// or .setVelcity(), will have to play with and check
	}

	@Override 
	public void gravity(Sprite sprite, double gravityMagnitude) {
		//TODO if gravity magnitude is given as a negative, will remove neg sign in op
		Acceleration gravityAcceleration = new Acceleration(0, -gravityMagnitude);
		accelerate(sprite, gravityAcceleration);
	}
	
	@Override
	public void interpolatePositions(float alpha) {
		// TODO for extension, infer future positions by priority of operations
		
	}
	
	private double gradualChange(double curr, double change){
		return curr + change*frameTime;
	}
	
	private double immediateChange(double curr, double change){
		return curr + change;
	}
}
