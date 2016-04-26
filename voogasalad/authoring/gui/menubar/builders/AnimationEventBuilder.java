package authoring.gui.menubar.builders;

import authoring.UIManager;
import authoring.model.ElementManager;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import player.gamedisplay.Menuable;

public class AnimationEventBuilder extends Stage {

	private StackPane stack;
	
	public AnimationEventBuilder(Menuable model) {
		ElementManager elManager = (ElementManager) ((UIManager) model).getManager();
		for(Node element : elManager.getElements()) {
			stack.getChildren().add(element);
		}
		Scene scene = new Scene(stack);
		this.setScene(scene);
		this.show();
	}
	
}
