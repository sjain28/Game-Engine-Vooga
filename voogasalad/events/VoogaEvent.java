package events;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import Player.leveldatamanager.EngineObjectManager;
=======
import gameengine.Sprite;
import physics.StandardPhysics;
import player.leveldatamanager.EngineObjectManager;
>>>>>>> master

public class VoogaEvent {

	//TODO: Either make physics static, put framerate inside of physics, get using method, etc.

	private double FrameRate = 1/60;
	private List<Cause> myCauses;
	private List<Effect> myEffects;
	private EngineObjectManager myEngineManager;
	private List<Sprite> myCauseSprites;
	private StandardPhysics myPhysicsEngine = new StandardPhysics(FrameRate);

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

	public void setManager(EngineObjectManager manager){
		myEngineManager = manager;
	}

	protected EngineObjectManager getManager(){
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