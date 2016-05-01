package authoring.splash;

import java.awt.Desktop;
import java.net.URI;

import authoring.Command;


public class LearnCommand implements Command {
	
	private static final String HELP_URL = "http://adityasrinivasan.io/voogatutorial.html";

    /**
     * executes command to get help
     */
    @Override
    public void execute () {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI(HELP_URL));
            }
            catch (Exception e) {

            }
        }
    }

}
