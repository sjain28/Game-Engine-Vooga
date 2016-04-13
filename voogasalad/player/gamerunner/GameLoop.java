package player.gamerunner;

import javafx.animation.AnimationTimer;

/**
 * Just for holding maximum step size in case we try other gameLoops
 * such as with variable game loop steps (max computers performance), 
 * or run without interpolating next frames.
 * 
 * @author mykuryshev
 */
@Deprecated
public abstract class GameLoop extends AnimationTimer{
	private float maximumStep = Float.MAX_VALUE;
	private float maximumTime = Float.MAX_VALUE;
	
	public float getMaximumStep(){
		return maximumStep;
	}
	
	public void setMaximumStep(float maximumStep){
		this.maximumStep = maximumStep;	
	}
}
