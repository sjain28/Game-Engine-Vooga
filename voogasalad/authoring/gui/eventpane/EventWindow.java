package authoring.gui.eventpane;

import authoring.VoogaScene;
import authoring.interfaces.model.EditEventable;
import events.CauseAndEffectFactory;
import events.VoogaEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class EventWindow extends Stage{
    private TabPane tabPane;
    private Scene myScene;
    private EventAccoridion causeAccoridion;
    private EventAccoridion effectAccoridion;
    
    private VoogaEvent event;
    private CauseAndEffectFactory eventFactory;
    
    public EventWindow(EditEventable manager){
        
        tabPane = new TabPane();
        myScene = new VoogaScene(tabPane);
        event = new VoogaEvent();
        
        Button apply1 = buttonFactory("Apply",e->apply());
        Button apply2=buttonFactory("Apply",e->apply());
        Button cancel1 = buttonFactory("Cancel",e->cancel());
        Button cancel2 = buttonFactory("Cancel",e->cancel());
        
        causeAccoridion = new EventAccoridion(manager,"Cause",apply1,cancel1);
        effectAccoridion = new EventAccoridion(manager,"Effect",apply2,cancel2);
        tabPane.getTabs().addAll(causeAccoridion,effectAccoridion);
        
        this.setTitle("New Event");
        this.setWidth(500);
        this.setHeight(500);
        this.setScene(myScene);
    }
    
    
    /**
     * Use Factory to construct Event--> add event to the manager
     */
    private void apply(){
        for (String event : causeAccoridion.getDetails()){
            
        }
        this.close();
    }
    
    private void cancel(){
        this.close();
    }
    
    private Button buttonFactory(String name,EventHandler e){
        Button button = new Button(name);
        button.setOnAction(e);
        button.setAlignment(Pos.CENTER);
        return button;
    }
}
