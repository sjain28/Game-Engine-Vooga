package authoring.gui.toolbar;

import java.lang.reflect.Method;
import authoring.gui.menubar.MenuItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import player.gamedisplay.Menuable;
import tools.VoogaAlert;
import tools.VoogaException;


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
            // TODO Auto-generated catch block
            new VoogaAlert(e1.getMessage());
        }
        System.out.println(toolbarEvent.getSource());
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
            System.out.println("Class-Tool: "+clazz);
            toolbarItemHandler =
                    (ToolbarItemHandler) clazz.getConstructor(Menuable.class)
                            .newInstance(myManager);
            System.out.println("ToolBarItemHandler-Tool: "+toolbarItemHandler);
            toolbarItemHandler.getClass().getDeclaredMethod(HANDLE).invoke(toolbarItemHandler);
        }
        catch (Exception ee) {
            ee.printStackTrace();
            throw new VoogaException(ITEM_NOT_IMPLEMENTED_ERROR);
        }
    }

}
