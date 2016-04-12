package authoring.gui;


import java.io.File;
import java.nio.file.Paths;
import java.util.UUID;

import authoring.interfaces.Elementable;
import authoring.model.ElementManager;
import authoring.model.GameObject;
import authoring.properties.PropertiesTabManager;
import authoring.resourceutility.ResourceDecipherer;
import authoring.resourceutility.VoogaFile;
import authoring.resourceutility.VoogaFileFormat;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class DesignBoard extends Tab {

	private static final String DESIGN_BOARD = "Design Board";
	private static final double HEIGHT = 1000;
	private static final double WIDTH = 1000;
	
	private ScrollPane container;
	private StackPane contentPane;
	private ElementManager elementManager;
	private PropertiesTabManager propertiesTabManager;
	private double y_offset, x_offset;

	public DesignBoard() {
		contentPane = new StackPane();
		contentPane.setMinSize(WIDTH, HEIGHT);
		propertiesTabManager = new PropertiesTabManager();
		elementManager = new ElementManager();
		initGlobalProperties();
		container = new ScrollPane();
		initializeDragAndDrop();
		container.setContent(contentPane);
		this.setContent(container);
		y_offset = HEIGHT/2;
		x_offset = WIDTH/2;
	}

	public StackPane getContent() {
		return this.contentPane;
	}
	
	public PropertiesTabManager getPropertiesTabManager() {
		return propertiesTabManager;
	}
	
	private void initGlobalProperties() {
		Elementable elem = elementManager.getGlobalPropertiesManager();
		propertiesTabManager.getGlobalPropertiesTab().getPropertiesMap(elem);
	}
	
	//Do something with Elementable.setOnClicked and call this method on self, or put into sprite class?
	private void displaySpriteProperties(Elementable elem) {
		propertiesTabManager.getSpritePropertiesTab().getPropertiesMap(elem);
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
			if (elementManager.hasElement(node.getPath())) {
				moveElement(node.getPath(), event);
			} else {
				addElement(node.getPath(), event);
			}
			success = true;
		}

		event.setDropCompleted(success);
	}

	private void mouseDragOver(final DragEvent event) {
		if (event.getGestureSource() != contentPane && event.getDragboard().hasContent(VoogaFileFormat.getInstance())) {
			VoogaFile content = (VoogaFile) event.getDragboard().getContent(VoogaFileFormat.getInstance());
			String color = "";
			if(content.getPath() != null) {
				color = "#64B5F6";
			} else {
				color = "red";
			}
			contentPane.setStyle(String.format("-fx-border-color: %s", color));
			event.acceptTransferModes(TransferMode.ANY);
		}
		event.consume();
	}


	private void addElement(String elementPath, DragEvent event) {

		Node node = null;
		if (elementPath != null) {
			if (ResourceDecipherer.isImage(elementPath)) {
				System.out.println("true");
				// node = new GameObject(elementManager.getSpriteFactory().createSprite(""));
				node = new ImageView(new Image(new File(elementPath).toURI().toString()));
				node.setTranslateX(event.getX() - x_offset);
				node.setTranslateY(event.getY() - y_offset);
			} else if (ResourceDecipherer.isAudio(elementPath)) {
				// node = new GameObject(elementManager.getSpriteFactory().createSprite(""));
				node = new MediaView(new MediaPlayer(new Media(Paths.get(elementPath).toUri().toString())));
			}
			addElement(node);
		}
	}

	public void addElement(Node node) {
		elementManager.addGameElements(node);
		contentPane.getChildren().add(node);
	}

	private void moveElement(String id, DragEvent e) {
		Node element = elementManager.getElement(id);
		System.out.println("" + e.getX() + " " + e.getY());
		element.setTranslateX(e.getX());
		element.setTranslateY(e.getY());


    }

}
