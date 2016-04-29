package stats.database;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import tools.VoogaNumber;

public abstract class VoogaSession extends CellEntry{
	private Instant startInstant;
	private Instant endInstant;
	private double gap;
	public void startSession(){
		startInstant = Instant.now();
	}
	public void endSession(){
		endInstant = Instant.now();
		gap = ChronoUnit.MINUTES.between(startInstant,endInstant);
		setDurationProperty(gap);
	}
	protected abstract void setDurationProperty(double gap);
}
