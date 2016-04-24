package authoring.gui.toolbar;

import authoring.resourceutility.ButtonMaker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import resources.VoogaBundles;


public class ToolPanel extends ToolBar {

    private EventHandler<ActionEvent> toolbarEvent;

    public ToolPanel (EventHandler<ActionEvent> toolbarEvent) {
        this.toolbarEvent = toolbarEvent;

        populate();

    }

    private void populate () {
        ButtonMaker maker = new ButtonMaker();
        for (String key : VoogaBundles.toolbarProperties.keySet()) {
            ImageView graphic =
                    new ImageView(new Image("file:resources/" +
                                            VoogaBundles.toolbarProperties.getString(key)));
            Button btn = maker.makeButton("", toolbarEvent);
            btn.setGraphic(graphic);
            btn.setId(key);
            btn.setTooltip(new Tooltip(key));
            this.getItems().add(btn);
        }
    }

}
