package authoring.gui.levelpreferences;

import java.util.List;
import java.util.ResourceBundle;

import authoring.CustomText;
import authoring.gui.SpriteNameIDPair;
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
import javafx.scene.control.Toggle;
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
	
	private static final double MIN_SPEED = 0;
	private static final double MAX_SPEED = 5;
	private static final double DEF_SPEED = 1;

	private VBox container;

	private TextField levelName;

	private RadioButton realistic;
	private RadioButton cartoon;
	private RadioButton continuous;
	private RadioButton tracking;
	
	private ToggleGroup trackingMode;
	private ToggleGroup physicsType;

	private Slider scrollSpeed;
	private ComboBox<SpriteNameIDPair> sprites;
	private ComboBox<String> continuousScrollType;
	private TextField angle;

	private HBox buttons;
	private HBox continuousControl;
	
	private CustomText speedLabel;

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
		makeContinuousControl();
	}

	/**
	 * Sets the name of the level.
	 * 
	 * @param name: name of level
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
		physicsType = new ToggleGroup();
		return createToggleGroup(physicsType, "Physics Module:", realistic, cartoon);
	}

	/**
	 * Choose tracking mode prompt.
	 * 
	 * @return: tracking prompt
	 */
	private HBox chooseTrackingMode() {
		continuous = new RadioButton("Continuous");
		tracking = new RadioButton("Tracking");
		trackingMode = new ToggleGroup();
		return createToggleGroup(trackingMode, "Scrolling Mode:", continuous, tracking);
	}

	/**
	 * Generates specific tracking speed based on user selection.
	 */
	private void chooseSpecificTrackingMode() {
		trackingMode.selectedToggleProperty().addListener((obs, old, n) -> {
			container.getChildren().remove(buttons);
			if(n == continuous) {
				container.getChildren().add(continuousControl);
				container.getChildren().remove(sprites);
			} else {
				container.getChildren().remove(continuousControl);
				container.getChildren().add(sprites);
			}
			container.getChildren().add(buttons);
		});
	}
	
	private HBox makeContinuousControl() {
		speedLabel = new CustomText("0");
		angle = new TextField();
		angle.setPromptText("Enter an angle, 0: right, 90: down, ...");
		scrollSpeed.valueProperty().addListener((obs, old, n) -> {
			speedLabel.setText(Double.toString((double) n));
		});
		continuousScrollType = new ComboBox<String>();
		continuousScrollType.getItems().addAll("Linear", "Exponential");
		continuousControl = makeRow(angle, continuousScrollType, scrollSpeed, speedLabel, sprites);
		return continuousControl;
	}

	/**
	 * Initializes slider for scroll speed (if continuous)
	 */
	private void initializeSpecifics() {
		scrollSpeed = new Slider(MIN_SPEED, MAX_SPEED, DEF_SPEED);
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
	 * Creates button row at bottom of selection panel.
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
	private HBox createToggleGroup(ToggleGroup group, String label, RadioButton... toggles) {
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
	 * @return the sprite to track based on ID
	 */
	public String getMainSpriteID() {
		return (sprites.getValue() == null) ? "" : sprites.getValue().getID();
	}

	public String getScrollingType() {
		return ((RadioButton) trackingMode.getSelectedToggle()).getText();
	}
	
	public Double getContinuousScrollSpeed() {
		return scrollSpeed.getValue();
	}
	
	public Double getScrollAngle() {
		return (this.angle.getText().isEmpty()) ? 0 : Double.parseDouble(this.angle.getText());
	}
	
	public String getContinuousScrollType() {
		return continuousScrollType.getValue();
	}
	
	public void setPhysics(String name) {
		for(Toggle toggle : physicsType.getToggles()) {
			if(((RadioButton) toggle).getText().equals(name)) {
				physicsType.selectToggle(toggle);
			}
		}
	}
	
	public void setScrolling(String name) {
		for(Toggle toggle : trackingMode.getToggles()) {
			if(((RadioButton) toggle).getText().equals(name)) {
				trackingMode.selectToggle(toggle);
			}
		}
	}

	public void setAngle(String value) {
		this.angle.setText(value);
	}
	
	public void setSpeed(Double value) {
		this.scrollSpeed.setValue(value);
	}
	
	public void setContinuousScrollType(String type) {
		this.continuousScrollType.getSelectionModel().select(type);
	}

	public void setMainSprite(String value) {
		for(SpriteNameIDPair s : sprites.getItems()) {
			if(s.getID().equals(value)) {
				this.sprites.getSelectionModel().select(s);
				break;
			}
		}
	}

}