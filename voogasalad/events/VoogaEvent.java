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
		myCauses = new ArrayList<>();
		myEffects = new ArrayList<>();
		myCauseSprites = new ArrayList<>();
	}

	public void addCause(Cause cause){
		myCauses.add(cause);
	}

	public void addEffect(Effect effect){
		myEffects.add(effect);
	}
	
	/**
	 * Runs through all of the causes held in the event and checks them. If the causes evaluate to true, executes all of the
	 * events according to their execute method.
	 */
	public void update(ILevelData data){
		myCauseSprites.clear();
		for(Cause c: myCauses){
			if(!c.check(data)){
				return;
			}
		}

		for(Effect e: myEffects){
			e.execute(data);
			System.out.println("Effect executing");
		}
	}

	public List<Cause> getCauses(){
		return myCauses;
	}

	public List<Effect> getEffects() {
		return myEffects;
	}

	public void addSpritesFromCause(List<Sprite> sprites){
		myCauseSprites.addAll(sprites);
	}

	public List<Sprite> getSpritesFromCauses(){
		return myCauseSprites;
	}
}