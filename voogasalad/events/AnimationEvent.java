package events;

import gameengine.Sprite;
import player.gamerunner.GameRunner;
import player.leveldatamanager.ILevelData;

//Process: 
//VoogaEvent has some outside Cause paired to an AnimationEffect
//AnimationEffect has an AnimationEvent in it
//AnimationEvent has a WrapperCause and RorateEffects/PathEffects
//Outside cause triggers animationeffect. animationeffect sets WrapperCause() to true
//As long as wrappercause is true, path/rotation effects execute.
//Once duration of the path/rotation effects is over, wrappercause gets set back to false. 


public class AnimationEvent extends VoogaEvent {

	private WrapperCause myCause;
	private AnimationEvent myNextEvent;
	private PathEffect myPathEffect;
	private RotateEffect myRotateEffect;
	private ImageAnimationEffect myImageEffect;
	private ScaleAnimationEffect myScaleEffect;
	private String myName;
	private Integer myCounter;

	private Integer myDuration;

	public AnimationEvent(String name, Integer duration){
		myName = name;
		myCause = new WrapperCause(this);
		myDuration = (int) GameRunner.FRAME_RATE * duration;
		myCounter = 0;
	}

	@Override
	public void update(ILevelData data){
		for (Sprite sprite : getCauseSprites()){
			if (!data.containsSprite(sprite.getId())){
				removeSprite(sprite);
			}
		}
		if(myCause.getValue()){
			for(Effect e: getEffects()){
				e.execute(data);
			}
			myCounter++;
			if (myCounter > myDuration){
				if(myNextEvent != null)
					myNextEvent.setCauseValue(true);

				setCauseValue(false);
				myCounter = 0;
			}
		}
	}
	protected WrapperCause getCause(){
		return myCause;
	}

	protected Integer getDuration(){
		return myDuration;
	}
	private void checkSprites(){
		for (Sprite sprite : getCauseSprites()){

		}
	}
	protected void setCauseValue(Boolean value){
		myCause.setValue(value);
	}
	public void addPathEffect(PathEffect pathEffect){
		if (myPathEffect != null){
			getEffects().remove(myPathEffect);
		}
		myPathEffect = pathEffect;
		myPathEffect.createAnimationPoints(myDuration);
		addEffect(pathEffect);
	}

	public void addRotateEffect(RotateEffect rotateEffect){
		if (myRotateEffect != null){
			getEffects().remove(myRotateEffect);
		}
		myRotateEffect = rotateEffect;
		myRotateEffect.setCycleRotation(myDuration);
		addEffect(rotateEffect);
	}

	public void addImageAnimationEffect(ImageAnimationEffect imageEffect){
		if (myImageEffect != null){
			getEffects().remove(myImageEffect);
		}
		myImageEffect = imageEffect;
		myImageEffect.setCycleTime(myDuration);
		addEffect(myImageEffect);
	}

	public void addScaleAnimationEffect(ScaleAnimationEffect scaleEffect){
		if (myScaleEffect != null){
			getEffects().remove(myScaleEffect);
		}
		myScaleEffect = scaleEffect;
		addEffect(myImageEffect);
	}
	
	protected AnimationEvent clone(){
		AnimationEvent clone = new AnimationEvent(myName, myDuration);
		clone.addRotateEffect(getRotateEffect().clone(clone));
		clone.addPathEffect(getPathEffect().clone(clone));
		clone.addImageAnimationEffect(getImageAnimationEffect().clone(clone));
		return clone;
	}
	protected void removeSprite(Sprite sprite){
		getCauseSprites().remove(sprite);
	}
	protected ImageAnimationEffect getImageAnimationEffect() {
		return myImageEffect;
	}
	protected RotateEffect getRotateEffect(){
		return myRotateEffect;
	}
	protected PathEffect getPathEffect(){
		return myPathEffect;
	}
	public String getName(){
		return myName;
	}
	public void setNextEvent(AnimationEvent event){
		myNextEvent = event;
	}
	protected Integer getCounter(){
		return myCounter;
	}
}
