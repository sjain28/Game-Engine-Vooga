package player.gamedisplay;

import java.util.List;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
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
	
//	private IGameRunner myGameRunner;
	private Stage myStage;
	private Scene myScene;
	private BorderPane myPane;
	private Pane myGameScreen;
	private PromptFactory myPrompt;
	private List<Node> myListToDisplay;
	
	/**
	 * Default constructor
	 * 
	 */
	public StandardDisplay() {
		
		myStage = new Stage();
		myPane = new BorderPane();
		myGameScreen = new Pane();
		myScene = new Scene(myPane, PANE_SIZE, PANE_SIZE);
		myPrompt = new PromptFactory();
		
	}
	
//	public StandardDisplay(IGameRunner gameRunner) {
//		
//		this();
//		this.myGameRunner = gameRunner;
//		
//	}

	/**
	 * Reads in the list of Nodes to display
	 * 
	 */
	public void read(List<Node> listToDisplay) {
		myListToDisplay = listToDisplay;
	}
	
	/**
	 * Public method defined in the interface that displays
	 * game display
	 * 
	 */
	@Override
	public void display() {
		//Creates the main pane
		createPane();
		//Creates the game screen
		populateGameScreen();
		//Shows the scene
		getStage().show();
	}

	/**
	 * Creates an interactive prompt and shows it to the user
	 * 
	 */
	@Override
	public void createPrompt(String message) {
		getPrompt().prompt(message);
	}
	
	/**
	 * Creates the game display
	 * 
	 */
	private void createPane() {
		//Adds all components into the main border pane
		getPane().setCenter(myGameScreen);
		getPane().setBottom(myControl.createControl());
		//Below is optional (adds HUD)
		getPane().setRight(myHUD.createHUD());
		getStage().setScene(getScene());
	}
	
	/**
	 * Populates the game screen that goes into the center
	 * of the game display (BorderPane)
	 * 
	 */
	private void populateGameScreen() {
		getListToDisplay().forEach(n -> getGameScreen().getChildren().add(n));
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

	/**
	 * @return the myListToDisplay
	 */
	public List<Node> getListToDisplay() {
		return myListToDisplay;
	}

	/**
	 * @param myListToDisplay the myListToDisplay to set
	 */
	public void setListToDisplay(List<Node> myListToDisplay) {
		this.myListToDisplay = myListToDisplay;
	}

	/**
	 * @return the myGameScreen
	 */
	public Pane getGameScreen() {
		return myGameScreen;
	}

}
