/**
 * 
 */
package Player.gamedisplay;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Provides a public method that allows Display to create a prompt with a string
 * 
 * @author Hunter Lee
 *
 */
public class PromptFactory implements IPromptFactory {

	//    private static final int XPROMPTSIZE = 500;
	//    private static final int YPROMPTSIZE = 300;
	//    private static final int PADDING = 30;
	//    private static final int LABEL_FONTSIZE = 36;
	//    private static final int TEXT_FONTSIZE = 20;
	//    private static final String FONT = "Georgia";

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


	public static void main(String[] args) {
		//The following doesn't work but will under Application
		PromptFactory p = new PromptFactory();
		p.prompt("Hello?!");
	}
}
