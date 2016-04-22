package authoring.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import authoring.CustomText;
import authoring.interfaces.model.CompleteAuthoringModelable;
import events.VoogaEvent;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class EventsWindow extends TabPane implements Observer{
	
	private static final String NAME = "Event Manager";
	private CompleteAuthoringModelable myManager;
	private Tab main;
	private VBox content;
	private List<VoogaEvent> current;
	
	/**
	 * Initialized the Events Window, responsible for displaying all the currently initialized Causes and Events and their links.
	 * 
	 */
    public EventsWindow(CompleteAuthoringModelable manager){
        myManager = manager;
        main = new Tab(NAME);
        content = new VBox();
        current = new ArrayList<VoogaEvent>();   
        initialize();
        current.addAll(myManager.getEvents());
    }
    
    private void initialize(){
        for(VoogaEvent e: myManager.getEvents()){
            if(!current.contains(e)){
                HBox info = new HBox();
                //ListView causes = new ListView((ObservableList) e.getCauses());
                //ListView effects = new ListView((ObservableList) e.getEffects());
                //info.getChildren().addAll(causes, effects);
                //content.getChildren().add(info);
                current.add(e);
            }
            
        }
        
        main.setContent(content);
        this.getTabs().add(main);
    }

    @Override
    public void update (Observable o, Object arg) {
        initialize();
    }

   
}
