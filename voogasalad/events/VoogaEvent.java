package events;

import java.util.ArrayList;
import java.util.List;

import gameengine.Sprite;
import player.leveldatamanager.ILevelData;
/**
 * The class for an Event, which executes Effects once certain Causes are triggered. 
 * @author Saumya Jain, Anita Desai
 *
 */
public class VoogaEvent {

	private List<Cause> myCauses;
	private List<Effect> myEffects;
	private List<Sprite> myCauseSprites;
	/**
	 * Initializes collections.
	 */
	public VoogaEvent(){
		myEffects = new ArrayList<>();
		myCauses = new ArrayList<>();
		setCauseSprites(new ArrayList<>());
	}
	/**
	 * Adds a Cause to this event. 
	 * @param cause An object that extends Cause.
	 */
	protected void addCause(Cause cause){
		getCauses().add(cause);
	}
	/**
	 * Adds an Effect to this event
	 * @param effect An object that extends Effect
	 */
	protected void addEffect(Effect effect){
		getEffects().add(effect);
	}
	
	/**
	 * Checks all of the Causes in the Event. If check() method of all causes returns true, calls execute() on all effects.
	 * @param data Contains leveldata to be passed to causes and effects
	 */
	public void update(ILevelData data){
		getCauseSprites().clear();
		for(Cause c: getCauses()){
			if(!c.check(data)){
				return;
			}
		}
		for(Effect e: getEffects()){
			e.execute(data);
		}
	}
	
	/**
	 * Getters and setters below.
	 * 
	 */
	public List<Cause> getCauses(){
		return myCauses;
	}

	public List<Effect> getEffects() {
		return myEffects;
	}

	protected void addSpritesFromCause(List<Sprite> sprites){
		List<Sprite> newSpriteList = new ArrayList<>();
		newSpriteList.addAll(sprites);
		myCauseSprites.addAll(newSpriteList);
	}

	protected List<Sprite> getCauseSprites() {
		List<Sprite> newSpriteList = new ArrayList<>();
		newSpriteList.addAll(myCauseSprites);
		return newSpriteList;
	}

	protected void setCauseSprites(List<Sprite> myCauseSprites) {
		this.myCauseSprites = myCauseSprites;
	}
}