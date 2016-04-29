package events;

import java.util.ArrayList;
import java.util.List;

import player.leveldatamanager.ILevelData;
import gameengine.Sprite;

public class VoogaEvent {

	private List<Cause> myCauses;
	private List<Effect> myEffects;
	private List<Sprite> myCauseSprites;

	public VoogaEvent(){
		setCauses(new ArrayList<>());
		setEffects(new ArrayList<>());
		setCauseSprites(new ArrayList<>());
	}

	public void addCause(Cause cause){
		getCauses().add(cause);
	}

	public void addEffect(Effect effect){
		getEffects().add(effect);
	}
	
	/**
	 * Runs through all of the causes held in the event and checks them. If the causes evaluate to true, executes all of the
	 * events according to their execute method.
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

	public List<Cause> getCauses(){
		return myCauses;
	}

	public List<Effect> getEffects() {
		return myEffects;
	}

	public void addSpritesFromCause(List<Sprite> sprites){
		getCauseSprites().addAll(sprites);
	}

	public List<Sprite> getCauseSprites() {
		return myCauseSprites;
	}

	public void setCauseSprites(List<Sprite> myCauseSprites) {
		this.myCauseSprites = myCauseSprites;
	}

	public void setCauses(List<Cause> myCauses) {
		this.myCauses = myCauses;
	}

	public void setEffects(List<Effect> myEffects) {
		this.myEffects = myEffects;
	}
}