package authoring;

import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * A custom extension of JavaFX's Scene, that applies CSS stylings automatically
 *
 */
public class VoogaScene extends Scene {
	
	private static final String STYLESHEET_PATH = "/application/application.css";
	
	public VoogaScene(Parent parent) {
		super(parent);
		this.getStylesheets().add(STYLESHEET_PATH);
	}
	
	public VoogaScene(Parent parent, double width, double height) {
		super(parent, width, height);
		this.getStylesheets().add(STYLESHEET_PATH);
	}

}
