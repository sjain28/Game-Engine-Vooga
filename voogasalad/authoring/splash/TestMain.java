package authoring.splash;

import javafx.application.Application;
import javafx.stage.Stage;

public class TestMain extends Application {

    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage primaryStage) throws Exception {
        Stage test = new LoginScreen();
        test.show();
    }

}
