package events;

import java.util.ArrayList;
import java.util.List;

import gameengine.EngineManager;
import gameengine.Sprite;

public class VoogaEvent {

	private List<Cause> myCauses;
	private List<Effect> myEffects;
	private EngineManager myEngineManager;
	private List<Sprite> myCauseSprites = new ArrayList<>();

	public void addCauses(List<Cause> cause){
		myCauses = cause;
	}
	
	public void addEffects(List<Effect> effect){
		myEffects = effect;
	}
	
	public void setManager(EngineManager manager){
		myEngineManager = manager;
	}
	
	protected EngineManager getManager(){
		return myEngineManager;
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
	
	public void addSpritesFromCause(List<Sprite> sprites){
		myCauseSprites.addAll(sprites);
	}
	
	public List<Sprite> getSpritesFromCauses(){
		return myCauseSprites;
	}

}
