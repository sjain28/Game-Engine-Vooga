package events;

import java.lang.reflect.Method;
import java.util.List;

import auxiliary.VoogaException;
import gameengine.Sprite;
import tools.interfaces.VoogaData;

public class PhysicsEffect extends SpriteEffect {

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
	public void execute(){
		setSprites();
		for (Sprite sprite: getSprites()){
			System.out.println("in sprite loop");
			callEffectMethod(sprite);
		}
		System.out.println("BOUNCING LEGGO");
		System.out.println(getSprites().size());
	}

	private void callEffectMethod(Sprite sprite){
		try{
			Method physicsMethod = getEvent().getPhysicsEngine().getClass()
					.getMethod(getMethodString(), new Class[]{Sprite.class, getParameters().getClass()});
			
			System.out.println("METHOD NAME: "+getMethodString());
			
			physicsMethod.invoke(getEvent().getPhysicsEngine(), sprite, getParameters());
		}catch (Exception e){
			e.printStackTrace();
			//throw new VoogaException(String.format(format, args));
		}
	}

}
