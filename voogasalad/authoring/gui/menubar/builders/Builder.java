package authoring.gui.menubar.builders;

import authoring.CustomText;
import authoring.VoogaScene;
import authoring.interfaces.model.EditElementable;
import authoring.resourceutility.ButtonMaker;
import gameengine.SpriteFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tools.GUIUtils;
import tools.VoogaAlert;


public abstract class Builder extends Stage {

    protected SpriteFactory mySpriteFactory;
    protected EditElementable myManager;
    protected static final double SPACING = 10;
    protected boolean compile;

    protected Builder (EditElementable editor) {
        if (editor != null) {
            mySpriteFactory = editor.getSpriteFactory();
            myManager = editor;
        }
    }

    protected void load (Parent region) {
        Scene scene = new VoogaScene(region);
        this.setScene(scene);
    }

    protected HBox makeButtons () {
        HBox buttons = new HBox();
        Button create;
        
        create = new ButtonMaker().makeButton("OK", e -> compile());
        
        Button cancel = new ButtonMaker().makeButton("Cancel", e -> quit());
        buttons.getChildren().addAll(create, cancel);
        return buttons;

    }

    protected HBox makeInfo (String label, String prompt, TextField tf) {
        tf.setPromptText(prompt);
        return GUIUtils.makeRow(new CustomText(label), tf);
    }

    protected void quit () {
        this.close();
    }

    protected void numberError (String s) {
        VoogaAlert alert = new VoogaAlert(s);
        alert.showAndWait();
    }

    public abstract void compile ();

    public abstract boolean compileStatus ();

}
