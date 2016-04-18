package authoring.gui.menubar.builders;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import authoring.CustomText;
import authoring.interfaces.model.EditElementable;
import authoring.model.GameObject;
import gameengine.Sprite;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tools.Vector;

public class GameObjectBuilder extends Builder {

	private String myArchtype;
	private VBox container;

	public GameObjectBuilder(EditElementable editor) {
		super(editor);
		populate();
		load(this.container);
	}

	private void populate() {
		this.container = new VBox();
		this.container.setSpacing(SPACING);
		container.getChildren().addAll(makeArchetypePicker(), makeButtons());
	}

	public void compile() {
		try {
			Sprite sprite = mySpriteFactory.createSprite(myArchtype);
			myManager.addGameElements(new GameObject(sprite));
			myManager.addElementId(myArchtype);
			quit();
		} catch (Exception e) {
			numberError("Please select an Archtype");
		}
	}

	private HBox makeArchetypePicker() {
		ComboBox<String> archetypes = new ComboBox<String>();
		Collection<String> items = new ArrayList<String>();
		items = (mySpriteFactory.getAllArchetypeNames().size() > 0) ? mySpriteFactory.getAllArchetypeNames() : new ArrayList<String>() {{
		    add("<No archetypes made yet>");
		}};
		archetypes.getItems().addAll(items);
		archetypes.setOnAction(e -> myArchtype = archetypes.getValue());
		return makeRow(new CustomText("Select an archetype:"), archetypes);
	}

}
