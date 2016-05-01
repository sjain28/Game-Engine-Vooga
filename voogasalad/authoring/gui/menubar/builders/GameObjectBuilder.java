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
import tools.GUIUtils;
import tools.VoogaAlert;

/**
 * Builder class for Game Objects.
 * 
 * @author Aditya Srinivasan, Harry Guo, Arjun Desai, Nick Lockett
 *
 */

public class GameObjectBuilder extends Builder {

	/**
	 * private instance variables
	 */
	private EditElementable editor;
	private TextField myName;
	private ComboBox<String> archetypes;
	private VBox container;
	private ArchetypeBuilder archetypeBuilder;
	private String path;

	/**
	 * Initializes selection choices in the dialog box.
	 * @param editor
	 */
	public GameObjectBuilder (EditElementable editor) {
		super(editor);
		this.editor = editor;
		populate();
		load(this.container);
	}

	/**
	 * Populates the builder dialog with options.
	 */
	private void populate () {
		this.container = new VBox();
		this.container.setSpacing(SPACING);
		this.myName = new TextField();

		container.getChildren().addAll(makeInfo("Name:", "Enter a name...", myName),
				makeArchetypePicker(), makeAddNewArchetypeButton(),
				makeButtons());
	}

	/**
	 * Method for add new archetype button.
	 * @return
	 */
	private HBox makeAddNewArchetypeButton () {
		HBox container = new HBox();
		Button button = new ButtonMaker().makeButton("Add a new archetype", e -> {
			archetypeBuilder = new ArchetypeBuilder(editor);
			if (path != null) {
				archetypeBuilder.setImagePath(path);
			}
			archetypeBuilder.showAndWait();
			archetypes.getItems().add(archetypeBuilder.getArchetypeName());
			archetypes.setValue(archetypeBuilder.getArchetypeName());
		});
		container.getChildren().add(button);
		return container;
	}

	/**
	 * Compiles information into manager/editor.
	 */
	@Override
	public void compile () {

		if (editor.getSpriteFactory().getAllArchetypeNames().contains(myName.getText()) ||
				editor.getMySpriteNames().contains(myName.getText())) {
			VoogaAlert alert = new VoogaAlert("This name already exists");
			alert.showAndWait();
			return;
		}

		compile = true;
		try {
			Sprite sprite = mySpriteFactory.createSprite(archetypes.getValue());
			GameObject object = new GameObject(sprite, myName.getText());
			myManager.addGameElements(object);
			quit();
		}
		catch (Exception e) {
			numberError("Please select an Archtype");
		}
	}

	/**
	 * Sets an archetype.
	 * @param string
	 */
	public void setArchetype (String string) {
		archetypes.setValue(string);
		archetypes.setDisable(true);
	}

	/**
	 * Makes an archetype picker.
	 * @return
	 */
	private HBox makeArchetypePicker () {
		archetypes = new ComboBox<>();
		Collection<String> items;
		items =
				(mySpriteFactory.getAllArchetypeNames().size() > 0) ? mySpriteFactory
						.getAllArchetypeNames()
						: new ArrayList<String>() {
							{
								add("<No archetypes made yet>");
							}
						};
						archetypes.getItems().addAll(items);
						return GUIUtils.makeRow(new CustomText("Select an archetype:"), archetypes);
	}

	/**
	 * Set image into local path.
	 * @param path
	 */
	public void setDraggedImage (String path) {
		this.path = path;
	}

	/**
	 * Compile status for debugging purposes.
	 */
	@Override
	public boolean compileStatus () {
		return compile;
	}

}
