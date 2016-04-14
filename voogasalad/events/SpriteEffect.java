package events;

import java.util.ArrayList;
import java.util.List;

import gameengine.Sprite;
import tools.interfaces.VoogaData;

public class SpriteEffect extends VariableEffect{

	private Boolean needsSprites;
	private String myArchetype;
	private List<Sprite> mySprites;

	// constructor with sprites- apply to given sprites
	public SpriteEffect(String spriteID, String method, String variable, VoogaEvent event) {
		super(method, variable, event);
		mySprites = new ArrayList<>();
		mySprites.add(getEvent().getManager().getSprite(spriteID));
		setNeedsSprites(false);
	}
	
	public SpriteEffect(String spriteID, String method, String variable, Double parameter, VoogaEvent event) {
		super(method, variable, parameter, event);
		mySprites = new ArrayList<>();
		mySprites.add(getEvent().getManager().getSprite(spriteID));
		setNeedsSprites(false);
	}
	
	public SpriteEffect(String spriteID, String method, String variable, Boolean parameter, VoogaEvent event) {
		super(method, variable, parameter, event);
		mySprites = new ArrayList<>();
		mySprites.add(getEvent().getManager().getSprite(spriteID));
		setNeedsSprites(false);
	}
	
	// constructor with archetype, boolean true- apply to all of archetype
	// constructor with archetype, boolean false- apply to all of archetype for which event supplies
	public SpriteEffect(String archetype, Boolean needsSprites, String method, String variable, VoogaEvent event) {
		super(method, variable, event);
		mySprites = new ArrayList<>();

		myArchetype = archetype;
		this.setNeedsSprites(needsSprites);
	}
	
	public SpriteEffect(String archetype, Boolean needsSprites, String method, String variable, Double parameter, VoogaEvent event) {
		super(method, variable, parameter, event);
		mySprites = new ArrayList<>();

		myArchetype = archetype;
		this.setNeedsSprites(needsSprites);
	}
	
	public SpriteEffect(String archetype, Boolean needsSprites, String method, String variable, Boolean parameter, VoogaEvent event) {
		super(method, variable, parameter, event);
		mySprites = new ArrayList<>();

		myArchetype = archetype;
		this.setNeedsSprites(needsSprites);
	}
	
	// constructor with nothing- apply to all for which event supplies
	public SpriteEffect(String method, String variable, VoogaEvent event) {
		super(method, variable, event);
		mySprites = new ArrayList<>();

		setNeedsSprites(true);
	}
	
	public SpriteEffect(String method, String variable, Double parameter, VoogaEvent event) {
		super(method, variable, parameter, event);
		mySprites = new ArrayList<>();

		setNeedsSprites(true);
	}
	
	public SpriteEffect(String method, String variable, Boolean parameter, VoogaEvent event) {
		super(method, variable, parameter, event);
		mySprites = new ArrayList<>();

		setNeedsSprites(true);
	}

	@Override
	public void execute() {
		setSprites();
		for (Sprite sprite : mySprites){
			VoogaData variable = sprite.getParameterMap().get(getVariable());
			callEffectMethod(variable);
		}
		mySprites.clear();

	}
	/**
	 * Determines which sprites need to be set for this effect depending on the constructor that was used, as well as the
	 * sprite outputs of the cause within the same event.
	 */
	protected void setSprites(){

		if (getNeedsSprites()){
			mySprites.add(getEvent().getManager().getSprite(getEvent().getManager().getSpriteIDs("default").get(0)));
			//mySprites = getEvent().getSpritesFromCauses();
		}
		if (myArchetype != null){
			// get sprite manager, get all sprites of archetype
			List<String> archSpriteIDs = getEvent().getManager().getSpriteIDs(myArchetype);
			if (mySprites.size() != 0){
				for(Sprite causeSprite : mySprites){
					if(!archSpriteIDs.contains(causeSprite.getID())){
						mySprites.remove(causeSprite);
					}
				}
			}else {
				mySprites.clear();
				for(String spriteid : archSpriteIDs){
					mySprites.add(getEvent().getManager().getSprite(spriteid));
				}
			}
		}
//		if (needsSprites){
//			mySprites = getEvent().getSpritesFromCauses();
//			System.out.println("needs sprites");
//		}
//		else if (myArchetype != null){
//			System.out.println("archetype for effect specified");
//
//			// get sprite manager, get all sprites of archetype
//			List<String> archSpriteIDs = getEvent().getManager().getSpriteIDs(myArchetype);
//			if (mySprites.size() != 0){
//				for(Sprite causeSprite : mySprites){
//					if(!archSpriteIDs.contains(causeSprite.getID())){
//						mySprites.remove(causeSprite);
//					}
//				}
//			}else {
//				mySprites.clear();
//				for(String spriteid : archSpriteIDs){
//					mySprites.add(getEvent().getManager().getSprite(spriteid));
//				}
//			}
//		}
//		else{
//			
//		}
	}
	protected List<Sprite> getSprites(){
		return mySprites;
	}

	public Boolean getNeedsSprites() {
		return needsSprites;
	}

	public void setNeedsSprites(Boolean needsSprites) {
		this.needsSprites = needsSprites;
	}
}
