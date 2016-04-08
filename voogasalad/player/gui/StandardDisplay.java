package player.gui;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Standard Display that creates a display with basic user-interaction controls
 * Uses composition to contain elements of the display
 * 
 * @author Hunter Lee
 *
 */
public class StandardDisplay implements IGameDisplay {
	
	private static final int PANE_SIZE = 600;
	
	private Stage myStage;
	private Scene myScene;
	private BorderPane myPane;
	private PromptFactory myPrompt;
	
	public StandardDisplay() {
		
		myStage = new Stage();
		myPane = new BorderPane();
		myScene = new Scene(myPane, PANE_SIZE, PANE_SIZE);
		myPrompt = new PromptFactory();
		
	}

	@Override
	public void read() {
		// Auto-generated method stub
		//TODO: What is this reading? Format of the file being read(Sprites)?
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		//Creates the main pane
		createPane();
		//Shows the scene
		getStage().show();
		
	}

	@Override
	public void createPrompt(String message) {
		
		getPrompt().prompt(message);
		
	}
	
	private void createPane() {
		
		//Adds all components into the main border pane
		getPane().setCenter(new StackPane());//TODO:this stackpane will be created elsewhere in createGameScreen()
		getPane().setBottom(myControl.createControl());
		getStage().setScene(getScene());
		
		
	}

	/**
	 * @return the pane
	 */
	public BorderPane getPane() {
		return myPane;
	}

	/**
	 * @return the myPrompt
	 */
	public PromptFactory getPrompt() {
		return myPrompt;
	}

	/**
	 * @return the myStage
	 */
	public Stage getStage() {
		return myStage;
	}

	/**
	 * @return the myScene
	 */
	public Scene getScene() {
		return myScene;
	}
}
