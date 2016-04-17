package events;

import java.util.ArrayList;
import java.util.List;

import player.leveldatamanager.LevelData;
import player.leveldatamanager.OUTDATEDObjectManager;
import gameengine.Sprite;
import physics.StandardPhysics;

public class VoogaEvent {

	private List<Cause> myCauses;
	private List<Effect> myEffects;
	//private OUTDATEDObjectManager myEngineManager;
	//private LevelData myData;
	private List<Sprite> myCauseSprites;
	private StandardPhysics myPhysicsEngine = new StandardPhysics();

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
	 * Tells the event the LevelData from which it should get information for 
	 * causes/updates (for sprite information, global variable information, etc.)
	 * 
	 * @param LevelData
	 */
	//public void setAffectableData(LevelData data){
	//	myData = data;
	//}

	//protected OUTDATEDObjectManager getAffectableData(){
	//	return myEngineManager;
	//}

	/**
	 * Runs through all of the causes held in the event and checks them. If the causes evaluate to true, executes all of the
	 * events according to their execute method.
	 */
	public void update(LevelData data){
		myCauseSprites.clear();
		
		for(Cause c: myCauses){
			if(!c.check(data)){
				return;
			}
		}
		for(Effect e: myEffects){
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
		myCauseSprites.addAll(sprites);
	}

	public List<Sprite> getSpritesFromCauses(){
		return myCauseSprites;
	}
	public StandardPhysics getPhysicsEngine(){
		return myPhysicsEngine;
	}
}