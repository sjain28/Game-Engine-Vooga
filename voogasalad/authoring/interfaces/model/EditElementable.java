package authoring.interfaces.model;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import authoring.interfaces.Elementable;
import javafx.scene.Node;

/**
 * Interface that allows an object to handle elements.
 * 
 * @author Aditya Srinivasan, Harry Guo, Arjun Desai, Nick Lockett
 *
 */

public interface EditElementable extends EditSpritable {

	/**
	 * Add elements to a data structure container
	 * @param elements: to add
	 */
	public void addGameElements (Node ... elements);

	/**
	 * Removes elements from the data structure container
	 * @param elements: to remove
	 */
	public void removeGameElements (Node ... elements);

	/**
	 * Gets the list of elements
	 * @return the list of elements
	 */
	public List<Node> getElements ();

	/**
	 * Adds element ID to a data structure container
	 * @param id
	 */
	public void addElementId (String id);

	/**
	 * Gets the node form of an element based on the ID
	 * @param id: of the desired element
	 * @return
	 */
	public Node getElement (String id);

	/**
	 * Gets the set of all the ID's
	 * @return the set of element ID's
	 */
	public Set<String> getIds ();

	/**
	 * Checks to see if object contains element
	 * @param id: element to check
	 * @return boolean expression of result
	 */
	public boolean hasElement (String id);

	/**
	 * Gets the Elementable 
	 * @param id: of elementable
	 * @return the desired elementable
	 */
	public Elementable getVoogaElement (String id);

	/**
	 * Gets the Sprite names collection
	 * @return: collection of sprite names
	 */
	public Collection<String> getMySpriteNames ();
}
