package events;

import gameengine.Sprite;
import player.leveldatamanager.ILevelData;

public class RotateEffect extends SpriteEffect {
	
	private Double myRotation;
	
	public RotateEffect(String spriteID, Double rotation, VoogaEvent event) {
		super(event);
		setSpriteID(spriteID);
		setNeedsSprites(false);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(ILevelData data){
		setSprites(data);
		for (Sprite sprite : getSprites()){
			sprite.getImage().setRotate(myRotation);
		}
		clearSprites();
	}
}
