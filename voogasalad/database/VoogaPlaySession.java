package database;

import java.util.Date;
import tools.VoogaNumber;

public class VoogaPlaySession extends VoogaEntry{
	private Date myDatePlayed;
	public static final String PLAY_DURATION = "play duration";
	public static final String SCORE = "score";
	public static final String LEVEL_REACHED = "level reached";
	public VoogaPlaySession(){
		super();
		myDatePlayed = new Date();
		setProperty(PLAY_DURATION, new VoogaNumber(0d));
		setProperty(SCORE, new VoogaNumber(0d));
		setProperty(LEVEL_REACHED, new VoogaNumber(0d));
	}
	
	public VoogaPlaySession(Date dateplayed, double playduration, double score, double levelreached){
		super();
		myDatePlayed = dateplayed;
		setProperty(PLAY_DURATION, new VoogaNumber(playduration));
		setProperty(SCORE, new VoogaNumber(score));
		setProperty(LEVEL_REACHED, new VoogaNumber(levelreached));
	}
	public Date getDatePlayed(){
	    return myDatePlayed;
	}
}
