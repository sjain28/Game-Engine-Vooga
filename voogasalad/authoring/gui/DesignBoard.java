package authoring.gui;

import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.model.ElementSelectionModel;
import authoring.model.GameObject;
import authoring.UIGridHousing;
import authoring.gui.menubar.builders.GameObjectBuilder;
import authoring.interfaces.Elementable;
import authoring.interfaces.FrontEndElementable;
import authoring.resourceutility.ButtonMaker;
import authoring.resourceutility.ResourceDecipherer;
import authoring.resourceutility.VoogaFile;
import authoring.resourceutility.VoogaFileFormat;
import authoring.resourceutility.VoogaFileType;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import resources.VoogaBundles;
import tools.VoogaAlert;
import tools.VoogaException;

/**
 * This class handles the display of all objects on the Authoring Environment
 * GUI. This is the board on which the author can build the game.
 * 
 * @author Aditya Srinivasan, Nick Lockett, Harry Guo, Arjun Desai
 *
 */
public class DesignBoard extends Tab implements Observer {

	private static final String DESIGN_BOARD = "Design Board";
	private static final double STROKE_WIDTH = 4;

	private VBox container;
	private ToolBar zoomBar;
	private ScrollPane scroller;
	private StackPane contentPane;

	private CompleteAuthoringModelable elementManager;
	private ElementSelectionModel selectionModel;

	private double y_offset, x_offset;

	/**
	 * Constructs DesignBoard with object that has the functionality described
	 * by CompleteAuthoringModelable interface
	 * 
	 * @param elem:
	 *            functionality described by CompleteAuthoringModelable
	 *            interface
	 */
	public DesignBoard(CompleteAuthoringModelable elem) {
		this.elementManager = elem;
		
		initializeContainers();
		initializeZoom();
		initializeObservables();

		initializeDragAndDrop();
		
		addGuides();
		displayElements(elem.getElements());
	}
	
	private void initializeContainers() {
		this.setText(DESIGN_BOARD);
		this.setClosable(false);
		
		contentPane = new StackPane();
		contentPane.setMinSize(Double.parseDouble(VoogaBundles.designboardProperties.getString("Width")),
							   Double.parseDouble(VoogaBundles.designboardProperties.getString("Height")));
		
		scroller = new ScrollPane();
		scroller.setContent(contentPane);
		
		zoomBar = new ToolBar();
		
		container = new VBox(zoomBar, scroller);
		this.setContent(container);
		
		y_offset = Double.parseDouble(VoogaBundles.designboardProperties.getString("Width")) / 2;
		x_offset = Double.parseDouble(VoogaBundles.designboardProperties.getString("Height")) / 2;
	}
	
	private void initializeZoom() {
		Slider zoomControl = new Slider(0.1, 10, 1);
		zoomBar.getItems().add(zoomControl);
		zoomControl.valueProperty().addListener((obs, old, n) -> {
			contentPane.setScaleX((double) n);
			contentPane.setScaleY((double) n);
		});
	}
	
	private void initializeObservables() {
		selectionModel = ElementSelectionModel.getInstance();
		selectionModel.addObserver(this);
		
		elementManager.addObserver(this);
	}

	private void addGuides() {
		double width = Double.parseDouble(VoogaBundles.preferences.getProperty("GameWidth"));
		double height = Double.parseDouble(VoogaBundles.preferences.getProperty("GameHeight"));
		Rectangle guide = new Rectangle(width, height);
		guide.setStroke(Paint.valueOf("white"));
		guide.setStrokeWidth(STROKE_WIDTH);
		guide.setFill(Paint.valueOf("transparent"));
		this.contentPane.getChildren().add(guide);
		guide.setTranslateX(width/2);
		guide.setTranslateY(height/2);
		this.scroller.setVvalue(0.5);
		this.scroller.setHvalue(0.5);
	}

	private void initializeDragAndDrop() {
		contentPane.setOnDragOver(e -> mouseDragOver(e));
		contentPane.setOnDragDropped(e -> mouseDragDropped(e));
		contentPane.setOnDragExited(e -> contentPane.setStyle("-fx-border-color: transparent;"));
	}

	private void mouseDragDropped(final DragEvent event) {
		Dragboard db = event.getDragboard();
		boolean success = false;
		if (db.hasContent(VoogaFileFormat.getInstance())) {
			VoogaFile node = (VoogaFile) db.getContent(VoogaFileFormat.getInstance());
			if (node.getType() != VoogaFileType.FOLDER) {
				if (node.getType() != VoogaFileType.ARCHETYPE && node.getType() != VoogaFileType.GAME_OBJECT) {
					if (elementManager.hasElement(node.getPath())) {
						moveElement(node.getPath(), event);
					} else {
						addElement(node, event, "");
					}
				} else {
					if (node.getType() == VoogaFileType.ARCHETYPE) {
						addElement(node, event, node.toString());
					}
				}
			}
			success = true;
		}
		if (db.hasString()) {
			Node object = (Node) elementManager.getElement(db.getString());
			object.setTranslateX(event.getX() - x_offset);
			object.setTranslateY(event.getY() - y_offset);
		}

		event.setDropCompleted(success);
	}

	private void mouseDragOver(final DragEvent event) {
		if (event.getGestureSource() != contentPane
				&& (event.getDragboard().hasContent(VoogaFileFormat.getInstance()))) {
			VoogaFile content = (VoogaFile) event.getDragboard().getContent(VoogaFileFormat.getInstance());
			String color = "";
			if (content.getType() != VoogaFileType.FOLDER) {
				color = "#64B5F6";
			} else {
				color = "red";
			}
			contentPane.setStyle(String.format("-fx-border-color: %s", color));
			event.acceptTransferModes(TransferMode.ANY);
		} else if (event.getDragboard().hasString()) {
			event.acceptTransferModes(TransferMode.ANY);
		}

		event.consume();
	}

	private void addElement(VoogaFile file, DragEvent event, String archetype) {
		String elementPath = file.getPath();
		if (elementPath != null) {
			try {
				if (ResourceDecipherer.isImage(elementPath)) {

					GameObjectBuilder builder = new GameObjectBuilder(elementManager);
					if (!archetype.isEmpty()) {
						builder.setArchetype(archetype);
					} else {
						builder.setDraggedImage(file.getPath());
					}
					builder.showAndWait();

				} else if (ResourceDecipherer.isAudio(elementPath)) {
					// node = new
					// GameObject(elementManager.getSpriteFactory().createSprite(""));
					AudioObject sound = new AudioObject(
							new MediaPlayer(new Media(Paths.get(elementPath).toUri().toString())));
					elementManager.addGameElements(sound);
				}
			} catch (VoogaException e) {
				new VoogaAlert(e.getMessage());
			}
			elementManager.addElementId(elementPath);
		}
	}

	private void moveElement(String id, DragEvent e) {
		Node element = elementManager.getElement(id);
		element.setTranslateX(e.getX() - x_offset);
		element.setTranslateY(e.getY() - y_offset);

	}

	private void displayElements(Collection<Node> nodeList) {
		for (Node node : nodeList) {
			if (!contentPane.getChildren().contains(node)) {
				System.out.println("displaying element" + node);
				contentPane.getChildren().add(node);
			}
		}
	}

	/**
	 * Updates changes to the class based on the observation from the Model,
	 * Specifically the ElementManager
	 */
	@Override
	public void update(Observable o, Object arg) {
		if ((o instanceof CompleteAuthoringModelable) && (arg instanceof List)) {
			System.out.println("updatign eelments");
			displayElements(((CompleteAuthoringModelable) o).getElements());
		}

		if ((o instanceof ElementSelectionModel) && (arg instanceof FrontEndElementable)) {
			for (Node object : contentPane.getChildren()) {
				if (object instanceof FrontEndElementable) {
					if (arg == object) {
						((FrontEndElementable) object).select(Selector.HIGHLIGHTED);
					} else {
						((FrontEndElementable) object).select(Selector.UNHIGHLIGHTED);
					}
				}
			}
		}
	}

}
