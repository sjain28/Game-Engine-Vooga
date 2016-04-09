package authoring.gui.menubar.menuitems;

import java.io.File;
import application.Launcher;
import authoring.gui.menubar.MenuItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.interfaces.model.Savable;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class OpenProjectFileItem extends MenuItemHandler {
    private Savable myManager;
	
	public OpenProjectFileItem(CompleteAuthoringModelable model) {
		super();
		myManager = model;
	}

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
