package authoring.gui;

import java.util.UUID;
import authoring.model.ElementManager;
import authoring.model.GameObject;
import authoring.resourceutility.ResourceDecipherer;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class DesignBoardHousing extends TabPane {
   

    public DesignBoardHousing () {
        this.getTabs().add(new DesignBoard());
    }

    public StackPane getContent () {
        return this.getContent();
    }

    public void addElement (String elementPath) {
        ((DesignBoard) this.getTabs().get(getSelectionModel().getSelectedIndex())).addElement(elementPath);
    }

    public void addElement (Node node) {
       ((DesignBoard) this.getTabs().get(getSelectionModel().getSelectedIndex())).addElement(node);
    }

    public void addScene () {
       DesignBoard design = new DesignBoard();
       this.getTabs().add(design);
    }

}
