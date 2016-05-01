 package stats.database;

//import java.time.Instant;
import java.util.Date;

import tools.VoogaDate;
import tools.VoogaNumber;


/**
 * Authoring Session is a type of VoogaSession. An authoring session is different from a playing session in that 
 * there are extra parameters logged for authoring session, such as sprites added.
 * 
 * @author Joshua Xu, Krista Opsahl-Ong
 *
 */

public class AuthorSession extends VoogaSession{
//	private Instant startInstant;
//	private Instant endInstant;
	public static final String DATE_AUTHORED = "date_authored";
	public static final String AUTHOR_DURATION = "author_duration";
	public static final String SPRITES_ADDED = "sprites_added";
	public AuthorSession(Date dateauthored){
		setProperty(DATE_AUTHORED, new VoogaDate(dateauthored));
		super.startSession();
	}
	@Override
	
	/**
	 * Records how long 
	 * 
	 * @author Joshua Xu, Krista Opsahl-Ong
	 *
	 *@param 
	 */
	protected void setDurationProperty(double gap) {
		setProperty(AUTHOR_DURATION, new VoogaNumber(gap));
	}
}
