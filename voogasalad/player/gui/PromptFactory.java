/**
 * 
 */
package player.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
