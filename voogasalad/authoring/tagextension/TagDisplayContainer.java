package authoring.tagextension;

import java.util.Observable;

import javafx.scene.layout.FlowPane;

public class TagDisplayContainer extends Observable {
	
	private static final double VGAP = 8;
	private static final double HGAP = 4;
	
	private FlowPane container;
	
	public TagDisplayContainer() {
		container = new FlowPane();
		container.setVgap(VGAP);
	    container.setHgap(HGAP);
	}
	
	public void addTag(String tag) {
		TagLabel label = new TagLabel(tag);
		label.setOnMouseClicked(e -> {
			container.getChildren().remove(label);
			setChanged();
			notifyObservers(container.getChildren());
		});
		container.getChildren().add(label);
		setChanged();
		notifyObservers(container.getChildren());
	}
	
	public FlowPane display() {
		return this.container;
	}

}
