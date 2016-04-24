package tests;

import java.io.File;

import javafx.application.Application;
import javafx.stage.Stage;


/**
 * GameRunnerTest tests reading .xml, creating sprites and displaying
 * them
 *
 */
public class TestFiles extends Application {


    public static void main (String[] args) {
        launch(args);

    }
	
    @Override
    public void start (Stage primaryStage) throws Exception {

       File newFile = new File("games/levels");
      System.out.println(newFile.listFiles());
        
    }

}