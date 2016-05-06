package stats.database;

import java.util.Date;

import tools.VoogaBoolean;
import tools.VoogaDate;
import tools.VoogaNumber;

public class PlaySession extends VoogaSession{
	public static final String IN_ACTION = "in_action";
	public static final String DATE_PLAYED = "date_played";
	public static final String PLAY_DURATION = "play_duration";
	public static final String SCORE = "score";
	public static final String LEVEL_REACHED = "level reached";
	/**
	 * Play session constructor
	 * @param dateplayed
	 */
	public PlaySession(Date dateplayed){
		super();
		super.startSession();
		setProperty(DATE_PLAYED, new VoogaDate(dateplayed));
		setProperty(IN_ACTION, new VoogaBoolean(true));
	}
	/**
	 * Sets the duration property for a play session
	 */
	protected void setDurationProperty(double gap) {
		setProperty(PLAY_DURATION, new VoogaNumber(gap));		
	}
	/**
	 * Ends the session given a score and level reached
	 * @param score
	 * @param levelreached
	 */
	public void endSession(VoogaNumber score, VoogaNumber levelreached) {
		setProperty(PlaySession.IN_ACTION, new VoogaBoolean(false));
		setProperty(PlaySession.SCORE, score);
		setProperty(PlaySession.LEVEL_REACHED, levelreached);
	}
}
