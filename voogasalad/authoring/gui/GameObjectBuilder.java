package authoring.gui;

import java.io.File;
import authoring.interfaces.model.EditElementable;
import authoring.model.GameObject;
import gameengine.Sprite;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tools.Vector;

public class GameObjectBuilder extends Builder{
    
    
    
    public GameObjectBuilder(EditElementable editor, Stage popup){
        super(editor, popup);
        
        makeArchetypePicker();
        //makeImagePicker();
        //makeXLocationPicker();
        //makeYLocationPicker();
        makeCreate();
    }
    
    public void compile () {
        Sprite sprite = getSpriteMaker().createSprite(getData().getArchetype());
        sprite.setPosition(new Vector(getData().getX(), getData().getY()));
        if(getData().getImagePath() != null){
            sprite.setImagePath(getData().getImagePath());
        }
        getManager().addGameElements(new GameObject(sprite));
        quit();
    }

//    private void makeImagePicker () {
//        HBox complete = new HBox();
//        Text label = new Text("Image");
//        label.setFill(Color.WHITE);
//        Button image = new Button("Choose Image");
//        image.setOnAction( new EventHandler<ActionEvent>(){
//            
//            @Override
//            public void handle(ActionEvent t) {
//                FileChooser fileChooser = new FileChooser();
//                 
//                //Set extension filter
//                FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
//                FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
//                fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
//                  
//                //Show open file dialog
//                File file = fileChooser.showOpenDialog(null);
//                getData().setImagePath(file.getAbsolutePath());
//            }
//        });
//        complete.getChildren().addAll(label, image);
//        this.getChildren().add(complete);
//    }

    private void makeArchetypePicker(){
        HBox complete = new HBox();
        Text label = new Text("Archetype");
        label.setFill(Color.WHITE);
        ComboBox<String> archtypes = new ComboBox<String>();
        //uncomment once instantiated in order to actually fill with archetype options
        //archtypes.getItems().addAll(mySpriteFactory.getArchetypeOptions());
        archtypes.getItems().add("Test");
        archtypes.setValue("Archtype");
        archtypes.setOnAction(e -> getData().setArchetype(archtypes.getValue()));
        complete.getChildren().addAll(label, archtypes);
        this.getChildren().add(complete);
        
    }
    
    

}
