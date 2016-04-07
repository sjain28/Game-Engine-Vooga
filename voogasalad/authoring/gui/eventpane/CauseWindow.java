package authoring.gui.eventpane;

import java.util.ResourceBundle;
import events.Cause;
import events.Event;
import javafx.scene.layout.GridPane;

public class CauseWindow extends GridPane {
    Event event;
    Cause cause;
    ResourceBundle causes;
    
    public CauseWindow(Event e){
        event = e;
        causes = ResourceBundle.getBundle("/events/CauseTypes");
    }
    
    

}
