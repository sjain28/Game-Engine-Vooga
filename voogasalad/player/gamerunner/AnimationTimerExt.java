/**
 * 
 */
package player.gamerunner;

import javafx.animation.AnimationTimer;

/**
 * AnimationTimerExt for controlling the speed at which timeline
 * calls handle method
 * 
 * @author Hunter Lee
 *
 */
public abstract class AnimationTimerExt extends AnimationTimer {

	private long sleepNs = 0;

	long prevTime = 0;

	public AnimationTimerExt( long sleepMs) {
		this.sleepNs = sleepMs * 1_000_000;
	}

	@Override
	public void handle(long now) {

		// some delay
		if ((now - prevTime) < sleepNs) {
			return;
		}

		prevTime = now;

		handle();
	}

	public abstract void handle();

}
