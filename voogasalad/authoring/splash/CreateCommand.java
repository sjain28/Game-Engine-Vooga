package authoring.splash;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import authoring.Command;
import authoring.UIManager;
import authoring.VoogaScene;
import authoring.model.ElementManager;
import authoring.model.ElementManagerUnserializer;
import authoring.model.Preferences;
import data.Deserializer;
import database.VoogaAuthorSession;
import database.VoogaDataBase;
import database.VoogaStatInfo;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import player.gamerunner.GameRunner;
import player.gamerunner.IGameRunner;
import resources.VoogaBundles;
import tools.VoogaException;
import authoring.interfaces.model.CompleteAuthoringModelable;


public class CreateCommand implements Command {

    private static final String DEFAULT_PROJECT_NAME = "My DoovalSalad Project";
    private static final String DEFAULT_DESCRIPTION = "A game I built with DoovalSalad";

    @Override
    public void execute () {
        ProjectChooseAuthoringTypePrompt authoringTypePrompt =
                new ProjectChooseAuthoringTypePrompt();
        authoringTypePrompt.show();
        authoringTypePrompt.setProceedEvent(e -> {
            authoringTypePrompt.close();
            if (((Button) e.getSource()).getId().equals("NewGame")) {
                showNewGamePrompt();
            }
            else if (((Button) e.getSource()).getId().equals("OpenGame")) {
                ProjectBrowseTypePrompt browsePrompt = new ProjectBrowseTypePrompt();
                browsePrompt.show();
                browsePrompt.setProceedEvent(ee -> {
                    browsePrompt.close();
                    if (((Button) ee.getSource()).getId().equals("OpenAll")) {
                        showAuthorGamePrompt(new ProjectOpenAllPrompt());
                    }
                    else if (((Button) ee.getSource()).getId().equals("OpenBySearch")) {
                        showAuthorGamePrompt(new ProjectOpenBySearchPrompt());
                    }
                });
            }
        });
    }

    private void showNewGamePrompt () {
    	VoogaDataBase database = VoogaDataBase.getInstance();
        ProjectInitializationPrompt newGamePrompt = new ProjectInitializationPrompt();
        newGamePrompt.setProceedEvent(ee -> {
            newGamePrompt.close();
            String name = getFieldOrDefault(newGamePrompt.getName(), DEFAULT_PROJECT_NAME);
            String description =
                    getFieldOrDefault(newGamePrompt.getDescription(), DEFAULT_DESCRIPTION);
            //add game to database if it's a new game
            database.checkThenAddIfNewGame(name, description);
            String width = newGamePrompt.getDimension().getFirst();
            String height = newGamePrompt.getDimension().getLast();
            VoogaBundles.preferences.setProperty("GameName", name);
            VoogaBundles.preferences.setProperty("GameDescription", description);
            VoogaBundles.preferences.setProperty("GameWidth", width);
            VoogaBundles.preferences.setProperty("GameHeight", height);
        	String username = VoogaBundles.preferences.getProperty("UserName");
        	VoogaStatInfo statinfo = (VoogaStatInfo) VoogaDataBase.getInstance().getStatByGameAndUser(name, username);
        	statinfo.addAuthoringSession(new VoogaAuthorSession(new Date()));
            UIManager manager = new UIManager(new ElementManager());
            Scene scene = new VoogaScene(manager);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.show();
            primaryStage.setOnCloseRequest(e -> {
                promptForSave();
            });
        });
        newGamePrompt.show();
    }

    private void promptForSave () {
    	String gamename = VoogaBundles.preferences.getProperty("GameName");
    	String username = VoogaBundles.preferences.getProperty("UserName");
    	VoogaStatInfo statinfo = (VoogaStatInfo) VoogaDataBase.getInstance().getStatByGameAndUser(gamename, username);
        statinfo.getLatestAuthoringSession().endSession();
    	VoogaDataBase.getInstance().printDataBase();
        VoogaDataBase.getInstance().save();
    }

    private void showAuthorGamePrompt (StarterPrompt prompt) {
        prompt.setProceedEvent(eee -> {
            try {
                prompt.close();
                String name = ((Button) eee.getSource()).getId();
                List<CompleteAuthoringModelable> models =
                        new ArrayList<CompleteAuthoringModelable>();
                Preferences preferences = (Preferences) Deserializer
                        .deserialize(1, "games/" + name + "/" + name + ".xml").get(0);
                VoogaBundles.preferences.setProperty("GameName", name);
                VoogaBundles.preferences.setProperty("GameDescription",
                                                     preferences.getDescription());
                VoogaBundles.preferences.setProperty("GameWidth", preferences.getWidth());
                VoogaBundles.preferences.setProperty("GameHeight", preferences.getHeight());
                String prefixPath = "games/" + name + "/levels/";
                File levelsFolder = new File(prefixPath);
                for (File level : levelsFolder.listFiles()) {
                    String levelPath = prefixPath + level.getName();
                    ElementManagerUnserializer unserializer =
                            new ElementManagerUnserializer(levelPath);
                    ElementManager em = unserializer.unserialize();
                    em.setName(level.getName().replace(".xml", ""));
                    models.add(em);
                }
                UIManager manager = new UIManager(models);
                Scene scene = new VoogaScene(manager);
                Stage primaryStage = new Stage();
                primaryStage.setScene(scene);
                primaryStage.show();
                primaryStage.setOnCloseRequest(e -> promptForSave());
            }
            catch (VoogaException ex) {


            }
        });
        prompt.show();
    }

    private String getFieldOrDefault (String field, String defaultField) {
        return (field == null || field.isEmpty()) ? defaultField : field;
    }

}
