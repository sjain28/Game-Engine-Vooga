package authoring.gui;


import java.io.File;
import java.util.UUID;
import authoring.model.ElementManager;
import authoring.model.GameObject;
import authoring.resourceutility.ResourceDecipherer;
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

    private void initializeDragAndDrop () {
        contentPane.setOnDragOver(e -> mouseDragOver(e));
        contentPane.setOnDragDropped(e -> mouseDragDropped(e));
        contentPane.setOnDragExited(e -> contentPane.setStyle("-fx-border-color: transparent;"));
    }

    private void mouseDragDropped (final DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasString()) {
            String nodeId = db.getString();
            if (elementManager.hasElement(nodeId)) {
                moveElement(nodeId,event);
            }
            else {
                addElement(db.getString());
            }
            success = true;
        }

        event.setDropCompleted(success);
    }

    private void mouseDragOver (final DragEvent event) {
        if (event.getGestureSource() != contentPane &&
            event.getDragboard().hasString()) {
        	contentPane.setStyle("-fx-border-color: #C6C6C6;");
        	event.acceptTransferModes(TransferMode.ANY);
        }
        event.consume();
    }

    public void addElement (String elementPath) {
        Node node;
        if (ResourceDecipherer.isImage(elementPath)) {
            //node = new GameObject(elementManager.getSpriteFactory().createSprite(""));
        	System.out.println(elementPath);
        	node = new ImageView(new Image(new File(elementPath).toURI().toString()));
            addElement(node);
        }
    }

    public void addElement (Node node) {
        elementManager.addGameElements(node);
        contentPane.getChildren().add(node);
    }

    private void moveElement (String id,DragEvent e) {
        Node element = elementManager.getElement(id);
        System.out.println("" + e.getX() + " " + e.getY());
        element.setTranslateX(e.getX());
        element.setTranslateY(e.getY());

    }
}
