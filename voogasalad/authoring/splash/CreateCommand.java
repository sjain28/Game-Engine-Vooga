package authoring.splash;

import java.io.File;
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
import resources.VoogaBundles;
import stats.database.VoogaDataBase;
import stats.interaction.CurrentSessionStats;
import tools.VoogaException;


public class CreateCommand implements Command {

    private static final String DEFAULT_PROJECT_NAME = "My DoovalSalad Project";
    private static final String DEFAULT_DESCRIPTION = "A game I built with DoovalSalad";
    private static final String LOAD = "load";
    private static final String GAME_STRING = "games/";

    /**
     * Executes the command to open up the create game screen
     */
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

        ProjectInitializationPrompt newGamePrompt = new ProjectInitializationPrompt();
        newGamePrompt.setProceedEvent(ee -> {
            newGamePrompt.close();
            storeInfo(getFieldOrDefault(newGamePrompt.getName(), DEFAULT_PROJECT_NAME),
                      getFieldOrDefault(newGamePrompt.getDescription(), DEFAULT_DESCRIPTION),
                      newGamePrompt.getDimension().getFirst(),
                      newGamePrompt.getDimension().getLast());

            open(null, "giberish");
        });
        newGamePrompt.show();
    }

    private void promptForSave () {
        CurrentSessionStats stats = new CurrentSessionStats();
        stats.endCurrentAuthoringSession();
        VoogaDataBase.getInstance().save();
    }

    private void showAuthorGamePrompt (StarterPrompt prompt) {
        prompt.setProceedEvent(eee -> {
            try {
                prompt.close();
                String name = ((Button) eee.getSource()).getId();
                List<CompleteAuthoringModelable> models = new ArrayList<>();
                Preferences p =
                        (Preferences) Deserializer
                                .deserialize(1, GAME_STRING + name + "/" + name + ".xml").get(0);
                storeInfo(name, p.getDescription(), p.getWidth(), p.getHeight());

                String prefixPath = GAME_STRING + name + "/levels/";
                File levelsFolder = new File(prefixPath);
                for (File level : levelsFolder.listFiles()) {
                    String levelPath = prefixPath + level.getName();
                    ElementManagerUnserializer unserializer =
                            new ElementManagerUnserializer(levelPath);
                    ElementManager em = unserializer.unserialize();
                    em.setName(level.getName().replace(".xml", ""));
                    models.add(em);
                }
                open(models, LOAD);
            }
            catch (VoogaException ex) {
            }
        });
        prompt.show();
    }

    private void storeInfo (String name, String description, String width, String height) {
        VoogaDataBase database = VoogaDataBase.getInstance();
        database.checkThenAddIfNewGame(name, description);

        VoogaBundles.preferences.setProperty("GameName", name);
        VoogaBundles.preferences.setProperty("GameDescription", description);
        VoogaBundles.preferences.setProperty("GameWidth", width);
        VoogaBundles.preferences.setProperty("GameHeight", height);
    }

    private String getFieldOrDefault (String field, String defaultField) {
        return (field == null || field.isEmpty()) ? defaultField : field;
    }

    @SuppressWarnings("unchecked")
    private void open (Object models, String tag) {
        CurrentSessionStats stats = new CurrentSessionStats();
        stats.startAuthoringSession(); 
        UIManager manager;
        if (tag.equals(LOAD)) {
            manager = new UIManager((List<CompleteAuthoringModelable>) models);
        }
        else {
            manager = new UIManager(new ElementManager());
        }
        Scene scene = new VoogaScene(manager);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> promptForSave());
    }

}
