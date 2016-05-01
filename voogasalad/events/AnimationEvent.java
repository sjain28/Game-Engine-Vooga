package events;

import gameengine.Sprite;
import player.gamerunner.GameRunner;
import player.leveldatamanager.ILevelData;
/**
 * This class houses animation actions (translations, rotations, etc) and executes them based on the status of a WrapperCause
 * This class is used by AnimationEffect to allow animation actions to run for their complete duration
 * @author Anita Desai, Saumya Jain
 */
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

	/**
	 * Constructor
	 * @param Name of the AnimationEvent
	 * @param duration Length of the animation in seconds
	 */
	public AnimationEvent(String name, Integer duration){
		myName = name;
		myCause = new WrapperCause(this);
		myDuration = (int) (GameRunner.FRAME_RATE)*duration;
		myCounter = 0;
	}
	/**
	 * Executes animation actions if wrappercause is true
	 * Updates its own list of Sprites 
	 * Contains logic to execute for the full length of its duration and then terminate until triggered again
	 */
	@Override
	public void update(ILevelData data){
		if(myCause.getValue()){
			for (Sprite sprite : getCauseSprites()){
				if (!data.containsSprite(sprite.getId())){
					removeSprite(sprite);
				}
			}
			for(Effect e: getEffects()){
				e.execute(data);
			}
			myCounter++;
			if (myCounter > myDuration){
				if(myNextEvent != null){
					myNextEvent.setCauseValue(true);
					myNextEvent.setCauseSprites(getCauseSprites());
					data.addEventAndPopulateKeyCombos(myNextEvent);

				}
				refreshEffects(data);
				getCauseSprites().clear();
				myCounter = 0;
				setCauseValue(false);
			}
		}
	}
	/**
	 * Resets counters for necessary effects and does needed correction for rounding errors
	 */
	private void refreshEffects(ILevelData data) {
		if(myPathEffect != null){
			myPathEffect.setCounter(1);
		}
		if(myImageEffect != null){
			myImageEffect.setCounter(0);
		}
		if(myRotateEffect != null){
			myRotateEffect.rotateCorrection(data);
		}
	}

	/**
	 * Getters and setters below
	 */

	public void addPathEffect(PathEffect pathEffect){
		if (myPathEffect != null){
			getEffects().remove(myPathEffect);
		}
		myPathEffect = pathEffect;
		myPathEffect.createAnimationPoints(myDuration);
		addEffect(myPathEffect);
	}

	public void addRotateEffect(RotateEffect rotateEffect){
		if (myRotateEffect != null){
			getEffects().remove(myRotateEffect);
		}
		myRotateEffect = rotateEffect;
		myRotateEffect.setCycleRotation(myDuration);
		addEffect(myRotateEffect);
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
		addEffect(myScaleEffect);
	}

	protected AnimationEvent clone(){
		AnimationEvent clone = new AnimationEvent(myName, myDuration/(int)GameRunner.FRAME_RATE);
		if(myRotateEffect != null){
			clone.addRotateEffect(getRotateEffect().clone(clone));
		}
		if(myPathEffect != null){
			clone.addPathEffect(getPathEffect().clone(clone));
		}
		if(myImageEffect != null){
			clone.addImageAnimationEffect(getImageAnimationEffect().clone(clone));
		}
		if(myScaleEffect != null){
			clone.addScaleAnimationEffect(getScaleAnimationEffect().clone(clone));
		}
		return clone;
	}

	protected void setCauseValue(Boolean value){
		myCause.setValue(value);
	}

	private ScaleAnimationEffect getScaleAnimationEffect() {
		return myScaleEffect;
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

	protected WrapperCause getCause(){
		return myCause;
	}

	protected Integer getDuration(){
		return myDuration;
	}
}
