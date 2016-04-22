package authoring.gui.cartography;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import authoring.UIManager;
import authoring.VoogaScene;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.model.ElementManager;
import authoring.resourceutility.ButtonMaker;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;
import player.gamedisplay.Menuable;
import resources.VoogaBundles;

public class LevelCartographer extends Stage {

	private static final double WINDOW_WIDTH = 1000;
	private static final double WINDOW_HEIGHT = 800;
	private static final double CIRCLE_SIZE = 200;
	private static final double CIRCLE_DEGREES = 360;
	private static final double RING_SIZE = 300;
	private static final double INCREASE_FACTOR = 1.2;
	
	private BorderPane myGUI;
	private Group myMap;
	private List<String> levelNames;
	private List<Level> levels;
	
	private UIManager manager;

	public LevelCartographer(Menuable model) {
		this.manager = (UIManager) model;
		initializeScene();
		loadLevels();
		loadLinesAndPoints();
		populate();
	}

	private void initializeScene() {
		myGUI = new BorderPane();
		myMap = new Group();
		myGUI.setCenter(myMap);
		myGUI.setBottom(buttons());
		this.setScene(new VoogaScene(myGUI, WINDOW_WIDTH, WINDOW_HEIGHT));
	}

	private HBox buttons() {
		HBox container = new HBox();
		container.getChildren().addAll(new ButtonMaker().makeButton("Add connection", e -> addConnector()),
				new ButtonMaker().makeButton("Make entrypoint", e -> makeEntrypoint()));
		container.setAlignment(Pos.CENTER);
		return container;
	}

	private void addConnector() {
		Connection connector = new Connection(this.manager.getManager());
		connector.getStartAnchor().centerXProperty().addListener((obs, old, n) -> {
			for (Level level : levels) {
				if (connector.getStartAnchor().getBoundsInParent().intersects(level.getBoundsInParent())) {
					connector.setStartpoint(level.getName());
				}
			}
		});
		connector.getEndAnchor().centerXProperty().addListener((obs, old, n) -> {
			for (Level level : levels) {
				if (connector.getEndAnchor().getBoundsInParent().intersects(level.getBoundsInParent())) {
					connector.setEndpoint(level.getName());
				}
			}
		});
		myMap.getChildren().addAll(connector);
	}

	private void makeEntrypoint() {
		Entrypoint ep = Entrypoint.getInstance();
		if (!myMap.getChildren().contains(ep)) {
			addEntrypoint(ep);
		}
		ep.setRadius(INCREASE_FACTOR * CIRCLE_SIZE / levelNames.size());
	}
	
	private void addEntrypoint(Entrypoint circ) {
		myMap.getChildren().add(circ);
		circ.centerXProperty().addListener((obs, old, n) -> {
			for (Level level : levels) {
				if (circ.getBoundsInParent().intersects(level.getBoundsInParent())) {
					circ.setEntrypoint(level.getName());
					return;
				}
			}
		});
		circ.toBack();
	}

	private void loadLevels() {
		// ================================================================================|
		// Temporary code until level saving and loading can be implemented
		// completely. |
		// ================================================================================|
		levels = new ArrayList<Level>();
		levelNames = this.manager.getAllManagerNames();
	}
	
	private void loadLinesAndPoints() {
		
	}

	private void populate() {
		for (int i = 0; i < levelNames.size(); i++) {
			Level circ = new Level(levelNames.get(i), CIRCLE_SIZE / levelNames.size());
			circ.setTranslateX(RING_SIZE * Math.cos(Math.toRadians((i - 1) * (CIRCLE_DEGREES / levelNames.size()))));
			circ.setTranslateY(RING_SIZE * Math.sin(Math.toRadians((i - 1) * (CIRCLE_DEGREES / levelNames.size()))));
			levels.add(circ);
			myMap.getChildren().add(circ);
			StackPane.setAlignment(circ, Pos.CENTER);
		}
	}

}
