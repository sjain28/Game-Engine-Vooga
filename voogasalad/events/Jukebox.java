package events;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Jukebox {
	
	private Map<String, MediaPlayer> mySounds;
	
	public Jukebox () {
		mySounds = new HashMap<String, MediaPlayer>();
    }

    private static class SingletonHolder {
        private static final Jukebox INSTANCE = new Jukebox();
    }

    public static Jukebox getInstance () {
        return SingletonHolder.INSTANCE;
    }
    
    public void play(String path) {
    	MediaPlayer myPlayer;
    	if(mySounds.containsKey(path)) {
    		myPlayer = mySounds.get(path);
    	} else {
    		myPlayer = new MediaPlayer(new Media(path));
    		mySounds.put(path, myPlayer);
    	}
    	myPlayer.play();
    }

}
