package authoring;

import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * A custom extension of JavaFX's Scene, that applies CSS stylings automatically
 * 
 * @author Aditya Srinivasan, Harry Guo, Nick Lockett, Arjun Desai
 *
 */
public class VoogaScene extends Scene {

	private static final String STYLESHEET_PATH = "/application/application.css";

	/**
	 * Initializes the new scene, automatically incorporating CSS
	 * 
	 * @param parent to house this scene
	 */
	public VoogaScene(Parent parent) {
		super(parent);
		this.getStylesheets().add(STYLESHEET_PATH);
	}

	/**
	 * Initializes the new scene, automatically incorporating CSS
	 * 
	 * @param parent to house this scene
	 * @param width of the new scene
	 * @param height of the new scene
	 */
	public VoogaScene(Parent parent, double width, double height) {
		super(parent, width, height);
		this.getStylesheets().add(STYLESHEET_PATH);
	}

	/**
	 * Initializes the new scene, custom CSS
	 * 
	 * @param parent to house this scene
	 * @param width of the new scene
	 * @param height of the new scene
	 */
	public VoogaScene(Parent parent, double width, double height, String csspath) {
		super(parent, width, height);
		this.getStylesheets().add(csspath);
	}

}
