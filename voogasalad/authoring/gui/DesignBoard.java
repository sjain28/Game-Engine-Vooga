package authoring.gui;

import authoring.model.ElementManager;
import authoring.model.GameObject;
import authoring.resourceutility.ResourceDecipherer;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
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
        contentPane.setOnDragOver( (DragEvent e) -> mouseDragOver(e));
        contentPane.setOnDragDropped( (DragEvent e) -> mouseDragDropped(e));
        contentPane.setOnDragExited( (DragEvent e) -> contentPane
                .setStyle("-fx-border-color: #C6C6C6;"));
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
        event.consume();
    }

    private void mouseDragOver (final DragEvent event) {

        if (event.getGestureSource() != contentPane &&
            event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }

    public void addElement (String elementPath) {
        Node node;
        if (ResourceDecipherer.isImage(elementPath)) {
            node = new GameObject(elementManager.getSpriteFactory().createSprite(""));
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
