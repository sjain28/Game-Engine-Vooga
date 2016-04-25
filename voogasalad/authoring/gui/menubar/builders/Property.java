package authoring.gui.menubar.builders;

import javafx.scene.Node;
import tools.interfaces.VoogaData;

public class Property {

	private String name;
	private VoogaData value;

	public Property(String name, VoogaData value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public Node getValue() {
		return this.value.display();
	}

}
