package authoring.gui.menubar;

import auxiliary.VoogaException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import player.gamedisplay.Menuable;

public abstract class MenuItemHandler {
	
	public abstract void handle() throws VoogaException;
}
