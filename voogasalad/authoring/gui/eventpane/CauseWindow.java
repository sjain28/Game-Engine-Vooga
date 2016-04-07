package authoring.gui.eventpane;

import events.Cause;
import events.Event;
import javafx.scene.layout.GridPane;

public class CauseWindow extends GridPane {
    Event event;
    Cause cause;
    
    public CauseWindow(Event e){
        event = e;
    }

}
