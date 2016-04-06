package authoring.gui.items;

/**
 * The New Property Factory that creates a new VoogaData based on the user input from the GUI.
 * @author Harry Guo, Aditya Srinivasan, Arjun Desai
 */

import java.util.ArrayList;

import tools.interfaces.VoogaData;

public class NewPropertyFactory {

	private final String COMMAND_PATH = "tools.";
	private ArrayList<String> myChoices = new ArrayList<String>();
	
	/**
	 * Constructor to populate list of choices
	 */
	public NewPropertyFactory() {
		populateChoices();
	}
	
	/**
	 * Method to populate choices for user to pick from (all forms of VoogaData)
	 */
	private void populateChoices() {
		myChoices.add("VoogaNumber");
		myChoices.add("VoogaBoolean");
	}
	
	/**
	 * @return list of choices
	 */
	public ArrayList<String> getChoices() {
		return myChoices;
	}
	
	/**
	 * Given the String of the type of Vooga Data they want a certain variable to be, instantiates the new property.
	 * @param s (String of type of Vooga Data)
	 * @return new Class of that Vooga Data
	 */
	public VoogaData createNewProperty(String s) {
		VoogaData vgData = null;
		try {
			Class c = Class.forName(COMMAND_PATH + s);
			vgData = (VoogaData) c.newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return vgData;
	}
	
}

