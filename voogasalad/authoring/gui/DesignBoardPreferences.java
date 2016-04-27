package authoring.gui;

import java.util.List;
import java.util.ResourceBundle;

import authoring.CustomText;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.model.GameObject;
import authoring.resourceutility.ButtonMaker;
import gameengine.Sprite;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import resources.VoogaBundles;

/**
 * Tab that allows the user to define their preferences for the design board in terms of
 * level name, type of scrolling, and physics module.
 * 
 * @author Harry Guo, Nick Lockett, Aditya Srinivasan, Arjun Desai
 *
 */

public class DesignBoardPreferences extends Tab {

	private double SPACING;
	private double WIDTH;

	private VBox container;

	private TextField levelName;

	private RadioButton realistic;
	private RadioButton cartoon;
	private RadioButton continuous;
	private RadioButton tracking;

	private Slider scrollSpeed;
	private ComboBox<SpriteNameIDPair> sprites;

	private HBox buttons;

	private List<Node> gameObjects;

	private EventHandler<ActionEvent> e;

	private ResourceBundle dbfProperties;

	/**
	 * Constructor to build the pop up for the user to specify preferences.
	 * 
	 * @param model: interface for back end and contains information when loading design board
	 */
	public DesignBoardPreferences(CompleteAuthoringModelable model) {
		gameObjects = model.getElements();
		container = new VBox();
		
		dbfProperties = VoogaBundles.designboardPreferencesProperties;
		SPACING = Double.parseDouble(dbfProperties.getString("Spacing"));
		WIDTH = Double.parseDouble(dbfProperties.getString("Width"));

		container.setSpacing(SPACING);
		container.setAlignment(Pos.CENTER);
		this.setContent(container);
		container.getChildren().addAll(header(), chooseName(), choosePhysicsModule(), chooseTrackingMode());

		initializeSpecifics();
		chooseSpecificTrackingMode();
	}

	/**
	 * Sets the name of the level.
	 * 
	 * @param name: name of leve
	 */
	public void setName(String name) {
		this.levelName.setText(name);
	}

	/**
	 * Sets the listener for the Design Board Preferences.
	 * Connects to other parts of the GUI such as Design Board Housing and Pref File Item.
	 * 
	 * @param proceed: event
	 */
	public void setListener(EventHandler<ActionEvent> proceed) {
		this.e = proceed;
		container.getChildren().add(buttonRow());
	}

	/**
	 * Title for pop up box
	 * 
	 * @return: title 
	 */
	private HBox header() {
		return makeRow(new CustomText(dbfProperties.getString("DefineLevelName"), FontWeight.BOLD, 
				Integer.parseInt(dbfProperties.getString("HeaderSpacing"))));
	}

	/**
	 * Choose name prompt.
	 * 
	 * @return: level name prompt
	 */
	private HBox chooseName() {
		levelName = new TextField();
		return makeRow(new CustomText(dbfProperties.getString("LevelNamePrompt")), levelName);
	}

	/**
	 * Choose physics module prompt.
	 * 
	 * @return: physics prompt
	 */
	private HBox choosePhysicsModule() {
		realistic = new RadioButton("Realistic");
		cartoon = new RadioButton("Cartoon");
		return createToggleGroup("Physics Module:", realistic, cartoon);
	}

	/**
	 * Choose tracking mode prompt.
	 * 
	 * @return: tracking prompt
	 */
	private HBox chooseTrackingMode() {
		continuous = new RadioButton("Continuous");
		tracking = new RadioButton("Tracking");
		return createToggleGroup("Scrolling Mode:", continuous, tracking);
	}

	/**
	 * Generates specific tracking speed based on user selection.
	 */
	private void chooseSpecificTrackingMode() {
		continuous.selectedProperty().addListener((obs, old, n) -> {
			container.getChildren().remove(buttons);
			if (n) {
				container.getChildren().add(scrollSpeed);
				container.getChildren().remove(sprites);
			} else {
				container.getChildren().add(sprites);
				container.getChildren().remove(scrollSpeed);
			}
			container.getChildren().add(buttons);
		});
	}

	/**
	 * Initializes slider for scroll speed (if continuous)
	 */
	private void initializeSpecifics() {
		scrollSpeed = new Slider();
		scrollSpeed.setMaxWidth(WIDTH);
		sprites = new ComboBox<SpriteNameIDPair>();
		if (this.gameObjects.size() > 0) {
			for (Node node : this.gameObjects) {
				if (node instanceof GameObject) {
					Sprite sprite = ((GameObject) node).getSprite();
					sprites.getItems().add(new SpriteNameIDPair(sprite.getName(), sprite.getId()));
				}
			}
		} else {
			sprites.getItems().add(new SpriteNameIDPair(dbfProperties.getString("NoSpriteToTrack"), ""));
		}
	}

	/**
	 * Creates utton row at bottom of selection panel.
	 * 
	 * @return
	 */
	private HBox buttonRow() {
		Button ok = new ButtonMaker().makeButton(dbfProperties.getString("Ok"), this.e);
		buttons = makeRow(ok);
		return buttons;
	}

	/**
	 * Helper method to create radio buttons
	 * 
	 * @param label: button name
	 * @param toggles: options for radio buttons
	 * @return
	 */
	private HBox createToggleGroup(String label, RadioButton... toggles) {
		ToggleGroup group = new ToggleGroup();
		HBox row = makeRow(new CustomText(label));
		for (RadioButton toggle : toggles) {
			toggle.setToggleGroup(group);
			row.getChildren().add(toggle);
		}
		return row;
	}

	/**
	 * Helper function to make an HBox row
	 * @param nodes
	 * @return
	 */
	private HBox makeRow(Node... nodes) {
		HBox row = new HBox();
		row.setSpacing(SPACING);
		row.setMaxWidth(WIDTH);
		row.getChildren().addAll(nodes);
		return row;
	}

	/**
	 * @return level name
	 */
	public String getName() {
		return this.levelName.getText();
	}

	/**
	 * @return if continuous scroll is selected
	 */
	public boolean isContinuous() {
		return this.continuous.isSelected();
	}

	/**
	 * @return the sprite to track based on ID
	 */
	public String getSpriteIDtoTrack() {
		return sprites.getValue().getID();
	}

}