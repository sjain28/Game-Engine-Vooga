package authoring.gui.menubar.menuitems;

import authoring.gui.DataBaseDisplay;
import authoring.gui.menubar.MenuItemHandler;
import player.gamedisplay.Menuable;
import tools.VoogaException;

/**
 * Game Item on the Data Drop down from the Menu Bar.
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo
 *
 */

public class GameInfoDataItem extends MenuItemHandler{

	/**
	 * Takes on super construct or
	 * @param manager
	 */
    public GameInfoDataItem(Menuable manager) {
       super();
    }

    /**
     * Accesses the database.
     */
    @Override
    public void handle () throws VoogaException {
       DataBaseDisplay data = new DataBaseDisplay();
       data.show();
    }

}
