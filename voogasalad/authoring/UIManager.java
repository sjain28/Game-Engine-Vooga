package authoring;

import authoring.gui.menubar.MenuPanel;
import authoring.gui.menubar.MenuPanelHandlingMirror;
import authoring.gui.toolbar.ToolPanel;
import authoring.gui.toolbar.ToolPanelHandlingMirror;
import authoring.model.ElementManager;
import auxiliary.VoogaException;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


/**
 * The UIManager is responsible for assembling view components, such
 * as the menubar, toolbar, and grid of windows
 * 
 */
// Temporarily extending GridPane, eventually will use Mosaic to display components
public class UIManager extends VBox {
    private ElementManager elementManager;
    
	public UIManager() {
		initializeComponents();
	}
	
	
	private void initializeComponents() {
		this.getChildren().addAll(new MenuPanel(e -> {
			try {
				new MenuPanelHandlingMirror(e);
			}
			catch(VoogaException ee) {
				Alert exception = new Alert(AlertType.ERROR);
				exception.setTitle(AlertType.ERROR.toString());
				exception.setContentText(ee.getMessage());
				exception.showAndWait();
			}
		}), new ToolPanel(e -> {
			new ToolPanelHandlingMirror(e);
		}),
			new WorkspacePanel());
	}
}
