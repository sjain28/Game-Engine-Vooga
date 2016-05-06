package events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gameengine.Sprite;
import player.leveldatamanager.ILevelData;
import tools.Position;

public class SpawnEffect extends Effect {

	private String myArchetype;
	private Position myPosition;
	private String myTargetID;
	private List<Sprite> myNewSprites;
	private Boolean needsSprites;

	public SpawnEffect(String archetype, Double xPos, Double yPos, VoogaEvent voogaEvent) {
		super(voogaEvent);
		myArchetype = archetype;
		setMyPosition(new Position(xPos, yPos));
	}
	
	public SpawnEffect(String archetype, String targetID, Double xPos, Double yPos, VoogaEvent voogaEvent) {
		super(voogaEvent);
		myArchetype = archetype;
		this.needsSprites = false;
		setMyTargetID(targetID);
		setMyPosition(new Position(xPos, yPos));
	}

	public SpawnEffect(String archetype, String targetID, Boolean needsSprites, VoogaEvent voogaEvent) {
		super(voogaEvent);
		myArchetype = archetype;
		setMyTargetID(targetID);
		this.needsSprites = needsSprites;
		setMyPosition(new Position(0, 0));
	}
	public SpawnEffect(String archetype, String targetID, Boolean needsSprites, Double xPos, Double yPos, VoogaEvent voogaEvent) {
		super(voogaEvent);
		myArchetype = archetype;
		this.needsSprites = needsSprites;
		setMyTargetID(targetID);
		setMyPosition(new Position(xPos, yPos));
	}

	@Override
	public void execute(ILevelData data) {
		List<Sprite> targetSprites = new ArrayList<>();
		List<Sprite> newSprites = new ArrayList<>();
		if(myTargetID.matches("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}")){
			targetSprites.add(data.getSpriteByID(myTargetID));
		}else{
			targetSprites.addAll(data.getSpritesByArch(myTargetID));
			if(needsSprites){
				for(int i = 0; i < targetSprites.size(); i++)
				if(!getEvent().getCauseSprites().contains(targetSprites.get(i))){
					targetSprites.remove(targetSprites.get(i));
				}
			}
		}
		if (getMyTargetID() == null){
			Sprite newSprite = data.addSprite(myArchetype);
			newSprite.setPosition(new Position(getMyPosition().getX(), getMyPosition().getY()));
			newSprites.add(newSprite);
		}
		if (getMyTargetID() != null){
			for(Sprite targetSprite : targetSprites){
				Sprite newSprite = data.addSprite(myArchetype);
				newSprite.setPosition(new Position(getMyPosition().getX(), getMyPosition().getY()));
				newSprite.getPosition().addVector(targetSprite.getPosition());
				newSprites.add(newSprite);
			}
		}
		setNewSprites(newSprites);
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
	protected List<Sprite> getNewSprites() {
		return myNewSprites;
	}
	protected void setNewSprites(List<Sprite> myNewSprites) {
		this.myNewSprites = myNewSprites;
	}

}
