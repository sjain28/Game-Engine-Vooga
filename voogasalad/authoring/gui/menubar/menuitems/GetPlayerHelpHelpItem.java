package authoring.gui.menubar.menuitems;

import authoring.gui.menubar.PlayerMenuItemHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import player.gamedisplay.Menuable;
import tools.VoogaException;

/**
 * Menu Item to select the board of the display
 *  
 * @author Nick, Hunter
 *
 */
public class GetPlayerHelpHelpItem extends PlayerMenuItemHandler {

	private static final String HELP_VIDEO_URL = "http://adityasrinivasan.io/voogaplayertutorial.html";
	private static final String EXIT_MESSAGE = "Press Ctrl + Z to exit full screen mode";

	private WebView	myBrowser;
	private WebEngine myWebEngine;

	/**
	 * Initializes the MenuItem
	 * 
	 * @param model to interface backend interactions with the model
	 * @param event: Unused vestige of previous poor programming. Should soon be phased out.
	 */
	public GetPlayerHelpHelpItem (Menuable model) {

	}

	/**
	 * Action to be taken on the selection of this menuItem
	 * 
	 * @throws VoogaException
	 */
	@Override
	public void handle () throws VoogaException {

		/**
		 * Provides an instructional video on how to play the game
		 * 
		 */
		popup(HELP_VIDEO_URL);
	}


	/**
	 * Takes the user to the customized help page
	 * 
	 * @param link
	 */
	private void popup(String link){
		myBrowser = new WebView();
		myWebEngine = myBrowser.getEngine();
		myWebEngine.load(link);
		VBox vbox = new VBox();
		Scene scene = new Scene(vbox);
		Stage stage = new Stage();        
		vbox.getChildren().addAll(myBrowser);
		VBox.setVgrow(myBrowser, Priority.ALWAYS);
		stage.setScene(scene);
		stage.setFullScreen(true);
		stage.setFullScreenExitHint(EXIT_MESSAGE);
		stage.setFullScreenExitKeyCombination
		(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN));
		stage.show();
	}

}
