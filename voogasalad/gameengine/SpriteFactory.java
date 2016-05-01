package gameengine;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import authoring.resourceutility.VoogaFile;
import authoring.resourceutility.VoogaFileType;
import data.Deserializer;
import data.Serializer;
import resources.VoogaBundles;
import tools.VoogaException;
import tools.VoogaNumber;
import tools.interfaces.VoogaData;


/**
 * Factory for creating Sprites from pre-formed Archetypes,
 * getting pre-formed Archetypes, and setting Archetypes.
 * Used by both front end and backend.
 * 
 * @author Krista
 *
 */

public class SpriteFactory extends Observable{

	private Map<String, Sprite> myArchetypes;
	private String archtypeError = "Could not save archetype";

	// Path for folder where all archetypes ever saved are stored
	private final static String ARCHETYPE_RESOURCE_PATH = "resources/saved_archetypes/";

	public SpriteFactory () {
		myArchetypes = new HashMap<>();
	}

	public SpriteFactory(Map<String, Sprite> archetypeMap) {
		myArchetypes = new HashMap<>(archetypeMap);
	}

	/**
	 * Create a completely new Sprite of a given archetype
	 * 
	 * @param archetype
	 * @return Sprite
	 */
	public Sprite createSprite (String archetype) {
		Sprite original = myArchetypes.get(archetype);

		Map<String,VoogaData> map = new HashMap<>(original.getParameterMap());

		return new Sprite(original.getImagePath(), original.getArchetype(),
				map,
				(VoogaNumber) map.get(VoogaBundles.spriteProperties.getString("MASS")));
	}

	/**
	 * Sets or creates a new Archetype
	 * Must specify what you want your default Sprite
	 * for this archetype to be
	 * 
	 * @param archetype
	 * @param s
	 */
	public void addArchetype (String archetypeName, Sprite archetype) throws VoogaException {
		if (myArchetypes.keySet().contains(archetypeName)) {
			throw new VoogaException("This archetype already exists");
		}
		else {
			myArchetypes.put(archetypeName, archetype);
			setChanged();
			VoogaFile file = new VoogaFile(VoogaFileType.ARCHETYPE, archetypeName);
			file.setPath(archetype.getImagePath());
			notifyObservers(file);
		}
	}

	/**
	 * Returns the default Sprite for a given
	 * Archetype
	 * 
	 * @param archetype
	 * @return
	 */
	public Sprite getArchetype (String archetype) {
		if (!myArchetypes.containsKey(archetype)){
			return null;
		}
		return myArchetypes.get(archetype);
	}
	
	public void resetArchetypeMap(Map<String, Sprite> newArchetypeMap){
		myArchetypes = newArchetypeMap;
	}
	
	/**
	 * Returns a set of all possible archetypes
	 * that you can choose from in your library
	 * 
	 * @return Set<String>
	 */

	public Set<String> getAllArchetypeNames () {
		return myArchetypes.keySet();
	}

	public void importArchetype (String ... archetypeNames) throws VoogaException {
		for (String name : archetypeNames) {
			loadArchetype(name);
		}
	}

	public void saveArchetype (String ... archetypeNames) throws VoogaException {
		for (String name : archetypeNames) {
			writeArchetype(name);
		}
	}

	private void loadArchetype (String archetypeName) throws VoogaException {
		Sprite newSpriteOfArchetype = (Sprite) Deserializer.deserialize(1, ARCHETYPE_RESOURCE_PATH +
				archetypeName + ".xml").get(0);
		addArchetype(archetypeName, newSpriteOfArchetype);

	}

	private void writeArchetype (String archetypeName) throws VoogaException {
		File archetypeFile = new File(ARCHETYPE_RESOURCE_PATH + archetypeName + ".xml");
		if (!archetypeFile.exists()) {
			try {
				archetypeFile.createNewFile();
				try {
					Serializer.serialize(myArchetypes.get(archetypeName), archetypeFile.getPath());
				}
				catch (ParserConfigurationException | TransformerException | IOException
						| SAXException e) {
					throw new VoogaException(archtypeError);
				}
			}
			catch (IOException e) {
				throw new VoogaException(archtypeError);
			}
		}
	}

	public Map<String,Sprite> getArchetypeMap(){
		return myArchetypes;
	}


}