package authoring;

import java.io.File;
import java.io.FileOutputStream;

import authoring.model.ElementManager;
import javafx.scene.Scene;
import javafx.stage.Stage;
import resources.VoogaBundles;

public class OpenCommand implements Command {
	
	private static final String DEFAULT_PROJECT_NAME = "My DoovalSalad Project";

	@Override
	public void execute() {
		ProjectOpenPrompt prompt = new ProjectOpenPrompt();
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
			try {
				VoogaBundles.preferences.storeToXML(new FileOutputStream(new File("LevelConnection.xml")), "Level Connection");
			} catch (Exception eee) {
				eee.printStackTrace();
			}
		});
		prompt.show();
	}

}
