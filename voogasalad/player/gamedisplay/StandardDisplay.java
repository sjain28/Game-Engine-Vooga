package player.gamedisplay;

import java.util.ArrayList;
import java.util.List;
import authoring.VoogaScene;
import authoring.gui.menubar.MenuPanel;
import authoring.gui.menubar.MenuPanelHandlingMirror;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import player.gamerunner.IGameRunner;
import resources.VoogaBundles;
import stats.database.VoogaDataBase;
import tools.IVoogaGameSound;
import tools.OrderedProperties;
import tools.VoogaGameSound;

/**
 * Standard Display that creates a display with basic user-interaction controls
 * Uses composition to contain elements of the display
 * 
 * @author Hunter Lee
 */
public class StandardDisplay implements IGameDisplay {
	private IPromptFactory myPrompt;
	private IControl myControl;
	private IHUD myHUD;
	private IVoogaGameSound myGameSound;
	private IGameRunner myGameRunner;
	private Stage myStage;
	private Scene myScene;
	private BorderPane myPane;
	private Pane myGameScreen;
	private List<Node> myListToDisplay;
	private List<KeyEvent> myKeyEvents;
	private List<KeyEvent> myKeyPresses;
	private List<KeyEvent> myKeyReleases;
	
	/**
	 * Overloaded constructor to set the reference to GameRunner
	 */
	public StandardDisplay(IGameRunner gamerunner) {
		myGameRunner = gamerunner;
		initialize();
	}
	
	/**
	 * Method that contains a series of initialize statements
	 */
	private void initialize() {
		myControl = new StandardControl(myGameRunner);
		myHUD = new StandardHUD(myGameRunner);
		myGameSound = new VoogaGameSound();
		myStage = new Stage();
		myPane = new BorderPane();
		myGameScreen = new Pane();	
//		myGameScreen = new StackPane();	
		// Made the scene from log in scene creator;
		// myScene = new VoogaScene(myPane, PANE_SIZE, PANE_SIZE);
//		myLogInScreen = new LogInSceneCreator(); 
//		myScene = myLogInScreen.createLogInScene(myPane, PANE_SIZE,PANE_SIZE);
		myPrompt = new PromptFactory();
		myKeyEvents = new ArrayList<>();
		myKeyPresses = new ArrayList<>();
		myKeyReleases = new ArrayList<>();
	}
	
	@Override
	public void setSceneDimensions(double width, double height) {
		myScene = new VoogaScene(myPane, width, height);
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
	
	@Override
	public Pane getScreen() {
		return this.myGameScreen;
	}

	/**
	 * Reads in the list of Nodes to display and populates the screen
	 */
	public void readAndPopulate(List<Node> listToDisplay) {
		myListToDisplay = listToDisplay;
		myGameScreen.getChildren().clear();
		myListToDisplay.forEach(n -> myGameScreen.getChildren().add(n));
	}

	/**
	 * Public method defined in the interface that displays game display
	 */
	@Override
	public void display() {
		createPane(VoogaBundles.playerMenubarProperties);
		addEffects();
	}
	
	/**
	 * Create a display to be used for testing (single level)
	 */
	@Override
	public void displayTestMode() {
		createPane(VoogaBundles.playerTesterMenubarProperties);
		addEffects();
	}
	
	/**
	 * Add secondary effects to the stage, called by display and displayTestMode
	 */
	private void addEffects() {
		myStage.show();
        myStage.setOnCloseRequest(e -> {
            promptForSave();
        });
		myScene.addEventHandler(KeyEvent.ANY, keyListener);
		myGameSound.playBGM();
		myStage.setOnCloseRequest(e -> {
			myGameRunner.getTimeline().stop();
			myGameRunner.finishPlaySession();
			myGameSound.stopBGM();
		});
	}
	
    private void promptForSave () {
    	VoogaDataBase.getInstance().printDataBase();
        VoogaDataBase.getInstance().save();
    }
	
	/**
	 * Creates the game display, adding all components
	 */
	private void createPane(OrderedProperties resource) {
		myPane.setCenter(myGameScreen);
		myPane.setTop(new MenuPanel(myGameRunner, e -> new MenuPanelHandlingMirror(e, myGameRunner), resource));
		myPane.setBottom(myControl.createControl());
		myPane.setRight(myHUD.createHUD());
		myStage.setScene(myScene);
	}
	
	/**
	 * Creates an interactive prompt and shows it to the user
	 */
	@Override
	public void createPrompt(String message) {
		myPrompt.prompt(message);
	}

	/**
	 * @param myListToDisplay the myListToDisplay to set
	 */
	public void setListToDisplay(List<Node> myListToDisplay) {
		this.myListToDisplay = myListToDisplay;
	}

	@Override
	public List<KeyEvent> getKeyEvents() {
		return myKeyEvents;
	}

	/**
	 * Clears the list of KeyEvents, called at each time step
	 */
	@Override
	public void clearKeyEvents() {
		myKeyEvents.clear();
	}
	/**
	 * @return the myControl
	 */
	public IControl getControl() {
		return myControl;
	}
	public IHUD getHUD() {
		return myHUD;
	}
	/**
	 * Stops the background music and close the stages
	 */
	@Override
	public void exit() {
		myGameSound.stopBGM();
		myStage.close();
	}
	
	public List<KeyEvent> getMyKeyPresses() {
		return myKeyPresses;
	}

	public List<KeyEvent> getMyKeyReleases() {
		return myKeyReleases;
	}
	
	public Scene getMyScene() {
		return myScene;
	}

	@Override
	public Stage getStage() {
		return myStage;
	}
}