package gameengine;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
		Sprite newSprite = new Sprite(toCopy.getImagePath(), archetype);
		newSprite.setProperties(newProperties);
		return newSprite;
	}
	/**
	 * Sets or creates a new Archetype
	 * Must specify what you want your default Sprite
	 * for this archetype to be 
	 * 
	 * @param archetype
	 * @param s
	 */
	public void setArchetype(String archetype, Sprite s){
		myArchetypes.put(archetype, s);
	}
	
	/**
	 * Returns the default Sprite for a given
	 * Archetype
	 * 
	 * @param archetype
	 * @return
	 */
	public Sprite getArchetype(String archetype){
		return myArchetypes.get(archetype);
	}

	/**
	 * Returns a set of all possible archetypes
	 * that you can choose from in your libary
	 * 
	 * @return Set<String>
	 */
	public Set<String> getAllArchetypeNames(){
		return myArchetypes.keySet();
	}
	
	/**
	 * Serializes all Default Sprites for each created archetype 
	 * In the same file location by a different file name (given
	 * by their archetype). 
	 * 
	 * todo: must check with front end to see if this is something
	 * they actually need or not
	 * 
	 * @param fileLocation 
	 */
	public void serializeArchetypes(String fileLocation){
		//TODO: Test to see if this actually works
		for(String archetype: myArchetypes.keySet()){
			try {
				FileOutputStream fileOut = new FileOutputStream(fileLocation+"/"+archetype);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(myArchetypes.get(archetype));
				out.close();
				fileOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * DeSerializes the DefaultSprite specified by the fileLocation.
	 * Puts it in the map along with the other archetypes.
	 * This could be used to load archetypes over from other games.
	 * 
	 * todo: Check to see if this is actually what the front end needs
	 * 
	 * @param fileLocation
	 */
	public void deSerializeArchetype(String fileLocation){
		//TODO: Test to see if this actually works
		try {
			FileInputStream fileIn = new FileInputStream(fileLocation);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			
			Sprite recoveredArchetype = (Sprite) in.readObject();
			myArchetypes.put(recoveredArchetype.getArchetype(),recoveredArchetype);
			in.close();
			fileIn.close();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
}
