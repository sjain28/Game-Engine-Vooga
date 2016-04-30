package tools.interfaces;

import java.util.Map;

import javafx.scene.media.MediaPlayer;

/**
 * IGameSound interface that provides public API methods that are used to play
 * different sounds such as background music and sound effects
 * 
 * @author Hunter Lee
 *
 */
public interface IVoogaGameSound {

	/**
	 * Returns all keys with their sounds
	 * 
	 * @return
	 */
	Map<String, MediaPlayer> getMediaMap();

	/**
	 * Play the sound mapped to a key name
	 * 
	 * @param soundname
	 */
	void play(String soundname);

	/**
	 * Play background music: constant sound
	 */
	void playBGM();

	/**
	 * Stop background, restart will play from start
	 */
	void stopBGM();

	/**
	 * Pause background music, restart plays from pause point
	 */
	void pauseBGM();

}
