package events;

import player.leveldatamanager.ILevelData;
import resources.VoogaBundles;

public class TimerCause extends Cause {
	
	//Both counted in SECONDS
	private Double myStartTime;
	private Double myInterval;
	
	public TimerCause(Double startTime, Double interval, VoogaEvent event){
		super(event);
		myStartTime = startTime;
		myInterval = interval;
	}

	@Override
	public boolean check(ILevelData data) {
		Double time = (Double) data.getGlobalVar(VoogaBundles.defaultglobalvars.getProperty("Time")).getValue();
		return time >= myStartTime && time%myInterval == 0;
	}

}
