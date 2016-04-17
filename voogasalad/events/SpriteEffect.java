package events;

import java.util.ArrayList;
import java.util.List;

import gameengine.Sprite;
import player.leveldatamanager.LevelData;
import tools.interfaces.VoogaData;

public class SpriteEffect extends VariableEffect{

	private Boolean needsSprites;
	private String mySpriteID;
	private String myArchetype;
	//private List<Sprite> mySprites = new ArrayList<>();

	// constructor with sprites- apply to given sprites
	public SpriteEffect(String spriteID, String variable, String method, VoogaEvent event) {
		super(variable, method, event);
		mySpriteID = spriteID;
		setNeedsSprites(false);
	}
	
	public SpriteEffect(String spriteID, String variable, String method, Double parameter, VoogaEvent event) {
		super(variable, method, parameter, event);
		mySpriteID = spriteID;
		setNeedsSprites(false);
	}
	
	public SpriteEffect(String spriteID, String variable, String method, Boolean parameter, VoogaEvent event) {
		super(variable, method, parameter, event);
		mySpriteID = spriteID;
		setNeedsSprites(false);
	}
	
	// constructor with archetype, boolean true- apply to all of archetype
	// constructor with archetype, boolean false- apply to all of archetype for which event supplies
	public SpriteEffect(String archetype, Boolean needsSprites, String variable, String method, VoogaEvent event) {
		super(variable, method, event);
		setMyArchetype(archetype);
		setNeedsSprites(needsSprites);
	}
	
	public SpriteEffect(String archetype, Boolean needsSprites, String variable, String method, Double parameter, VoogaEvent event) {
		super(variable, method, parameter, event);
		setMyArchetype(archetype);
		setNeedsSprites(needsSprites);
	}
	
	public SpriteEffect(String archetype, Boolean needsSprites, String variable, String method, Boolean parameter, VoogaEvent event) {
		super(variable, method, parameter, event);
		setMyArchetype(archetype);
		setNeedsSprites(needsSprites);
	}
	
	// constructor with nothing- apply to all for which event supplies
	public SpriteEffect(String method, String variable, VoogaEvent event) {
		super(variable, method, event);
		setNeedsSprites(true);
	}
	
	public SpriteEffect(String variable, String method, Double parameter, VoogaEvent event) {
		super(variable, method, parameter, event);
		setNeedsSprites(true);
	}
	
	public SpriteEffect(String variable, String method, Boolean parameter, VoogaEvent event) {
		super(variable, method, parameter, event);
		setNeedsSprites(true);
	}

	public SpriteEffect(String method, Double parameter, VoogaEvent event){
		super(method, parameter, event);
	}
	
	@Override
	public void init(){
	   //why is this part needed if setSprites is already a method we have?
		//sprite could be deleted
	   /* System.out.println("Initializing srite effect");
		if(mySpriteID != null){
			System.out.println("Initializing the Sprite now");
			mySprites.add(getEvent().getManager().getSprite(mySpriteID));
			System.out.println(mySprites.size());
		}*/
	}
	
	@Override
	public void execute(LevelData data) {
		List<Sprite> sprites = getSpritesToBeEffected(data);
		for (Sprite sprite : sprites){
			VoogaData variable = sprite.getParameterMap().get(getVariable());
			callEffectMethod(variable);
		}
	}
	/**
	 * Determines which sprites need to be set for this effect depending on the constructor that was used, as well as the
	 * sprite outputs of the cause within the same event.
	 * 
	 * TODO: CONSIDER THE CASE OF IF A SINGLE SPRITE ID IS SET TO BE EFFECTED AND THAT SPRITE DIES
	 * NEED TO CHECK 
	 */
	protected List<Sprite> getSpritesToBeEffected(LevelData data){
		List<Sprite> sprites = new ArrayList<Sprite>();
		// if this is some sort of collision effect that needs to know what sprite's were involved in the cause
		if (needsSprites){
			sprites = getEvent().getSpritesFromCauses();
			
			//remove all sprites that are not of specified archetype if archetype was specified as well
			if(myArchetype!=null){
				List<String> archSpriteIDs = data.getSpriteIDs(getMyArchetype());
				for(Sprite sprite : sprites){
					if(!archSpriteIDs.contains(sprite.getID())){
						sprites.remove(sprite);
					}
				}
			}
			
			//remove all sprite's that are not of specified id if that id was specified
			if(mySpriteID!=null){
				for(int i = 0; i<sprites.size(); i++){
					if(sprites.get(i).getID()!=mySpriteID){
						sprites.remove(i);
					}
				}
			}
		}
		// otherwise should affect all sprite's of a given archetype 
		else if (myArchetype != null){
			//get all sprite's of archetype
			for(String spriteID : data.getSpriteIDs(getMyArchetype())){
				sprites.add(data.getSprite(spriteID));
			}
			
		}
		// or a single sprite that was specified 
		//ADDED IN CASE HERE FOR IF ITS NOT AN ARCHETYPE OR A LIST OF SPRITES FROM CAUSES
		else if(mySpriteID!=null){
			sprites.add(data.getSprite(mySpriteID));
		}
		return sprites;
	}
	
	/*protected List<Sprite> getSprites(){
		return mySprites;
	}*/

	public String getMyArchetype() {
		return myArchetype;
	}

	public void setMyArchetype(String archetype) {
		myArchetype = archetype;
	}
	public void setNeedsSprites(Boolean needsSprites){
		this.needsSprites = needsSprites;
	}
	public void setSpriteID(String spriteID){
		mySpriteID = spriteID;
	}
}
