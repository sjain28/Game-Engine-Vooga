package database;

import java.util.Date;

import tools.VoogaDate;
import tools.VoogaNumber;

public class VoogaPlaySession extends VoogaSession{
	public static final String DATE_PLAYED = "date_played";
	public static final String PLAY_DURATION = "play_duration";
	public static final String SCORE = "score";
	public static final String LEVEL_REACHED = "level reached";
	public VoogaPlaySession(){
		super();
		setProperty(DATE_PLAYED, new VoogaDate(new Date()));
		setProperty(PLAY_DURATION, new VoogaNumber(0d));
		setProperty(SCORE, new VoogaNumber(0d));
		setProperty(LEVEL_REACHED, new VoogaNumber(0d));
	}
	public VoogaPlaySession(Date dateplayed, double playduration, double score, double levelreached){
		super();
		setProperty(DATE_PLAYED, new VoogaDate(dateplayed));
		setProperty(PLAY_DURATION, new VoogaNumber(playduration));
		setProperty(SCORE, new VoogaNumber(score));
		setProperty(LEVEL_REACHED, new VoogaNumber(levelreached));
	}

	@Override
	protected void setDurationProperty(double gap) {
		setProperty(PLAY_DURATION, new VoogaNumber(gap));		
	}
	public String toString(){
		return getProperty(DATE_PLAYED).toString()+" "+getProperty(PLAY_DURATION).toString()+" "+getProperty(SCORE);
	}
}
