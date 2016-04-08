package events;

import java.util.List;

import gameengine.Sprite;

public class SpriteEffect extends Effect{

	private Boolean needsSprites;
	private String myArchetype;
	private List<Sprite> mySprites;

	// constructor with sprites- apply to given sprites
	public SpriteEffect(VoogaEvent event, List<Sprite> sprites) {
		super(event);
		mySprites = sprites;
		needsSprites = false;
	}

	// constructor with archetype- apply to all of archetype
	// constructor with archetype- apply to all of archetype for which event supplies
	public SpriteEffect(VoogaEvent event, String archetype, Boolean needsSprites) {
		super(event);
		myArchetype = archetype;
		this.needsSprites = needsSprites;
	}

	// constructor with nothing- apply to all for which event supplies
	public SpriteEffect(VoogaEvent event) {
		super(event);
		needsSprites = true;
	}

	@Override
	public void execute() {
		setSprites();
		for (Sprite sprite : mySprites){
			sprite.getParameterMap().get(getVariable());
		}

	}
	public void setSprites(){
		if (needsSprites){
			mySprites = getEvent().getSpritesFromCauses();
		}
		if (myArchetype != null){
			// get sprite manager, get all sprites of archetype
			List<Sprite> archSprites = getEvent().getManager().getSpritesByArchetype(myArchetype);
			if (mySprites.size() != 0){
				for(Sprite causeSprite : mySprites){
					if(!archSprites.contains(causeSprite)){
						mySprites.remove(causeSprite);
					}
				}
			}else {
				mySprites = archSprites;
			}
		}
	}
}
