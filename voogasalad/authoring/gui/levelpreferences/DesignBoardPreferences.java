package authoring.gui.levelpreferences;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import authoring.CustomText;
import authoring.gui.SpriteNameIDPair;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.model.GameObject;
import authoring.resourceutility.ButtonMaker;
import gameengine.Sprite;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import resources.VoogaBundles;
import tools.interfaces.VoogaData;

public class DesignBoardPreferences extends Tab {

	private static final double SPACING = 10;
	private static final double WIDTH = 500;

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

	public DesignBoardPreferences(CompleteAuthoringModelable model) {
		gameObjects = model.getElements();
		container = new VBox();
		container.setSpacing(SPACING);
		container.setAlignment(Pos.CENTER);
		this.setContent(container);
		container.getChildren().addAll(header(), chooseName(), choosePhysicsModule(), chooseTrackingMode());
		
		initializeSpecifics();
		chooseSpecificTrackingMode();
		makeContinuousControl();
	}
	
	public void setName(String name) {
		this.levelName.setText(name);
	}
	
	public void setListener(EventHandler<ActionEvent> proceed) {
		this.e = proceed;
		container.getChildren().add(buttonRow());
	}

	private HBox header() {
		return makeRow(new CustomText("Define your level.", FontWeight.BOLD, 20));
	}

	private HBox chooseName() {
		levelName = new TextField();
		return makeRow(new CustomText("Level name:"), levelName);
	}

	private HBox choosePhysicsModule() {
		realistic = new RadioButton("Realistic");
		cartoon = new RadioButton("Cartoon");
		physicsType = new ToggleGroup();
		return createToggleGroup(physicsType, "Physics Module:", realistic, cartoon);
	}

	private HBox chooseTrackingMode() {
		continuous = new RadioButton("Continuous");
		tracking = new RadioButton("Tracking");
		trackingMode = new ToggleGroup();
		return createToggleGroup(trackingMode, "Scrolling Mode:", continuous, tracking);
	}

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
		continuousControl = makeRow(angle, continuousScrollType, scrollSpeed, speedLabel);
		return continuousControl;
	}

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
			sprites.getItems().add(new SpriteNameIDPair("<No game objects created yet to track>", ""));
		}
	}

	private HBox buttonRow() {
		Button ok = new ButtonMaker().makeButton("OK", this.e);
		buttons = makeRow(ok);
		return buttons;
	}

	private HBox createToggleGroup(ToggleGroup group, String label, RadioButton... toggles) {
		HBox row = makeRow(new CustomText(label));
		for (RadioButton toggle : toggles) {
			toggle.setToggleGroup(group);
			row.getChildren().add(toggle);
		}
		return row;
	}

	private HBox makeRow(Node... nodes) {
		HBox row = new HBox();
		row.setSpacing(SPACING);
		row.setMaxWidth(WIDTH);
		row.getChildren().addAll(nodes);
		return row;
	}

	public String getName() {
		return this.levelName.getText();
	}

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
		return Double.parseDouble(this.angle.getText());
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

}