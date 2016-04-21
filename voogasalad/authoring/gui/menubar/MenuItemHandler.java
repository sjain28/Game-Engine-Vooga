package authoring.gui.menubar;

import authoring.interfaces.model.CompleteAuthoringModelable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import player.gamedisplay.Menuable;
import tools.VoogaException;

public abstract class MenuItemHandler {
	
	public abstract void handle() throws VoogaException;
}
