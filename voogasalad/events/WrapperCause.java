package events;

import player.leveldatamanager.ILevelData;

public class WrapperCause extends Cause {
	
	private Boolean myValue;
	private Double myCounter;

	public WrapperCause(AnimationEvent voogaEvent) {
		super(voogaEvent);
	}

	@Override
	public boolean check(ILevelData data) {
		myCounter++;
		if (myCounter >= ((AnimationEvent)getEvent()).getDuration()){
			setValue(false);
		}
		return myValue;
	}
	protected void setValue(Boolean value){
		myValue = value;
	}
	
	protected boolean getValue(){
		return myValue;
	}

}
