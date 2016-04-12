package events;

import java.util.List;

import gameengine.Sprite;
import tools.interfaces.VoogaData;

public class SpriteEffect extends VariableEffect{

	private Boolean needsSprites;
	private String myArchetype;
	private List<Sprite> mySprites;

	// constructor with sprites- apply to given sprites
	public SpriteEffect(List<Sprite> sprites, String method, String variable, VoogaEvent event) {
		super(method, variable, event);
		mySprites = sprites;
		needsSprites = false;
	}

	// constructor with archetype- apply to all of archetype
	// constructor with archetype- apply to all of archetype for which event supplies
	public SpriteEffect(String archetype, Boolean needsSprites, String method, String variable, VoogaEvent event) {
		super(method, variable, event);
		myArchetype = archetype;
		this.needsSprites = needsSprites;
	}

	// constructor with nothing- apply to all for which event supplies
	public SpriteEffect(String method, String variable, VoogaEvent event) {
		super(method, variable, event);
		needsSprites = true;
	}

	@Override
	public void execute() {
		setSprites();
		for (Sprite sprite : mySprites){
			VoogaData variable = sprite.getParameterMap().get(getVariable());
			callEffectMethod(variable);
		}

	}
	protected void setSprites(){
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
	protected List<Sprite> getSprites(){
		return mySprites;
	}
}