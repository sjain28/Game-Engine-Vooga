// This entire file is part of my masterpiece.
// Joshua Xu

/* I created this class in order to create an interface between KeyHandler and the Standard Display. Doing so in this manner
 * 
 * Much of the functionality in this class was originally in the Standard Display. Through my refactoring efforts, I believe
 * I have vastly improved the design of the standard display. There were many flaws originally with this class, including
 * the following.
 * 
 * 1) This class was a huge ball of mud, meaning that it was a "GOD" class. It contained several different functionalities,
 * such as carrying out key presses, while simultaneously being responsible for displaying nodes in the game display.
 * 
 * 2) This class was not easily extensible, meaning the parts that could not be easily abstracted (such as what we wanted the 
 * physical layout of the display to look like). In order to create a more flexible design, took many of the components
 * I believed would stay constant in the Game Display and placed them in this class. These components include the read and populate
 * method, as well as the addEffects() method. Then, I made the Standard Display extend this class.
 * 
 * Here are a few design patterns I would like to point out about this code. 
 * 
 * 1) The template method. This class is a superclass for all the different game displays. I determined that the methods
 * that remained constant among all displays would be in this class. Meanwhile, the things that varied about the display
 * would be abstracted in their own individual classes. Thus, this class is an abstract class, containing a single abstract method
 * called setPositionalNodes(). This setPositionalNodes() determines what the different parts of the BorderPane
 * contained in this class would contained. If one wanted to simply flip the screen such that the standard control
 * was at the top and the menu was at the bottom, it would be extremely easily to go into the Standard Display class, edit the
 * setPositionalNodes() method, and that change would be observed.
 * 
 * 2) The composition design pattern. The idea behind Standard Display is that it contains many functions, including logging
 * key presses and key releases, and because this class is a controller class, it is also in charge of the control
 * (such as the stop and start buttons, etc). What I have done here is to make all the classes apart from this class into
 * smart classes, each performing their own function. Thus, if one wanted to create a class that only logged key presses or key releases,
 * that could be easily done by editing the type of KeyHandler used.
 * 
 * In addition, I refactored out the class IKeyHandler to serve as an interface between KeyHandler and the Standard Display. Doing so in this manner
 * allows the functionality of handling key presses to be separate from the functionality of displaying all the nodes on
 * the screen. In a way, this could be viewed as the Model-View-Controller design pattern, in which back end components (the model)
 * is kept separate from the front end components (the nodes being displayed). 
 * 
 * The purpose of this interface is to not give the Game Display complete access to the key handler. In this manner,
 * the Game Display can only interact with the key handler through limited means, ala the methods outlined in this interface.
 * 
 * In conclusion, this class fulfills SOLID design by fulfilling a single responsibility (being the master class for the
 * display), as well as the open/close principle (by defining preset addEffects() and readAndPopulate() methods). 
 * Finally, the subclass of this class, Standard Display, satisfies the Liskov Substitution principle, in which a standard display
 * could easily be substituted for another one. 
 * 
 */
package player.gamedisplay;
import java.util.List;
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

 * @author Hunter Lee, Joshua Xu
 */
public abstract class GameDisplay implements IGameDisplay {
	private IPromptFactory myPrompt;
	private IGameRunner myGameRunner;
	private Stage myStage;
	private Scene myScene;
	private BorderPane myPane;
	private Pane myGameScreen;
	private Pane myUIScreen;
	private StackPane myScreensHolder;
	private IKeyHandler myKeyHandler;
	private Pair<Double, Double> myDimensions;
	/**
	 * Constructor to set the reference to GameRunner (required for key presses), as well as to set the dimensions
	 * of the display.
	 *  
	 * @param height 
	 * @param width 
	 */
	public GameDisplay(IGameRunner gamerunner, double width, double height) {
		myGameRunner = gamerunner;
		myStage = new Stage();
		myPane = new BorderPane();
		myGameScreen = new Pane();
		myUIScreen = new Pane();
		myScreensHolder = new StackPane();
		myPrompt = new PromptFactory();
		myKeyHandler = new KeyHandler();
		myDimensions = new Pair<>(width, height);
		myScene = new VoogaScene(myPane, width, height);
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
	 * Helper method, creates the game display, adding all components, except the borders which are explicitly defined
	 * in the subclass.
	 */
	private void createPane(){
		myScreensHolder.getChildren().addAll(myGameScreen, myUIScreen);
		setBorders();
		myPane.setCenter(myScreensHolder);	
		myStage.setScene(myScene);
	}
	
	/**
	 *  SetBorders determined by the game display subclass to see what exactly goes on in the panes that are not the game screen.  
	 */
	protected abstract void setBorders();
	
	/**
	 * Add secondary effects to the stage, called by display and displayTestMode. These effects include making the 
	 * scene receptive to event handlers, playing music through the jukebox, as well as specifying that the database will save when the stage is closed.
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
	 *  Every time the stage is closed, the information in the database is automatically saved.  
	 */
	private void saveDatabase() {
		VoogaDataBase.getInstance().save();
	}
	public void exit() {
		VoogaJukebox.getInstance().stopBGM();
		myStage.close();
	}
	public void createPrompt(String message) {
		myPrompt.prompt(message);
	}
	
	/**
	 *  Since the scene has a keylistener, these getter methods below are all related to the keyevents that occur due to key
	 *  presses and key releases.
	 */
	public List<KeyEvent> getKeyEvents() {
		return myKeyHandler.getKeyEvents();
	}
	public List<KeyEvent> getMyKeyPresses() {
		return myKeyHandler.getMyKeyPresses();
	}
	public List<KeyEvent> getMyKeyReleases() {
		return myKeyHandler.getMyKeyReleases();
	}
	public void clearKeyEvents() {
		myKeyHandler.clearKeyEvents();
	}
	
	/**
	 *  These methods below are related to getting the JavaFX elements that are in the display.
	 */
	public Pair<Double, Double> getDimensions() {
		return this.myDimensions;
	}
	
	public Stage getStage() {
		return myStage;
	}
	public Scene getScene() {
		return myScene;
	}
	public Pane getScreen() {
		return myGameScreen;
	}
	/**
	 *  Only the subclass should have access to these methods. 
	 */
	protected BorderPane getBorderPane() {
		return this.myPane;
	}
	protected IGameRunner getGameRunner(){
		return myGameRunner;
	}
}