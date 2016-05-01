package authoring.gui.toolbar;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import player.gamedisplay.Menuable;
import tools.VoogaAlert;
import tools.VoogaException;

/**
 * class to reflectively manage the clicks on each button in the toolbar
 * @author Nick
 *
 */
public class ToolPanelHandlingMirror {
    private static final String ITEM_NOT_IMPLEMENTED_ERROR =
            "This item has not been implemented. Please do so.";
    private static final String PACKAGE_LOCATION = "authoring.gui.toolbar.toolbaritems.";
    private static final String HANDLE = "handle";

    private ActionEvent e;
    private Menuable myManager;

    public ToolPanelHandlingMirror (ActionEvent toolbarEvent, Menuable manager) {
        this.e = toolbarEvent;
        this.myManager = manager;
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
     * @throws VoogaException 
     */
    private void handleEvent () throws VoogaException {
        Button toolbarItem = (Button) e.getSource();
        ToolbarItemHandler toolbarItemHandler;
        Class<?> clazz;
        try {
            clazz = Class.forName(PACKAGE_LOCATION + toolbarItem.getId());
            toolbarItemHandler =
                    (ToolbarItemHandler) clazz.getConstructor(Menuable.class)
                            .newInstance(myManager);
            toolbarItemHandler.getClass().getDeclaredMethod(HANDLE).invoke(toolbarItemHandler);
        }
        catch (Exception ee) {
            throw new VoogaException(ITEM_NOT_IMPLEMENTED_ERROR);
        }
    }

}
