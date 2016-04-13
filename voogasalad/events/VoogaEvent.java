package events;

import java.util.ArrayList;
import java.util.List;
import player.leveldatamanager.ObjectManager;
import gameengine.Sprite;
import physics.StandardPhysics;

public class VoogaEvent {

	private List<Cause> myCauses;
	private List<Effect> myEffects;
	private ObjectManager myEngineManager;
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

	public void setManager(ObjectManager manager){
		myEngineManager = manager;
	}

	protected ObjectManager getManager(){
		return myEngineManager;
	}

	public void update(){
		myCauseSprites.clear();
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
	public StandardPhysics getPhysicsEngine(){
		return myPhysicsEngine;
	}
}