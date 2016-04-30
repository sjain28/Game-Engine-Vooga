package player.gamedisplay;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import authoring.VoogaScene;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import player.gamerunner.IGameRunner;

/**
 * Standard Display that creates a display with basic user-interaction controls
 * Uses composition to contain elements of the display
 * 
 * @author Hunter Lee
 *
 */
public class GameboyDisplay implements IGameDisplay {

	private static final int PANE_WIDTH = 1200;
	private static final int PANE_HEIGHT = 800;
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
		myControl = new StandardControl(getGameRunner());
		myHUD = new StandardHUD(getGameRunner());
		myStage = new Stage();
	//	myPane = new BorderPane();
		myPane = new AnchorPane();

		myPane.setId("bp");
		
//		image = new Image ("http://wall.rimbuz.com/wp-content/uploads/Black-Texture-Wallpaper-High-Definition.jpg");
//        myPane.setBackground(new Background(new BackgroundImage(image,BackgroundRepeat.REPEAT,
//                BackgroundRepeat.REPEAT,
//                BackgroundPosition.DEFAULT,
//                BackgroundSize.DEFAULT)));
		
		myGameScreen = new Pane();
		//myGameScreen.setPrefSize(280, 370);
//		myGameScreen.setLayoutX(400);
//		myGameScreen.setLayoutY(200);
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
			if(event.getEventType().equals(KeyEvent.KEY_PRESSED)){
				myKeyPresses.add(event);
			}else{
				myKeyReleases.add(event);
			}
		}
	};

	/**
	 * Reads in the list of Nodes to display
	 * 
	 */
	@Override
	public void readAndPopulate(List<Node> listToDisplay) {
		myListToDisplay = listToDisplay;
		getGameScreen().getChildren().clear();
		getListToDisplay().forEach(n -> {
			getGameScreen().getChildren().add(n);
//			getGameScreen().setClip(n);
//			n.setLayoutX(n.getLayoutX() + 400);
//			n.setLayoutY(n.getLayoutY() + 200);
		});
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
		VBox HUD = myHUD.createHUD();
		Parent control = myControl.createControl();

		getPane().getChildren().addAll(myGameScreen, HUD, control);
		AnchorPane.setTopAnchor(myGameScreen, 190.0);
		AnchorPane.setLeftAnchor(myGameScreen, 380.0);
		AnchorPane.setTopAnchor(HUD, 0.0);
		AnchorPane.setRightAnchor(HUD, 0.0);
		AnchorPane.setBottomAnchor(control, 0.0);
		AnchorPane.setRightAnchor(control, 350.0);
		//fitWidthProperty().bind(center.widthProperty());
	    Rectangle clip = new Rectangle(435, 345);
	    clip.setLayoutX(0);
	    clip.setLayoutY(0);
//	    clip.autosize();
	    myGameScreen.setClip(clip);
	    //clip.widthProperty().bind(clip.widthProperty()*myGameScreen.widthProperty()/PANE_WIDTH);
	    //clip.heightProperty().bind(myGameScreen.heightProperty());
		
//		anchor.setTopAnchor(r2, 0.0);
//		anchor.setRightAnchor(r2, 0.0);
		
//		
//		//Adds all components into the main border pane
//		getPane().setCenter(myGameScreen);
//		getPane().setBottom(myControl.createControl());
//		//Below is optional (adds HUD)
//		getPane().setRight(myHUD.createHUD());
//		
//		
//		Pane l = new Pane(); l.setId("left"); getPane().setLeft(l);
//		Pane t = new Pane(); t.setId("top"); getPane().setTop(t);
//
//		
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

//	/**
//	 * Populates the game screen that goes into the center
//	 * of the game display (BorderPane)
//	 * 
//	 */
//	@Override
//	public void populateGameScreen() {
//		getGameScreen().getChildren().clear();
//		getListToDisplay().forEach(n -> {
//			getGameScreen().getChildren().add(n);
////			getGameScreen().setClip(n);
////			n.setLayoutX(n.getLayoutX() + 400);
////			n.setLayoutY(n.getLayoutY() + 200);
//		});
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

	public void exit() {
		// TODO Auto-generated method stub
		myMediaPlayer.stop();
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pane getScreen() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Scene getMyScene() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSceneDimensions(double width, double height) {
		myScene = new VoogaScene(myPane, width, height, CSS_PATH);
	}

	@Override
	public Pane getUI() {
		// TODO Auto-generated method stub
		return null;
	}


}
