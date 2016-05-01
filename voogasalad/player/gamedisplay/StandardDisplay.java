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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import player.gamerunner.IGameRunner;
import resources.VoogaBundles;
import stats.database.VoogaDataBase;
import tools.OrderedProperties;
import tools.Pair;
import tools.VoogaJukebox;
/**
 * Standard Display that creates a display with basic user-interaction controls
 * Uses composition to contain elements of the display
 * 
 * @author Hunter Lee
 */
public class StandardDisplay implements IGameDisplay {
	private IPromptFactory myPrompt;
	private IControl myControl;
	private IGameRunner myGameRunner;
	private Stage myStage;
	private Scene myScene;
	private BorderPane myPane;
	private Pane myGameScreen;
	private Pane myUIScreen;
	private StackPane myScreensHolder;
	private List<KeyEvent> myKeyEvents;
	private List<KeyEvent> myKeyPresses;
	private List<KeyEvent> myKeyReleases;
	private Pair<Double, Double> myDimensions;

	/**
	 * Overloaded constructor to set the reference to GameRunner
	 */
	public StandardDisplay(IGameRunner gamerunner) {
		myGameRunner = gamerunner;
		myControl = new StandardControl(myGameRunner);
		myStage = new Stage();
		myPane = new BorderPane();
		myGameScreen = new Pane();
		myUIScreen = new Pane();
		myScreensHolder = new StackPane();
		myPrompt = new PromptFactory();
		myKeyEvents = new ArrayList<>();
		myKeyPresses = new ArrayList<>();
		myKeyReleases = new ArrayList<>();	
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
	 * Reads in the list of Nodes to display and populates the screen
	 */
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
		myScene.addEventHandler(KeyEvent.ANY, keyListener);
		VoogaJukebox.getInstance().playBGM();
		myStage.setOnCloseRequest(e -> {
			saveDatabase();
			myGameRunner.getTimeline().stop();
			myGameRunner.finishPlaySession();
			VoogaJukebox.getInstance().stopBGM();
		});
	}

	/**
	 * Creates the game display, adding all components
	 */
	private void createPane(OrderedProperties resource) {
		myScreensHolder.getChildren().addAll(myGameScreen, myUIScreen);
		myPane.setCenter(myScreensHolder);
		myPane.setTop(new MenuPanel(myGameRunner, e -> new MenuPanelHandlingMirror(e, myGameRunner), resource));
		myPane.setBottom(myControl.createControl());
		myStage.setScene(myScene);
	}

	private void saveDatabase() {
		VoogaDataBase.getInstance().save();
	}

	@Override
	public void setSceneDimensions(double width, double height) {
		myDimensions = new Pair<>(width, height);
		myScene = new VoogaScene(myPane, width, height);
	}

	@Override
	public Pair<Double, Double> getDimensions() {
		return this.myDimensions;
	}

	@Override
	public void createPrompt(String message) {
		myPrompt.prompt(message);
	}

	@Override
	public List<KeyEvent> getKeyEvents() {
		return myKeyEvents;
	}

	@Override
	public void clearKeyEvents() {
		myKeyEvents.clear();
	}

	public IControl getControl() {
		return myControl;
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

	public Scene getMyScene() {
		return myScene;
	}
	@Override
	public Stage getStage() {
		return myStage;
	}
	@Override
	public Pane getScreen() {
		return this.myGameScreen;
	}
	@Override
	public Pane getUI() {
		return this.myUIScreen;
	}
}