package player.gamedisplay;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import authoring.VoogaScene;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import player.gamerunner.IGameRunner;

/**
 * Standard Display that creates a display with basic user-interaction controls
 * Uses composition to contain elements of the display
 * 
 * @author Hunter Lee
 *
 */
public class StandardDisplay implements IGameDisplay {

	private static final int PANE_SIZE = 600;
	//	private static final String BGM_PATH = "resources/sound/zelda_theme.mp3";
	private static final String BGM_PATH = "resources/sound/hypnotize.mp3";

	private IPromptFactory myPromptFactory;
	private IControl myControl;
	private IHUD myHUD;
	private IGameRunner myGameRunner;
	private Stage myStage;
	private Scene myScene;
	private BorderPane myPane;
	private Pane myGameScreen;
	private PromptFactory myPrompt;
	private List<Node> myListToDisplay;
	//	private EventHandler<KeyEvent> myKeyListener;
	private List<KeyEvent> myKeyEvents;

	// BGM
	private Media myBGM;
	private MediaPlayer myMediaPlayer;

	/**
	 * Default constructor
	 * 
	 */
	public StandardDisplay() {
		initialize();
	}
	
	/**
	 * Overloaded constructor to set the reference to GameRunner
	 * 
	 */
	public StandardDisplay(IGameRunner gamerunner) {
		myGameRunner = gamerunner;
		initialize();
	}

	/**
	 * Method that contains a series of initialize statements
	 * 
	 */
	private void initialize() {
		myPromptFactory = new PromptFactory();
		myControl = new StandardControl(getGameRunner());
		myHUD = new StandardHUD(getGameRunner());
		myStage = new Stage();
		myPane = new BorderPane();
		myGameScreen = new Pane();
		myScene = new VoogaScene(myPane, PANE_SIZE, PANE_SIZE);
		myPrompt = new PromptFactory();
		myKeyEvents = new ArrayList<>();
		myBGM = new Media(new File(BGM_PATH).toURI().toString());
		myMediaPlayer = new MediaPlayer(myBGM);
	}
	
	/**
	 * Creates a keyListener for listening in on key inputs
	 * Adds each event to the list
	 * 
	 */
	private EventHandler<KeyEvent> keyListener = new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent event) {
			myKeyEvents.add(event);
		}
	};

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
		//		populateGameScreen();

		//Shows the scene
		getStage().show();
		//Adds keyinput listener
		getScene().addEventHandler(KeyEvent.ANY, keyListener);
		//Plays BGM music
		playMusic();
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
	 * Plays the given BGM using MediaPlayer and appends a close-stage action
	 * to make the music stop when the stage is exited
	 * 
	 */
	private void playMusic() {
		getMediaPlayer().play();
		getStage().setOnCloseRequest(e -> getMediaPlayer().stop());
	}

	/**
	 * Populates the game screen that goes into the center
	 * of the game display (BorderPane)
	 * 
	 */
	@Override
	public void populateGameScreen() {
		getGameScreen().getChildren().clear();
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

	/**
	 * @return the myKeyEvents
	 */
	@Override
	public List<KeyEvent> getKeyEvents() {
		return myKeyEvents;
	}

	/**
	 * Clears KeyEvents
	 * 
	 */
	@Override
	public void clearKeyEvents() {
		myKeyEvents.clear();
	}
	/**
	 * @return the myBGM
	 */
	public Media getBGM() {
		return myBGM;
	}

	/**
	 * @param myBGM the myBGM to set
	 */
	public void setBGM(Media myBGM) {
		this.myBGM = myBGM;
	}

	/**
	 * @return the myMediaPlayer
	 */
	public MediaPlayer getMediaPlayer() {
		return myMediaPlayer;
	}

	/**
	 * @return the myGameRunner
	 */
	public IGameRunner getGameRunner() {
		return myGameRunner;
	}

	/**
	 * @return the myPromptFactory
	 */
	public IPromptFactory getPromptFactory() {
		return myPromptFactory;
	}

	/**
	 * @return the myControl
	 */
	public IControl getControl() {
		return myControl;
	}

	/**
	 * @return the myHUD
	 */
	public IHUD getHUD() {
		return myHUD;
	}


}
