/**
 * 
 */
package player.gamedisplay;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import player.gamerunner.IGameRunner;

/**
 * StandardHUD class that creates a standard HUD component to be included 
 * in the StandardDisplay pane
 * 
 * @author Hunter Lee
 *
 */
public class StandardHUD implements IHUD {

	private IGameRunner myGameRunner;
	private VBox myHUD;
	private static final int SPACING = 10;
	private static final String SCORE_LABEL = "Current Score: ";

	/**
	 * Default constructor
	 * 
	 */
	public StandardHUD() {
		myHUD = new VBox();
	}

	/**
	 * Overloaded constructor with a reference to GameRunner
	 * for receiving global data and variable
	 * 
	 */
	public StandardHUD(IGameRunner gamerunner) {
		this();
		this.myGameRunner = gamerunner;
	}
	/**
	 * Creates a VBox that represents the HUD
	 * VBox is chosen because it is expected that this HUD component will
	 * be placed on the right side of the screen (right side of BorderPane)
	 * 
	 * @return myHUD
	 */
	@Override
	public VBox createHUD() {
		myHUD.setPadding(new Insets(SPACING)); //TODO: Magic number
		myHUD.setSpacing(SPACING); //TODO: Magic number

		Label score = new Label(SCORE_LABEL);
		//	    score.textProperty().bind(valueProperty);
		//	    Label timer = new Timer();
		Timer cttimer = new Timer();

		myHUD.getChildren().addAll(score, cttimer.getTimeLabel());
		return myHUD;
	}

	/**
	 * @return the myGameRunner
	 */
	public IGameRunner getGameRunner() {
		return myGameRunner;
	}

}