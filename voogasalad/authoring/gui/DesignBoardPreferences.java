package authoring.gui;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import authoring.CustomText;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.model.GameObject;
import authoring.resourceutility.ButtonMaker;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import resources.VoogaBundles;

public class DesignBoardPreferences extends Tab {

	private static final double SPACING = 10;
	private static final double WIDTH = 300;

	private VBox container;

	private TextField levelName;

	private RadioButton realistic;
	private RadioButton cartoon;
	private RadioButton continuous;
	private RadioButton tracking;

	private Slider scrollSpeed;
	private ComboBox<String> sprites;

	private HBox buttons;

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
		return createToggleGroup("Physics Module:", realistic, cartoon);
	}

	private HBox chooseTrackingMode() {
		continuous = new RadioButton("Continuous");
		tracking = new RadioButton("Tracking");
		return createToggleGroup("Scrolling Mode:", continuous, tracking);
	}

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

	private void initializeSpecifics() {
		scrollSpeed = new Slider();
		scrollSpeed.setMaxWidth(WIDTH);
		sprites = new ComboBox<String>();
		if (this.gameObjects.size() > 0) {
			for (Node node : this.gameObjects) {
				if (node instanceof GameObject) {
					sprites.getItems().add(((GameObject) node).getSprite().getName());
				}
			}
		} else {
			sprites.getItems().add("<No game objects created yet to track>");
		}
	}

	private HBox buttonRow() {
		Button ok = new ButtonMaker().makeButton("OK", this.e);
		buttons = makeRow(ok);
		return buttons;
	}

	private HBox createToggleGroup(String label, RadioButton... toggles) {
		ToggleGroup group = new ToggleGroup();
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

}