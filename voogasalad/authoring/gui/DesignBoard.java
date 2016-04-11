package authoring.gui;


import java.io.File;
import java.nio.file.Paths;
import java.util.UUID;
import authoring.model.ElementManager;
import authoring.model.GameObject;
import authoring.resourceutility.ResourceDecipherer;
import authoring.resourceutility.VoogaFile;
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

public class DesignBoard extends Tab{

    private static final String DESIGN_BOARD = "Design Board";
    private StackPane contentPane;
    private ScrollPane container;
    private ElementManager elementManager;

    
    public DesignBoard () {
        contentPane = new StackPane();
        contentPane.setMinSize(1000, 1000);
        elementManager = new ElementManager();
        container = new ScrollPane();
        initializeDragAndDrop();
        container.setContent(contentPane);
        this.setContent(container);
        this.setText(DESIGN_BOARD);
        contentPane.getChildren().add(new ResizableImage(new Rectangle(200, 400, Color.CADETBLUE)));
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
				addElement(node.getPath());
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


	private void addElement(String elementPath) {
		Node node = null;
		if (elementPath != null) {
			if (ResourceDecipherer.isImage(elementPath)) {
				System.out.println("true");
				// node = new GameObject(elementManager.getSpriteFactory().createSprite(""));
				node = new ImageView(new Image(new File(elementPath).toURI().toString()));
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
