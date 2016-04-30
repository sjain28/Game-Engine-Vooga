package authoring.tagextension;

import authoring.CustomText;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * A simple display of a tag, but made to look pretty.
 * 
 * @author adityasrinivasan
 *
 */
public class TagLabel extends HBox {
	
	/**
	 * Constants
	 */
	private static final double SPACING = 7;
	private static final double INSETS = 5;
	private static final double RADIUS = 10;
	private static final String DELETE = "x";
	
	private String tag;
	private CustomText delete;
	
	/**
	 * Constructs the tag, placing the name of the tag and a symbol for deletion inside
	 * of the HBox.
	 * @param tag: the tag name to display.
	 */
	public TagLabel(String tag) {
		this.tag = tag;
		delete = new CustomText(DELETE);
		this.getChildren().addAll(new CustomText(tag), delete);
		this.setPadding(new Insets(INSETS));
		this.setSpacing(SPACING);
		this.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, new CornerRadii(RADIUS), Insets.EMPTY)));
	}
	
	public void setOnDeletion(EventHandler<MouseEvent> e) {
		this.delete.setOnMouseClicked(e);
	}
	
	/**
	 * Returns the tag name.
	 */
	@Override
	public String toString() {
		return tag;
	}

}
