package events;

import java.lang.reflect.Method;
import java.util.List;

import auxiliary.VoogaException;
import gameengine.Sprite;
import physics.StandardPhysics;
import player.leveldatamanager.LevelData;
import tools.interfaces.VoogaData;

public class PhysicsEffect extends SpriteEffect {
	private StandardPhysics myPhysicsEngine = new StandardPhysics();
	
	//TODO: WHY SHOULD THE EVENT BE HOLDING THE PHYSICS ENGINE AND NOT THE PHYSICS EFFECT??
	//^^moved it to be in here
	public PhysicsEffect(String spriteID, String method, Double parameter, VoogaEvent event) {
		super(method, parameter, event);
		setSpriteID(spriteID);
		setNeedsSprites(false);
	}

	public PhysicsEffect(String archetype, Boolean needsSprites, String method, Double parameter, VoogaEvent event) {
		super(method, parameter, event);
		setMyArchetype(archetype);
		setNeedsSprites(needsSprites);
	}

	public PhysicsEffect(String method, Double parameter, VoogaEvent event) {
		super(method, parameter, event);
		setNeedsSprites(true);
	}

	@Override
	public void execute(LevelData data){
		List<Sprite> sprites = super.getSpritesToBeEffected(data);
		for (Sprite sprite: sprites){
			System.out.println("in sprite loop");
			callEffectMethod(sprite);
		}
		System.out.println("BOUNCING LEGGO");
		//System.out.println(getSprites().size());
	}

	private void callEffectMethod(Sprite sprite){
		try{
			Method physicsMethod = myPhysicsEngine.getClass()
					.getMethod(getMethodString(), new Class[]{Sprite.class, getParameters().getClass()});
			
			System.out.println("METHOD NAME: "+getMethodString());
			
			physicsMethod.invoke(myPhysicsEngine, sprite, getParameters());
		}catch (Exception e){
			e.printStackTrace();
			//throw new VoogaException(String.format(format, args));
		}
	}

}
