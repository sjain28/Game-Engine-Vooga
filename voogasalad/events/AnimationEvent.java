package events;

//Process: 
//VoogaEvent has some outside Cause paired to an AnimationEffect
//AnimationEffect has an AnimationEvent in it
//AnimationEvent has a WrapperCause and RorateEffects/PathEffects
//Outside cause triggers animationeffect. animationeffect sets WrapperCause() to true
//As long as wrappercause is true, path/rotation effects execute.
//Once duration of the path/rotation effects is over, wrappercause gets set back to false. 


public class AnimationEvent extends VoogaEvent {

	private WrapperCause myCause;
	
	private PathEffect myPathEffect;
	private RotateEffect myRotateEffect;
	private String myName;
	
	private Double myDuration;
	private Boolean reverse;
	
	public AnimationEvent(String name){
		myName = name;
		myCause = new WrapperCause(this);
	}
	
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
	protected void setReverse(Boolean reverse){
		this.reverse = reverse;
		if (reverse){
			//path needs to get reversed and appended
			//rotation needs to get reversed and appended
		}
	}
	public void addPathEffect(PathEffect pathEffect){
		if (myPathEffect != null){
			getEffects().remove(myPathEffect);
		}
		myPathEffect = pathEffect;
		addEffect(pathEffect);
	}
	public void addRotateEffect(RotateEffect rotateEffect){
		if (myRotateEffect != null){
			getEffects().remove(myRotateEffect);
		}
		myRotateEffect = rotateEffect;
		addEffect(rotateEffect);
	}
	public RotateEffect getRotateEffect(){
		return myRotateEffect;
	}
	public PathEffect getPathEffect(){
		return myPathEffect;
	}
	public String getName(){
		return myName;
	}
}
