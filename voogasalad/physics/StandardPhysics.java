package physics;

import gameengine.Sprite;
import tools.Acceleration;
import tools.Position;
import tools.Velocity;


public class StandardPhysics implements IPhysicsEngine {
    // TODO find which methods overlap in code and reduce the bulk from that
    private double frameTime;

    public StandardPhysics () {
        // this.frameTime = (float) FixedStepLoopWithInterpolation.FRAME_TIME;
        frameTime=1.0/61.0;
    }

    @Override
    public void translate (Sprite sprite, Velocity change) {
        double newX = gradualChange(sprite.getPosition().getX(), change.getX());
        double newY = gradualChange(sprite.getPosition().getY(), change.getY());
        Position position = new Position(newX, newY);
        sprite.setPosition(position);
    }

    @Override
    public void setPosition (Sprite sprite, Position newPosition) {
        sprite.setPosition(newPosition);
    }

    @Override
    public void addPosition (Sprite sprite, Position addedPosition) {
        double newX = immediateChange(sprite.getPosition().getX(), addedPosition.getX());
        double newY = immediateChange(sprite.getPosition().getY(), addedPosition.getY());
        Position position = new Position(newX, newY);
        sprite.setPosition(position);
    }

    @Override
    public void accelerate (Sprite sprite, Acceleration change) {
        double newXVel = gradualChange(sprite.getVelocity().getX(), change.getX());
        
        double newYVel = gradualChange(sprite.getVelocity().getY(), change.getY());
        System.out.println("Newvelocity X: "+newXVel);
        System.out.println("Newvelocity Y: "+newYVel);
        Velocity velocity = new Velocity(newXVel, newYVel);
        sprite.setVelocity(velocity);
    }

    @Override
    public void setVelocity (Sprite sprite, Velocity newVelocity) {
        sprite.setVelocity(newVelocity);
    }

    @Override
    public void addVelocity (Sprite sprite, Velocity addedVelocity) {
        double newXVel = immediateChange(sprite.getVelocity().getX(), addedVelocity.getX());
        double newYVel = immediateChange(sprite.getVelocity().getY(), addedVelocity.getY());
        Velocity velocity = new Velocity(newXVel, newYVel);
        sprite.setVelocity(velocity);
    }

    @Override
    public void bounce (Sprite sprite, Double bounceCoefficient) {
        System.out.println("SHIT");
        System.out.println("ARCH IN BOUNCE: " + sprite.getArchetype());
        Velocity curr = sprite.getVelocity();
        System.out.println("Before: "+curr.getY());
        System.out.println("SPrite before" + sprite.getVelocity().getY());
        curr.setX(-1 * curr.getX() * bounceCoefficient);
        curr.setY(-1 * curr.getY() * bounceCoefficient);
        System.out.println("After: " + curr.getY());

        System.out.println(sprite.getVelocity().getY());
        System.out.println(bounceCoefficient);
    }

    @Override
    public void friction (Sprite sprite, Double frictionCoefficient) {
        Acceleration curr =
                new Acceleration(sprite.getVelocity().getX(), sprite.getVelocity().getY());
        curr.setX(curr.getX() * frictionCoefficient);
        curr.setY(curr.getY() * frictionCoefficient);
        accelerate(sprite, curr);
    }

    @Override
    public void jump (Sprite sprite, Double jumpMagnitude) {
        Velocity jumpVelocity = new Velocity(0, jumpMagnitude);
        setVelocity(sprite, jumpVelocity);
        System.out.println("JUMPING");
    }

    @Override
    public void gravity (Sprite sprite, Double gravityMagnitude) {
        System.out.println("gravity magnitude: " + gravityMagnitude);
        Acceleration gravityAcceleration = new Acceleration(0, gravityMagnitude);
        accelerate(sprite, gravityAcceleration);
    }

    @Override
    public void interpolatePositions (float alpha) {
        // TODO for extension, infer future positions by priority of operations
    }

    private double gradualChange (double curr, double change) {
        System.out.println("FrameTime: "+frameTime);
        return curr + (change * frameTime);
    }

    private double immediateChange (double curr, double change) {
        return curr + change;
    }
}
