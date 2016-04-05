package authoring.gui;

import java.util.UUID;
import authoring.model.ElementManager;
import authoring.model.GameObject;
import authoring.resourceutility.ResourceDecipherer;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;


public class DesignBoard extends ScrollPane {
    private StackPane contentPane;
    private ElementManager elementManager;

    public DesignBoard () {
        this.setWidth(1000);
        this.setWidth(1000);
        contentPane = new StackPane();
        contentPane.setMinSize(1000, 1000);
        elementManager = new ElementManager();
        initializeDragAndDrop();
        this.setContent(contentPane);
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
        if (db.hasString()){
            String nodeId = db.getString();
            if (elementManager.hasElement(nodeId)){
                moveElement(nodeId, db);
            } else {
                addElement(db.getString());
            }
            success=true;
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

    private void addElement (String elementPath) {
        Node node;
        if (ResourceDecipherer.isImage(elementPath)){
            node = new GameObject(elementPath,UUID.randomUUID().toString());
            addElement(node);
        } 
    }
    
    public void addElement(Node node){
        elementManager.addGameElements(node);
        contentPane.getChildren().add(node);
    }
    
    private void moveElement(String id,Dragboard db){
        Node element = elementManager.getElement(id);
        System.out.println(""+db.getDragViewOffsetX()+" "+db.getDragViewOffsetY());
        element.setTranslateX(db.getDragViewOffsetX());
        element.setTranslateY(db.getDragViewOffsetY());
       
    }

}
