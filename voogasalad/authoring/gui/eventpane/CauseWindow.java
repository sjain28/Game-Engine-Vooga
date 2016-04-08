package authoring.gui.eventpane;

import java.util.ResourceBundle;
import events.Cause;
import events.VoogaEvent;
import javafx.scene.layout.GridPane;

public class CauseWindow extends GridPane {
    VoogaEvent event;
    Cause cause;
    ResourceBundle causes;
    
    public CauseWindow(VoogaEvent e){
        event = e;
        causes = ResourceBundle.getBundle("/events/CauseTypes");
    }
    
    
}
