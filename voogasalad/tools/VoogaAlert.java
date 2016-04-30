package tools;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Handling of alerts whenever a user makes an error in the salad
 */
public class VoogaAlert {

	public VoogaAlert(String message) {
		alert(message);
	}

	/**
	 * Display alert message to the user
	 * 
	 * @param message
	 */
	public void alert(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("ERROR");
		alert.setContentText(message);
		alert.showAndWait();
	}
}
