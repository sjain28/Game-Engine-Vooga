package player.gamerunner;

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

	void speedUp();

	void speedDown();

	void mute();

	IGameRunner getSelf();

	void playLevel(String teststring);

	// Methods below only called by LevelDataManager

	//List<?> getKeyEvents();

	void clearKeyEvents();

	@Deprecated
	void read(Collections nodesToDisplay);


}
