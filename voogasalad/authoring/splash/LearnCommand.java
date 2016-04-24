package authoring.splash;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;

import authoring.Command;
import authoring.model.ElementManager;
import javafx.scene.Scene;
import javafx.stage.Stage;
import resources.VoogaBundles;
import tools.VoogaException;

public class LearnCommand implements Command {
	
	private static final String HELP_URL =
            "https://www.google.com/search?q=how+to+use+voogasalad&oq=how+to+use+voogasalad&aqs=chrome..69i57j69i60j69i65j69i60l3.2085j0j1&sourceid=chrome&ie=UTF-8";

	@Override
	public void execute() {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI(HELP_URL));
            }
            catch (Exception e) {
            	
            }
        }
	}

}
