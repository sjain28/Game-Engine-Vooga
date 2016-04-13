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
	
	// Methods below only called by LevelDataManager
	Collections getKeyEvents();
	
	void clearKeyEvents();
	
	void read(Collections nodesToDisplay);
	
}
