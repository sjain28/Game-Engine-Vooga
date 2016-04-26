package authoring.tagextension;

import java.util.Observable;

import javafx.scene.layout.FlowPane;

/**
 * The region that holds and displays user-defined tags after they have been entered.
 * 
 * @author adityasrinivasan
 *
 */
public class TagDisplayContainer extends Observable {
	
	/**
	 * Constants
	 */
	private static final double VGAP = 8;
	private static final double HGAP = 4;
	
	private FlowPane container;
	
	public TagDisplayContainer() {
		container = new FlowPane();
		container.setVgap(VGAP);
	    container.setHgap(HGAP);
	}
	
	/**
	 * Adds a tag to the FlowPane, and allows it to be removed on click.
	 * @param tag: the tag name
	 */
	public void addTag(String tag) {
		TagLabel label = new TagLabel(tag);
		label.setOnDeletion(e -> {
			container.getChildren().remove(label);
			setChanged();
			notifyObservers(container.getChildren());
		});
		container.getChildren().add(label);
		setChanged();
		notifyObservers(container.getChildren());
	}
	
	/**
	 * Returns the FlowPane object that actually holds the tags.
	 * @return
	 */
	public FlowPane display() {
		return this.container;
	}

}
