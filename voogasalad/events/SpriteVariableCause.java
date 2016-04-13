package events;

import java.util.ArrayList;
import java.util.List;
import gameengine.Sprite;

public class SpriteVariableCause extends VariableCause {
	
	private List<Sprite> mySprites;
	private String mySpriteID;
	private String myVarName;
	
	public SpriteVariableCause(String spriteID, String varName, String predicate, Double targetValue, VoogaEvent voogaEvent) {
		super(predicate, voogaEvent);
		super.setTarget(targetValue);
		mySpriteID = spriteID;
		myVarName = varName;
	}
	
	public SpriteVariableCause(String spriteID, String varName, String predicate, Boolean targetValue, VoogaEvent voogaEvent) {
		super(predicate, voogaEvent);
		super.setTarget(targetValue);
		mySpriteID = spriteID;
		myVarName = varName;
	}

	@Override
	public boolean check(){
		if(super.check()){
			getEvent().addSpritesFromCause(mySprites);
			return true;
		}
		return false;
	}
	
	@Override
	public void init(){
		Sprite temp = getEvent().getManager().getSprite(mySpriteID);
		mySprites = new ArrayList<>();
		mySprites.add(temp);
		super.setVariable(temp.getProperty(myVarName));
	}

}
