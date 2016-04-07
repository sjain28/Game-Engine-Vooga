package gameengine;

import java.util.HashMap;
import java.util.Map;

import tools.VoogaNumber;
import tools.interfaces.VoogaData;
/**
 * Factor for creating Sprites from pre-formed Archetypes,
 * getting pre-formed Archetypes, and setting Archetypes.
 * Used by both front end and backend.
 * 
 * @author Krista
 *
 */
public class SpriteFactory {

	private Map<String,Sprite> myArchetypes; 

	public SpriteFactory() {
		myArchetypes = new HashMap<String,Sprite>();
	}
	/**
	 * Create a completely new Sprite of a given archetype
	 * @param archetype
	 * @return Sprite
	 */
	public Sprite createSprite(String archetype){
		Sprite original = myArchetypes.get(archetype);
		Sprite clone = new Sprite(original.getImagePath(), original.getArchetype(), original.getParameterMap(), (VoogaNumber)original.getParameterMap().get(Sprite.MASS));
		return clone;
	}

	public void addArchetype(String archetype, Sprite sprite){
		myArchetypes.put(archetype, sprite);
	}
	public Sprite getArchetype(String archetype){
		return myArchetypes.get(archetype);
	}
}
