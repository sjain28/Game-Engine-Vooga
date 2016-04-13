package gameengine;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import data.Serializer;
import data.DeSerializer;
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

	private static final String DEFAULT_IMAGE = "/smile.jpg";
	private static final String DEFAULT_ARCH = "default";
	public static final Sprite DEFAULT_SPRITE = 
			new Sprite(DEFAULT_IMAGE, DEFAULT_ARCH, new HashMap<String, VoogaData>(), new VoogaNumber(1.0));
	private Map<String,Sprite> myArchetypes; 

	public SpriteFactory() {
		myArchetypes = new HashMap<String,Sprite>();
		myArchetypes.put(DEFAULT_SPRITE.getArchetype(), DEFAULT_SPRITE);
	}
	
	/**
	 * Create a completely new Sprite of a given archetype
	 * @param archetype
	 * @return Sprite
	 */
	public Sprite createSprite(String archetype){
		Sprite original = myArchetypes.get(archetype);
		Sprite clone = new Sprite(original.getImagePath(), original.getArchetype(), 
					  original.getParameterMap(), (VoogaNumber)original.getParameterMap().get(Sprite.MASS));
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
	public void setArchetype(String archetype, Sprite sprite){
		myArchetypes.put(archetype, sprite);
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
	 * @param fileLocation 
	 * @throws SAXException 
	 * @throws IOException 
	 * @throws TransformerException 
	 * @throws ParserConfigurationException 
	 */
	public void serializeArchetypes(String fileLocation){
		Serializer serializer = new Serializer();
		try {
			for(String key : myArchetypes.keySet()){
				System.out.println(key);
				serializer.serialize(myArchetypes.get(key), fileLocation+"_"+key);
			}
		} catch (ParserConfigurationException | TransformerException | IOException | SAXException e) {
			e.printStackTrace();
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
		DeSerializer deserializer = new DeSerializer();
		Sprite newArchetype = (Sprite) deserializer.deserialize(1,fileLocation);
		setArchetype(newArchetype.getArchetype(), newArchetype);
		System.out.println(newArchetype);
	}
}
