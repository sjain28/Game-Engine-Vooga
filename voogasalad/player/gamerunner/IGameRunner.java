package player.gamerunner;

import javafx.animation.Timeline;
import player.gamedisplay.Menuable;

/**
 * Main control interface for player runner
 * 
 * @author Hunter, Michael
 */
public interface IGameRunner extends Menuable {
	
	Timeline getTimeline();

	void exit();

	void speedUp();

	void speedDown();
		
	void playGame(String xmlList);
	
	void testLevel(String levelName);
	
	void takeSnapShot();

	void finishPlaySession();
}
