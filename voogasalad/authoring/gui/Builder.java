package authoring.gui;

import authoring.interfaces.model.EditElementable;
import gameengine.SpriteFactory;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public abstract class Builder extends VBox{
    private SpriteFactory mySpriteFactory;
    private EditElementable myManager;
    private ObjectData myData;
    private Stage myStage;
    
    protected Builder(EditElementable editor, Stage popup){
        mySpriteFactory = editor.getSpriteFactory();
        myData = new ObjectData();
        myManager = editor;
        myStage = popup;
    }
    
    protected EditElementable getManager(){
        return myManager;
    }
    
    protected ObjectData getData(){
        return myData;
    }
    protected SpriteFactory getSpriteMaker(){
       return mySpriteFactory;
   }
    protected void makeCreate () {
        HBox buttons = new HBox();
        Button create = new Button("Create");
        Button cancel = new Button("Cancel");
        create.setOnAction(e -> compile());
        cancel.setOnAction(e -> quit());
        buttons.getChildren().addAll(create, cancel);
        this.getChildren().add(buttons);
    }
    
    protected void makeXLocationPicker() {
        HBox location = new HBox();
        Text label = new Text("X Location");
        label.setFill(Color.WHITE);
        TextField input = new TextField();
        location.getChildren().addAll(label, input);
        this.getChildren().add(location);
    }
    
    protected void makeYLocationPicker() {
        HBox location = new HBox();
        Text label = new Text("Y Location");
        label.setFill(Color.WHITE);
        TextField input = new TextField();
        location.getChildren().addAll(label, input);
        this.getChildren().add(location);
    }


    protected void quit () {
        myStage.close();
    }
    
    public abstract void compile();
}
