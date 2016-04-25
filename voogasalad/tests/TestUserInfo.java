package tests;

import java.io.File;

import javafx.application.Application;
import javafx.stage.Stage;
import socialcenter.UserInfo;


/**
 * GameRunnerTest tests reading .xml, creating sprites and displaying
 * them
 *
 */
public class TestUserInfo extends Application {


    public static void main (String[] args) {
        launch(args);

    }
	
    @Override
    public void start (Stage primaryStage) throws Exception {
    	File newFile = new File("images/mario.png");
        UserInfo user = new UserInfo("Jimmy","mario.png");
        user.savePlayerInfo();
    }

}