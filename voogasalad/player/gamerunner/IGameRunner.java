package player.gamerunner;

import player.gamedisplay.Menuable;

/**
 * Main control interface for player runner
 * 
 * @author mykuryshev, Hunter
 */
public interface IGameRunner extends Menuable {
	
	void exit();

	void speedUp();

	void speedDown();
		
	void playGame(String xmlList);
	
	void testLevel(String levelName);
	
	void playNextLevel();
		
	void replayLevel();

	void saveGameProgress(String playerName);

	void takeSnapShot();
}
