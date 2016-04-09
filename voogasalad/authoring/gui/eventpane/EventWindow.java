package authoring.gui.eventpane;

import javafx.scene.control.TabPane;

public class EventWindow extends TabPane{
    public EventWindow(){
        this.getTabs().add(new CauseAccoridion());
        
    }
}
