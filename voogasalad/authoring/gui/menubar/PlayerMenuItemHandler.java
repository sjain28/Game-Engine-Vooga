package authoring.gui.menubar;


import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import player.gamedisplay.Menuable;
import player.gamerunner.GameRunner;
import tools.VoogaException;

public abstract class PlayerMenuItemHandler extends MenuItemHandler {
	
	@Override
	public abstract void handle() throws VoogaException;

}
