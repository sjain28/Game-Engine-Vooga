package authoring.gui.menubar.builders;

import java.io.File;
import authoring.interfaces.model.EditElementable;
import authoring.model.GameObject;
import gameengine.Sprite;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tools.Vector;

public class GameObjectBuilder extends Builder{
    
    private String myArchtype;
    
    public GameObjectBuilder(EditElementable editor, Stage popup){
        super(editor, popup);
        
        makeArchetypePicker();
        makeCreate();
    }
    
    public void compile () {
        try{
            System.out.println("New Archetype: "+myArchtype);
            Sprite sprite = getSpriteMaker().createSprite(myArchtype);
            System.out.println("Sprite:"+sprite);
            getManager().addGameElements(new GameObject(sprite));
            getManager().addElementId(myArchtype);
            quit();
        }
        catch(Exception e){
            e.printStackTrace();
           numberError("Please select an Archtype");
        }
    }


    private void makeArchetypePicker(){
        HBox complete = new HBox();
        Text label = new Text("Archetype");
        label.setFill(Color.WHITE);
        ComboBox<String> archtypes = new ComboBox<String>();
        archtypes.getItems().addAll(getSpriteMaker().getAllArchetypeNames());
        archtypes.setOnAction(e -> myArchtype = archtypes.getValue());
        complete.getChildren().addAll(label, archtypes);
        this.getChildren().add(complete);
        
    }
    
    

}
