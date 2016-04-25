package authoring.gui;

public class SpriteNameIDPair {
	
	private String name;
	private String ID;

	public SpriteNameIDPair(String name, String ID) {
		this.name = name;
		this.ID = ID;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getID() {
		return this.ID;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
}
