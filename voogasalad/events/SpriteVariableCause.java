package events;

import gameengine.Sprite;

public class SpriteVariableCause extends VariableCause {
	
	public SpriteVariableCause(String spriteID, String varName, Double targetValue, String predicate, VoogaEvent voogaEvent) {
		super(predicate, voogaEvent);
		super.setTarget(targetValue);
		Sprite temp = getEvent().getManager().getSprite(spriteID);
		super.setVariable(temp.getProperty(varName));
	}
	
	public SpriteVariableCause(String spriteID, String varName, Boolean targetValue, String predicate, VoogaEvent voogaEvent) {
		super(predicate, voogaEvent);
		super.setTarget(targetValue);
		Sprite temp = getEvent().getManager().getSprite(spriteID);
		super.setVariable(temp.getProperty(varName));
	}

	public boolean check(){
		return super.check();
	}
	
	

}
