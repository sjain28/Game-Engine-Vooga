package events;

import java.util.List;

import gameengine.GlobalVariableManager;
import gameengine.SpriteManager;

public abstract class Event {

	private List<Cause> myCauses;
	private List<Effect> myEffects;
	private SpriteManager mySpriteManager;
	private GlobalVariableManager myVariableManager;
	
	public Event(List<Cause> cause, List<Effect> effect) {
		myCauses = cause;
		myEffects = effect;
		
		for(Effect e: myEffects){
			e.setEvent(this);
		}
	}

	public void setSpriteManager(SpriteManager manager){
		mySpriteManager = manager;
	}
	
	public void setVariableManager(GlobalVariableManager manager){
		myVariableManager = manager;
	}
	
	protected SpriteManager getSpriteManager(){
		return mySpriteManager;
	}
	
	protected GlobalVariableManager getVariableManager(){
		return myVariableManager;
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

}
