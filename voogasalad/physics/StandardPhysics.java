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
		//sprite.setPosition(Position.setXY(newX, newY));
	}

	@Override
	public void setPosition(Sprite sprite, Position newPosition) {
		sprite.setPosition(newPosition);
	}

	@Override
	public void addPosition(Sprite sprite, Position addedPosition) {
		double newX = immediateChange(sprite.getPosition().getX(), addedPosition.getX());
		double newY = immediateChange(sprite.getPosition().getY(), addedPosition.getY());
		//sprite.setPosition(Position.setXY(newX, newY));
	}

	@Override
	public void accelerate(Sprite sprite, Acceleration change) {
		double newXVel = gradualChange(sprite.getVelocity().getX(), change.getX());
		double newYVel = gradualChange(sprite.getVelocity().getY(), change.getY());
		//sprite.setVelocity(Velocty.setXY(newXVel, newYVel));
	}
	
	@Override
	public void setVelocity(Sprite sprite, Velocity newVelocity) {
		sprite.setVelocity(newVelocity);
	}

	@Override
	public void addVelocity(Sprite sprite, Velocity addedVelocity) {
		double newXVel = immediateChange(sprite.getVelocity().getX(), addedVelocity.getX());
		double newYVel = immediateChange(sprite.getVelocity().getY(), addedVelocity.getY());
		//sprite.setVelocity(Velocity.setXY(newXVel, newYVel));
	}

	@Override
	public void bounce(Sprite sprite, double bounceCoefficient) {
		//TODO get direction of sprite direction
		//create velocity to be ratio of velocity in opposite directions
		//set velocity to replace the current
	}

	@Override
	public void friction(Sprite sprite, double frictionCoefficeint) {
		//TODO get direction of sprite motion
		//find effective acceleration in x and y and create accel vector
		//then just accelerate
		
	}

	@Override
	public void jump(Sprite sprite, double jumpMagnitude) {
		//TODO handle whether this will be up or down with negative sign
		//TODO by our setup, this may have to be addVelocity instead
		Velocity jumpVelocity = new Velocity(0, jumpMagnitude);
		addVelocity(sprite, jumpVelocity);
	}

	@Override 
	public void gravity(Sprite sprite, double gravityMagnitude) {
		Acceleration gravityAcceleration = new Acceleration(0, gravityMagnitude);
		accelerate(sprite, gravityAcceleration);
	}
	
	@Override
	public void interpolatePositions(float alpha) {
		// TODO Auto-generated method stub
		
	}
	
	private double gradualChange(double curr, double change){
		return curr + change*frameTime;
	}
	
	private double immediateChange(double curr, double change){
		return curr + change;
	}
}
