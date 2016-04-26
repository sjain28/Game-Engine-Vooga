package authoring.splash;

import authoring.Command;
import authoring.UIManager;
import authoring.VoogaScene;
import authoring.model.ElementManager;
import javafx.scene.Scene;
import javafx.stage.Stage;
import resources.VoogaBundles;

public class CreateCommand implements Command {
	
	private static final String DEFAULT_PROJECT_NAME = "My DoovalSalad Project";

	@Override
	public void execute() {
		ProjectInitializationPrompt prompt = new ProjectInitializationPrompt();
		prompt.setProceedEvent(ee -> {
			prompt.close();
			String name = (prompt.getName() == null || prompt.getName().isEmpty()) ? DEFAULT_PROJECT_NAME : prompt.getName();
			VoogaBundles.preferences.setProperty("GameName", name);
			UIManager manager = new UIManager(new ElementManager());
			Scene scene = new VoogaScene(manager);
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.show();
		});
		prompt.show();
	}

}
