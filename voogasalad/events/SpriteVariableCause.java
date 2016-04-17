package events;
import java.util.List;
import gameengine.Sprite;
import player.leveldatamanager.LevelData;

public class SpriteVariableCause extends VariableCause {
	
	private List<Sprite> mySprites;
	private String mySpriteID;
	private String myVarName;
	
	public SpriteVariableCause(String spriteID, String varName, String predicate, Double targetValue, VoogaEvent voogaEvent) {
		super(voogaEvent);
		super.setPredicate(predicate);
		super.setTarget(targetValue);
		mySpriteID = spriteID;
		myVarName = varName;
	}
	
	public SpriteVariableCause(String spriteID, String varName, String predicate, Boolean targetValue, VoogaEvent voogaEvent) {
		super(voogaEvent);
		super.setPredicate(predicate);
		super.setTarget(targetValue);
		mySpriteID = spriteID;
		myVarName = varName;
	}

	@Override
	public boolean check(LevelData data){
		//TODO: use the init method here instead. get all the info u need
		//straight from leveldata each time
		Sprite sprite = data.getSprite(mySpriteID);
		//returns false if the Sprite has already been killed off
		if(sprite == null){return false;}
		super.setVariable(sprite.getProperty(myVarName));
		
		if(super.check(data)){
			getEvent().addSpritesFromCause(mySprites);
			return true;
		}
		return false;
	}
	
	@Override
	public void init(){
		/*System.out.println("EVENT: "+getEvent());
		System.out.println("MANAGER: "+getEvent().getManager());
		Sprite temp = getEvent().getManager().getSprite(mySpriteID);
		mySprites = new ArrayList<>();
		mySprites.add(temp);
		super.setVariable(temp.getProperty(myVarName));*/
	}

}
