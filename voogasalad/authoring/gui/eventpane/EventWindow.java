package authoring.gui.eventpane;

import authoring.interfaces.model.EditEventable;
import javafx.scene.control.TabPane;

public class EventWindow extends TabPane{
    public EventWindow(EditEventable manager){
        this.getTabs().add(new CauseAccoridion(manager));
        this.setWidth(1000);
        this.setHeight(1000);
    }
}
