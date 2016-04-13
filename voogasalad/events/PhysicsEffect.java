package events;

import java.lang.reflect.Method;
import java.util.List;

import auxiliary.VoogaException;
import gameengine.Sprite;
import tools.interfaces.VoogaData;

public class PhysicsEffect extends SpriteEffect {

	public PhysicsEffect(String spriteID, String method, String variable, VoogaEvent event) {
		super(spriteID, method, variable, event);
	}
	public PhysicsEffect(String spriteID, String method, String variable, Double parameter, VoogaEvent event) {
		super(spriteID, method, variable, parameter, event);
	}
	public PhysicsEffect(String spriteID, String method, String variable, Boolean parameter, VoogaEvent event) {
		super(spriteID, method, variable, parameter, event);
	}

	public PhysicsEffect(String archetype, Boolean needsSprites, String method, String variable, VoogaEvent event) {
		super(archetype, needsSprites, method, variable, event);
	}
	public PhysicsEffect(String archetype, Boolean needsSprites, String method, String variable, Double parameter, VoogaEvent event) {
		super(archetype, needsSprites, method, variable, parameter, event);
	}
	public PhysicsEffect(String archetype, Boolean needsSprites, String method, String variable, Boolean parameter, VoogaEvent event) {
		super(archetype, needsSprites, method, variable, parameter, event);
	}

	public PhysicsEffect(String method, String variable, VoogaEvent event) {
		super(method, variable, event);
	}
	public PhysicsEffect(String method, String variable, Double parameter, VoogaEvent event) {
		super(method, variable, parameter, event);
	}
	public PhysicsEffect(String method, String variable, Boolean parameter, VoogaEvent event) {
		super(method, variable, parameter, event);
	}
	
	@Override
	public void execute(){
		setSprites();
		System.out.println("my sprite lenght in effects: " +getSprites().size());
		for (Sprite sprite: getSprites()){
			System.out.println("Sprite that the effect is checking now: "+sprite);
			callEffectMethod(sprite);
		}
	}

	private void callEffectMethod(Sprite sprite){
		Class physicsClass = getEvent().getPhysicsEngine().getClass();
		Method[] methods = physicsClass.getMethods();
		try{
			System.out.println("calling "+getMethodString()+" method effect");
			//TODO: physicsMethod is returning null
			System.out.println("Sprite id: "+getSprites().get(0).getID());
			Method physicsMethod = getMethodfromString(methods, getMethodString());
			System.out.println("HEREEE"+physicsMethod);

			Class[] parameterType = physicsMethod.getParameterTypes();
			System.out.println("param types: "+parameterType);
			physicsMethod.invoke(sprite, getParameters());
			//physicsMethod.invoke(sprite, getParameters()[0]);
		}catch (Exception e){
			//throw new VoogaException(String.format(format, args));
		}
	}

}
