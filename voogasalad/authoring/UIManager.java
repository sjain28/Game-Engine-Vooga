package authoring;

import authoring.gui.menubar.Menubar;
import authoring.gui.menubar.MenubarHandlingReflector;
import auxiliary.VoogaException;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

// Temporarily extending Group, eventually will use Mosaic to display components
public class UIManager extends Group {

	public UIManager() {
		initializeComponents();
	}
	
	private void initializeComponents() {
		this.getChildren().add(new Menubar(e -> {
			try {
				new MenubarHandlingReflector(e);
			}
			catch(VoogaException ee) {
				Alert exception = new Alert(AlertType.ERROR);
				exception.setTitle(AlertType.ERROR.toString());
				exception.setContentText(ee.getMessage());
				exception.showAndWait();
			}
		}));
	}
	
}
