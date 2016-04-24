package authoring.gui.toolbar;

import java.util.Enumeration;
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
        
        for (Enumeration<?> enumer = VoogaBundles.toolbarProperties.propertyNames(); enumer.hasMoreElements();) {
            String key = (String) enumer.nextElement();
            
            ImageView graphic =
                    new ImageView(new Image("file:resources/" +
                                            VoogaBundles.toolbarProperties.getProperty(key)));
            graphic.setFitWidth(30);
            graphic.setFitHeight(30);
            Button btn = maker.makeButton("", toolbarEvent);
            btn.setGraphic(graphic);
            btn.setId(key);
            btn.setTooltip(new Tooltip(key));
            this.getItems().add(btn);
        }
    }

}
