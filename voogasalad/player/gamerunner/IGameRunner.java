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

	void testLevel(String fileName);
	
	void playLevel(String teststring);
	
	void playGame(String xmlList);
	
	void replayGame();
	
	void replayLevel();

	void saveGameProgress(String playerName);
	

	void playNextLevel();

}
