package events;

public class AnimationEvent extends VoogaEvent {

	private Double myDuration;
	private WrapperCause myCause;
	private PathEffect myPathEffect;
	private RotateEffect myRotateEffect;
	private String myName;

	public AnimationEvent() {
		
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
}
