package authoring.gui.cartography;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import authoring.VoogaScene;
import authoring.interfaces.model.CompleteAuthoringModelable;
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

public class LevelCartographer extends Stage {
	
	private static final double WINDOW_WIDTH = 1000;
	private static final double WINDOW_HEIGHT = 800;
	private static final double CIRCLE_SIZE = 200;
	private static final double CIRCLE_DEGREES = 360;
	private static final double RING_SIZE = 300;
	private BorderPane myGUI;
	private Group myMap;
	private List<String> levels;
	
	public LevelCartographer(CompleteAuthoringModelable model) {
		initializeScene();
		loadLevels();
		populate();
	}
	
	private void initializeScene() {
		myGUI = new BorderPane();
		myMap = new Group();
		myGUI.setCenter(myMap);
		myGUI.setBottom(buttons());
		this.setScene(new VoogaScene(myGUI, WINDOW_WIDTH, WINDOW_HEIGHT));
		this.show();
	}
	
	private HBox buttons() {
		HBox container = new HBox();
		container.getChildren().add(new ButtonMaker().makeButton("Add connection", e -> {
			Connection connector = new Connection();
			myMap.getChildren().addAll(connector);
		}));
		container.setAlignment(Pos.CENTER);
		return container;
	}
	
	private void loadLevels() {
		//================================================================================|
		//   Temporary code until level saving and loading can be implemented completely. |
		//================================================================================|
		levels = new ArrayList<String>();
		levels.addAll(Arrays.asList("Splash Screen", "Intro: Forest of Fire", "I: Hills of Hell", "II: Dunes of Doom",
				"Splash Screen", "Intro: Forest of Fire", "I: Hills of Hell", "II: Dunes of Doom"));
	}
	
	private void populate() {
		for(int i = 0; i < levels.size(); i++) {
			Level circ = new Level(levels.get(i), CIRCLE_SIZE/levels.size());
			circ.setTranslateX(RING_SIZE * Math.cos(Math.toRadians((i-1)*(CIRCLE_DEGREES/levels.size()))));
			circ.setTranslateY(RING_SIZE * Math.sin(Math.toRadians((i-1)*(CIRCLE_DEGREES/levels.size()))));
			myMap.getChildren().add(circ);
			StackPane.setAlignment(circ, Pos.CENTER);
		}
	}

}
