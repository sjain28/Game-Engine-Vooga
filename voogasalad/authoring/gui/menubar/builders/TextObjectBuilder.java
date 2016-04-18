package authoring.gui.menubar.builders;

import authoring.interfaces.model.EditElementable;
import authoring.model.VoogaFrontEndText;
import gameengine.Sprite;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tools.Vector;

public class TextObjectBuilder extends Builder {

	private VBox container;
	private TextField text;

	public TextObjectBuilder(EditElementable editor) {
		super(editor);
		this.text = new TextField();
		populate();
		load(this.container);
	}

	private void populate() {
		this.container = new VBox();
		container.setSpacing(SPACING);
		container.getChildren().addAll(makeInfo("Text:", "Enter text...", text), 
									   makeButtons());
	}

	@Override
	public void compile() {
		VoogaFrontEndText text = new VoogaFrontEndText(this.text.getText());
		myManager.addGameElements(text);
		quit();
	}

}
