package database;

import java.util.Date;

import tools.VoogaDate;
import tools.VoogaNumber;

public class VoogaPlaySession extends VoogaEntry{
	public static final String DATE_PLAYED = "date";
	public static final String PLAY_DURATION = "play duration";
	public static final String SCORE = "score";
	public static final String LEVEL_REACHED = "level reached";
	public VoogaPlaySession(Date dateplayed, double playduration, double score, double levelreached){
		super();
		setProperty(DATE_PLAYED, new VoogaDate(dateplayed));
		setProperty(PLAY_DURATION, new VoogaNumber(playduration));
		setProperty(SCORE, new VoogaNumber(score));
		setProperty(LEVEL_REACHED, new VoogaNumber(levelreached));
	}
}
