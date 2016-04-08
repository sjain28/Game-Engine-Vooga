package player.gamerunner;

import java.util.function.Consumer;

import player.gamedisplay.IGameDisplay;
import player.gamedisplay.StandardDisplay;

public class GameRunner implements IGameRunner{
	
	IGameDisplay myDisplay = new StandardDisplay();

	//TODO implement the proper updater, renderer, interpolater, and fpsReporter
	private final Consumer<Float> updater = null;
	private final Runnable renderer = null;
	private final Consumer<Float> interpolater = null; 
	private final Consumer<Integer> fps_reporter = null; 
	GameLoop myGameLoop = new FixedStepLoopWithInterpolation(updater, renderer, interpolater, fps_reporter);


	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void getFrameRate() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setFrameRate(double frameRate) {
		// TODO Auto-generated method stub
		
	}
}
