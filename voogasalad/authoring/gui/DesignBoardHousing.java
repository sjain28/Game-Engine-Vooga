package authoring.gui;

import java.util.UUID;
import authoring.interfaces.model.CompleteAuthoringModelable;
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
   

    public DesignBoardHousing (CompleteAuthoringModelable elem) {
        this.getTabs().add(new DesignBoard(elem));
    }

    public DesignBoard getDesignBoard() {
        return ((DesignBoard) this.getTabs().get(getSelectionModel().getSelectedIndex()));
    }

    public void addScene (CompleteAuthoringModelable elem) {
       DesignBoard design = new DesignBoard(elem);
       this.getTabs().add(design);
    }

}
