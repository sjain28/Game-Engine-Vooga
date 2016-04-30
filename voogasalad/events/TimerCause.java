package events;

import player.leveldatamanager.ILevelData;
import resources.VoogaBundles;
/**
 * This class triggers events periodically at a fixed time interval.
 * @author Saumya Jain
 */
public class TimerCause extends Cause {

	private Double myStartTime;
	private Double myInterval;
	/**
	 * 
	 * @param startTime Time in seconds to first trigger cause
	 * @param interval Interval in seconds between executions of event
	 * @param event Event that this cause triggers
	 */
	public TimerCause(Double startTime, Double interval, VoogaEvent event){
		super(event);
		myStartTime = startTime;
		myInterval = interval;
	}
	/**
	 * Checks whether game time is past start time, and returns true periodically based on the interval
	 */
	@Override
	public boolean check(ILevelData data) {
		Double time = (Double) data.getGlobalVar(VoogaBundles.defaultglobalvars.getProperty("Time")).getValue();
		if(time >= myStartTime){
			myStartTime += myInterval;
			return true;
		}
		return false;
	}
	
	public String toString(){
		return "Every " + myInterval + " seconds after " + myStartTime + " seconds";
	}

}
