package authoring.gui.menubar;

import auxiliary.VoogaException;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;

/**
 * This is the class that handles reflection on menu panel actions.
 *
 */
public class MenuPanelHandlingMirror {

	private static final String ITEM_NOT_IMPLEMENTED_ERROR = "This item has not been implemented. Please do so.";
	private static final String PACKAGE_LOCATION = "authoring.gui.menubar.";
	private static final String HANDLE = "handle";
	
    private ActionEvent e;

    /**
     * Instantiates the menu panel handler.
     * @param e
     * @param view
     * @param graphicsWindow
     */
    public MenuPanelHandlingMirror (ActionEvent e) {
        this.e = e;
        
        handleEvent();
    }

    /**
     * Handles events from the menu panel.
     */
    private void handleEvent() {
        MenuItem menuItem = (MenuItem) e.getSource();
        MenuItemHandler menuItemHandler;
        Class<?> clazz;
		try {
			clazz = Class.forName(PACKAGE_LOCATION + menuItem.getId());
	        menuItemHandler = (MenuItemHandler) clazz.getConstructor().newInstance();
			menuItemHandler.getClass().getDeclaredMethod(HANDLE).invoke(menuItemHandler);
		} catch (Exception ee) {
			throw new VoogaException(ITEM_NOT_IMPLEMENTED_ERROR);
		}
    }
}
