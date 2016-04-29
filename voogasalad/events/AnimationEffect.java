package events;

import player.leveldatamanager.ILevelData;

public class AnimationEffect extends SpriteEffect {
	
	private AnimationEvent myAnimationEvent;
	private String myAnimationName;

	public AnimationEffect(String animationEvent, String spriteID, VoogaEvent voogaEvent) {
		super(voogaEvent);
		myAnimationName = animationEvent;
		setSpriteID(spriteID);
		setNeedsSprites(false);
	}
	public AnimationEffect(String animationEvent, String archetype, Boolean needsSprites, VoogaEvent voogaEvent) {
		super(voogaEvent);
		myAnimationName = animationEvent;
		setMyArchetype(archetype);
		setNeedsSprites(needsSprites);
	}
	public AnimationEffect(String animationEvent, VoogaEvent voogaEvent) {
		super(voogaEvent);
		myAnimationName = animationEvent;
		setNeedsSprites(true);
	}

	@Override
	public void execute(ILevelData data) {
		setSprites(data);
		// either have sequence or animation
		// check which one you have
		// if myAnimationEvent == null, get a copy using getAnimationEvent() in leveldata
		//Else, just use myAnimationEvent
		
		//myAnimationEvent = data.getAnimationEvent(myAnimationName) --> make this method always return a list<AnimationEvent>
		myAnimationEvent.addSpritesFromCause(getSprites());
		//data.getEventManager().addEvent(myAnimationEvent);
		myAnimationEvent.setCauseValue(true);
	}

	@Override
	public String toString() {
		String effectString = "Applies " + myAnimationEvent.toString() + " to ";
		if (getSpriteID() != null){
			effectString += getSpriteID();
		}
		if (getMyArchetype() != null){
			effectString += getMyArchetype();
		}
		if (getNeedsSprites()){
			effectString += " sprites from cause";
		}		
		return effectString;
	}

}
