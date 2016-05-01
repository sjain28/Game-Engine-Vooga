package authoring.gui;

/**
 * Class to handle Sprite name and ID pair.
 * 
 * @author Nick Lockett, Arjun Desai, Aditya Srinivasan, Harry Guo
 *
 */

public class SpriteNameIDPair {
	
	private String name;
	private String ID;

	/**
	 * Constructor to initialize a name and ID pair.
	 * @param name
	 * @param ID
	 */
	public SpriteNameIDPair(String name, String ID) {
		this.name = name;
		this.ID = ID;
	}
	
	/**
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return ID
	 */
	public String getID() {
		return this.ID;
	}
	
	/**
	 * to String method just returns name
	 */
	@Override
	public String toString() {
		return this.name;
	}
	
}
