package events;

import gameengine.Sprite;
import player.leveldatamanager.ILevelData;

public class RotateEffect extends SpriteEffect {
	
	private Double myRotation;
	private Double myCycleRotation;
	
	public RotateEffect(Double rotation, VoogaEvent event) {
		super(event);
		setNeedsSprites(true);
		myRotation = rotation;
	}
	
	protected void setCycleRotation(Integer duration){
		myCycleRotation = myRotation/duration;
	}

	@Override
	public void execute(ILevelData data){
		setSprites(data);
		for (Sprite sprite : getSprites()){
			sprite.getImage().setRotate(sprite.getImage().getRotate() + myCycleRotation);
		}
		clearSprites();
	}
	protected Double getRotation(){
		return myRotation;
	}
	protected RotateEffect clone(AnimationEvent event){
       return new RotateEffect(myRotation, event);
	}
	protected void rotateCorrection(ILevelData data){
		setSprites(data);
		for (Sprite sprite : getSprites()){
			double offset = sprite.getImage().getRotate() - (myRotation%360);
			sprite.getImage().setRotate(sprite.getImage().getRotate() - offset);
		}
		clearSprites();
	}
}
