package tests;

import javafx.application.Application;
import javafx.stage.Stage;
import player.gamerunner.GameRunner;
import player.gamerunner.IGameRunner;

/**
 * GameRunnerTest tests reading .xml, creating sprites and displaying
 * them
 *
 */
public class TestLevelsTest extends Application {

    //private static final String TESTXML_PATH = "TestWriting.xml";
    private static final String TESTXML_PATH = "games/joshtest1/levels/test1.xml";

    public static void main (String[] args) {
        launch(args);

    }

    @Override
    public void start (Stage primaryStage) throws Exception {

        IGameRunner gameRunner = new GameRunner();
        gameRunner.testLevel(TESTXML_PATH);
    }

}


