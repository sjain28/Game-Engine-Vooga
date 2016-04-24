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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import resources.VoogaBundles;
import tools.VoogaException;

public class OpenCommand implements Command {

	@Override
	public void execute() {
		ProjectOpenPrompt prompt = new ProjectOpenPrompt();
		prompt.setProceedEvent(ee -> {
			try {
			prompt.close();
			String name = ((Button) ee.getSource()).getId();
			List<CompleteAuthoringModelable> models = new ArrayList<CompleteAuthoringModelable>();
			VoogaBundles.preferences.setProperty("GameName", name);
			String prefixPath = "games/" + name + "/levels/";
			File levelsFolder = new File(prefixPath);
			for(File level : levelsFolder.listFiles()) {
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
			//primaryStage.setMaximized(true);
			primaryStage.show();
			} catch(VoogaException eee) {
				
			}
		});
		prompt.show();
	}

}
