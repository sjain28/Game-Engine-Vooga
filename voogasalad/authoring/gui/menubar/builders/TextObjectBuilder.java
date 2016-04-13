package authoring.gui.menubar.builders;

import authoring.interfaces.model.EditElementable;
import authoring.model.VoogaFrontEndText;
import gameengine.Sprite;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tools.Vector;

public class TextObjectBuilder extends Builder{
   
    public TextObjectBuilder (EditElementable editor, Stage popup) {
        super(editor, popup);
        makeInfo("Text");
       // makeXLocationPicker();
       // makeYLocationPicker();
        makeCreate();
    }

    @Override
    public void compile () {
        VoogaFrontEndText text = new VoogaFrontEndText(getInfo());
        getManager().addGameElements(text);
        quit();
    }

}
