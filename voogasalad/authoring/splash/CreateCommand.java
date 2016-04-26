package authoring.splash;

import java.io.File;
import java.io.FileOutputStream;

import authoring.Command;
import authoring.UIManager;
import authoring.VoogaScene;
import authoring.model.ElementManager;
import javafx.scene.Scene;
import javafx.stage.Stage;
import resources.VoogaBundles;

public class CreateCommand implements Command {
	
	private static final String DEFAULT_PROJECT_NAME = "My DoovalSalad Project";
	private static final String DEFAULT_DESCRIPTION = "A game I built with DoovalSalad";

	@Override
	public void execute() {
		ProjectInitializationPrompt prompt = new ProjectInitializationPrompt();
		prompt.setProceedEvent(ee -> {
			prompt.close();
			String name = getFieldOrDefault(prompt.getName(), DEFAULT_PROJECT_NAME);
			String description = getFieldOrDefault(prompt.getDescription(), DEFAULT_DESCRIPTION);
			String width = prompt.getDimension().getFirst();
			String height = prompt.getDimension().getLast();
			VoogaBundles.preferences.setProperty("GameName", name);
			VoogaBundles.preferences.setProperty("GameDescription", description);
			VoogaBundles.preferences.setProperty("GameWidth", width);
			VoogaBundles.preferences.setProperty("GameHeight", height);
			UIManager manager = new UIManager(new ElementManager());
			Scene scene = new VoogaScene(manager);
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.show();
		});
		prompt.show();
	}
	
	private String getFieldOrDefault(String field, String defaultField) {
		return (field == null || field.isEmpty()) ? defaultField : field;
	}

}
