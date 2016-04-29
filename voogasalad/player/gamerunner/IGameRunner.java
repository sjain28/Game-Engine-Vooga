package player.gamerunner;

import javafx.animation.Timeline;
import player.gamedisplay.Menuable;

/**
 * Main control interface for player runner
 * 
 * @author Hunter, Michael
 */
public interface IGameRunner extends Menuable {
	
	void exit();
	
	Timeline getTimeline();

	void speedUp();

	void speedDown();
		
	void playGame(String xmlList);
	
	void testLevel(String levelName);
	
	void playNextLevel();
		
	void replayLevel();

	void saveGameProgress(String playerName);

	void takeSnapShot();

	void startVideoCapture();

	void endVideoCapture();
}
