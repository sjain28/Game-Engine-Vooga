package authoring.gui.menubar.builders;

import authoring.interfaces.model.EditElementable;
import gameengine.SpriteFactory;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public abstract class Builder extends VBox{
    private TextField[] myText = new TextField[5];
    private SpriteFactory mySpriteFactory;
    private EditElementable myManager;
    private Stage myStage;
    
    protected Builder(EditElementable editor, Stage popup){
        mySpriteFactory = editor.getSpriteFactory();
        myManager = editor;
        myStage = popup;
    }
    
    protected EditElementable getManager(){
        return myManager;
    }
   
    protected SpriteFactory getSpriteMaker(){
       return mySpriteFactory;
   }
    
    public void makeInfo(String prompt, int index){
        HBox text = new HBox();
        Text label = new Text(prompt);
        label.setFill(Color.WHITE);
        myText[index] = new TextField();
        text.getChildren().addAll(label, myText[index]);
        this.getChildren().add(text);
    }
    
    public void makeInfo(String prompt){
        makeInfo(prompt, 0);
    }
    
    public String getInfo(int index){
        return myText[index].getText();
    }
    
    public String getInfo(){
        return myText[0].getText();
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
//    
//    protected void makeXLocationPicker() {
//        HBox location = new HBox();
//        Text label = new Text("X Location");
//        label.setFill(Color.WHITE);
//        TextField input = new TextField();
//        location.getChildren().addAll(label, input);
//        this.getChildren().add(location);
//    }
//    
//    protected void makeYLocationPicker() {
//        HBox location = new HBox();
//        Text label = new Text("Y Location");
//        label.setFill(Color.WHITE);
//        TextField input = new TextField();
//        location.getChildren().addAll(label, input);
//        this.getChildren().add(location);
//    }
//

    protected void quit () {
        myStage.close();
    }
    
    protected void numberError(String s){
        Alert number = new Alert(AlertType.ERROR);
        number.setTitle("Error");
        number.setContentText(s);
        number.showAndWait();
    }
    
    public abstract void compile();
}
