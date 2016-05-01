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
		System.out.println("Executing rotate effect");
		System.out.println("my rotation: " + myRotation);
		setSprites(data);
		System.out.println("Num SPrites: " + getSprites().size());
		for (Sprite sprite : getSprites()){
			sprite.getImage().setRotate(myCycleRotation);
			System.out.println("Rotation: " + sprite.getImage().getRotate());
		}
		
		clearSprites();
	}
	protected Double getRotation(){
		return myRotation;
	}
	protected RotateEffect clone(AnimationEvent event){
       return new RotateEffect(myRotation, event);
	}
}
