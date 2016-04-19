package authoring;

import java.util.ArrayList;
import java.util.UUID;
import com.sun.glass.events.MouseEvent;
import authoring.gui.menubar.MenuPanel;
import authoring.gui.menubar.MenuPanelHandlingMirror;
import authoring.gui.toolbar.ToolPanel;
import authoring.gui.toolbar.ToolPanelHandlingMirror;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.Sceneable;
import authoring.model.ElementManager;
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
import tools.VoogaAlert;
import tools.VoogaException;
import resources.VoogaBundles;

/**
 * The UIManager is responsible for assembling view components, such as the
 * menubar, toolbar, and grid of windows
 * 
 */
// Temporarily extending GridPane, eventually will use Mosaic to display
// components
public class UIManager extends VBox implements Sceneable{
	private ArrayList<CompleteAuthoringModelable> elementManagers;
	private UIGridHousing grid;

	/**
	 * Initializes the UI Manager
	 * 
	 * @param model Interface to mediate interactions with backend
	 */
	public UIManager(CompleteAuthoringModelable model) {
		elementManagers = new ArrayList<CompleteAuthoringModelable>();
		elementManagers.add(model);
		initializeComponents(model);
	}

	/**
	 * Initializes all the pieces of the authoring environment
	 */
	private void initializeComponents(CompleteAuthoringModelable manager) {
		this.getChildren().addAll(new MenuPanel(manager, e -> {
			new MenuPanelHandlingMirror(e, manager, this);
		}, VoogaBundles.menubarProperties), new ToolPanel(e -> {
			new ToolPanelHandlingMirror(e, manager);
		}), grid = new UIGridHousing(manager));
	}
	
	public void addScene(){
	    elementManagers.add(new ElementManager());
	    grid.addScene(elementManagers.get(elementManagers.size() - 1));
	}
}
