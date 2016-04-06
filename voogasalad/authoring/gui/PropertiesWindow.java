package authoring.gui;

import authoring.CustomText;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class PropertiesWindow extends TabPane {

	private static final String PROPERTIES = "Properties";
	
    public PropertiesWindow(){

    	
        Tab props = new Tab();
        props.setText(PROPERTIES);
        props.setContent(new CustomText(PROPERTIES));
        this.getTabs().add(props);
    }
}
