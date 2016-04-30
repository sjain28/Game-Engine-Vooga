package events;

import java.nio.file.Paths;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Jukebox {
	
	public Jukebox () {
    }

    private static class SingletonHolder {
        private static final Jukebox INSTANCE = new Jukebox();
    }

    public static Jukebox getInstance () {
        return SingletonHolder.INSTANCE;
    }
    
    public void play(String path, boolean loop) {
    	MediaPlayer myPlayer = new MediaPlayer(new Media(Paths.get(path).toUri().toString()));
    	int cycleCount = loop ? Integer.MAX_VALUE : 1;
    	myPlayer.setCycleCount(cycleCount);
    	myPlayer.play();
    }

}
