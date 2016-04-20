package authoring.gui.menubar.builders;

import java.util.ArrayList;
import java.util.Collection;
import authoring.CustomText;
import authoring.interfaces.model.EditElementable;
import authoring.model.GameObject;
import authoring.resourceutility.ButtonMaker;
import gameengine.Sprite;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameObjectBuilder extends Builder {

	private EditElementable editor;
	private TextField myName;
	private ComboBox<String> archetypes;
	private VBox container;

	public GameObjectBuilder(EditElementable editor) {
		super(editor);
		this.editor = editor;
		populate();
		load(this.container);
	}

	private void populate() {
		this.container = new VBox();
		this.container.setSpacing(SPACING);
		this.myName = new TextField();
		container.getChildren().addAll(makeInfo("Name:", "Enter a name...", myName), makeArchetypePicker(), makeAddNewArchetypeButton(),
				makeButtons());
	}
	
	private HBox makeAddNewArchetypeButton() {
		HBox container = new HBox();
		Button button = new ButtonMaker().makeButton("Add a new archetype", e -> {
			ArchetypeBuilder archetypeBuilder = new ArchetypeBuilder(this.editor);
			archetypeBuilder.showAndWait();
			archetypes.getItems().add(archetypeBuilder.getArchetypeName());
			archetypes.setValue(archetypeBuilder.getArchetypeName());
		});
		container.getChildren().add(button);
		return container;
	}

	public void compile() {
		try {
			Sprite sprite = mySpriteFactory.createSprite(archetypes.getValue());
			myManager.addGameElements(new GameObject(sprite, myName.getText()));
			quit();
		} catch (Exception e) {
			e.printStackTrace();
			numberError("Please select an Archtype");
		}
	}

	public void setArchetype(String string) {
		archetypes.setValue(string);
		archetypes.setDisable(true);
	}

	private HBox makeArchetypePicker() {
		archetypes = new ComboBox<String>();
		Collection<String> items = new ArrayList<String>();
		items = (mySpriteFactory.getAllArchetypeNames().size() > 0) ? mySpriteFactory.getAllArchetypeNames()
				: new ArrayList<String>() {
					{
						add("<No archetypes made yet>");
					}
				};
		archetypes.getItems().addAll(items);
		return makeRow(new CustomText("Select an archetype:"), archetypes);
	}

}
