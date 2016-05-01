package authoring.interfaces;

import java.util.Map;

import javafx.scene.Node;
import tools.VoogaException;
import tools.interfaces.VoogaData;


/**
 * Forms a relationship between all elements, objects that are going to be present in the game.
 * This relationship makes it easier to define any basic options for these objects, such as dragging
 * them onto the screen. This interface also defines a basic properties structure for the element.
 * 
 * @author Aditya Srinivasan, Arjun Desai, Harry Guo, Nick Lockett
 *
 */
public interface Elementable extends Updatable{

	/**
	 * Gets the properties map of the elementable
	 * @return the map of VoogaProperties for the elementable
	 */
	Map<String, VoogaData> getVoogaProperties ();

	/**
	 * Sets the properties map of the elementable
	 * @param newVoogaProperties to be put in place of current VoogaProperties
	 */
	void setVoogaProperties(Map<String,VoogaData> newVoogaProperties);

	/**
	 * Adds a property to the VoogaProperties map.
	 * @param name of property
	 * @param data of the given property (either VoogaBoolean or VoogaNumber)
	 */
	void addProperty (String name, VoogaData data);

	/**
	 * Removes a property from the VoogaProperties map.
	 * @param name of the property to be removed
	 */
	void removeProperty (String name);

	/**
	 * Gets the name of the current Elementable
	 * @return name of element
	 */
	String getName ();

	/**
	 * Sets the name of the current Elementable
	 * @param name to be set as name of element
	 */
	void setName(String name);

	/**
	 * Gets the ID of the element
	 * @return id of elementable
	 */
	String getId ();

	/**
	 * Gets the node object form of the elementable
	 * @return the elementable represented as a node
	 */
	Node getNodeObject ();

	/**
	 * Initializes the Elementable since JavaFX objects can't be serialized.
	 * @throws VoogaException
	 */
	void init() throws VoogaException;

}
