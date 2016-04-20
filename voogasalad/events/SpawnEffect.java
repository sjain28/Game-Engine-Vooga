package events;

import gameengine.Sprite;
import player.leveldatamanager.ILevelData;
import tools.Position;

public class SpawnEffect extends Effect {
	
	private String myArchetype;
	private Position myPosition;
	private String myTargetID;

	public SpawnEffect(String archetype, Double x, Double y, VoogaEvent voogaEvent) {
		super(voogaEvent);
		myArchetype = archetype;
		myPosition = new Position(x, y);
	}
	public SpawnEffect(String archetype, String targetID, VoogaEvent voogaEvent) {
		super(voogaEvent);
		myArchetype = archetype;
		myTargetID = targetID;
	}

	@Override
	public void execute(ILevelData data) {
		if (myTargetID != null){
			myPosition = data.getSpriteByID(myTargetID).getPosition();
		}
		// make new copy of sprite of that archetype
		// set position to x, y

	}

	@Override
	public String toString() {
		String effectString = "Creates new " + myArchetype + " at ";
		
		if (myTargetID != null){
			effectString += myTargetID + "'s position";
		}else{
			effectString += myPosition.getX() + ", " + myPosition.getY();
		}
		return effectString;
	}

}
