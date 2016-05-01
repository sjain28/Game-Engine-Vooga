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

/**
 * Level Cause window to handle events in the level cartography.
 * 
 * @author Harry Guo, Nick Lockett, Arjun Desai, Aditya Srinivasan
 *
 */
public class LevelCauseWindow extends Stage {

	private EventAccoridion causeAccoridion;
	private CompleteAuthoringModelable model;
	private VoogaEvent event;
	private CauseAndEffectFactory eventFactory;
	private String endLevel;

	/**
	 * Instantiates the window and connects it to the complete authoring model
	 * @param model
	 * @param endLevel
	 */
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

	/**
	 * Initializes the stage.
	 */
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

	/**
	 * Populates the event
	 * @param eventDetails
	 * @throws Exception
	 */
	private void populateEvent(String eventDetails) throws Exception{
		try{
			eventFactory.create(event, eventDetails);
		} catch (Exception e){
			VoogaAlert alert = new VoogaAlert(e.getMessage());
			alert.showAndWait();
		}
	}

	/**
	 * Cancels the creation of an event.
	 */
	private void cancel(){
		this.close();
	}

	/**
	 * Gets the list of causes based on the event selected.
	 * @return
	 */
	public List<Cause> getCauseDetails() {
		return event.getCauses();
	}

}
