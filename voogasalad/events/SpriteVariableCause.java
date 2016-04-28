package events;

import java.util.ArrayList;
import java.util.List;
import gameengine.Sprite;
import player.leveldatamanager.ILevelData;

public class SpriteVariableCause extends VariableCause {
	
	private List<Sprite> mySprites = new ArrayList<>();
	private String mySpriteID;
	private String myVarName;
	private Object targetValue;
	
	public SpriteVariableCause(String spriteID, String variable, String predicate, Double target, VoogaEvent voogaEvent) {
		super(predicate, voogaEvent);
		super.setTarget(target);
		mySpriteID = spriteID;
		myVarName = variable;
		targetValue = target;
	}
	
	public SpriteVariableCause(String spriteID, String variable, String predicate, Boolean target, VoogaEvent voogaEvent) {
		super(predicate, voogaEvent);
		super.setTarget(target);
		mySpriteID = spriteID;
		myVarName = variable;
		targetValue = target;
	}

	@Override
	public boolean check(ILevelData data){
		
		mySprites.clear();
		Sprite temp = data.getSpriteByID(mySpriteID);		
		mySprites.add(temp);
		
		super.setVariable(temp.getProperty(myVarName));
		
		if(super.check(data)){
			getEvent().addSpritesFromCause(mySprites);
			return true;
		}
		return false;
	}
	
	@Override
	public String toString(){
		return "Checking if the variable " + myVarName + " in Sprite " + mySpriteID + " is equal to " + targetValue;
	}
}
