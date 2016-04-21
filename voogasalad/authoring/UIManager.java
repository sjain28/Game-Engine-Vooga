package authoring;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.sun.glass.events.MouseEvent;
import authoring.gui.menubar.MenuPanel;
import authoring.gui.menubar.MenuPanelHandlingMirror;
import authoring.gui.toolbar.ToolPanel;
import authoring.gui.toolbar.ToolPanelHandlingMirror;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.Sceneable;
import authoring.model.ElementManager;
import authoring.model.ElementTabManager;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
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
import player.gamedisplay.Menuable;
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
public class UIManager extends VBox implements Menuable {
	private UIGridHousing grid;
	private SimpleIntegerProperty currentTabIndex;
	private ElementTabManager elementTabManager;

	/**
	 * Initializes the UI Manager
	 * 
	 * @param model
	 *            Interface to mediate interactions with backend
	 */
	public UIManager(CompleteAuthoringModelable model) {
		this.currentTabIndex = new SimpleIntegerProperty(-1);
		this.elementTabManager = new ElementTabManager();
		this.elementTabManager.addManager(new ElementManager());
		Bindings.bindBidirectional(this.currentTabIndex, elementTabManager.getCurrentManagerIndexProperty());

		initializeComponents();
	}

	/**
	 * Initializes all the pieces of the authoring environment
	 */
	private void initializeComponents() {
		this.getChildren().addAll(new MenuPanel(this, e -> {
			new MenuPanelHandlingMirror(e, this);
		}, VoogaBundles.menubarProperties), new ToolPanel(e -> {
			new ToolPanelHandlingMirror(e, this);
		}), grid = new UIGridHousing(elementTabManager.getCurrentManager()));
	}

	public void addScene() {
		elementTabManager.addManager(new ElementManager());
		// initializes a new scene using the most recently added model;
		grid.addScene(elementTabManager.getCurrentManager());
	}

	public CompleteAuthoringModelable getManager() {
		return grid.getManager();
	}

	// TODO: Format output correctly
	public void saveAll() {
		for (CompleteAuthoringModelable m : elementTabManager.getAllManagers()) {
			try {
				m.onSave();
			} catch (VoogaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
