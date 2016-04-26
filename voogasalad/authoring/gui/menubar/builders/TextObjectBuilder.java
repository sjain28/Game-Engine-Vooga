package authoring.gui.menubar.builders;

import authoring.interfaces.model.EditElementable;
import authoring.model.VoogaFrontEndText;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import tools.VoogaAlert;


public class TextObjectBuilder extends Builder {

    private VBox container;
    private TextField text;
    private VoogaFrontEndText returnText;
    
    public TextObjectBuilder (EditElementable editor) {
        super(editor);
        this.text = new TextField();
        returnText = new VoogaFrontEndText();
        populate();
        load(this.container);
    }

    public TextObjectBuilder (EditElementable editor, VoogaFrontEndText text) {
        this(editor);
        returnText = text;
    }

    private void populate () {
        this.container = new VBox();
        container.setSpacing(SPACING);
        container.getChildren().addAll(makeInfo("Text:", "Enter text...", text),
                                       makeButtons());
    }

    @Override
    public void compile () {
        
        returnText.setText(this.text.getText());
        myManager.addGameElements(returnText);
        quit();
    }

    @Override
    public boolean compileStatus () {
        return compile;
    }

}
