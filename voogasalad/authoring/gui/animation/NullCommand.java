package authoring.gui.animation;

import authoring.Command;
import javafx.scene.layout.VBox;

public class NullCommand implements Command {
	
	public NullCommand(Mediator mediator, VBox container) {}

	@Override
	public void execute() {
		return;
	}

}
