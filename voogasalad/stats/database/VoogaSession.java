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
	 * Starts session
	 */
	public void startSession(){
		startInstant = Instant.now();
	}
	/**
	 * Ends the sessions
	 */
	public void endSession(){
		endInstant = Instant.now();
		gap = ChronoUnit.MINUTES.between(startInstant,endInstant);
		setDurationProperty(gap);
	}
	/**
	 * Sets the duration property
	 * @param gap
	 */
	protected abstract void setDurationProperty(double gap);
}
