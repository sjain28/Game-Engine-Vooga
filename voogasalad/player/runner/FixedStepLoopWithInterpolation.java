package player.runner;

import java.util.function.Consumer;

public class FixedStepLoopWithInterpolation extends GameLoop 
{
	// TODO remove comments once we decide on what to keep for AnimationTimer
	// TODO find which constants list, or resource bundle to toss values into
	private final Consumer<Float> UPDATER;
	private final Runnable RENDERER;
	private final Consumer<Float> INTERPOLATER; /* Used for looking at forward frames if time remains in frame calcs*/
	private final Consumer<Integer> FPS_REPORTER; /* If we want to test fps when running the game */

	private static final Integer FRAMES_PER_SECOND = 60;
	private static final float frameTime = 1/FRAMES_PER_SECOND;
	
	public FixedStepLoopWithInterpolation(Consumer<Float> updater,
			Runnable renderer, Consumer<Float> interpolater,
			Consumer<Integer> fpsReporter) 
	{
		this.UPDATER = updater;
		this.RENDERER = renderer;
		this.INTERPOLATER = interpolater;
		this.FPS_REPORTER = fpsReporter;
	}
	
	// TODO any way to initialize and pass to handle?
	private float previousTime = 0;
	private float totalTime = 0;
	
	private float secondsSinceLastFpsUpdate = 0f;
	private int framesSinceLastFpsUpdate = 0;
	
	@Override
	public void handle(long currentTime)
	{
		if (previousTime == 0){
			previousTime = currentTime;
			return;
		}
		
		float secondsElapsed = (currentTime - previousTime) / 1e9f;
		float secondsElapsedCapForFrame = Math.min(secondsElapsed, getMaximumStep());
		totalTime += secondsElapsedCapForFrame;
		previousTime = currentTime;
		
		handleInterpolation(secondsElapsed, totalTime, frameTime);
		
		secondsSinceLastFpsUpdate += secondsElapsed;
		framesSinceLastFpsUpdate++;
		
		if (secondsSinceLastFpsUpdate >= 1f){ /*Set to update once every second */
			updateFpsReporter(framesSinceLastFpsUpdate, secondsSinceLastFpsUpdate);
		}
	}
	
	private void handleInterpolation(float secondsElapsed, float totalTime, float frameTime)
	{
		if (totalTime < frameTime){
			float timeLeftInFrame = totalTime - secondsElapsed;
			float timeLeftFromPastInterpolation = frameTime - timeLeftInFrame;
			float alphaInRemainderOfFrameTime = secondsElapsed/timeLeftFromPastInterpolation;
			INTERPOLATER.accept(alphaInRemainderOfFrameTime);
			return;
		}
		
		while (totalTime >= (2 * frameTime)){
			UPDATER.accept(frameTime);
			totalTime -= frameTime;
		}
		
		RENDERER.run();
		UPDATER.accept(frameTime);
		totalTime -= frameTime;
		float alpha = totalTime / frameTime;
		INTERPOLATER.accept(alpha);
	}
	
	private void updateFpsReporter(float framesSinceLastFpsUpdate, float secondsSinceLastFpsUpdate)
	{
		int fps = Math.round(framesSinceLastFpsUpdate / secondsSinceLastFpsUpdate);
		FPS_REPORTER.accept(fps);
		secondsSinceLastFpsUpdate = 0;
		framesSinceLastFpsUpdate = 0;
	}
	
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
