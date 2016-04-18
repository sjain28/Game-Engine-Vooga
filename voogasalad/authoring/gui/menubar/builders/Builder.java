package authoring.gui.menubar.builders;

import authoring.CustomText;
import authoring.VoogaScene;
import authoring.interfaces.model.EditElementable;
import authoring.resourceutility.ButtonMaker;
import gameengine.SpriteFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tools.VoogaAlert;

public abstract class Builder extends Stage {
	
    protected SpriteFactory mySpriteFactory;
    protected EditElementable myManager;
	protected static final double SPACING = 10;
    
    protected Builder(EditElementable editor){
        mySpriteFactory = editor.getSpriteFactory();
        myManager = editor;
    }
    
    protected void show(Parent region) {
        Scene scene = new VoogaScene(region);
        this.setScene(scene);
        this.show();
    }
    
    protected HBox makeButtons() {
        HBox buttons = new HBox();
        Button create = new ButtonMaker().makeButton("OK", e -> compile());
        Button cancel = new ButtonMaker().makeButton("Cancel", e -> quit());
        buttons.getChildren().addAll(create, cancel);
        return buttons;
    }
    
    protected HBox makeInfo(String label, String prompt, TextField tf) {
		tf.setPromptText(prompt);
		return makeRow(new CustomText(label), tf);
	}

    protected void quit () {
        this.close();
    }
    
    protected HBox makeRow(Node... items) {
    	HBox row = new HBox();
    	for(Node node : items) {
    		row.getChildren().add(node);
    	}
    	row.setSpacing(10);
    	return row;
    }
    
    protected void numberError(String s){
        new VoogaAlert(s);
    }
    
    public abstract void compile();
    
    
}
