package authoring.gui.levelpreferences;

import java.util.Arrays;
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
import tools.GUIUtils;
import tools.VoogaAlert;
import tools.VoogaFileChooser;

/**
 * Tab that allows the user to define their preferences for the design board in terms of
 * level name, type of scrolling, and physics module.
 * 
 * @author Aditya Srinivasan
 *
 */

public class DesignBoardPreferences extends Tab {
	private static final double MIN_SPEED = 0;
	private static final double MAX_SPEED = 5;
	private static final double DEF_SPEED = 1;

	private double spacing;
	private double width;
	private List<Node> gameObjects;
	private VBox container;
	private HBox buttons;
	private HBox continuousControl;
	private HBox trackingControl;
	private TextField levelName;
	private TextField angle;
	private RadioButton continuous;
	private RadioButton tracking;
	private ToggleGroup trackingMode;
	private Slider scrollSpeed;
	private ComboBox<SpriteNameIDPair> sprites;
	private ComboBox<SpriteNameIDPair> contSprites;
	private ComboBox<String> continuousScrollType;
	private ComboBox<String> trackingDirection;
	private CustomText speedLabel;
	private EventHandler<ActionEvent> e;
	private ResourceBundle dbfProperties;
	private String soundTrackPath;

	/**
	 * Constructor to build the pop up for the user to specify preferences.
	 * 
	 * @param model: interface for back end and contains information when loading design board
	 */
	public DesignBoardPreferences(CompleteAuthoringModelable model) {
		gameObjects = model.getElements();
		container = new VBox();

		dbfProperties = VoogaBundles.designboardPreferencesProperties;
		spacing = Double.parseDouble(dbfProperties.getString("Spacing"));
		width = Double.parseDouble(dbfProperties.getString("Width"));

		container.setSpacing(spacing);
		container.setAlignment(Pos.CENTER);
		container.getChildren().addAll(header(), chooseName(), chooseBGM(), chooseTrackingMode());
		this.setContent(container);
		buttons = new HBox();
		sprites = spriteBox();
		contSprites = spriteBox();

		initializeSpecifics();
		chooseSpecificTrackingMode();
		makeContinuousControl();
		makeTrackingControl();
	}

	private Button chooseBGM() {
		return new ButtonMaker().makeButton("Choose Soundtrack", e -> {
			VoogaFileChooser chooser = new VoogaFileChooser();
			try {
				this.soundTrackPath = chooser.launch();
			} catch (Exception ee) {
				VoogaAlert alert = new VoogaAlert("Not a correct file.");
				alert.showAndWait();
			}
		});
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
		return  customHBox(GUIUtils.makeRow(new CustomText(dbfProperties.getString("DefineLevelName"), FontWeight.BOLD, 
				Integer.parseInt(dbfProperties.getString("HeaderSpacing")))));
	}

	private HBox customHBox(HBox box) {
		box.setAlignment(Pos.CENTER);
		return box;
	}

	/**
	 * Choose name prompt.
	 * 
	 * @return: level name prompt
	 */
	private HBox chooseName() {
		levelName = new TextField();
		return customHBox(GUIUtils.makeRow(new CustomText(dbfProperties.getString("LevelNamePrompt")), levelName));
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
				container.getChildren().remove(trackingControl);
				container.getChildren().add(continuousControl);
			} else {
				container.getChildren().remove(continuousControl);
				container.getChildren().add(trackingControl);
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
		continuousScrollType = new ComboBox<>();
		continuousScrollType.getItems().addAll("Linear", "Exponential");
		continuousControl = customHBox(GUIUtils.makeRow(angle, continuousScrollType, contSprites, scrollSpeed, speedLabel));
		return continuousControl;
	}

	private HBox makeTrackingControl() {
		trackingDirection = new ComboBox<>();
		trackingDirection.getItems().addAll(Arrays.asList("X", "Y", "Both"));
		trackingControl = customHBox(GUIUtils.makeRow(sprites, trackingDirection));
		return trackingControl;
	}

	/**
	 * Initializes slider for scroll speed (if continuous)
	 */
	private void initializeSpecifics() {
		scrollSpeed = new Slider(MIN_SPEED, MAX_SPEED, DEF_SPEED);
		scrollSpeed.setMaxWidth(width);
	}

	private ComboBox<SpriteNameIDPair> spriteBox() {
		ComboBox<SpriteNameIDPair> sprites = new ComboBox<>();
		sprites.getItems().clear();
		if (!gameObjects.isEmpty()) {
			for (Node node : this.gameObjects) {
				if (node instanceof GameObject) {
					Sprite sprite = ((GameObject) node).getSprite();
					sprites.getItems().add(new SpriteNameIDPair(sprite.getName(), sprite.getId()));
				}
			}
		} else {
			sprites.getItems().add(new SpriteNameIDPair(dbfProperties.getString("NoSpriteToTrack"), ""));
		}
		return sprites;
	}

	/**
	 * Creates button row at bottom of selection panel.
	 * 
	 * @return
	 */
	private HBox buttonRow() {
		Button ok = new ButtonMaker().makeButton(dbfProperties.getString("Ok"), this.e);
		buttons = customHBox(GUIUtils.makeRow(ok));
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
		HBox row = customHBox(GUIUtils.makeRow(new CustomText(label)));
		for (RadioButton toggle : toggles) {
			toggle.setToggleGroup(group);
			row.getChildren().add(toggle);
		}
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
		if(contSprites.getValue() == null && sprites.getValue() != null) {
			return sprites.getValue().getID();
		}
		if(sprites.getValue() == null && contSprites.getValue() != null) {
			return contSprites.getValue().getID();
		}
		return "";
	}

	/**
	 * Get scrolling type selected.
	 * @return
	 */
	public String getScrollingType() {
		return ((RadioButton) trackingMode.getSelectedToggle()).getText();
	}

	/**
	 * Get continuous scroll speed selected.
	 * @return
	 */
	public Double getContinuousScrollSpeed() {
		return scrollSpeed.getValue();
	}

	/**
	 * Get scroll angle selected.
	 * @return
	 */
	public Double getScrollAngle() {
		return (this.angle.getText().isEmpty()) ? 0 : Double.parseDouble(this.angle.getText());
	}

	/**
	 * Get continuous scroll type selected.
	 * @return
	 */
	public String getContinuousScrollType() {
		return continuousScrollType.getValue();
	}

	/**
	 * Set the type of scrolling.
	 * @param name: type of scrolling
	 */
	public void setScrolling(String name) {
		for(Toggle toggle : trackingMode.getToggles()) {
			if(((RadioButton) toggle).getText().equals(name)) {
				trackingMode.selectToggle(toggle);
			}
		}
	}

	/**
	 * Set angle to scroll at.
	 * @param value
	 */
	public void setAngle(String value) {
		this.angle.setText(value);
	}

	/**
	 * Set scrolling speed.
	 * @param value
	 */
	public void setSpeed(Double value) {
		this.scrollSpeed.setValue(value);
	}

	/**
	 * Select the continuous scroll type.
	 * @param type
	 */
	public void setContinuousScrollType(String type) {
		this.continuousScrollType.getSelectionModel().select(type);
	}

	/**
	 * Sets the main sprite for scrolling to center around.
	 * @param value
	 */
	public void setMainSprite(String value) {
		for(SpriteNameIDPair s : sprites.getItems()) {
			if(s.getID().equals(value)) {
				this.sprites.getSelectionModel().select(s);
				break;
			}
		}
	}

	/**
	 * Get tracking direction selected.
	 * @return
	 */
	public String getTrackingDirection() {
		return (trackingDirection.getValue() == null) ? "" : trackingDirection.getValue();
	}

	/**
	 * Sets the value of the tracking direction combo box.
	 * @param value
	 */
	public void setTrackingDirection(String value) {
		this.trackingDirection.setValue(value);
	}

	/**
	 * Get the background music path.
	 * @return
	 */
	public String getBGM() {
		return (this.soundTrackPath.isEmpty() || this.soundTrackPath == null) ? "" : this.soundTrackPath;
	}
	
	/**
	 * set the background music.
	 * @param BGM
	 */
	public void setBGM(String BGM) {
		this.soundTrackPath = BGM;
	}

}