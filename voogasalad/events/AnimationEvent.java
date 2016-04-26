package events;

//Process: 
//VoogaEvent has some outside Cause paired to an AnimationEffect
//AnimationEffect has an AnimationEvent in it
//AnimationEvent has a WrapperCause and RorateEffects/PathEffects
//Outside cause triggers animationeffect. animationeffect sets WrapperCause() to true
//As long as wrappercause is true, path/rotation effects execute.
//Once duration of the path/rotation effects is over, wrappercause gets set back to false. 


public class AnimationEvent extends VoogaEvent {

	private Double myDuration;
	private WrapperCause myCause;
	private PathEffect myPathEffect;
	private RotateEffect myRotateEffect;
	private String myName;
	
	//Got rid of an empty constructor
	
	protected void setDuration(Double duration){
		myDuration = duration;
		myCause = new WrapperCause(this);
		if (myPathEffect != null){
			myPathEffect.createAnimationPoints(duration);
		}
		if (myRotateEffect != null){
			myRotateEffect.setCycleRotation(myDuration);
		}
	}
	
	protected WrapperCause getCause(){
		return myCause;
	}
	
	protected Double getDuration(){
		return myDuration;
	}
	
	protected void setCauseValue(Boolean value){
		myCause.setValue(value);
	}
}
