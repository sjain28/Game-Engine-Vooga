package authoring.gui.eventpane;

import authoring.interfaces.model.EditEventable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tools.VoogaException;

public class CollisionCauseGUI implements EventGUI{
    private SpriteComboBox group1;
    private SpriteComboBox group2;
    
    public CollisionCauseGUI(EditEventable manager){
        group1=new SpriteComboBox(manager);
        group2= new SpriteComboBox(manager);
    }

    @Override
    public Node display () {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(50,50,50,50));
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(group1,group2);
        return hbox;
    }

    @Override
    public String getDetails () throws VoogaException {
        
        return "events.CollisionCause "+group1.getSpriteId()+" "+group2.getSpriteId();
    }
}
