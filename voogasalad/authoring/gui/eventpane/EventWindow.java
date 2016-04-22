package authoring.gui.eventpane;

import authoring.VoogaScene;
import authoring.interfaces.model.EditEventable;
import authoring.resourceutility.ButtonMaker;
import events.CauseAndEffectFactory;
import events.VoogaEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import tools.VoogaAlert;
import tools.VoogaException;

public class EventWindow extends Stage{
    private TabPane tabPane;
    private Scene myScene;
    private EventAccoridion causeAccoridion;
    private EventAccoridion effectAccoridion;
    private EditEventable manager;
    
    private VoogaEvent event;
    private CauseAndEffectFactory eventFactory;
    private boolean ready;
    
    public EventWindow(EditEventable manager){
        this.manager = manager;
        
        ButtonMaker maker = new ButtonMaker();
        
        tabPane = new TabPane();
        myScene = new VoogaScene(tabPane);
        event = new VoogaEvent();
        eventFactory = new CauseAndEffectFactory();
        
        Button apply1 = maker.makeButton("Apply",e->apply());
        Button apply2= maker.makeButton("Apply",e->apply());
        Button cancel1 = maker.makeButton("Cancel",e->cancel());
        Button cancel2 = maker.makeButton("Cancel",e->cancel());
        
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
        try{
            for (String eventDetails : causeAccoridion.getDetails()){
                populateEvent(eventDetails);
            }
        
            for (String eventDetails : effectAccoridion.getDetails()){
                populateEvent(eventDetails);
            }
            manager.addEvents(event);
            this.close();
            
        } catch (Exception e){
            ready=false;
            new VoogaAlert(e.getMessage());
        }
        
        
    }
    
    private void populateEvent(String eventDetails) throws Exception{
        try{
            System.out.println("EventDetails-EventWindow: "+eventDetails);
            eventFactory.create(event, eventDetails);
        } catch (Exception e){
            throw e;
        }
    }
    private void cancel(){
        this.close();
    }
    
}
