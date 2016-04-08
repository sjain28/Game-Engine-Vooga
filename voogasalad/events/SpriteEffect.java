package events;

import java.util.List;

import gameengine.Sprite;

public class SpriteEffect extends Effect{

	private Boolean needsSprites;
	private String myArchetype;
	private List<Sprite> mySprites;

	// constructor with sprites- apply to given sprites
	public SpriteEffect(Event event, List<Sprite> sprites) {
		super(event);
		mySprites = sprites;
		needsSprites = false;
	}

	// constructor with archetype- apply to all of archetype
	// constructor with archetype- apply to all of archetype for which event supplies
	public SpriteEffect(Event event, String archetype, Boolean needsSprites) {
		super(event);
		myArchetype = archetype;
		this.needsSprites = needsSprites;
	}

	// constructor with nothing- apply to all for which event supplies
	public SpriteEffect(Event event) {
		super(event);
		needsSprites = true;
	}

	@Override
	public void execute() {
		if (needsSprites){
			// get event, get sprites, mySprites.add()
		}
		if (myArchetype != null){
			// get sprite manager, get all sprites of archetype
			if (mySprites.size() != 0){
				// retain only mySprites also in archetype
			}
		}
		// get 

	}
	public void setSprites(){
		// 
	}

}
