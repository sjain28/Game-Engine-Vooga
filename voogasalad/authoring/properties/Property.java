package authoring.properties;

import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class Property extends HBox {
	
	public Property(Node key, Node value) {
		this.setSpacing(10);
		this.getChildren().addAll(key, value);
	}

}
