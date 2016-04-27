package events;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import player.leveldatamanager.ILevelData;

public class SoundEffect extends Effect {
	
	private String mySound;
	private Boolean soundAdded;

	public SoundEffect(String sound, VoogaEvent voogaEvent) {
		super(voogaEvent);
		mySound = sound;
		soundAdded = false;
	}

	@Override
	public void execute(ILevelData data) {
		if (!soundAdded){
//			MediaPlayer player = new MediaPlayer(new Media(mySound));
//			player.play();
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
