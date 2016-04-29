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
	public PlaySession(Date dateplayed){
		super();
		super.startSession();
		setProperty(DATE_PLAYED, new VoogaDate(dateplayed));
		setProperty(IN_ACTION, new VoogaBoolean(true));
	}
	@Override
	protected void setDurationProperty(double gap) {
		setProperty(PLAY_DURATION, new VoogaNumber(gap));		
	}
	public String toString(){
		return getProperty(DATE_PLAYED).toString()+" "+getProperty(PLAY_DURATION).toString()+" "+getProperty(SCORE);
	}
	public void endSession(VoogaNumber score, VoogaNumber levelreached) {
		System.out.println("ending sesion");
		setProperty(PlaySession.IN_ACTION, new VoogaBoolean(false));
		setProperty(PlaySession.SCORE, score);
		setProperty(PlaySession.LEVEL_REACHED, levelreached);
	}
}
