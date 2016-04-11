package authoring.gui.toolbar;

import javafx.event.ActionEvent;


public class ToolPanelHandlingMirror {

    public ToolPanelHandlingMirror (ActionEvent toolbarEvent) {
        System.out.println(toolbarEvent.getSource());
    }

}
