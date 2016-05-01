package events;

import java.util.ArrayList;
import java.util.List;

import gameengine.Sprite;
import player.leveldatamanager.ILevelData;
import tools.interfaces.VoogaData;

public class SpriteEffect extends VariableEffect{

	private Boolean needsSprites;
	private String mySpriteID;
	private String myArchetype;
	private List<Sprite> mySprites = new ArrayList<>();

	// constructor with sprites- apply to given sprites

	public SpriteEffect(String spriteID, String variable, String method, Double parameter, VoogaEvent event) {
		super(variable, method, parameter, event);
		setSpriteID(spriteID);
		setNeedsSprites(false);
	}

	public SpriteEffect(String spriteID, String variable, String method, Boolean parameter, VoogaEvent event) {
		super(variable, method, parameter, event);
		setSpriteID(spriteID);
		setNeedsSprites(false);
	}
	
	public SpriteEffect(String spriteID, String variable, String method, String parameter, VoogaEvent event) {
		super(variable, method, parameter, event);
		setSpriteID(spriteID);
		setNeedsSprites(false);
	}
	
	// constructor with archetype, boolean true- apply to all of archetype
	// constructor with archetype, boolean false- apply to all of archetype for which event supplies

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
	
	public SpriteEffect(String archetype, Boolean needsSprites, String variable, String method, String parameter, VoogaEvent event) {
		super(variable, method, parameter, event);
		setMyArchetype(archetype);
		setNeedsSprites(needsSprites);
	}
	public SpriteEffect(String method, Double parameter, VoogaEvent event){
		super(method, parameter, event);
	}
	public SpriteEffect(VoogaEvent event){
		super(event);
	}

	@Override
	public void execute(ILevelData data) {
		setSprites(data);
		if (!mySprites.isEmpty() && mySprites.size() > 0){
			for (Sprite sprite : mySprites){
				VoogaData variable = sprite.getParameterMap().get(getVariable());
				callEffectMethod(variable);
			}
		}
		mySprites.clear();
	}
	/**
	 * Determines which sprites need to be set for this effect depending on the constructor that was used, as well as the
	 * sprite outputs of the cause within the same event.
	 */
	protected void setSprites(ILevelData data){
		if(getSpriteID() != null){
			mySprites.add(data.getSpriteByID(getSpriteID()));
		}
		if (getNeedsSprites()){
			mySprites = getEvent().getCauseSprites();
		}
		if (getMyArchetype() != null){
			List<Sprite> archSpriteIDs = data.getSpritesByArch(getMyArchetype());
			if (!mySprites.isEmpty()){
				for(Sprite causeSprite : mySprites){
					if(!archSpriteIDs.contains(causeSprite)){
						mySprites.remove(causeSprite);
					}
				}
			}else {
				for(Sprite sprite : archSpriteIDs){
					mySprites.add(sprite);
				}
			}
		}
	}
	
	@Override
	public String toString() {
		String effectString = getMethodString() + " " + getVariable() + " for " ;
		
		if (myArchetype != null){
			effectString += myArchetype;
		}
		if (getSpriteID() != null){
			effectString += " " + getSpriteID();
		}
		
		//TODO: PUT THIS IN RESOURCE BUNDLE
		if (getNeedsSprites()){
			effectString += " sprites from causes";
		}
		if (getParameter() != null){
			effectString += " [" + getParameter().toString() + "]";
		}
		return effectString;
	}
	protected List<Sprite> getSprites(){
		return mySprites;
	}

	protected String getMyArchetype() {
		return myArchetype;
	}

	protected void setMyArchetype(String archetype) {
		myArchetype = archetype;
	}
	protected void setNeedsSprites(Boolean needsSprites){
		this.needsSprites = needsSprites;
	}
	protected List<Sprite> getMySprites(){
		return mySprites;
	}
	protected void setSpriteID(String spriteID){
		mySpriteID = spriteID;
	}

	protected String getSpriteID() {
		return mySpriteID;
	}

	protected Boolean getNeedsSprites() {
		return needsSprites;
	}
	protected void clearSprites(){
		mySprites.clear();
	}
}
