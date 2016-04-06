package authoring.gui;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class PropertiesWindow extends Pane{


    public PropertiesWindow(){
        Text t =new Text("Properties Window");
        t.setTranslateX(20);
        t.setTranslateY(20);
        this.getChildren().add(t);
    }
}
