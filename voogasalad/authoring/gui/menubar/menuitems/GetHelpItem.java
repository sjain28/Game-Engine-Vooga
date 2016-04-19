package authoring.gui.menubar.menuitems;

import java.awt.Desktop;
import java.net.URI;
import authoring.gui.menubar.MenuItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import tools.VoogaException;


/**
 * MenuItem to open up a help page.
 * 
 * @author Nick, Hunter
 *
 */
public class GetHelpItem extends MenuItemHandler {

//    private static final String HELP_URL =
//            "https://www.google.com/search?q=how+to+use+voogasalad&oq=how+to+use+voogasalad&aqs=chrome..69i57j69i60j69i65j69i60l3.2085j0j1&sourceid=chrome&ie=UTF-8";
	private static final String HELP_VIDEO_URL = "http://www.youtube.com/watch?v=mJ68sEM7YFs";
	private static final String EXIT_MESSAGE = "Press Ctrl + Z to exit full screen mode";
	
    private WebView	myBrowser;
    private WebEngine myWebEngine;
	
    /**
     * Initializes the MenuItem
     * 
     * @param model to interface backend interactions with the model
     * @param event: Unused vestige of previous poor programming. Should soon be phased out.
     */
    public GetHelpItem (CompleteAuthoringModelable model, EventHandler<InputEvent> event) {

    }

    /**
     * Action to be taken on the selection of this menuItem
     * 
     * @throws VoogaException
     */
    @Override
    public void handle () throws VoogaException {
        // TODO Auto-generated method stub
//        if (Desktop.isDesktopSupported()) {
//            try {
//                Desktop.getDesktop().browse(new URI(HELP_URL));
//            }
//            catch (Exception e) {
//                throw new VoogaException();
//            }
//        }
    	
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
//        stage.setTitle(getResourceLoader().getString("PopupTitle"));
//        stage.setWidth(POPUP_WIDTH);
//        stage.setHeight(POPUP_HEIGHT);
        stage.setScene(scene);
		stage.setFullScreen(true);
		stage.setFullScreenExitHint(EXIT_MESSAGE);
		stage.setFullScreenExitKeyCombination
					(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN));
        stage.show();
    }

}
