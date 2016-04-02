package authoring.gui;

import java.io.File;
import java.util.Map;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


public class DesignBoard extends ScrollPane {
    private StackPane contentPane;
    
    public DesignBoard () {
        contentPane = new StackPane();
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
        // Get item id here, which was stored when the drag started.
        boolean success = false;
        // If this is a meaningful drop...

//        if (db.hasString()) {
//            String nodeId = db.getString();
//            // ...search for the item on body. If it is there...
//            ImageView cloth = (ImageView) bodyPane.lookup("#" + nodeId);
//            if (cloth != null) {
//                // ... the item is removed from body
//                // and added to an unequipped container.
//                bodyPane.getChildren().remove(cloth);
//                itemPane.getChildren().add(cloth);
//                success = true;
//            }
//            // ...anyway, the item is not active or equipped anymore.
//            items.get(nodeId).takeOff();
//        }
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

}
