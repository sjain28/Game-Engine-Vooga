package authoring.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import authoring.CustomText;
import authoring.gui.eventpane.EventWindow;
import authoring.interfaces.model.CompleteAuthoringModelable;
import events.Cause;
import events.Effect;
import events.VoogaEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class EventsWindow extends TabPane implements Observer{
	
	private static final String NAME = "Event Manager";
	private CompleteAuthoringModelable myManager;
	private Tab main;
	private VBox content;
	private Map<VoogaEvent, ObservableList<String>> effects;
	private Map<VoogaEvent, ObservableList<String>> causes;
	
	/**
	 * Initialized the Events Window, responsible for displaying all the currently initialized Causes and Events and their links.
	 * 
	 */
    public EventsWindow(CompleteAuthoringModelable manager){
        myManager = manager;
        myManager.addObserver(this);
        main = new Tab(NAME);
        content = new VBox();
        causes = new HashMap<VoogaEvent, ObservableList<String>>(); 
        effects = new HashMap<VoogaEvent, ObservableList<String>>(); 
        initialize();
        main.setContent(new VBox(content, addButton()));
        this.getTabs().add(main);
    }
    
    private void initialize(){
        for(VoogaEvent e: myManager.getEvents()){
            if(!causes.keySet().contains(e) && !effects.keySet().contains(e)){
                ObservableList<String> causesString = FXCollections.observableArrayList();
                for(Cause cause: e.getCauses()){
                    causesString.addAll(cause.toString());
                }  
                causes.put(e, causesString);
                ObservableList<String> effectsString = FXCollections.observableArrayList();
                for(Effect effect: e.getEffects()){
                    System.out.println(effect.toString());
                    effectsString.addAll(effect.toString());
                }  
                effects.put(e, effectsString);
                HBox info = new HBox();
                ListView<String> causeList = new ListView<String>(causes.get(e));
                ListView<String> effectList = new ListView<String>(effects.get(e));
                Button delete = new Button("X");
                delete.setOnAction(ee -> delete(e, info));
                info.getChildren().addAll(causeList, effectList, delete);
                content.getChildren().add(info);
            }
        }
        
    }
    
    private Button addButton(){
        Button add = new Button("Add Event");     
        add.setOnAction(e -> addEvent());
        return add;
    }

    private void addEvent () {
      EventWindow popup = new EventWindow(myManager);
      popup.show();
    }

    private void delete (VoogaEvent e, HBox info) {
       myManager.getEvents().remove(e);
       content.getChildren().remove(info);
       causes.remove(e);
       effects.remove(e);
    }

    @Override
    public void update (Observable o, Object arg) {
        initialize();
    }

   
}
