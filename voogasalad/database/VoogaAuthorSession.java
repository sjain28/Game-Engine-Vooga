package database;

import java.time.Instant;
import java.util.Date;

import tools.VoogaDate;
import tools.VoogaNumber;

public class VoogaAuthorSession extends VoogaSession{
	private Instant startInstant;
	private Instant endInstant;
	public static final String DATE_AUTHORED = "date_authored";
	public static final String AUTHOR_DURATION = "author_duration";
	public static final String SPRITES_ADDED = "sprites_added";
	public VoogaAuthorSession(Date dateauthored){
		setProperty(DATE_AUTHORED, new VoogaDate(dateauthored));
		super.startSession();
	}
	@Override
	protected void setDurationProperty(double gap) {
		setProperty(AUTHOR_DURATION, new VoogaNumber(gap));
	}
}
