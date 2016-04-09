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
        makeInfo("Archetype");
        makeCreate();
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
