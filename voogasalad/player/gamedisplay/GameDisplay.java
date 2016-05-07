package player.gamedisplay;
import java.util.List;
import java.util.Map;
import authoring.VoogaScene;
import javafx.scene.input.KeyEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import player.gamerunner.IGameRunner;
import stats.database.VoogaDataBase;
import tools.Pair;
import tools.VoogaJukebox;
/**
 * Standard Display that creates a display with basic user-interaction controls
 * Uses composition to contain elements of the display
 * 
 * @author Hunter Lee
 */
public abstract class GameDisplay implements IGameDisplay {
	private IPromptFactory myPrompt;
	private IControl myControl;
	private IGameRunner myGameRunner;
	private Stage myStage;
	private Scene myScene;
	private BorderPane myPane;
	private Pane myGameScreen;
	private Pane myUIScreen;
	private StackPane myScreensHolder;
	private IKeyHandlerDisplay myKeyHandler;
	private Pair<Double, Double> myDimensions;

	/**
	 * Overloaded constructor to set the reference to GameRunner
	 */
	public GameDisplay(IGameRunner gamerunner) {
		myGameRunner = gamerunner;
		myControl = new StandardControl(myGameRunner);
		myStage = new Stage();
		myPane = new BorderPane();
		myGameScreen = new Pane();
		myUIScreen = new Pane();
		myScreensHolder = new StackPane();
		myPrompt = new PromptFactory();
		myKeyHandler = new KeyHandlerDisplay();
	}

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
		createPane();
		addEffects();
	}

	/**
	 * Add secondary effects to the stage, called by display and displayTestMode
	 */
	private void addEffects() {
		myStage.show();
		myScene.addEventHandler(KeyEvent.ANY, myKeyHandler.createKeyListener());
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
	private void createPane(){
		myScreensHolder.getChildren().addAll(myGameScreen, myUIScreen);
		myPane.setCenter(myScreensHolder);
		myPane.setTop(setPositionalNode().get(NodeLocation.TOP));
		myPane.setBottom(setPositionalNode().get(NodeLocation.BOTTOM));
		myStage.setScene(myScene);
	}
	
	protected abstract Map<NodeLocation,Node> setPositionalNode();

	
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
		return myKeyHandler.getKeyEvents();
	}

	@Override
	public void clearKeyEvents() {
		myKeyHandler.clearKeyEvents();
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
		return myKeyHandler.getMyKeyPresses();
	}

	public List<KeyEvent> getMyKeyReleases() {
		return myKeyHandler.getMyKeyReleases();
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