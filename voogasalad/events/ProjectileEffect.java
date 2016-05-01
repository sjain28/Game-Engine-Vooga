package events;

import physics.StandardPhysics;
import player.leveldatamanager.ILevelData;
import tools.Velocity;

public class ProjectileEffect extends SpawnEffect {

	private Velocity myVelocity;
	private String myShooterID;
	private Double myVelocityScale;

	public ProjectileEffect(String archetype, Double xPos, Double yPos, Double xVelocity, Double yVelocity, VoogaEvent voogaEvent) {
		super(archetype, xPos, yPos, voogaEvent);
		myVelocity = new Velocity(xVelocity, yVelocity);
	}
	public ProjectileEffect(String archetype, String targetID, Double xPos, Double yPos, Double xVelocity, Double yVelocity, VoogaEvent voogaEvent) {
		super(archetype, targetID, xPos, yPos, voogaEvent);
		myShooterID = targetID;
		myVelocity = new Velocity(xVelocity, yVelocity);
	}

	public ProjectileEffect(String archetype, String targetID, Double xPos, Double yPos, Double velocityScaleFactor, VoogaEvent voogaEvent) {
		super(archetype, targetID, xPos, yPos, voogaEvent);
		myShooterID = targetID;
		myVelocityScale = velocityScaleFactor;
	}


	@Override
	public void execute(ILevelData data){
	
		if (myVelocityScale != null){
			myVelocity = new Velocity(data.getSpriteByID(myShooterID).getVelocity().getX()*myVelocityScale, 
					data.getSpriteByID(myShooterID).getVelocity().getY()*myVelocityScale);
		}
		
		super.execute(data);
		getNewSprite().setVelocity(new Velocity(myVelocity.getX()*StandardPhysics.REDUCE_FACTOR, 
				myVelocity.getY()*StandardPhysics.REDUCE_FACTOR));

	}

	@Override
	public String toString(){
		String effectString= "Shoots projectile " + getArchetype() + " from ";
		if (getMyTargetID() != null){
			effectString += getMyTargetID() + " ";
		}else{
			effectString += getMyPosition().getX() + ", " + getMyPosition().getY();
		}
		effectString += " with velocity " + myVelocity.getX() + ", " + myVelocity.getY();

		return effectString;
	}

}
