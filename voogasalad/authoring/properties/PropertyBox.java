package authoring.properties;

import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class PropertyBox extends HBox {
	
	public PropertyBox(Node key, Node value) {
		this.setSpacing(10);
		this.getChildren().addAll(key, value);
	}

}
