package authoring.properties;

import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import resources.VoogaBundles;

/**
 * Property Box to contain a node and key.
 * To display in the properties pane.
 * 
 * @author Harry Guo, Aditya Srinivasan, Nick Lockett, Arjun Desai
 *
 */

public class PropertyBox extends HBox {

	private ResourceBundle ppProperties;
	
	public PropertyBox(Node key, Node value) {
		ppProperties = VoogaBundles.propertiesPaneProperties;
		this.setSpacing(Double.parseDouble(ppProperties.getString("Spacing")));
		this.getChildren().addAll(key, value);
	}

}
