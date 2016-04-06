package player.runner;

import javafx.animation.AnimationTimer;

/**
 * Just for holding maximum step size in case we try other gameLoops
 * Free to change max step size if we choose it matters
 * @author mykuryshev
 *
 */
public abstract class GameLoop extends AnimationTimer{
	private float maximumStep = Float.MAX_VALUE;
	
	public float getMaximumStep(){
		return maximumStep;
	}
	
	public void setMaximumStep(float maximumStep){
		this.maximumStep = maximumStep;	
	}
}
