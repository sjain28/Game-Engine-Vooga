package authoring.gui.menubar;

import java.lang.reflect.InvocationTargetException;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;
import player.gamedisplay.Menuable;
import tools.VoogaAlert;
import tools.VoogaException;


/**
 * This is the class that handles reflection on menu panel actions.
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo
 *
 */
public class MenuPanelHandlingMirror {

	private static final String ITEM_NOT_IMPLEMENTED_ERROR =
			"This item has not been implemented. Please do so.";
	private static final String PACKAGE_LOCATION = "authoring.gui.menubar.menuitems.";
	private static final String HANDLE = "handle";

	private ActionEvent e;
	private Menuable myManager;

	/**
	 * Instantiates the menu panel handler.
	 * 
	 * @param e
	 * @param newSceneCommand
	 * @param view
	 * @param graphicsWindow
	 */
	public MenuPanelHandlingMirror (ActionEvent e,
			Menuable manager) {
		this.e = e;
		myManager = manager;
		try {
			handleEvent();
		}
		catch (VoogaException e1) {
			VoogaAlert alert = new VoogaAlert(e1.getMessage());
			alert.showAndWait();
		}
	}

	/**
	 * Handles events from the menu panel.
	 * 
	 * @throws VoogaException
	 */
	public void handleEvent () throws VoogaException {
		MenuItem menuItem = (MenuItem) e.getSource();
		MenuItemHandler menuItemHandler;
		Class<?> clazz;
		try {
			clazz = Class.forName(PACKAGE_LOCATION + menuItem.getId());
			menuItemHandler =
					(MenuItemHandler) clazz.getConstructor(Menuable.class)
					.newInstance(myManager);
			menuItemHandler.getClass().getDeclaredMethod(HANDLE).invoke(menuItemHandler);
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e1) {
			throw new VoogaException(ITEM_NOT_IMPLEMENTED_ERROR);
		}

	}

}
