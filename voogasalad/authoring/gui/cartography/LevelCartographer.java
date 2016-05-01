package authoring.gui.cartography;

import java.io.IOException;
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
import tools.VoogaAlert;
import tools.VoogaException;

/**
 * Displays the mapping of levels to allow users to define transitions, entrypoints, and endpoints visually.
 * 
 * @author Aditya Srinivasan
 *
 */
public class LevelCartographer extends Stage {

	private static final String GAME_NAME = "GameName";
	private static final int CON_MIN = -100;
	private static final int CON_MAX = 100;

	/**
	 * Constants.
	 */
	private static final double WINDOW_WIDTH = 1000;
	private static final double WINDOW_HEIGHT = 800;
	private static final double CIRCLE_SIZE = 200;
	private static final double CIRCLE_DEGREES = 360;
	private static final double RING_SIZE = 300;
	private static final double INCREASE_FACTOR = 1.2;
	private static final String MAP_XML_PATH = VoogaPaths.GAME_FOLDER + VoogaBundles.preferences.getProperty(GAME_NAME)
	+ "/map/" + VoogaBundles.preferences.getProperty(GAME_NAME) + "Map.xml";

	/**
	 * Private instance variables.
	 */
	private BorderPane myGUI;
	private Group myMap;
	private List<String> levelNames;
	private Set<Level> levels;
	private Set<Connection> connectors;
	private Map<String, String> mappings;
	private NetworkContainer container;
	private Map<String, LevelType> levelTypes;
	private UIManager manager;

	/**
	 * Instantiates the Level Cartographer.
	 * @param model to handle saving
	 * 
	 */
	public LevelCartographer(Menuable model) {
		this.manager = (UIManager) model;
		initializeScene();
		loadLevels();
		populate();
		loadLinesAndPoints();
	}

	/**
	 * Initializes the scene.
	 */
	private void initializeScene() {
		myGUI = new BorderPane();
		myMap = new Group();
		myGUI.setCenter(myMap);
		myGUI.setBottom(buttons());
		this.levelTypes = new HashMap<>();
		this.mappings = new HashMap<>();
		this.connectors = new HashSet<>();
		this.setScene(new VoogaScene(myGUI, WINDOW_WIDTH, WINDOW_HEIGHT));
	}

	/**
	 * Initializes the buttons.
	 * @return
	 */
	private HBox buttons() {
		HBox container = new HBox();
		container.getChildren().addAll(new ButtonMaker().makeButton("Add connection", e -> {
			addConnector();
		}),
				new ButtonMaker().makeButton("Make entrypoint", e -> makeEntrypoint()),
				new ButtonMaker().makeButton("Make endpoint", e -> makeEndpoint()),
				new ButtonMaker().makeButton("Save", e -> save()));
		container.setAlignment(Pos.CENTER);
		return container;
	}

	/**
	 * Saves the current level map configuration.
	 */
	private void save() {
		connectorsToCoordinates();
		new Save(this.manager).handle();
		try {
			assignLevelTypes();
			container = new NetworkContainer(mappings, levelTypes);
			Serializer.serializeLevel(container, MAP_XML_PATH);
		} catch (ParserConfigurationException | TransformerException | IOException | SAXException e) {
		}
		this.close();
	}

	/**
	 * Assigns level types based on the points.
	 */
	private void assignLevelTypes() {
		myMap.getChildren().stream()
		.filter(n -> (n instanceof Point))
		.forEach(m -> levelTypes.put(((Point) m).getPoint(), ((Point) m).getLevelType()));
	}

	/**
	 * Utilizes the connection lines entry and endpoints to map levels together.
	 */
	private void connectorsToCoordinates() {
		for(Connection connector : connectors) {
			mappings.put(connector.getStartpoint().getName(), connector.getEndpoint().getName());
		}
	}

	/**
	 * Adds a connector to the map.
	 */
	private void addConnector() {
		Connection connector = new Connection(this.manager.getManager(), 0, 0, CON_MAX, CON_MIN);
		makeConnectorListenable(connector);
		connectors.add(connector);
		myMap.getChildren().add(connector);
	}

	/**
	 * Makes the connection line listenable to ensure correct mappings.
	 * @param c
	 */
	private void makeConnectorListenable(Connection c) {
		c.getStartAnchor().centerXProperty().addListener((obs, old, n) -> {
			for (Level level : levels) {
				if (c.getStartAnchor().getBoundsInParent().intersects(level.getBoundsInParent())) {
					c.setStartpoint(level);
				}
			}
		});
		c.getEndAnchor().centerXProperty().addListener((obs, old, n) -> {
			for (Level level : levels) {
				if (c.getEndAnchor().getBoundsInParent().intersects(level.getBoundsInParent())) {
					c.setEndpoint(level);
				}
			}
		});
	}

	/**
	 * Creates an entry point.
	 */
	private void makeEntrypoint() {
		Entrypoint ep = Entrypoint.getInstance();
		if (!myMap.getChildren().contains(ep)) {
			addPoint(ep);
		}
	}

	/**
	 * Creates an end point.
	 */
	private void makeEndpoint() {
		Endpoint endpoint = new Endpoint();
		addPoint(endpoint);
	}

	/**
	 * Adds a circle for another level.
	 * @param circ
	 */
	private void addPoint(Point circ) {
		myMap.getChildren().add(circ);
		circ.centerXProperty().addListener((obs, old, n) -> {
			for (Level level : levels) {
				if (circ.getBoundsInParent().intersects(level.getBoundsInParent())) {
					circ.setPoint(level.getName());
					return;
				}
			}
		});
		circ.setRadius(INCREASE_FACTOR * CIRCLE_SIZE / levelNames.size());
		circ.toBack();
	}

	/**
	 * Adds an entry point.
	 * @param levelCenter
	 */
	private void addEntrypoint(String levelCenter) {
		Entrypoint circ = Entrypoint.getInstance();
		Level entry = getLevelFromString(levelCenter);
		for(Level level : levels) {
			if(level == entry) {
				circ.setCenterX(level.getTranslateX() + CIRCLE_SIZE / levelNames.size());
				circ.setCenterY(level.getTranslateY() + CIRCLE_SIZE / levelNames.size());
				break;
			}
		}
		addPoint(circ);
	}

	/**
	 * Adds an end point.
	 * @param levelCenter
	 */
	private void addEndpoint(String levelCenter) {
		Endpoint circ = new Endpoint();
		circ.setPoint(levelCenter);
		Level entry = getLevelFromString(levelCenter);
		for(Level level : levels) {
			if(level == entry) {
				circ.setCenterX(level.getTranslateX() + CIRCLE_SIZE / levelNames.size());
				circ.setCenterY(level.getTranslateY() + CIRCLE_SIZE / levelNames.size());
				break;
			}
		}
		addPoint(circ);
	}

	/**
	 * Loads levels from the manager.
	 */
	private void loadLevels() {
		levels = new HashSet<>();
		levelNames = this.manager.getAllManagerNames();
		levelNames.stream()
		.forEach(n -> levelTypes.put(n, LevelType.NORMAL));
	}

	/**
	 * Loads lines and points from saved XML data.
	 */
	private void loadLinesAndPoints() {
		try {
			List<Object> deserialization = Deserializer.deserialize(1, MAP_XML_PATH);
			if (!deserialization.isEmpty()) {
				container = (NetworkContainer) deserialization.get(0);
				mappings = (Map<String, String>) container.getMappings();
				for(String startPoint : mappings.keySet()) {
					Level startLevel = getLevelFromString(startPoint);
					Level endLevel = getLevelFromString(mappings.get(startPoint));
					Connection connection = new Connection(this.manager.getManager(),
							startLevel.getTranslateX() + CIRCLE_SIZE / levelNames.size(),
							startLevel.getTranslateY() + CIRCLE_SIZE / levelNames.size(),
							endLevel.getTranslateX() + CIRCLE_SIZE / levelNames.size(),
							endLevel.getTranslateY()+ CIRCLE_SIZE / levelNames.size());
					connection.setStartpoint(startLevel);
					connection.setEndpoint(endLevel);
					makeConnectorListenable(connection);
					connectors.add(connection);
					myMap.getChildren().add(connection);
				}
				reloadByLevelTypes();
			}
		} catch (VoogaException e) {
			VoogaAlert alert = new VoogaAlert("This is the first time opening the Cartographer.");
			alert.showAndWait();
		}
	}

	/**
	 * Reloads the levels based on types.
	 */
	private void reloadByLevelTypes() {
		for(String levelName : container.getLevelTypes().keySet()) {
			if(container.getLevelTypes().get(levelName) == LevelType.ENTRYPOINT) {
				addEntrypoint(levelName);
				Entrypoint.getInstance().setPoint(levelName);
			} else if (container.getLevelTypes().get(levelName) == LevelType.ENDPOINT) {
				addEndpoint(levelName);
			}
		}
	}

	/**
	 * Gets a level based on string name.
	 * @param levelName
	 * @return
	 */
	private Level getLevelFromString(String levelName) {
		for(Level level : levels) {
			if(level.getName().equals(levelName)) {
				return level;
			}
		}
		return null;
	}

	/**
	 * Populates the GUI.
	 */
	private void populate() {
		int i = 0;
		for (String level : levelNames) {
			Level circ = new Level(level, CIRCLE_SIZE / levelNames.size());
			circ.setTranslateX(RING_SIZE * Math.cos(Math.toRadians((i - 1) * (CIRCLE_DEGREES / levelNames.size()))));
			circ.setTranslateY(RING_SIZE * Math.sin(Math.toRadians((i - 1) * (CIRCLE_DEGREES / levelNames.size()))));
			levels.add(circ);
			myMap.getChildren().add(circ);
			StackPane.setAlignment(circ, Pos.CENTER);
			i++;
		}
	}
}