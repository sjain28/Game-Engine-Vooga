package authoring.splash;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import authoring.Command;
import authoring.UIManager;
import authoring.VoogaScene;
import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.model.ElementManager;
import authoring.model.ElementManagerUnserializer;
import authoring.model.Preferences;
import data.Deserializer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import player.gamerunner.GameRunner;
import player.gamerunner.IGameRunner;
import resources.VoogaBundles;
import tools.VoogaException;

public class OpenCommand implements Command {

	@Override
	public void execute() {
		ProjectOpenPrompt prompt = new ProjectOpenPrompt();
		prompt.setProceedEvent(e -> {
			prompt.close();
			//TODO: use reflection to open the correct one instead of if statements
			if(((Button) e.getSource()).getId().equals("OpenAll")) {
				ProjectOpenAllPrompt openAllPrompt = new ProjectOpenAllPrompt();
				openAllPrompt.setProceedEvent(ee -> {
					try {
						openAllPrompt.close();
						String name = ((Button) ee.getSource()).getId();
						List<CompleteAuthoringModelable> models = new ArrayList<CompleteAuthoringModelable>();
						Preferences preferences = (Preferences) Deserializer.deserialize(1, "games/" + name + "/" + name + ".xml").get(0);
						VoogaBundles.preferences.setProperty("GameName", name);
						VoogaBundles.preferences.setProperty("GameDescription", preferences.getDescription());
						VoogaBundles.preferences.setProperty("GameWidth", preferences.getWidth());
						VoogaBundles.preferences.setProperty("GameHeight", preferences.getHeight());
						String prefixPath = "games/" + name + "/levels/";
						File levelsFolder = new File(prefixPath);
						for (File level : levelsFolder.listFiles()) {
							String levelPath = prefixPath + level.getName();
							ElementManagerUnserializer unserializer = new ElementManagerUnserializer(levelPath);
							ElementManager em = unserializer.unserialize();
							em.setName(level.getName().replace(".xml", ""));
							models.add(em);
						}
						UIManager manager = new UIManager(models);
						Scene scene = new VoogaScene(manager);
						Stage primaryStage = new Stage();
						primaryStage.setScene(scene);
						primaryStage.show();
					} catch (VoogaException eee) {
		
					}
				});
				openAllPrompt.show();
			} else if(((Button) e.getSource()).getId().equals("OpenBySearch")) {
				ProjectOpenBySearchPrompt openBySearchPrompt = new ProjectOpenBySearchPrompt();
				openBySearchPrompt.setProceedEvent(ee -> {
					openBySearchPrompt.close();
					String name = ((Button) ee.getSource()).getId();
					IGameRunner gameRunner = new GameRunner();
			        gameRunner.playGame(name);
				});
				openBySearchPrompt.show();
			}
		});
		prompt.show();
	}

}
