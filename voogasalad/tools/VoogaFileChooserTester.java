package tools;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * 'Tester' for selecting files within vooga
 */
public class VoogaFileChooserTester extends Application{

    @Override
    public void start (Stage primaryStage) throws Exception {
        VoogaFileChooser chooser = new VoogaFileChooser();
        chooser.launch();
    }

    public static void main(String[] args){
        launch(args);
    }
}
