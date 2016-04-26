package authoring.model;

import java.util.List;

public class Preferences {
	
	private String name;
	private String description;
	private String width;
	private String height;
	private List<String> managerNames;
	
	public Preferences(String name, String description, String width, String height, List<String> managerNames) {
		this.name = name;
		this.description = description;
		this.width = width;
		this.height = height;
		this.managerNames = managerNames;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public String getWidth() {
		return this.width;
	}
	
	public String getHeight() {
		return this.height;
	}
	
	public List<String> getManagerNames() {
		return this.managerNames;
	}

}
