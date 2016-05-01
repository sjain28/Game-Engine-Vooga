 package stats.database;

 import java.util.Date;
import tools.VoogaDate;
import tools.VoogaNumber;

public class AuthorSession extends VoogaSession{
	public static final String DATE_AUTHORED = "date_authored";
	public static final String AUTHOR_DURATION = "author_duration";
	public static final String SPRITES_ADDED = "sprites_added";
	public AuthorSession(Date dateauthored){
		setProperty(DATE_AUTHORED, new VoogaDate(dateauthored));
		super.startSession();
	}
	@Override
	protected void setDurationProperty(double gap) {
		setProperty(AUTHOR_DURATION, new VoogaNumber(gap));
	}
}
