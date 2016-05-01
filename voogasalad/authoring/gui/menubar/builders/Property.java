package authoring.gui.menubar.builders;

import javafx.scene.Node;
import tools.interfaces.VoogaData;

/**
 * Custom class to define what a property is.
 * Name and Value.
 * 
 * @author Aditya Srinivasan, Harry Guo, Arjun Desai, Nick Lockett
 *
 */
public class Property {

	private String name;
	private VoogaData value;

	/**
	 * Constructor to build property
	 * @param name
	 * @param value
	 */
	public Property(String name, VoogaData value) {
		this.name = name;
		this.value = value;
	}

	/**
	 * Returns the name of the property
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns the value of the property
	 * @return
	 */
	public Node getValue() {
		return this.value.display();
	}

}
