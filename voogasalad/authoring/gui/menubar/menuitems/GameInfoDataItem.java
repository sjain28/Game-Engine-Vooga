package authoring.gui.menubar.menuitems;

import authoring.gui.DataBaseDisplay;
import authoring.gui.menubar.MenuItemHandler;
import player.gamedisplay.Menuable;
import tools.VoogaException;

public class GameInfoDataItem extends MenuItemHandler{

    public GameInfoDataItem(Menuable manager) {
       super();
    }

    @Override
    public void handle () throws VoogaException {
       DataBaseDisplay data = new DataBaseDisplay();
       data.show();
    }

}
