package events;

import java.util.List;

import gameengine.SpriteManager;

public abstract class VoogaEvent {

	private List<Cause> myCauses;
	private List<Effect> myEffects;
	private SpriteManager mySpriteManager;
	
	public VoogaEvent(List<Cause> cause, List<Effect> effect) {
		myCauses = cause;
		myEffects = effect;
		
		for(Effect e: myEffects){
			e.setEvent(this);
		}
	}
	
	
	
	public void setManager(SpriteManager manager){
		mySpriteManager = manager;
	}
	
	protected SpriteManager getManager(){
		return mySpriteManager;
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
