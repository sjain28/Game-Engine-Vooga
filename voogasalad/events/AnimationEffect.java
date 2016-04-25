package events;

import java.util.ArrayList;
import java.util.List;

import player.leveldatamanager.ILevelData;

public class AnimationEffect extends SpriteEffect{
	
	private List<Double> xCoord;
	private List<Double> yCoord;
	
	public AnimationEffect(String spriteID, List<Double> xCoord, List<Double> yCoord, VoogaEvent event) {
		super(event);
		setSpriteID(spriteID);
		setNeedsSprites(false);
		// TODO Auto-generated constructor stub
	}
	
	public AnimationEffect(String archetype, Boolean needsSprites, List<Double> xCoord, List<Double> yCoord, VoogaEvent event) {
		super(event);
		setMyArchetype(archetype);
		setNeedsSprites(needsSprites);
		// TODO Auto-generated constructor stub
	}
	
	public AnimationEffect(List<Double> xCoord, List<Double> yCoord, VoogaEvent event) {
		super(event);
		setNeedsSprites(true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(ILevelData data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
