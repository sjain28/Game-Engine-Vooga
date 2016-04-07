package authoring.gui.menubar.menuitems;

import java.awt.Desktop;
import java.net.URI;
import authoring.gui.menubar.MenuItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;
import auxiliary.VoogaException;

public class GetHelpHelpItem extends MenuItemHandler {
	
	private static final String HELP_URL = "https://www.google.com/search?q=how+to+use+voogasalad&oq=how+to+use+voogasalad&aqs=chrome..69i57j69i60j69i65j69i60l3.2085j0j1&sourceid=chrome&ie=UTF-8";
	
	public GetHelpHelpItem(CompleteAuthoringModelable model) {
		super(model);
	}

	@Override
	public void handle() {
		// TODO Auto-generated method stub
		if(Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(new URI(HELP_URL));
			} catch (Exception e) {
				throw new VoogaException();
			}
		}
	}

}
