
package player.gamerunner;

import javafx.application.Application;
import javafx.stage.Stage;


/**
 * GameRunnerTest tests reading .xml, creating sprites and displaying
 * them
 *
 */
public class GameRunnerTest extends Application {

    //private static final String TESTXML_PATH = "TestWriting.xml";
    private static final String TESTXML_PATH = "DataWritingTest.xml";

    public static void main (String[] args) {
        launch(args);

    }

    @Override
    public void start (Stage primaryStage) throws Exception {

        IGameRunner gameRunner = new GameRunner();
        gameRunner.testLevel(TESTXML_PATH);
    }

}
