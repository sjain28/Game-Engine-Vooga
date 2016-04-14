package events;

import java.lang.reflect.Method;
import java.util.List;

import auxiliary.VoogaException;
import gameengine.Sprite;
import tools.interfaces.VoogaData;

public class PhysicsEffect extends SpriteEffect {

	public PhysicsEffect(String spriteID, String method, Double parameter, VoogaEvent event) {
		super(method, parameter, event);
		getMySprites().add(getEvent().getManager().getSprite(spriteID));
	}

	public PhysicsEffect(String archetype, Boolean needsSprites, String method, Double parameter, VoogaEvent event) {
		super(method, parameter, event);
		setMyArchetype(archetype);
		setNeedsSprites(needsSprites);
	}

	public PhysicsEffect(String method, Double parameter, VoogaEvent event) {
		super(method, parameter, event);
	}

	
	@Override
	public void execute(){
		setSprites();
		for (Sprite sprite: getSprites()){
			callEffectMethod(sprite);
		}
	}

	private void callEffectMethod(Sprite sprite){
		Class physicsClass = getEvent().getPhysicsEngine().getClass();
		Method[] methods = physicsClass.getMethods();
		try{
			Method physicsMethod = getMethodfromString(methods, getMethodString());
			Class[] parameterType = physicsMethod.getParameterTypes();
			//physicsMethod.invoke(sprite, getParameters()[0]);
		}catch (Exception e){
			//throw new VoogaException(String.format(format, args));
		}
	}

}
