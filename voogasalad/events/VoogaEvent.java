package events;

import java.util.List;
import gameengine.SpriteManager;

public class VoogaEvent {

	private List<Cause> myCauses;
	private List<Effect> myEffects;
	private SpriteManager mySpriteManager;

	public void addCauses(List<Cause> cause){
		myCauses = cause;
	}
	
	public void addEffects(List<Effect> effect){
		myEffects = effect;
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

	public List<Effect> getEffects() {
		return myEffects;
	}

}
