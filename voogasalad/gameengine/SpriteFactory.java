package gameengine;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import data.Serializer;
import resources.VoogaBundles;
import data.DeSerializer;
import auxiliary.VoogaException;
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

    // private static final String DEFAULT_IMAGE = "/smile.jpg";
    // private static final String DEFAULT_ARCH = "default";
    // public static final Sprite DEFAULT_SPRITE =
    // new Sprite(DEFAULT_IMAGE, DEFAULT_ARCH, new HashMap<String, VoogaData>(), new
    // VoogaNumber(1.0));

    private Map<String, Sprite> myArchetypes;

    // Path for folder where all archetypes ever saved are stored
    private static final String ARCHETYPE_RESOURCE_PATH = "file:resources/saved_archetypes/";

    public SpriteFactory () {
        myArchetypes = new HashMap<String, Sprite>();
        // myArchetypes.put(DEFAULT_SPRITE.getArchetype(), DEFAULT_SPRITE);
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

    /**
     * Serializes all Default Sprites for each created archetype
     * In the same file location by a different file name (given
     * by their archetype).
     * 
     * @param fileLocation
     * @throws SAXException
     * @throws IOException
     * @throws TransformerException
     * @throws ParserConfigurationException
     */
    public void serializeArchetypes (String fileLocation) {
        Serializer serializer = new Serializer();
        try {
            for (String key : myArchetypes.keySet()) {
                System.out.println(key);
                serializer.serialize(myArchetypes.get(key), fileLocation + "_" + key);
            }
        }
        catch (ParserConfigurationException | TransformerException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    /**
     * DeSerializes the DefaultSprite specified by the fileLocation.
     * Puts it in the map along with the other archetypes.
     * This could be used to load archetypes over from other games.
     * 
     * TODO: Check to see if this is actually what the front end needs
     * 
     * @param fileLocation
     * @throws Exception
     */
    public void deSerializeArchetype (String fileLocation) throws VoogaException {
        DeSerializer deserializer = new DeSerializer();
        Sprite newArchetype = (Sprite) deserializer.deserialize(1, fileLocation);
        addArchetype(newArchetype.getArchetype(), newArchetype);
        System.out.println(newArchetype);
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
        Sprite newSpriteOfArchetype =
                (Sprite) DeSerializer
                        .deserialize(1, ARCHETYPE_RESOURCE_PATH +
                                        VoogaBundles.archetypeProperties.getString(archetypeName));
        addArchetype(archetypeName, newSpriteOfArchetype);

    }

    private void writeArchetype (String archetypeName) throws VoogaException {
        File dir = new File(ARCHETYPE_RESOURCE_PATH);
        File archetypeFile = new File(dir, archetypeName + ".xml");
        if (!archetypeFile.exists()) {
            try {
                archetypeFile.createNewFile();
                try {
                    System.out.println(archetypeName);
                    Serializer.serialize(myArchetypes.get(archetypeName), archetypeFile.getPath());
                }
                catch (ParserConfigurationException | TransformerException | IOException
                        | SAXException e) {
                    throw new VoogaException(e.getMessage());
                }
            }
            catch (IOException e) {
                throw new VoogaException("Could not create file");
            }
        }
    }
}
