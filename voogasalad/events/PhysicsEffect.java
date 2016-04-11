package events;

import java.lang.reflect.Method;
import java.util.List;

import auxiliary.VoogaException;
import gameengine.Sprite;
import tools.interfaces.VoogaData;

public class PhysicsEffect extends SpriteEffect {

	public PhysicsEffect(List<Sprite> sprites, String method, String variable, VoogaEvent event) {
		super(sprites, method, variable, event);
		// TODO Auto-generated constructor stub
	}

	public PhysicsEffect(String archetype, Boolean needsSprites, String method, String variable, VoogaEvent event) {
		super(archetype, needsSprites, method, variable, event);
		// TODO Auto-generated constructor stub
	}

	public PhysicsEffect(String method, String variable, VoogaEvent event) {
		super(method, variable, event);
		// TODO Auto-generated constructor stub
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
			physicsMethod.invoke(sprite, getParameters()[0]);
		}catch (Exception e){
			//throw new VoogaException(String.format(format, args));
		}
	}

}
