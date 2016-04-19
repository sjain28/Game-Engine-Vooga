package events;

import java.util.ArrayList;
import java.util.List;

import gameengine.Sprite;
import tools.interfaces.VoogaData;

public class SpriteEffect extends VariableEffect{

	private Boolean needsSprites;
	private String mySpriteID;
	private String myArchetype;
	private List<Sprite> mySprites = new ArrayList<>();

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
	    System.out.println("Initializing srite effect");
		if(mySpriteID != null){
			System.out.println("Initializing the Sprite now");
			mySprites.add(getEvent().getManager().getSprite(mySpriteID));
			System.out.println(mySprites.size());
		}
	}
	
	@Override
	public void execute() {
		setSprites();
		for (Sprite sprite : mySprites){
			VoogaData variable = sprite.getParameterMap().get(getVariable());
			callEffectMethod(variable);
		}
	}
	/**
	 * Determines which sprites need to be set for this effect depending on the constructor that was used, as well as the
	 * sprite outputs of the cause within the same event.
	 */
	protected void setSprites(){
		if (needsSprites){
			mySprites = getEvent().getSpritesFromCauses();
		}
		if (getMyArchetype() != null){
			// get sprite manager, get all sprites of archetype
			List<String> archSpriteIDs = getEvent().getManager().getSpriteIDs(getMyArchetype());
			if (mySprites.size() != 0){
				for(Sprite causeSprite : mySprites){
					if(!archSpriteIDs.contains(causeSprite.getId())){
						mySprites.remove(causeSprite);
					}
				}
			}else {
				for(String spriteID : archSpriteIDs){
					mySprites.add(getEvent().getManager().getSprite(spriteID));
				}
			}
		}
	}
	
	protected List<Sprite> getSprites(){
		return mySprites;
	}

	public String getMyArchetype() {
		return myArchetype;
	}

	public void setMyArchetype(String archetype) {
		myArchetype = archetype;
	}
	public void setNeedsSprites(Boolean needsSprites){
		this.needsSprites = needsSprites;
	}
	public List<Sprite> getMySprites(){
		return mySprites;
	}
	public void setSpriteID(String spriteID){
		mySpriteID = spriteID;
	}
}
