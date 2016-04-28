package events;

import java.nio.file.Paths;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import player.leveldatamanager.ILevelData;

public class SoundEffect extends Effect {
	
	private String mySound;
	private Boolean soundAdded;
	private Boolean loop;

	public SoundEffect(String sound, Boolean loop, VoogaEvent voogaEvent) {
		super(voogaEvent);
		mySound = sound;
		soundAdded = false;
		this.loop = loop;
	}

	@Override
	public void execute(ILevelData data) {
		if (!soundAdded){
			MediaPlayer player = new MediaPlayer(new Media(Paths.get(mySound).toUri().toString()));
			player.play();
		// data.getGameSound().createMediaPlayer(mySound);
			soundAdded = true;
		}
		// data.getGameSound().play(mySound);

	}

	@Override
	public String toString() {
		return "Plays sound effect + " + mySound;
	}

}
