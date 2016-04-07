package gameengine;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

	private HashMap<String,Sprite> myArchetypes; 
	
	public SpriteFactory() {
		myArchetypes = new HashMap<String,Sprite>();
	}
	/**
	 * Create a completely new Sprite of a given archetype
	 * @param archetype
	 * @return Sprite
	 */
	public Sprite createSprite(String archetype){
		Sprite toCopy = myArchetypes.get(archetype);
		Map<String, VoogaData> newProperties = new HashMap<String, VoogaData>(toCopy.getParameterMap());
		Sprite clone = new Sprite(toCopy.getImagePath(), archetype,newProperties);
		return clone;
	}
	
	public void setArchetype(String archetype, Sprite s){
		myArchetypes.put(archetype, s);
	}
	public Sprite getArchetype(String archetype){
		return myArchetypes.get(archetype);
	}
	
	public Set<String> getAllArchetypeNames(){
		return myArchetypes.keySet();
	}
}
