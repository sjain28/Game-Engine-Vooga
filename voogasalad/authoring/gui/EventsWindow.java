package authoring.gui;

import authoring.CustomText;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.text.Text;

public class EventsWindow extends TabPane {
	
	private static final String CAUSES = "Causes";
	private static final String EFFECTS = "Effects";
	
	/**
	 * Initialized the Events Window, responsible for displaying all the currently initialized Causes and Events and their links.
	 * 
	 * TODO: actually implement
	 */
    public EventsWindow(){
    	
        Tab cause = new Tab();
        cause.setText(CAUSES);
        cause.setContent(new CustomText(CAUSES));
        this.getTabs().add(cause);
        
        Tab effect = new Tab();
        effect.setText(EFFECTS);    
        effect.setContent(new CustomText(EFFECTS));
        this.getTabs().add(effect);
        
    }
}
