package authoring.gui.cartography;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
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

public class LevelCartographer extends Stage {

	/**
	 * Constants.
	 */
	private static final double WINDOW_WIDTH = 1000;
	private static final double WINDOW_HEIGHT = 800;
	private static final double CIRCLE_SIZE = 200;
	private static final double CIRCLE_DEGREES = 360;
	private static final double RING_SIZE = 300;
	private static final double INCREASE_FACTOR = 1.2;
	private static final String MAP_XML_PATH = VoogaPaths.GAME_FOLDER + VoogaBundles.preferences.getProperty("GameName")
			+ "/map/" + VoogaBundles.preferences.getProperty("GameName") + "Map.xml";

	/**
	 * Private instance variables.
	 */
	private BorderPane myGUI;
	private Group myMap;
	private List<String> levelNames;
	private Set<Level> levels;
	private Set<Connection> connectors;
	private Set<Mapping> mappings;
	private NetworkContainer container;
	private UIManager manager;

	public LevelCartographer(Menuable model) {
		this.manager = (UIManager) model;
		initializeScene();
		loadLevels();
		populate();
		loadLinesAndPoints();
	}

	private void initializeScene() {
		myGUI = new BorderPane();
		myMap = new Group();
		myGUI.setCenter(myMap);
		myGUI.setBottom(buttons());
		this.mappings = new HashSet<Mapping>();
		this.connectors = new HashSet<Connection>();
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

	private void save() {
		connectorsToCoordinates();
		new Save(this.manager).handle();
		try {
			container = new NetworkContainer(mappings, Entrypoint.getInstance().getEntrypoint());
			Serializer.serializeLevel(container, MAP_XML_PATH);
		} catch (ParserConfigurationException | TransformerException | IOException | SAXException e) {
			e.printStackTrace();
		}
		this.close();
	}
	
	private void connectorsToCoordinates() {
		for(Connection connector : connectors) {
			mappings.add(new Mapping(connector.getStartpoint().getName(), connector.getEndpoint().getName()));
		}
	}

	private void addConnector() {
		Connection connector = new Connection(this.manager.getManager(), 0, 0, 100, -100);
		connector.getStartAnchor().centerXProperty().addListener((obs, old, n) -> {
			for (Level level : levels) {
				if (connector.getStartAnchor().getBoundsInParent().intersects(level.getBoundsInParent())) {
					connector.setStartpoint(level);
				}
			}
		});
		connector.getEndAnchor().centerXProperty().addListener((obs, old, n) -> {
			for (Level level : levels) {
				if (connector.getEndAnchor().getBoundsInParent().intersects(level.getBoundsInParent())) {
					connector.setEndpoint(level);
				}
			}
		});
		connectors.add(connector);
		myMap.getChildren().add(connector);
	}

	private void makeEntrypoint() {
		Entrypoint ep = Entrypoint.getInstance();
		if (!myMap.getChildren().contains(ep)) {
			addEntrypoint(ep);
		}
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
		circ.setRadius(INCREASE_FACTOR * CIRCLE_SIZE / levelNames.size());
		circ.toBack();
	}
	
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
		addEntrypoint(circ);
	}

	private void loadLevels() {
		levels = new HashSet<Level>();
		levelNames = this.manager.getAllManagerNames();
	}

	private void loadLinesAndPoints() {
		Set<Mapping> mappings;
		try {
			List<Object> deserialization = Deserializer.deserialize(1, MAP_XML_PATH);
			if (deserialization.size() > 0) {
				container = (NetworkContainer) deserialization.get(0);
				mappings = (Set<Mapping>) container.getMappings();
				for(Mapping mapping : mappings) {
					Level startLevel = getLevelFromString(mapping.getStart());
					Level endLevel = getLevelFromString(mapping.getEnd());
					Connection connection = new Connection(this.manager.getManager(),
						    startLevel.getTranslateX() + CIRCLE_SIZE / levelNames.size(),
						    startLevel.getTranslateY() + CIRCLE_SIZE / levelNames.size(),
						    endLevel.getTranslateX() + CIRCLE_SIZE / levelNames.size(),
						    endLevel.getTranslateY()+ CIRCLE_SIZE / levelNames.size());
					connection.setStartpoint(startLevel);
					connection.setEndpoint(endLevel);
					connectors.add(connection);
					myMap.getChildren().add(connection);
				}
				addEntrypoint((String) container.getEntrypoint());
				Entrypoint.getInstance().setEntrypoint((String) container.getEntrypoint());
			}
		} catch (VoogaException e) {
			new VoogaAlert("This is the first time opening the Cartographer.");
		}
	}
	
	private Level getLevelFromString(String levelName) {
		for(Level level : levels) {
			if(level.getName().equals(levelName)) {
				return level;
			}
		}
		return null;
	}
	
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