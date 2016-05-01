package authoring.gui.menubar.menuitems;

import java.awt.Desktop;
import java.net.URI;

import authoring.gui.menubar.MenuItemHandler;
import player.gamedisplay.Menuable;
import tools.VoogaException;

/**
 * MenuItem to open up a help page.
 * 
 * @author Nick
 *
 */
public class GetHelpHelpItem extends MenuItemHandler {

	private static final String HELP_URL =
			"https://www.google.com/search?q=how+to+use+voogasalad&oq=how+to+use+voogasalad&aqs=chrome..69i57j69i60j69i65j69i60l3.2085j0j1&sourceid=chrome&ie=UTF-8";

	/**
	 * Initializes the MenuItem
	 * 
	 * @param model to interface backend interactions with the model
	 * @param event: Unused vestige of previous poor programming. Should soon be phased out.
	 */
	public GetHelpHelpItem (Menuable model) {


	}

	/**
	 * Action to be taken on the selection of this menuItem
	 * 
	 * @throws VoogaException
	 */
	@Override
	public void handle () throws VoogaException {

		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(new URI(HELP_URL));
			}
			catch (Exception e) {
				throw new VoogaException();
			}
		}
	}

}