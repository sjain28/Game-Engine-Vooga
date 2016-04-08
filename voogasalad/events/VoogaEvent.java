package events;

import java.util.List;

import Player.leveldatamanager.EngineObjectManager;

public class VoogaEvent {

	private List<Cause> myCauses;
	private List<Effect> myEffects;
	private EngineObjectManager myObjectManager;

	public void addCauses(List<Cause> cause){
		myCauses = cause;
	}
	
	public void addEffects(List<Effect> effect){
		myEffects = effect;
	}
	
	public void setManager(EngineObjectManager manager){
		myObjectManager = manager;
	}
	
	protected EngineObjectManager getManager(){
		return myObjectManager;
	}
	
	public void update(){
		for(Cause c: myCauses){
			if(!c.check()){
				return;
			}
		}
		for(Effect e: myEffects){
			e.execute();
		}
	}
	
	public List<Cause> getCauses(){
		return myCauses;
	}

	public List<Effect> getEffects() {
		return myEffects;
	}

}
