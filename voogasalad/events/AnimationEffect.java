package events;

import player.leveldatamanager.ILevelData;

public class AnimationEffect extends SpriteEffect {
	
	private AnimationEvent myAnimationEvent;
	private String myAnimationName;
	private Double myDuration;
	private Boolean reverse;

	public AnimationEffect(String animationEvent, Double duration, Boolean reverse, String spriteID, VoogaEvent voogaEvent) {
		super(voogaEvent);
		myAnimationName = animationEvent;
		setSpriteID(spriteID);
		setNeedsSprites(false);
		myDuration = duration;
		this.reverse = reverse;
	}
	public AnimationEffect(String animationEvent, Double duration, Boolean reverse, String archetype, Boolean needsSprites, VoogaEvent voogaEvent) {
		super(voogaEvent);
		myAnimationName = animationEvent;
		setMyArchetype(archetype);
		setNeedsSprites(needsSprites);
		myDuration = duration;
		this.reverse = reverse;
	}
	public AnimationEffect(String animationEvent, Double duration, Boolean reverse, VoogaEvent voogaEvent) {
		super(voogaEvent);
		myAnimationName = animationEvent;
		setNeedsSprites(true);
		myDuration = duration;
		this.reverse = reverse;
	}

	@Override
	public void execute(ILevelData data) {
		setSprites(data);
		//myAnimationEvent = data.getAnimationEvent(myAnimationName)
		myAnimationEvent.addSpritesFromCause(getSprites());
		myAnimationEvent.setDuration(myDuration);
		myAnimationEvent.setReverse(reverse);
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
