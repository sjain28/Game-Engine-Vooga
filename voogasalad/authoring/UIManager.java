package authoring;

import java.util.ArrayList;
import java.util.UUID;
import com.sun.glass.events.MouseEvent;

import authoring.gui.menubar.MenuPanel;
import authoring.gui.menubar.MenuPanelHandlingMirror;
import authoring.gui.toolbar.ToolPanel;
import authoring.gui.toolbar.ToolPanelHandlingMirror;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.model.ElementManager;
import auxiliary.VoogaAlert;
import auxiliary.VoogaException;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.InputEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;

/**
 * The UIManager is responsible for assembling view components, such as the
 * menubar, toolbar, and grid of windows
 * 
 */
// Temporarily extending GridPane, eventually will use Mosaic to display
// components
public class UIManager extends VBox {
    private ArrayList<CompleteAuthoringModelable> elementManagers;
    private Dragboard db;
    private UIGrid grid;
    

	public UIManager(CompleteAuthoringModelable model) {
		elementManagers = new ArrayList<CompleteAuthoringModelable>();
		        elementManagers.add(model);
		initializeComponents();
	}

    private void initializeComponents () {
        this.getChildren().addAll(new MenuPanel(elementManagers.get(0), e -> {
            try {
                new MenuPanelHandlingMirror(e, elementManagers.get(0), newScene);
            }
            catch (VoogaException ee) {
                Alert exception = new Alert(AlertType.ERROR);
                exception.setTitle(AlertType.ERROR.toString());
                exception.setContentText(ee.getMessage());
                exception.showAndWait();
            }
        }), new ToolPanel(e -> {
            new ToolPanelHandlingMirror(e);
        }), grid = new UIGrid(elementManagers.get(0)));
    }
    
    EventHandler newScene = new EventHandler() {
        Event e = new Event(Event.ANY);
        public void handle(Event e){
            elementManagers.add(new ElementManager());
            grid.addScene(elementManagers.get(1));
        }            
    };
    

}
