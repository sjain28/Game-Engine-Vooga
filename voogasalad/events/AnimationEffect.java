package events;

import player.leveldatamanager.ILevelData;

public class AnimationEffect extends SpriteEffect {
	
	private AnimationEvent myAnimationEvent;
	private Double myDuration;

	public AnimationEffect(AnimationEvent animationEvent, String spriteID, Double duration, VoogaEvent voogaEvent) {
		super(voogaEvent);
		myAnimationEvent = animationEvent;
		setSpriteID(spriteID);
		setNeedsSprites(false);
		myDuration = duration;
	}
	public AnimationEffect(AnimationEvent animationEvent, String archetype, Boolean needsSprites, Double duration, VoogaEvent voogaEvent) {
		super(voogaEvent);
		myAnimationEvent = animationEvent;
		setMyArchetype(archetype);
		setNeedsSprites(needsSprites);
		myDuration = duration;
	}
	public AnimationEffect(AnimationEvent animationEvent, Double duration, VoogaEvent voogaEvent) {
		super(voogaEvent);
		myAnimationEvent = animationEvent;
		setNeedsSprites(true);
		myDuration = duration;
	}

	@Override
	public void execute(ILevelData data) {
		setSprites(data);
		myAnimationEvent.addSpritesFromCause(getSprites());
		myAnimationEvent.setDuration(myDuration);
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
		effectString += " for " + myDuration + " seconds";
		
		return effectString;
	}

}
