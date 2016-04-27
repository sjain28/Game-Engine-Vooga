package events;

import gameengine.Sprite;
import player.leveldatamanager.ILevelData;

public class RotateEffect extends SpriteEffect {
	
	private Double myRotation;
	private Double myCycleRotation;
	
	public RotateEffect(Double rotation, VoogaEvent event) {
		super(event);
		setNeedsSprites(true);
	}
	
	protected void setCycleRotation(Double duration){
		myCycleRotation = myRotation/duration;
	}

	@Override
	public void execute(ILevelData data){
		setSprites(data);
		for (Sprite sprite : getSprites()){
			sprite.getImage().setRotate(myCycleRotation);
		}
		clearSprites();
	}
}
