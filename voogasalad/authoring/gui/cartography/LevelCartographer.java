package authoring.gui.cartography;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import authoring.UIManager;
import authoring.VoogaScene;
import authoring.gui.toolbar.toolbaritems.Save;
import authoring.resourceutility.ButtonMaker;
import data.Deserializer;
import data.Serializer;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import player.gamedisplay.Menuable;
import resources.VoogaBundles;
import resources.VoogaPaths;
import tools.VoogaException;

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
	private List<Connection> connectors;
	private Map<String, Set<String>> levelMap;

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
		this.levelMap = new HashMap<String, Set<String>>();
		this.connectors = new ArrayList<Connection>();
		this.setScene(new VoogaScene(myGUI, WINDOW_WIDTH, WINDOW_HEIGHT));
	}

	private HBox buttons() {
		HBox container = new HBox();
		container.getChildren().addAll(new ButtonMaker().makeButton("Add connection", e -> addConnector()),
				new ButtonMaker().makeButton("Make entrypoint", e -> makeEntrypoint()),
				new ButtonMaker().makeButton("Save", e -> save()));
		container.setAlignment(Pos.CENTER);
		return container;
	}

	@SuppressWarnings("unchecked")
	private void save() {
		writeLinesAndPoints();
		new Save(this.manager).handle();
		try {
			String mapXMLPath = VoogaPaths.GAME_FOLDER + VoogaBundles.preferences.getProperty("GameName") + "/map/"
					+ VoogaBundles.preferences.getProperty("GameName") + "Map.xml";
			Serializer.serializeLevel(levelMap, mapXMLPath);
			Map<String, List<String>> map = (Map<String, List<String>>) Deserializer.deserialize(1, mapXMLPath);
			System.out.println(map.keySet());
		} catch (ParserConfigurationException | TransformerException | IOException | SAXException | VoogaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void writeLinesAndPoints() {
		
	}

	private void addConnector() {
		Connection connector = new Connection(this.manager.getManager(), 0, 0, 100, -100);
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
		//connectors.add
		myMap.getChildren().add(connector);
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
		levels = new ArrayList<Level>();
		levelNames = this.manager.getAllManagerNames();
	}

	private void loadLinesAndPoints() {
		for (Level level : levels) {
			
		}
	}

	private void populate() {
		for (int i = 0; i < levelNames.size(); i++) {
			Level circ = new Level(levelNames.get(i), CIRCLE_SIZE / levelNames.size());
			circ.setTranslateX(RING_SIZE * Math.cos(Math.toRadians((i - 1) * (CIRCLE_DEGREES / levelNames.size()))));
			circ.setTranslateY(RING_SIZE * Math.sin(Math.toRadians((i - 1) * (CIRCLE_DEGREES / levelNames.size()))));
			levels.add(circ);
			myMap.getChildren().add(circ);
			StackPane.setAlignment(circ, Pos.CENTER);
			if (!levelMap.containsKey(circ.getName())) {
				levelMap.put(circ.getName(), new HashSet<String>());
			}
		}
	}

}
