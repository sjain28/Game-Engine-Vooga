package authoring.gui.eventpane;

import authoring.interfaces.model.EditEventable;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;

public class EventWindow extends TabPane{
    public EventWindow(EditEventable manager){
        Button apply1 = buttonFactory("Apply",e->apply());
        Button apply2=buttonFactory("Apply",e->apply());
        this.getTabs().add(new EventAccoridion(manager,"Cause",apply1));
        this.getTabs().add(new EventAccoridion(manager,"Effect",apply2));
        this.setWidth(1000);
        this.setHeight(1000);
    }
    
    
    /**
     * Use Factory to construct Event--> add event to the manager
     */
    private void apply(){
        
    }
    
    private Button buttonFactory(String name,EventHandler e){
        Button button = new Button(name);
        button.setOnAction(e);
        button.setAlignment(Pos.CENTER);
        return button;
    }
}
