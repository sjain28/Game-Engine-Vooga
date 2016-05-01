package authoring.gui.eventpane;

import authoring.VoogaScene;
import authoring.interfaces.model.EditEventable;
import authoring.resourceutility.ButtonMaker;
import events.CauseAndEffectFactory;
import events.VoogaEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import tools.VoogaAlert;

public class EventWindow extends Stage {
	
	private static final String APPLY = "Apply";
	private static final String CANCEL = "Cancel";
	private static final int SCREEN_SIZE = 500;
	
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
        
        Button apply1 = maker.makeButton(APPLY ,e->apply());
        Button apply2= maker.makeButton(APPLY ,e->apply());
        Button cancel1 = maker.makeButton(CANCEL ,e->cancel());
        Button cancel2 = maker.makeButton(CANCEL ,e->cancel());
        
        causeAccoridion = new EventAccoridion(manager,"Cause",apply1,cancel1);
        effectAccoridion = new EventAccoridion(manager,"Effect",apply2,cancel2);
        tabPane.getTabs().addAll(causeAccoridion,effectAccoridion);
        
        this.setTitle("New Event");
        this.setWidth(SCREEN_SIZE);
        this.setHeight(SCREEN_SIZE);
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
            setReady(false);
            VoogaAlert alert = new VoogaAlert(e.getMessage());
            alert.showAndWait();
        }
    }
    
    private void populateEvent(String eventDetails) throws Exception{
        try{
            eventFactory.create(event, eventDetails);
        } catch (Exception e){
            VoogaAlert alert = new VoogaAlert(e.getMessage());
            alert.showAndWait();
        }
    }
    private void cancel(){
        this.close();
    }

	/**
	 * @return the ready
	 */
	public boolean isReady() {
		return ready;
	}

	/**
	 * @param ready the ready to set
	 */
	public void setReady(boolean ready) {
		this.ready = ready;
	}
    
}
