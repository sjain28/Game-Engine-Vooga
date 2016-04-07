package authoring.gui;

import authoring.interfaces.gui.Windowable;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GlobalPropertiesTab extends Tab {

	public GlobalPropertiesTab() {
		// TODO Auto-generated constructor stub
	}
	
	public void displayProperties() {
		propertiesPane.getChildren().clear();
		propertyName.getChildren().clear();
		propertyVoogaData.getChildren().clear();
		propertiesHBox.getChildren().clear();

		for(String str: propertiesMap.keySet()) {
			//can refactor here
			Text name = new Text(str);
			name.setFill(Paint.valueOf("WHITE"));
			name.setFont(new Font(23));

			// can refactor here
			ContextMenu menu = new ContextMenu();
			MenuItem delete = new MenuItem("Delete");
			delete.setOnAction(e -> removeProperty(str));
			menu.getItems().add(delete);

			name.setOnMouseClicked(e -> {
				if (e.getButton() == MouseButton.SECONDARY) {
					menu.show(myScrollPane, e.getScreenX(), e.getScreenY());
				}
			});

			propertyName.getChildren().add(name);
			propertyVoogaData.getChildren().add(propertiesMap.get(str).display());
		}

		propertiesHBox.getChildren().addAll(propertyName, propertyVoogaData);
		myScrollPane.setContent(propertiesHBox);
		propertiesPane.getChildren().add(myScrollPane);

		createButtons();
	}

	public Tab getThisTab() {
		return this;
	}

}
