package authoring.gui;

import authoring.interfaces.model.EditElementable;
import authoring.model.VoogaText;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ArchetypeBuilder extends Builder {
    private TextField myText;

    public ArchetypeBuilder (EditElementable editor, Stage popup) {
        super(editor, popup);
        makeArchtype();
        makeCreate();
    }

    private void makeArchtype () {
        HBox text = new HBox();
        Text label = new Text("Archetype");
        label.setFill(Color.WHITE);
        myText = new TextField();
        text.getChildren().addAll(label, myText);
        this.getChildren().add(text);
    }

    @Override
    public void compile () {
        //TODO: 
        //add in archetype initialization
        //Archetype arch = new Archetype(myText.getValue());
        getManager().addGameElements();
        quit();
    }

}
