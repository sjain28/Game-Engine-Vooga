package authoring.gui;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.text.Text;

public class EventsWindow extends TabPane{
    public EventsWindow(){
        Tab cause = new Tab();
        cause.setText("Causes");
        cause.setContent(new Text("Causes"));
        this.getTabs().add(cause);
        
        Tab effect = new Tab();
        effect.setText("Effects");    
        effect.setContent(new Text("Effects"));
        this.getTabs().add(effect);
    }
}
