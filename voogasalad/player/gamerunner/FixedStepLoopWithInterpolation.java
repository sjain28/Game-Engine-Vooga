package player.gamerunner;

/**
 * @author Michael Kuryshev
 */
@Deprecated
public class FixedStepLoopWithInterpolation extends GameLoop 
{
	/*
	private final Consumer<Float> updater;
	private final Runnable renderer;
	private final Consumer<Float> interpolater; 
	private final Consumer<Integer> fps_reporter; 
	*/
	public static final float FRAMES_PER_SECOND = 60;
	public static final float FRAME_TIME = 1/FRAMES_PER_SECOND;
	private static final float NANOSECONDS_PER_SECOND = 1e9f;
	private static final float FPS_REPORTER_UPDATE_RATE = 1f;
	
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
	public FixedStepLoopWithInterpolation() 
	{
		/*
		this.updater = updater;
		this.renderer = renderer;
		this.interpolater = interpolater;
		this.fps_reporter = fpsReporter;
		*/
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
		
		//handleInterpolation(secondsElapsed, totalTime);
		
		secondsSinceLastFpsUpdate += secondsElapsed;
		framesSinceLastFpsUpdate++;
		
		if (secondsSinceLastFpsUpdate >= FPS_REPORTER_UPDATE_RATE);
			//updateFpsReporter(framesSinceLastFpsUpdate, secondsSinceLastFpsUpdate);
	}
	
	/**
	 * Calculate time remaining in the frame and if it is greater than zero,
	 * begin predictions for the next frame.
	 * 
	 * @param secondsElapsed
	 * @param totalTime
	 */
	/*
	private void handleInterpolation(float secondsElapsed, float totalTime)
	{
		if (totalTime < FRAME_TIME){
			float timeLeftInFrame = totalTime - secondsElapsed;
			float timeLeftFromPastInterpolation = FRAME_TIME - timeLeftInFrame;
			float alphaInRemainderOfFrameTime = secondsElapsed/timeLeftFromPastInterpolation;
			interpolater.accept(alphaInRemainderOfFrameTime);
			return;
		}
		
		while (totalTime >= (2 * FRAME_TIME)){
			updater.accept(FRAME_TIME);
			totalTime -= FRAME_TIME;
		}
		
		renderer.run();
		updater.accept(FRAME_TIME);
		totalTime -= FRAME_TIME;
		float alpha = totalTime / FRAME_TIME;
		interpolater.accept(alpha);
	}
	*/
	
	/**
	 * Update the FPS rate which can viewed in the GUI for testing or play.
	 * 
	 * @param framesSinceLastFpsUpdate
	 * @param secondsSinceLastFpsUpdate
	 */
	/*
	private void updateFpsReporter(float framesSinceLastFpsUpdate, float secondsSinceLastFpsUpdate)
	{
		int fps = Math.round(framesSinceLastFpsUpdate / secondsSinceLastFpsUpdate);
		fps_reporter.accept(fps);
		secondsSinceLastFpsUpdate = 0;
		framesSinceLastFpsUpdate = 0;
	}
	*/
	
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
}
