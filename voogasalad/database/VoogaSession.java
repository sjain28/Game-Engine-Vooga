package database;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import tools.VoogaNumber;

public abstract class VoogaSession extends VoogaEntry{
	private Instant startInstant;
	private Instant endInstant;
	private double gap;
	public void startSession(){
		startInstant = Instant.now();
	}
	public void endSession(){
		endInstant = Instant.now();
		
		// COMMENT for now 
//		gap = ChronoUnit.MINUTES.between(startInstant,endInstant);
//		System.out.println("The gap here is " + gap);
//		setDurationProperty(gap);
	}
	protected abstract void setDurationProperty(double gap);
}
