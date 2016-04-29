package database;

import java.util.Date;

import tools.VoogaDate;
import tools.VoogaNumber;

public class VoogaAuthorSession extends VoogaEntry{
	public static final String DATE_AUTHORED = "date_authored";
	public static final String AUTHOR_DURATION = "author_duration";
	public static final String SPRITES_ADDED = "sprites_added";
	public VoogaAuthorSession(Date dateauthored, double authorduration, double spritesadded){
		setProperty(DATE_AUTHORED, new VoogaDate(dateauthored));
		setProperty(AUTHOR_DURATION, new VoogaNumber(authorduration));
		setProperty(SPRITES_ADDED, new VoogaNumber(spritesadded));
	}
}
