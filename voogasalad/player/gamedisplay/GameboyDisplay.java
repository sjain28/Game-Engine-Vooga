package player.gamedisplay;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import authoring.VoogaScene;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import player.gamerunner.IGameRunner;
import tools.Pair;
import tools.VoogaJukebox;

/**
 * Standard Display that creates a display with basic user-interaction controls
 * Uses composition to contain elements of the display
 * 
 * @author Hunter Lee
 *
 */
@Deprecated
public class GameboyDisplay implements IGameDisplay {

	private static final String BGM_PATH = "resources/sound/hypnotize.mp3";
	private static final String CSS_PATH = "/player/gamedisplay/style.css";

	private IPromptFactory myPromptFactory;
	private IControl myControl;
	private IHUD myHUD;
	private IGameRunner myGameRunner;
	private Stage myStage;
	private Scene myScene;
	private AnchorPane myPane;
	private Pane myGameScreen;
	private PromptFactory myPrompt;
	private List<Node> myListToDisplay;
	private List<KeyEvent> myKeyEvents;
	private List<KeyEvent> myKeyPresses;
	private List<KeyEvent> myKeyReleases;
	private Pair<Double, Double> myDimensions;
	private StackPane myScreensHolder;
	private Pane myUIScreen;

	// BGM
	private Media myBGM;
	private MediaPlayer myMediaPlayer;

	/**
	 * Default constructor
	 * 
	 */
	public GameboyDisplay() {
		initialize();
	}
	
	/**
	 * Overloaded constructor to set the reference to GameRunner
	 * 
	 */
	public GameboyDisplay(IGameRunner gamerunner) {
		myGameRunner = gamerunner;
		initialize();
	}

	/**
	 * Method that contains a series of initialize statements
	 * 
	 */
	private void initialize() {
		myPromptFactory = new PromptFactory();
//		myControl = new StandardControl(getGameRunner());
		//myHUD = new StandardHUD(getGameRunner());
		myStage = new Stage();
		myPane = new AnchorPane();
		myPane.setId("bp");
		myGameScreen = new Pane();
		myPrompt = new PromptFactory();
		myKeyEvents = new ArrayList<>();
		myBGM = new Media(new File(BGM_PATH).toURI().toString());
		myMediaPlayer = new MediaPlayer(myBGM);
		myScreensHolder = new StackPane();
		myUIScreen = new Pane();

	}
	
	/**
	 * Creates a keyListener for listening in on key inputs and adds each event to the list
	 */
	private EventHandler<KeyEvent> keyListener = new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent event) {
			myKeyEvents.add(event);
			if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
				myKeyPresses.add(event);
			} else {
				myKeyReleases.add(event);
			}
		}
	};

	/**
	 * Public method defined in the interface that displays
	 * game display
	 * 
	 */
	@Override
	public void display() {
		createPane();
		getStage().show();
		getScene().addEventHandler(KeyEvent.ANY, keyListener);
//		playMusic();
	}
	
	/**
	 * Reads in the list of Nodes to display and populates the screen
	 */
	@Override
	public void readAndPopulate(List<Pair<Node, Boolean>> listToDisplay) {
		myGameScreen.getChildren().clear();
		myUIScreen.getChildren().clear();
		for(Pair<Node, Boolean> p : listToDisplay) {
			if (p.getLast()) {
				myUIScreen.getChildren().add(p.getFirst());
			} else {
				myGameScreen.getChildren().add(p.getFirst());
			}
		}
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
//		//VBox HUD = myHUD.createHUD();
//		Parent control = myControl.createControl();
//		getPane().getChildren().addAll(myGameScreen, control);
//		AnchorPane.setTopAnchor(myGameScreen, 190.0);
//		AnchorPane.setLeftAnchor(myGameScreen, 380.0);
//		//AnchorPane.setTopAnchor(HUD, 0.0);
//		//AnchorPane.setRightAnchor(HUD, 0.0);
//		AnchorPane.setBottomAnchor(control, 0.0);
//		AnchorPane.setRightAnchor(control, 350.0);
//	    Rectangle clip = new Rectangle(435, 345);
//	    clip.setLayoutX(0);
//	    clip.setLayoutY(0);
//	    myGameScreen.setClip(clip);
//		getStage().setScene(getScene());
	}

	/**
	 * Plays the given BGM using MediaPlayer and appends a close-stage action
	 * to make the music stop when the stage is exited
	 * 
	 */
//	private void playMusic() {
//		getMediaPlayer().play();
//		getStage().setOnCloseRequest(e -> getMediaPlayer().stop());
//	}

	/**
	 * @return the pane
	 */
	public AnchorPane getPane() {
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

//	/**
//	 * @return the myMediaPlayer
//	 */
//	public MediaPlayer getMediaPlayer() {
//		return myMediaPlayer;
//	}
//
//	/**
//	 * @return the myGameRunner
//	 */
//	public IGameRunner getGameRunner() {
//		return myGameRunner;
//	}
//
//	/**
//	 * @return the myPromptFactory
//	 */
//	public IPromptFactory getPromptFactory() {
//		return myPromptFactory;
//	}
//
//	/**
//	 * @return the myControl
//	 */
//	public IControl getControl() {
//		return myControl;
//	}

	/**
	 * @return the myHUD
	 */
	public IHUD getHUD() {
		return myHUD;
	}

	@Override
	public void exit() {
		VoogaJukebox.getInstance().stopBGM();
		myStage.close();
	}
	
	public List<KeyEvent> getMyKeyPresses() {
		return myKeyPresses;
	}

	public List<KeyEvent> getMyKeyReleases() {
		return myKeyReleases;
	}


	@Override
	public void displayTestMode() {		
	}

	@Override
	public Pane getScreen() {
		return null;
	}
	
	@Override
	public Scene getMyScene() {
		return null;
	}

	@Override
	public void setSceneDimensions(double width, double height) {
		myDimensions = new Pair<>(width, height);
		myScene = new VoogaScene(myPane, width, height);
	}

	@Override
	public Pane getUI() {
		return this.myUIScreen;
	}


	@Override
	public Pair<Double, Double> getDimensions() {
		return this.myDimensions;
	}


}
