package authoring.gui.menubar.menuitems;

import java.io.File;
import application.Launcher;
import authoring.Command;
import authoring.gui.menubar.MenuItemHandler;
import authoring.interfaces.gui.Saveable;
import authoring.interfaces.model.CompleteAuthoringModelable;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import player.gamedisplay.Menuable;

public class OpenProjectFileItem extends MenuItemHandler {
    private Saveable myManager;
    /**
     * Initializes the MenuItem
     * 
     * @param model to interface backend interactions with the model
     * @param event: Unused vestige of previous poor programming. Should soon be phased out.
     */
	public OpenProjectFileItem(Menuable model, Command event) {
		super();
		myManager = (Saveable) model;
	}
	/**
         * Action to be taken on the selection of this menuItem
         */
	@Override
	public void handle() {
	    FileChooser popUp = new FileChooser();
	    popUp.setTitle("Open New Project File");
	    popUp.getExtensionFilters().addAll(
	                                             //TODO: add in appropriate file extension filter
	                                             new ExtensionFilter("All Files", "*.*"));
	    File file = popUp.showOpenDialog(new Stage());
	    if(file != null){
	        Launcher newProject = new Launcher(new Stage(), file);
	    };
	}

}
