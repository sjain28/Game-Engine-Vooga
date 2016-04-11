package authoring.gui.menubar;

import authoring.interfaces.model.CompleteAuthoringModelable;
import auxiliary.VoogaException;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;


/**
 * This is the class that handles reflection on menu panel actions.
 *
 */
public class MenuPanelHandlingMirror {

    private static final String ITEM_NOT_IMPLEMENTED_ERROR =
            "This item has not been implemented. Please do so.";
    private static final String PACKAGE_LOCATION = "authoring.gui.menubar.menuitems.";
    private static final String HANDLE = "handle";

    private ActionEvent e;
    private CompleteAuthoringModelable myManager;

    /**
     * Instantiates the menu panel handler.
     * 
     * @param e
     * @param view
     * @param graphicsWindow
     */
    public MenuPanelHandlingMirror (ActionEvent e, CompleteAuthoringModelable manager) {
        this.e = e;
        myManager = manager;
        handleEvent();
    }

    /**
     * Handles events from the menu panel.
     */
    private void handleEvent () {
        MenuItem menuItem = (MenuItem) e.getSource();
        MenuItemHandler menuItemHandler;
        Class<?> clazz;
        try {
            clazz = Class.forName(PACKAGE_LOCATION + menuItem.getId());
            menuItemHandler =
                    (MenuItemHandler) clazz.getConstructor(CompleteAuthoringModelable.class)
                            .newInstance(myManager);
            menuItemHandler.getClass().getDeclaredMethod(HANDLE).invoke(menuItemHandler);
        }
        catch (Exception ee) {
            ee.printStackTrace();
            throw new VoogaException(ITEM_NOT_IMPLEMENTED_ERROR);
        }
    }
}
