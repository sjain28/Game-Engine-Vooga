package player.gamerunner;

import java.util.Collections;

/**
 * Main control interface for player runner
 * 
 * @author mykuryshev, Hunter
 */
public interface IGameRunner {

	void stop();

	void start();

	void speedUp();

	void speedDown();

	void mute();

	IGameRunner getSelf();

	void playLevel(String teststring);
	
	void replayLevel();
	
	void advanceToNextLevel();
	
	void replayGame();

	// Methods below only called by LevelDataManager

	//List<?> getKeyEvents();

	void clearKeyEvents();

	@Deprecated
	void read(Collections nodesToDisplay);


}
