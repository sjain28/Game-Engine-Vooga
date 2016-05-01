package authoring.gui.cartography;

import javafx.scene.paint.Color;

/**
 * Class to that toggles the display of the endpoint on the connection line.
 * 
 * @author Harry Guo, Nick Lockett, Arjun Desai, Aditya Srinivasan
 *
 */
public class Endpoint extends Point {

	private String endLevel;

	public Endpoint() {
		super(0, Color.ORANGERED);
	}

	/**
	 * Sets the end point to a level name.
	 * @param name
	 */
	@Override
	protected void setPoint(String name) {
		this.endLevel = name;
	}

	/**
	 * Gets the level name that the point is on.
	 * @return
	 */
	@Override
	protected String getPoint() {
		return this.endLevel;
	}

	/**
	 * Get the level type that the point defines.
	 * In this case, it is an endpoint.
	 * @return
	 */
	@Override
	protected LevelType getLevelType() {
		return LevelType.ENDPOINT;
	}

}
