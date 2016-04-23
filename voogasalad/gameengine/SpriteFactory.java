package gameengine;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Set;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

import authoring.resourceutility.VoogaFile;
import authoring.resourceutility.VoogaFileType;
import data.Serializer;
import data.Deserializer;
import tools.VoogaException;
import tools.VoogaNumber;


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

    // Path for folder where all archetypes ever saved are stored
    private final String ARCHETYPE_RESOURCE_PATH = "resources/saved_archetypes/";

    public SpriteFactory () {
        myArchetypes = new HashMap<String, Sprite>();
    }

    public SpriteFactory(Map<String, Sprite> archetypeMap) {
		myArchetypes = new HashMap<String,Sprite>(archetypeMap);
	}

	/**
     * Create a completely new Sprite of a given archetype
     * 
     * @param archetype
     * @return Sprite
     */
    public Sprite createSprite (String archetype) {
        Sprite original = myArchetypes.get(archetype);
        Sprite clone = new Sprite(original.getImagePath(), original.getArchetype(),
                                  original.getParameterMap(),
                                  (VoogaNumber) original.getParameterMap().get(Sprite.MASS));
        return clone;
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
        return myArchetypes.get(archetype);
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
                    throw new VoogaException("Could not save archetype");
                }
            }
            catch (IOException e) {
                throw new VoogaException("Could not save archetype");
            }
        }
    }
    
    public Map<String,Sprite> getArchetypeMap(){
        return myArchetypes;
    }
    

}