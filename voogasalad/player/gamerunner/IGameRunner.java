package player.gamerunner;

import java.io.File;
import java.util.Collections;

import player.gamedisplay.Menuable;

/**
 * Main control interface for player runner
 * 
 * @author mykuryshev, Hunter
 */
public interface IGameRunner extends Menuable {

	void stop();

	void start();
	
	void exit();

	void speedUp();

	void speedDown();

	void mute();

	IGameRunner getSelf();

	void playLevel(String teststring, boolean debugMode);
	
	void playGame(String xmlList);
	
	void replayGame();
	
	void replayLevel();

	// Methods below only called by LevelDataManager

	//List<?> getKeyEvents();

	@Deprecated
	void read(Collections nodesToDisplay);

}
