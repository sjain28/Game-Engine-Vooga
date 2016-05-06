package events;

import java.util.ArrayList;
import java.util.List;

import gameengine.Sprite;
import player.leveldatamanager.ILevelData;
	/**
	 *A class to define event causes based on the state of a Sprite 
	 * @author Saumya Jain
	 *
	 */
public class SpriteCause extends VariableCause {
	
	private List<Sprite> mySprites;
	private String mySpriteID;
	private String myVarName;
	
	/**
	 * 
	 * @param spriteID ID of the Sprite being checked
	 * @param varName Name of the variable of that Sprite that we're interested in
	 * @param predicate A predicate method to be applied to the Sprite proeprty
	 * @param voogaevent Event that this cause belongs to
	 */
	private SpriteCause(String spriteID, String varName, String predicate, VoogaEvent voogaevent){
		super(predicate, voogaevent);
		mySpriteID = spriteID;
		myVarName = varName;
		mySprites = new ArrayList<>();
	}
	/**
	 * 
	 * @param spriteID
	 * @param varName
	 * @param predicate
	 * @param target A target value for testing equality or other logical relationships to the Sprite property
	 * @param voogaEvent
	 */
	public SpriteCause(String spriteID, String varName, String predicate, Double target, VoogaEvent voogaEvent) {
		this(spriteID, varName, predicate, voogaEvent);
		setTarget(target);
	}
	/**
	 * Separate constructor to take a Double instead of a Boolean. Needed for Reflection in factory.
	 * @param spriteID
	 * @param varName
	 * @param predicate
	 * @param target
	 * @param voogaEvent
	 */
	public SpriteCause(String spriteID, String varName, String predicate, Boolean target, VoogaEvent voogaEvent) {
		this(spriteID, varName, predicate, voogaEvent);
		setTarget(target);
	}
	/**
	 * Separate constructor to take a Double instead of a Boolean. Needed for Reflection in factory.
	 * @param spriteID
	 * @param varName
	 * @param predicate
	 * @param target
	 * @param voogaEvent
	 */
	public SpriteCause(String spriteID, String varName, String predicate, String target, VoogaEvent voogaEvent) {
		this(spriteID, varName, predicate, voogaEvent);
		setTarget(target);
	}
	/**
	 * Applies predicate to Sprite property and returns result of predicate.
	 * Uses logic from super to check predicate.
	 */
	@Override
	public boolean check(ILevelData data){
		boolean myVal = false;
		mySprites.clear();
		ArrayList<Sprite> trueSprites = new ArrayList<>();
		if(mySpriteID.matches("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}")){		
			mySprites.add(data.getSpriteByID(mySpriteID)); //If contains dash, it's a Sprite ID
		}
		else{
			mySprites.addAll(data.getSpritesByArch(mySpriteID));//Else, it's an arch name
		}
		
//		Sprite temp = data.getSpriteByID(mySpriteID);		
//		mySprites.add(temp);
		
		for(Sprite sprite: mySprites){
			super.setVariable(sprite.getProperty(myVarName));
			if(super.check(data)){
				trueSprites.add(sprite);
				myVal = true;
			}
		}
		getEvent().addSpritesFromCause(trueSprites);
		trueSprites.clear();
	
		return myVal;
	}

	
	@Override
	public String toString(){
		return "Checking if the variable " + myVarName + " in Sprite " + mySpriteID + " is equal to " + super.getTarget();
	}
}
