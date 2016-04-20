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
public class UIManager extends VBox {
	private UIGrid grid;
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
		this.getChildren().addAll(new MenuPanel(this.elementTabManager.getCurrentManager(), e -> {
			new MenuPanelHandlingMirror(e, this.elementTabManager.getCurrentManager(), new NewSceneCommand(this.elementTabManager, grid));
		}, VoogaBundles.menubarProperties), new ToolPanel(e -> {
			new ToolPanelHandlingMirror(e, this.elementTabManager.getCurrentManager());
		}), grid = new UIGrid(this.elementTabManager.getCurrentManager()));
	}

}
