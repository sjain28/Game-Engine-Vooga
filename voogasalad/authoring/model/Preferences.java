package authoring.model;

import java.util.List;

public class Preferences {
	
	private String name;
	private String description;
	private String width;
	private String height;
	private List<String> managerNames;
	/**
	 * Initializes a preference based upon Name, Description, width, height, and managers
	 * @param name
	 * @param description
	 * @param width
	 * @param height
	 * @param managerNames
	 */
	public Preferences(String name, String description, String width, String height, List<String> managerNames) {
		this.name = name;
		this.description = description;
		this.width = width;
		this.height = height;
		this.managerNames = managerNames;
	}
	
	/**
	 * returns preference name
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * returns preference description
	 * @return
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * returns preference width
	 * @return
	 */
	public String getWidth() {
		return this.width;
	}
	
	/**
	 * returns preference height
	 * @return
	 */
	public String getHeight() {
		return this.height;
	}
	
	/**
	 * returns managers names
	 * @return
	 */
	public List<String> getManagerNames() {
		return this.managerNames;
	}

}
