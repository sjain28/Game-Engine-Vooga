package events;

import player.leveldatamanager.ILevelData;

public class SoundEffect extends Effect {
	
	private String mySound;
	//private Boolean soundAdded;
	private Boolean loop;

	public SoundEffect(String sound, Boolean loop, VoogaEvent voogaEvent) {
		super(voogaEvent);
		mySound = sound;
		//soundAdded = false;
		this.loop = loop;
	}

	@Override
	public void execute(ILevelData data) {
		//if (!soundAdded){
			Jukebox.getInstance().play(mySound, loop);
		// data.getGameSound().createMediaPlayer(mySound);
			//soundAdded = true;
		//}
		// data.getGameSound().play(mySound);

	}

	@Override
	public String toString() {
		return "Plays sound effect + " + mySound;
	}

}
