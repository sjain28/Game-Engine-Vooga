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
		if (myCounter >= ((AnimationEvent)getMyEvent()).getDuration()){
			setValue(false);
		}
		return myValue;
		//Questions from Saumya: 
		//Is getDuration() a number of update cycles? If not, then we'll need to scale it for this counter. 
		//should myCounter get set to 0 after myValue gets set to false? 
	}
	protected void setValue(Boolean value){
		myValue = value;
	}

}
