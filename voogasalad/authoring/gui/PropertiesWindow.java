package authoring.gui;

import authoring.CustomText;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class PropertiesWindow extends TabPane {

	private static final String PROPERTIES = "Properties";
	
    public PropertiesWindow(){
<<<<<<< HEAD
        Text t =new Text("Properties Window");
        t.setTranslateX(20);
        t.setTranslateY(20);
        this.getChildren().add(t);
=======
    	
        Tab props = new Tab();
        props.setText(PROPERTIES);
        props.setContent(new CustomText(PROPERTIES));
        this.getTabs().add(props);
        
>>>>>>> 1783aa88b8f1cc1f42c20edef310da3cbf562bd3
    }
}
