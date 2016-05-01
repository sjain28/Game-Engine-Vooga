package authoring.gui.cartography;

import java.util.List;

import authoring.VoogaScene;
import authoring.gui.eventpane.EventAccoridion;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.resourceutility.ButtonMaker;
import events.Cause;
import events.CauseAndEffectFactory;
import events.LevelTransitionEffect;
import events.VoogaEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import tools.VoogaAlert;

public class LevelCauseWindow extends Stage {
	
	private EventAccoridion causeAccoridion;
	private CompleteAuthoringModelable model;
	private VoogaEvent event;
	private CauseAndEffectFactory eventFactory;
	private String endLevel;

	public LevelCauseWindow(CompleteAuthoringModelable model, String endLevel) {
		this.model = model;
		this.event = new VoogaEvent();
		this.eventFactory = new CauseAndEffectFactory();
		this.endLevel = endLevel;
		
		ButtonMaker maker = new ButtonMaker();
		Button apply1 = maker.makeButton("Apply",e->apply());
        Button cancel1 = maker.makeButton("Cancel",e->cancel());
        causeAccoridion = new EventAccoridion(model,"Cause", apply1, cancel1);
        
        setStage();
	}

	private void setStage() {
        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(causeAccoridion);
        this.setScene(new VoogaScene(tabPane));
	}
	
	/**
	 * Use Factory to construct Event--> add event to the manager
	 */
	private void apply() {
		try {
			for (String eventDetails : causeAccoridion.getDetails()) {
				populateEvent(eventDetails);
			}
			new LevelTransitionEffect(endLevel, event);
			populateEvent("events.LevelTransitionEffect," + endLevel);
			model.addEvents(event);
			this.close();
		} catch (Exception e) {
			VoogaAlert alert = new VoogaAlert(e.getMessage());
			alert.showAndWait();
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
	
	public List<Cause> getCauseDetails() {
		return event.getCauses();
	}

}
