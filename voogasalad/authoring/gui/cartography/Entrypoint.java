package authoring.gui.cartography;

import javafx.scene.paint.Color;

/**
 * Class to that toggles the display of the endpoint on the connection line.
 * 
 * @author Harry Guo, Nick Lockett, Arjun Desai, Aditya Srinivasan
 *
 */

public class Entrypoint extends Point {

	private String entryLevel;

	/**
	 * Instantiates the Entrypoint and define the color.
	 */
	private Entrypoint() {
		super(0, Color.LIGHTGREEN);
	}

	/**
	 * Creating a singleton holder to have one static instance of an entry point.
	 */
	private static class SingletonHolder {
		private static final Entrypoint INSTANCE = new Entrypoint();
	}

	/**
	 * Gets the instance of the singleton holder of the entrypoint.
	 * @return
	 */
	public static Entrypoint getInstance() {
		return SingletonHolder.INSTANCE;
	}

	/**
	 * Sets the end point to a level name.
	 * @param name
	 */
	@Override
	protected void setPoint(String name) {
		this.entryLevel = name;
	}

	/**
	 * Gets the level name that the point is on.
	 * @return
	 */
	@Override
	protected String getPoint() {
		return this.entryLevel;
	}

	/**
	 * Get the level type that the point defines.
	 * In this case, it is an entrypoint.
	 * @return
	 */
	@Override
	protected LevelType getLevelType() {
		return LevelType.ENTRYPOINT;
	}



}
