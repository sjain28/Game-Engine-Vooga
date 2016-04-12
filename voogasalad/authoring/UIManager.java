package authoring;

import authoring.gui.menubar.MenuPanel;
import authoring.gui.menubar.MenuPanelHandlingMirror;
import authoring.gui.toolbar.ToolPanel;
import authoring.gui.toolbar.ToolPanelHandlingMirror;
import authoring.interfaces.model.CompleteAuthoringModelable;
import auxiliary.VoogaAlert;
import auxiliary.VoogaException;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.VBox;

/**
 * The UIManager is responsible for assembling view components, such as the
 * menubar, toolbar, and grid of windows
 * 
 */
// Temporarily extending GridPane, eventually will use Mosaic to display
// components
public class UIManager extends VBox {
	private CompleteAuthoringModelable elementManager;
	private Dragboard db;

	public UIManager(CompleteAuthoringModelable model) {
		elementManager = model;
		initializeComponents();
	}

	private void initializeComponents() {
		this.getChildren().addAll(new MenuPanel(elementManager, e -> {
			try {
				new MenuPanelHandlingMirror(e, elementManager);
			} catch (VoogaException ee) {
				new VoogaAlert(ee.getMessage());
			}
		}), new ToolPanel(e -> {
			new ToolPanelHandlingMirror(e);
		}), new UIGrid());
	}

}
