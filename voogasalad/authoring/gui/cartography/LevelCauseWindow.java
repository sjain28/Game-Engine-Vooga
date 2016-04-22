package authoring.gui.cartography;

import authoring.VoogaScene;
import authoring.gui.eventpane.EventAccoridion;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.resourceutility.ButtonMaker;
import events.CauseAndEffectFactory;
import events.VoogaEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import tools.VoogaAlert;

public class LevelCauseWindow extends Stage {
	
	private EventAccoridion causeAccoridion;
	private CompleteAuthoringModelable model;
	private VoogaEvent event;
	private CauseAndEffectFactory eventFactory;

	public LevelCauseWindow(CompleteAuthoringModelable model) {
		this.model = model;
		this.event = new VoogaEvent();
		this.eventFactory = new CauseAndEffectFactory();
		
		ButtonMaker maker = new ButtonMaker();
		Button apply1 = maker.makeButton("Apply",e->apply());
        Button cancel1 = maker.makeButton("Cancel",e->cancel());
        causeAccoridion = new EventAccoridion(model,"Cause",apply1,cancel1);
        
        setStage();
	}

	private void setStage() {
        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(causeAccoridion);
        this.setScene(new VoogaScene(tabPane));
        this.show();
	}
	
	/**
	 * Use Factory to construct Event--> add event to the manager
	 */
	private void apply() {
		try {
			for (String eventDetails : causeAccoridion.getDetails()) {
				populateEvent(eventDetails);
			}
			model.addEvents(event);
			this.close();
		} catch (Exception e) {
			e.printStackTrace();
			new VoogaAlert(e.getMessage());
		}
	}
	
	private void populateEvent(String eventDetails) throws Exception{
        try{
            eventFactory.create(event, eventDetails);
        } catch (Exception e){
            throw e;
        }
    }
	
	private void cancel(){
        this.close();
    }

}
