package tools;

import javafx.beans.property.Property;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tools.interfaces.VoogaData;

/**
 * Handling and passing of images in the salad
 */
public class VoogaDataImage implements VoogaData {

	String imagePath;
	ImageView image;

	public VoogaDataImage(String imagePath) {
		this.imagePath = imagePath;
	}

	/**
	 * Return data image as a node
	 */
	@Override
	public Node display() {
		Button button = new Button();
		button.setGraphic(new ImageView(new Image(this.getClass()
				.getResourceAsStream(imagePath))));
		return button;
	}

	/**
	 * Getters and setters below
	 */
	@Override
	public Object getValue() {
		return imagePath;
	}

	@Override
	public void setValue(Object o) {

	}

	@Override
	public Property<Object> getProperty() {
		return null;
	}

	@Override
	public <T> void setProperty(T newVal) {

	}
}
