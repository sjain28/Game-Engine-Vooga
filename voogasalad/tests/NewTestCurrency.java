package tests;

import java.io.File;

import javafx.application.Application;
import javafx.stage.Stage;
import stats.database.VoogaDataBase;
import stats.interaction.CurrentSessionStats;


/**
 * GameRunnerTest tests reading .xml, creating sprites and displaying
 * them
 *
 */
public class NewTestCurrency extends Application {


    public static void main (String[] args) {
        launch(args);

    }
	
    @Override
    public void start (Stage primaryStage) throws Exception {
		VoogaDataBase database = VoogaDataBase.getInstance();
		//database.clear();
		database.checkThenAddIfNewGame("game 1", "fun game for friends to play");
		database.checkThenAddIfNewGame("game 2", "cool game with sharks");
		database.checkThenAddIfNewGame("game 3", "scary game with dinosaurs");
		database.checkThenAddIfNewUser("Krista", "klo14", "password1", "mario.png");
		
		CurrentSessionStats stats = new CurrentSessionStats();
		stats.saveGameProgress("HI");
		System.out.println("The progress here should be hi but it is " +stats.loadGameProgress());
		
    }

}