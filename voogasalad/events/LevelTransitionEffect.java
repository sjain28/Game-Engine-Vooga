package events;

import player.leveldatamanager.ILevelData;

public class LevelTransitionEffect extends Effect{
	
	private String myNextLevel;

	public LevelTransitionEffect(String nextLevel, VoogaEvent event) {
		super(event);
		myNextLevel = nextLevel;
	}

	@Override
	public void execute(ILevelData data) {
		data.getGlobalVar("NextLevelIndex").setValue(myNextLevel);
	}

	@Override
	public String toString() {
		return "Sets the next level for this cause bundle to " + myNextLevel;
	}

}
