 package stats.database;

 import java.util.Date;
import tools.VoogaDate;
import tools.VoogaNumber;
/**
 * Authoring Session Stats Cell
 * Allows the database to save interesting stats
 * about any given authoring session for a user to 
 * analyze
 * @author Krista
 *
 */
public class AuthorSession extends VoogaSession{
	public static final String DATE_AUTHORED = "date_authored";
	public static final String AUTHOR_DURATION = "author_duration";
	public static final String SPRITES_ADDED = "sprites_added";
	/**
	 * Author Session Constructor
	 * @param dateauthored
	 */
	public AuthorSession(Date dateauthored){
		setProperty(DATE_AUTHORED, new VoogaDate(dateauthored));
		super.startSession();
	}
	/**
	 * Sets the duration from the super class
	 */
	@Override
	protected void setDurationProperty(double gap) {
		setProperty(AUTHOR_DURATION, new VoogaNumber(gap));
	}
}
