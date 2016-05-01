package stats.database;

import java.time.Instant;
import java.time.temporal.ChronoUnit;


/**
 * Each StatInfo contains a list of authoring sessions and playing sessions. Both of these session types extend
 * this superclass. 
 * 
 * @author Joshua Xu, Krista Opsahl-Ong
 *
 */

public abstract class VoogaSession extends CellEntry{
	private Instant startInstant;
	private Instant endInstant;
	private double gap;
	
	/**
	 * This method should be called when a session is initiated, typically after logging in and beginning to play the game
	 * or authoring a game.
	 */
	
	public void startSession(){
		startInstant = Instant.now();
	}
	
	/**
	 * This method should be called when a session is ended, typically after closing out of the screen, or when a next
	 * action must be performed.
	 */
	
	public void endSession(){
		endInstant = Instant.now();
		gap = ChronoUnit.MINUTES.between(startInstant,endInstant);
		setDurationProperty(gap);
	}
	protected abstract void setDurationProperty(double gap);
}
