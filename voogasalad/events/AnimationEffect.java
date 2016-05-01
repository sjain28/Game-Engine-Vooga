package events;

import player.leveldatamanager.ILevelData;
/**
 * Class for effects that execute animations
 * This class delegates execution of animations to other effects, 
 * and is primarily responsible for triggering those animations through their full duration
 * @author Anita Desai
 *
 */
public class AnimationEffect extends SpriteEffect {

	private AnimationEvent myAnimationEvent;
	private String myAnimationName;
	/**
	 * 
	 * @param animationEvent Bundle of animation actions being applied
	 * @param spriteID Sprite that the effect animates
	 * @param voogaEvent Event that this effect belongs to
	 */
	public AnimationEffect(String animationEvent, String spriteID, VoogaEvent voogaEvent) {
		super(voogaEvent);
		myAnimationName = animationEvent;
		setSpriteID(spriteID);
		setNeedsSprites(false);
	}
	/**
	 * Another constructor that initializes Sprites differently
	 * @param animationEvent
	 * @param archetype Archetype of Sprites that this effect animates
	 * @param needsSprites Determines whether effect needs Sprites from its Event
	 * @param voogaEvent
	 */
	public AnimationEffect(String animationEvent, String archetype, Boolean needsSprites, VoogaEvent voogaEvent) {
		super(voogaEvent);
		myAnimationName = animationEvent;
		setMyArchetype(archetype);
		setNeedsSprites(needsSprites);
	}
	/**
	 * Another constructor that initializes Sprites from Event by default
	 * @param animationEvent
	 * @param voogaEvent
	 */
	public AnimationEffect(String animationEvent, VoogaEvent voogaEvent) {
		super(voogaEvent);
		myAnimationName = animationEvent;
		setNeedsSprites(true);
	}
	/**
	 * When triggered, this method creates an event bundling together several animation actions, 
	 * and triggers a wrapper cause that allows those animation actions to execute.
	 */
	@Override
	public void execute(ILevelData data) {
		setSprites(data);
		if (myAnimationEvent == null){
			myAnimationEvent = data.getAnimationFromFactory(myAnimationName);
			data.addEventAndPopulateKeyCombos(myAnimationEvent);
		}
		if (myAnimationEvent.getCauseSprites().size() == 0){
			myAnimationEvent.addSpritesFromCause(getSprites());
		}
		myAnimationEvent.setCauseValue(true);
		clearSprites();
	}

	@Override
	public String toString() {
		String effectString = "Applies " + myAnimationName + " to ";
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
