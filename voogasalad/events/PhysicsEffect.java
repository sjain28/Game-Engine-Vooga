package events;

import java.lang.reflect.Method;
import gameengine.Sprite;
import player.leveldatamanager.ILevelData;

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
	public void execute(ILevelData data){
		setSprites(data);
		if (getSprites().size() > 0){
			for (Sprite sprite: getSprites()){
				callEffectMethod(sprite, data);
			}
		}
	}

	private void callEffectMethod(Sprite sprite, ILevelData data){
		try{
			Method physicsMethod = data.getPhysicsEngine().getClass()
					.getMethod(getMethodString(), new Class[]{Sprite.class, getParameters().getClass()});

			physicsMethod.invoke(data.getPhysicsEngine(), sprite, getParameters());
		}catch (Exception e){
			e.printStackTrace();
			//throw new VoogaException(String.format(format, args));
		}
	}

}