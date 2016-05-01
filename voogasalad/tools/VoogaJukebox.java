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
public class VoogaJukebox {

	private static VoogaJukebox gameSound;

	private Map<String, MediaPlayer> myMediaMap;
	private MediaPlayer myBGM;

	private VoogaJukebox() {
		myMediaMap = new HashMap<>();
	}
	
	public synchronized static VoogaJukebox getInstance() {
		if(gameSound == null) {
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
		System.out.println("SETTING BGM");
		System.out.println("filename: "+filename);
		myBGM = new MediaPlayer(new Media(new File(filename).toURI().toString()));
		System.out.println("myBGM:"+myBGM);
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
		System.out.println(myBGM);
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
/*
	public void pauseBGM() {
		myBGM.pause();
	}

	/**
	 * Getters and setters below
	 */
/*
	public Map<String, MediaPlayer> getMediaMap() {
		return myMediaMap;
	}
*/
}