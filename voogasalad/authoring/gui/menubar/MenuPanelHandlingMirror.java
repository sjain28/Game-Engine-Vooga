package authoring.gui.menubar;

import authoring.Command;
import authoring.NewSceneCommand;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.input.InputEvent;
import player.gamedisplay.Menuable;
import tools.VoogaAlert;
import tools.VoogaException;


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
    private Menuable myManager;
    private Command myEvent;

    /**
     * Instantiates the menu panel handler.
     * 
     * @param e
     * @param newSceneCommand
     * @param view
     * @param graphicsWindow
     */
    public MenuPanelHandlingMirror (ActionEvent e,
                                    Menuable manager,
                                    NewSceneCommand newSceneCommand) {
        this.e = e;
        myManager = manager;
        myEvent = newSceneCommand;
        try {
            handleEvent();
        }
        catch (VoogaException e1) {
            new VoogaAlert(e1.getMessage());
        }
    }

    public MenuPanelHandlingMirror (ActionEvent e, Menuable manager) {
        this.e = e;
        this.myManager = manager;
        try {
            handleEvent();
        }
        catch (VoogaException e1) {
            new VoogaAlert(e1.getMessage());
        }
    }

    /**
     * Handles events from the menu panel.
     * 
     * @throws VoogaException
     */
    private void handleEvent () throws VoogaException {
        MenuItem menuItem = (MenuItem) e.getSource();
        MenuItemHandler menuItemHandler;
        Class<?> clazz;
        try {
            clazz = Class.forName(PACKAGE_LOCATION + menuItem.getId());
            menuItemHandler =
                    (MenuItemHandler) clazz.getConstructor(Menuable.class, Command.class)
                            .newInstance(myManager, myEvent);
            menuItemHandler.getClass().getDeclaredMethod(HANDLE).invoke(menuItemHandler);
        }
        catch (Exception ee) {
            ee.printStackTrace();
            throw new VoogaException(ITEM_NOT_IMPLEMENTED_ERROR);
        }

    }

}
