package authoring.gui.menubar.builders;

import authoring.VoogaScene;
import authoring.interfaces.model.EditElementable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PreferencesSetter extends Builder{
   
    public PreferencesSetter(EditElementable model){
        super(model);
    }
    
    public void initialize(){
        HBox aSetting = new HBox();
        Label aSettingText = new Label("Camera Moves With Player: ");
        CheckBox CameraMovement = new CheckBox();
        
        aSetting.getChildren().addAll(aSettingText, CameraMovement);
        HBox someSetting = new HBox();
        HBox anotherSetting = new HBox();
        Label someSettingText = new Label("Move With Player: ");
        Label anotherSettingText = new Label("Move With Player: ");
        
       // contents.getChildren().addAll(aSetting);
    }

    @Override
    public void compile () {
        // TODO Auto-generated method stub
        
    }

}
