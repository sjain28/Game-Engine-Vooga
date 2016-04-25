package authoring.gui.eventpane;

import authoring.interfaces.model.EditEventable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tools.VoogaException;

public class CollisionCauseGUI implements EventGUI{
    private SpriteComboBox group1;
    private SpriteComboBox group2;
    private ComboBox<String> collisionType;
    
    public CollisionCauseGUI(EditEventable manager){
        group1=new SpriteComboBox(manager);
        group2= new SpriteComboBox(manager);
        collisionType = new ComboBox<String>();
        collisionType.getItems().addAll("CollisionX","CollisionY");
    }

    @Override
    public Node display () {
        VBox vbox = new VBox();
        vbox.getChildren().add(collisionType);
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(50,50,50,50));
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(group1,group2);
        vbox.getChildren().add(hbox);
        return vbox;
    }

    @Override
    public String getDetails () throws VoogaException {
        
        return "events.CollisionCause "+collisionType.getValue()+" "+group1.getSpriteId()+" "+group2.getSpriteId();
    }
}
