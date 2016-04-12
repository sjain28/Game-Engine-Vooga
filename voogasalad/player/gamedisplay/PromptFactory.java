/**
 * 
 */
package player.gamedisplay;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Provides a public method that allows Display to create a prompt with a string
 * 
 * @author Hunter Lee
 *
 */
public class PromptFactory implements IPromptFactory {

	/**
	 * Takes in a string and displays it as a message the user can view
	 * 
	 */
	public void prompt(String message) {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("VoogaSalad Dialog"); //TODO: String to from ResourceLoader
		alert.setHeaderText(null); //If needed
		alert.setContentText(message);
		alert.showAndWait();

	}

	/**
	 * For testing only
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		PromptFactory p = new PromptFactory();
		p.prompt("Hello?!");
	}
}
