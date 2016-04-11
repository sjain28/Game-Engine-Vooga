package events;

import java.util.ArrayList;
import java.util.List;

import gameengine.Sprite;

public class SpriteVariableCause extends VariableCause {
	
	private List<Sprite> mySprites;
	
	public SpriteVariableCause(String spriteID, String varName, Double targetValue, String predicate, VoogaEvent voogaEvent) {
		super(predicate, voogaEvent);
		super.setTarget(targetValue);
		Sprite temp = getEvent().getManager().getSprite(spriteID);
		mySprites = new ArrayList<>();
		mySprites.add(temp);
		super.setVariable(temp.getProperty(varName));
	}
	
	public SpriteVariableCause(String spriteID, String varName, Boolean targetValue, String predicate, VoogaEvent voogaEvent) {
		super(predicate, voogaEvent);
		super.setTarget(targetValue);
		Sprite temp = getEvent().getManager().getSprite(spriteID);
		super.setVariable(temp.getProperty(varName));
	}

	public boolean check(){
		if(super.check()){
			getEvent().addSpritesFromCause(mySprites);
			return true;
		}
		return false;
	}
	
	

}
