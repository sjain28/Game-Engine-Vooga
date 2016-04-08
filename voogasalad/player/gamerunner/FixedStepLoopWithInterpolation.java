package Player.gamerunner;

import java.util.function.Consumer;

/**
 * @author Michael Kuryshev
 */

public class FixedStepLoopWithInterpolation extends GameLoop 
{
	private final Consumer<Float> updater;
	private final Runnable renderer;
	private final Consumer<Float> interpolater; 
	private final Consumer<Integer> fps_reporter; 

	private int framesPerSecond = 60;
	private float frameTime = 1/framesPerSecond;
	private static final float NANOSECONDS_PER_SECOND = 1e9f;
	private static final float fps_reporter_UPDATE_RATE = 1f;
	
	/**
	 * Modify the animation timer to fix the frame rate as much as possible when
	 * playing the game. Also, have a displayable frame rate reporter for users
	 * to play with
	 * 
	 * @param updater
	 * @param renderer
	 * @param interpolater
	 * @param fpsReporter
	 */
	public FixedStepLoopWithInterpolation(Consumer<Float> updater,
			Runnable renderer, Consumer<Float> interpolater,
			Consumer<Integer> fpsReporter) 
	{
		this.updater = updater;
		this.renderer = renderer;
		this.interpolater = interpolater;
		this.fps_reporter = fpsReporter;
	}
	
	private float previousTime = 0;
	private float totalTime = 0;
	
	private float secondsSinceLastFpsUpdate = 0f;
	private int framesSinceLastFpsUpdate = 0;
	
	/**
	 * Set handler to start from stops, find time passed in the current frame,
	 * and check for FPS reporting update.
	 */
	@Override
	public void handle(long currentTime)
	{
		if (previousTime == 0){
			previousTime = currentTime;
			return;
		}
		
		float secondsElapsed = (currentTime - previousTime) / NANOSECONDS_PER_SECOND;
		float secondsElapsedCapForFrame = Math.min(secondsElapsed, getMaximumStep());
		totalTime += secondsElapsedCapForFrame;
		previousTime = currentTime;
		
		handleInterpolation(secondsElapsed, totalTime, frameTime);
		
		secondsSinceLastFpsUpdate += secondsElapsed;
		framesSinceLastFpsUpdate++;
		
		if (secondsSinceLastFpsUpdate >= fps_reporter_UPDATE_RATE)
			updateFpsReporter(framesSinceLastFpsUpdate, secondsSinceLastFpsUpdate);
	}
	
	/**
	 * Calculate time remaining in the frame and if it is greater than zero,
	 * begin predictions for the next frame.
	 * 
	 * @param secondsElapsed
	 * @param totalTime
	 * @param frameTime
	 */
	private void handleInterpolation(float secondsElapsed, float totalTime, float frameTime)
	{
		if (totalTime < frameTime){
			float timeLeftInFrame = totalTime - secondsElapsed;
			float timeLeftFromPastInterpolation = frameTime - timeLeftInFrame;
			float alphaInRemainderOfFrameTime = secondsElapsed/timeLeftFromPastInterpolation;
			interpolater.accept(alphaInRemainderOfFrameTime);
			return;
		}
		
		while (totalTime >= (2 * frameTime)){
			updater.accept(frameTime);
			totalTime -= frameTime;
		}
		
		renderer.run();
		updater.accept(frameTime);
		totalTime -= frameTime;
		float alpha = totalTime / frameTime;
		interpolater.accept(alpha);
	}
	
	/**
	 * Update the FPS rate which can viewed in the GUI for testing or play.
	 * 
	 * @param framesSinceLastFpsUpdate
	 * @param secondsSinceLastFpsUpdate
	 */
	private void updateFpsReporter(float framesSinceLastFpsUpdate, float secondsSinceLastFpsUpdate)
	{
		int fps = Math.round(framesSinceLastFpsUpdate / secondsSinceLastFpsUpdate);
		fps_reporter.accept(fps);
		secondsSinceLastFpsUpdate = 0;
		framesSinceLastFpsUpdate = 0;
	}
	
	/**
	 * In addition to standard timer stop, also reset all counts to make the
	 * timer calculations start fresh when play recommences.
	 */
	@Override
	public void stop()
	{
		previousTime = 0;
		totalTime = 0;
		secondsSinceLastFpsUpdate = 0f;
		framesSinceLastFpsUpdate = 0;
		super.stop();
	}
	
	public double getFrameRate(){
		return (double) framesPerSecond;
	}
	
	public void setFrameRate(double newRate){
		framesPerSecond = (int) newRate;
	}
}
