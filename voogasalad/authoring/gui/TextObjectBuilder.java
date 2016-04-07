package authoring.gui;

import authoring.interfaces.model.EditElementable;
import authoring.model.VoogaText;
import gameengine.Sprite;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tools.Vector;

public class TextObjectBuilder extends Builder{
    private TextField myText;

    public TextObjectBuilder (EditElementable editor, Stage popup) {
        super(editor, popup);
        makeText();
       // makeXLocationPicker();
       // makeYLocationPicker();
        makeCreate();
    }

    public void makeText(){
        HBox text = new HBox();
        Text label = new Text("Text");
        label.setFill(Color.WHITE);
        TextField input = new TextField();
        text.getChildren().addAll(label, input);
        this.getChildren().add(text);
    }
    @Override
    public void compile () {
        VoogaText text = new VoogaText(myText.getText());
        getManager().addGameElements(text);
        quit();
    }

}
