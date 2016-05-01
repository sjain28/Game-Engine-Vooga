package tools;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * GameSound that manages all sounds that are to be played in the display
 * Uses a Singleton design pattern
 * 
 * @author Hunter Lee
 *
 */
public class VoogaJukebox {

	private static VoogaJukebox gameSound;

	private Map<String, MediaPlayer> myMediaMap;
	private MediaPlayer myBGM;

	private VoogaJukebox() {
		myMediaMap = new HashMap<>();
	}
	
	public static VoogaJukebox getInstance() {
		if (gameSound == null) {
			gameSound = new VoogaJukebox();
		}
		return gameSound;
	}

	/**
	 * Create media player for initializing sound play
	 * 
	 * @param filename
	 * @return
	 */
	public MediaPlayer createMediaPlayer(String filename) {
		Media media = new Media(new File(filename).toURI().toString());
		return new MediaPlayer(media);
	}
	
	/**
	 * Create media player for initializing sound play
	 * 
	 * @param filename
	 * @return
	 */
	public void setBGM(String filename) {
		myBGM = new MediaPlayer(new Media(new File(filename).toURI().toString()));
	}

	/**
	 * Play a sound bite
	 */
	public void play(String soundname) {
		myMediaMap.get(soundname).play();
	}

	/**
	 * Play a continuous, controllable sound piece
	 */
	public void playBGM() {
		myBGM.play();
	}

	/**
	 * Permanently stop background music, needs restarting
	 */
	public void stopBGM() {
		if (myBGM != null) {
			myBGM.stop();
		}
	}

	/**
	 * Pause background music for later return to point
	 */

	public void pauseBGM() {
		myBGM.pause();
	}

	/**
	 * Getters and setters below
	 */

	public Map<String, MediaPlayer> getMediaMap() {
		return myMediaMap;
	}

}
