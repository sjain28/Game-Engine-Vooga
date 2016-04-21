package authoring.gui;

import java.io.File;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import com.sun.prism.paint.Color;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.model.ElementSelectionModel;
import authoring.model.GameObject;
import authoring.VoogaScene;
import authoring.gui.menubar.builders.ArchetypeBuilder;
import authoring.gui.menubar.builders.GameObjectBuilder;
import authoring.interfaces.Elementable;
import authoring.properties.PropertiesTabManager;
import authoring.resourceutility.ResourceDecipherer;
import authoring.resourceutility.VoogaFile;
import authoring.resourceutility.VoogaFileFormat;
import authoring.resourceutility.VoogaFileType;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
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
	private static final double HEIGHT = 2000;
	private static final double WIDTH = 2000;
	private static final double DISPLAY_WIDTH = 600;
	private static final double DISPLAY_HEIGHT = 600;

	private ScrollPane container;
	private StackPane contentPane;
	private Node node;

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
		selectionModel = ElementSelectionModel.getInstance();
		selectionModel.addObserver(this);
		this.setText(DESIGN_BOARD);
		this.setClosable(false);
		contentPane = new StackPane();
		contentPane.setMinSize(WIDTH, HEIGHT);
		elementManager = elem;
		elementManager.addObserver(this);
		container = new ScrollPane();
		initializeDragAndDrop();
		container.setContent(contentPane);
		this.setContent(container);
		y_offset = HEIGHT / 2;
		x_offset = WIDTH / 2;
		addGuides();
	}

	private void addGuides() {
		// TODO: replace hardcode with actual value from standard display
		Rectangle guide = new Rectangle(DISPLAY_WIDTH, DISPLAY_HEIGHT);
		guide.setStroke(Paint.valueOf("white"));
		guide.setStrokeWidth(4);
		guide.setFill(Paint.valueOf("transparent"));
		guide.setStrokeDashOffset(40);
		guide.setTranslateX(DISPLAY_WIDTH/2);
		guide.setTranslateY(DISPLAY_HEIGHT/2);
		this.contentPane.getChildren().add(guide);
		this.container.setVvalue(0.72);
		this.container.setHvalue(0.8);
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
			GameObject object = (GameObject) elementManager.getElement(db.getString());
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
		node = null;
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
					node = new MediaView(new MediaPlayer(new Media(Paths.get(elementPath).toUri().toString())));
				}
			} catch (VoogaException e) {
				new VoogaAlert(e.getMessage());
			}
			elementManager.addElementId(elementPath);
		}

		System.out.println(elementManager.getIds());

	}

	private void moveElement(String id, DragEvent e) {
		Node element = elementManager.getElement(id);
		System.out.println("" + e.getX() + " " + e.getY());
		element.setTranslateX(e.getX() - x_offset);
		element.setTranslateY(e.getY() - y_offset);

	}

	private void displayElements(Collection<Node> nodeList) {
		for (Node node : nodeList) {
			if (!contentPane.getChildren().contains(node)) {
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
			displayElements(((CompleteAuthoringModelable) o).getElements());
		}
		if ((o instanceof ElementSelectionModel) && (arg instanceof Elementable)) {
			for (Node object : contentPane.getChildren()) {
				if (object instanceof GameObject) {
					if (arg == object) {
						((GameObject) object).select(Selector.HIGHLIGHTED);
					} else {
						((GameObject) object).select(Selector.UNHIGHLIGHTED);
					}
				}
			}
		}
	}

}
