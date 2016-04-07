package authoring;

import java.util.UUID;

import authoring.gui.menubar.MenuPanel;
import authoring.gui.menubar.MenuPanelHandlingMirror;
import authoring.gui.toolbar.ToolPanel;
import authoring.gui.toolbar.ToolPanelHandlingMirror;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.model.ElementManager;
import authoring.model.GameObject;
import authoring.resourceutility.ResourceDecipherer;
import auxiliary.VoogaException;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;


/**
 * The UIManager is responsible for assembling view components, such
 * as the menubar, toolbar, and grid of windows
 * 
 */
// Temporarily extending GridPane, eventually will use Mosaic to display components
public class UIManager extends VBox {
    private CompleteAuthoringModelable elementManager;
    private Dragboard db;
    

    public UIManager (CompleteAuthoringModelable model) {
        elementManager = model;
        initializeComponents();
    }

    private void initializeComponents () {
        this.getChildren().addAll(new MenuPanel(elementManager, e -> {
            try {
                new MenuPanelHandlingMirror(e);
            }
            catch (VoogaException ee) {
                Alert exception = new Alert(AlertType.ERROR);
                exception.setTitle(AlertType.ERROR.toString());
                exception.setContentText(ee.getMessage());
                exception.showAndWait();
            }
        }), new ToolPanel(e -> {
            new ToolPanelHandlingMirror(e);
        }), new UIGrid());
    }
}
