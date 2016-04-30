package tools;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * GameSound that manages all sounds that are to be played in the display
 * 
 * @author Hunter Lee
 *
 */
public class VoogaGameSound implements IVoogaGameSound {

	// TODO put string to path resource bundles
	private static final String BGM_PATH = "resources/sound/";
	private static final String BGM = "chanel_show.mp3";

	private Map<String, MediaPlayer> myMediaMap;
	private MediaPlayer myBGM;

	public VoogaGameSound() {
		myMediaMap = new HashMap<>();

		myBGM = createMediaPlayer(BGM);
		myMediaMap.put(BGM, myBGM);
	}

	/**
	 * Create media player for initializing sound play
	 * 
	 * @param filename
	 * @return
	 */
	private MediaPlayer createMediaPlayer(String filename) {
		Media media = new Media(new File(BGM_PATH + filename).toURI()
				.toString());
		return new MediaPlayer(media);
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
		myBGM.stop();
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
