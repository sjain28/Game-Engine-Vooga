package events;

import player.leveldatamanager.ILevelData;
import tools.Velocity;

public class ProjectileEffect extends SpawnEffect {

	private Velocity myVelocity;
	private String myShooterID;
	private Double myVelocityScale;

	public ProjectileEffect(String archetype, Double x, Double y, Double xVelocity, Double yVelocity, VoogaEvent voogaEvent) {
		super(archetype, x, y, voogaEvent);
		myVelocity = new Velocity(xVelocity, yVelocity);
	}
	public ProjectileEffect(String archetype, String shooterID, Double x, Double y, Double xVelocity, Double yVelocity, VoogaEvent voogaEvent) {
		super(archetype, shooterID, x, y, voogaEvent);
		myShooterID = shooterID;
		myVelocity = new Velocity(xVelocity, yVelocity);
	}

	public ProjectileEffect(String archetype, String shooterID, Double x, Double y, Double velocityScaleFactor, VoogaEvent voogaEvent) {
		super(archetype, shooterID, x, y, voogaEvent);
		myShooterID = shooterID;
		myVelocityScale = velocityScaleFactor;
	}


	@Override
	public void execute(ILevelData data){
	
		if (myVelocityScale != null){
			myVelocity = new Velocity(data.getSpriteByID(myShooterID).getVelocity().getX()*myVelocityScale, 
					data.getSpriteByID(myShooterID).getVelocity().getY()*myVelocityScale);
		}
		
		super.execute(data);
		getNewSprite().setVelocity(new Velocity(myVelocity.getX(), myVelocity.getY()));

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
