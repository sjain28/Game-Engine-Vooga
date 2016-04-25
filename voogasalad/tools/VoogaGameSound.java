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
	
	private static final String BGM_PATH = "resources/sound/";
//	private static final String BGM = "energy_flow.mp3";
	private static final String BGM = "chanel_show.mp3";


//	private static final String BGM_PATH = "resources/sound/zelda_theme.mp3";
	//private static final String BGM_PATH = "resources/sound/hypnotize.mp3";
//	private Media myMedia;
//	private MediaPlayer myMediaPlayer;
	private Map<String, MediaPlayer> myMediaMap;
	private MediaPlayer myBGM;
	
	public VoogaGameSound() {
		myMediaMap = new HashMap<>();
		
		myBGM = createMediaPlayer(BGM);
		myMediaMap.put(BGM, myBGM);
	}
	
	private MediaPlayer createMediaPlayer(String filename) {
		Media media = new Media(new File(BGM_PATH + filename).toURI().toString());
		return new MediaPlayer(media);
	}

	/**
	 * @return the myMediaMap
	 */
	public Map<String, MediaPlayer> getMediaMap() {
		return myMediaMap;
	}
	
	public void play(String soundname) {
		myMediaMap.get(soundname).play();
	}
	
	public void playBGM() {
		myBGM.play();
	}
	
	public void stopBGM() {
		myBGM.stop();
	}
	
	public void pauseBGM() {
		myBGM.pause();
	}
	

}
