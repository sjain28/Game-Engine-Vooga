package events;

import gameengine.Sprite;
import player.leveldatamanager.ILevelData;
import tools.Position;

public class SpawnEffect extends Effect {
	
	private String myArchetype;
	private Position myPosition;
	private String myTargetID;
	private Sprite myNewSprite;

	public SpawnEffect(String archetype, Double xPos, Double yPos, VoogaEvent voogaEvent) {
		super(voogaEvent);
		myArchetype = archetype;
		setMyPosition(new Position(xPos, yPos));
	}
	public SpawnEffect(String archetype, String targetID, VoogaEvent voogaEvent) {
		super(voogaEvent);
		myArchetype = archetype;
		setMyTargetID(targetID);
		setMyPosition(new Position(0, 0));
	}
	public SpawnEffect(String archetype, String targetID, Double xPos, Double yPos, VoogaEvent voogaEvent) {
		super(voogaEvent);
		myArchetype = archetype;
		setMyTargetID(targetID);
		setMyPosition(new Position(xPos, yPos));
	}

	@Override
	public void execute(ILevelData data) {
		Sprite newSprite = data.addSprite(myArchetype);
		newSprite.setPosition(new Position(getMyPosition().getX(), getMyPosition().getY()));
		
		if (getMyTargetID() != null){
			newSprite.getPosition().addVector(data.getSpriteByID(getMyTargetID()).getPosition());
		}
		setNewSprite(newSprite);
	}

	@Override
	public String toString() {
		String effectString = "Creates new " + myArchetype + " at ";
		
		if (getMyTargetID() != null){
			effectString += getMyTargetID() + "'s position";
		}else{
			effectString += getMyPosition().getX() + ", " + getMyPosition().getY();
		}
		return effectString;
	}
	
	protected String getArchetype(){
		return myArchetype;
	}
	protected String getMyTargetID() {
		return myTargetID;
	}
	protected void setMyTargetID(String myTargetID) {
		this.myTargetID = myTargetID;
	}
	protected Position getMyPosition() {
		return myPosition;
	}
	protected void setMyPosition(Position myPosition) {
		this.myPosition = myPosition;
	}
	protected Sprite getNewSprite() {
		return myNewSprite;
	}
	protected void setNewSprite(Sprite myNewSprite) {
		this.myNewSprite = myNewSprite;
	}

}
