package events;

import java.util.ArrayList;
import java.util.List;
import gameengine.Sprite;
import player.leveldatamanager.ILevelData;

public class SpriteVariableCause extends VariableCause {
	
	private List<Sprite> mySprites = new ArrayList<>();
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
}
