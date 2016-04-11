/**
 * 
 */
package player.gamedisplay;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * StandardHUD class that creates a standard HUD component to be included 
 * in the StandardDisplay pane
 * 
 * @author Hunter Lee
 *
 */
public class StandardHUD implements IHUD {
	
	private VBox myHUD;
	
	/**
	 * Default constructor
	 * 
	 */
	public StandardHUD() {
		
		myHUD = new VBox();
		
	}
	
	/**
	 * Creates a VBox that represents the HUD
	 * 
	 * @return myHUD
	 */
	/* (non-Javadoc)
	 * @see player.gamedisplay.IHUD#createHUD()
	 */
	@Override
	public VBox createHUD() {
		// TODO Auto-generated method stub
	    myHUD.setPadding(new Insets(15, 12, 15, 12)); //TODO: Magic number
	    myHUD.setSpacing(10); //TODO: Magic number
	    
	    Label score = new Label("Current Score: ");
//	    score.textProperty().bind(valueProperty);
	    Label timer = new Timer();
	    CountdownTimer cttimer = new CountdownTimer();
	    
	    myHUD.getChildren().addAll(score, cttimer.getTimeString());
		return myHUD;
	}

}