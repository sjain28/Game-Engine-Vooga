package authoring.gui.menubar.menuitems;

import authoring.gui.eventpane.EventWindow;
import authoring.gui.menubar.MenuItemHandler;
import authoring.interfaces.model.EditEventable;
import player.gamedisplay.Menuable;

/**
 * Player Menu Item to create a new event.
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo
 *
 */

public class NewEventNewItem extends MenuItemHandler{
	private EditEventable myManager;

	/**
	 * Initializes the MenuItem
	 * 
	 * @param model to interface backend interactions with the model
	 * @param event: Unused vestige of previous poor programming. Should soon be phased out.
	 */

	public NewEventNewItem(Menuable model){
		super();
		myManager = (EditEventable) model.getManager();
	}

	/**
	 * Action to be taken on the selection of this menuItem
	 */
	@Override
	public void handle() {
		EventWindow eventWindow = new EventWindow(myManager);
		eventWindow.show();

	}


}