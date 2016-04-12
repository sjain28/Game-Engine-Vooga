package authoring.gui.toolbar;

import java.lang.reflect.Method;
import authoring.gui.menubar.MenuItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;
import auxiliary.VoogaException;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;


public class ToolPanelHandlingMirror {
    private static final String ITEM_NOT_IMPLEMENTED_ERROR =
            "This item has not been implemented. Please do so.";
    private static final String PACKAGE_LOCATION = "authoring.gui.toolbar.toolbaritems.";
    private static final String HANDLE = "handle";

    private ActionEvent e;
    private CompleteAuthoringModelable myManager;

    public ToolPanelHandlingMirror (ActionEvent toolbarEvent, CompleteAuthoringModelable manager) {
        this.e = toolbarEvent;
        this.myManager = manager;
        handleEvent();
        System.out.println(toolbarEvent.getSource());
    }

    /**
     * Handles events from the menu panel.
     */
    private void handleEvent () {
        Button toolbarItem = (Button) e.getSource();
        ToolbarItemHandler toolbarItemHandler;
        Class<?> clazz;
        try {
            clazz = Class.forName(PACKAGE_LOCATION + toolbarItem.getId());
            System.out.println("Class-Tool: "+clazz);
            toolbarItemHandler =
                    (ToolbarItemHandler) clazz.getConstructor(CompleteAuthoringModelable.class)
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
