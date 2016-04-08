package player.gamedisplay;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * Class that generates a set of controls that becomes a component of StandardDisplay
 * Speed control, volume muting, etc.
 * 
 * @author Hunter Lee
 *
 */
public class StandardControl implements IControl {
	
	
	HBox myControl;
	
	/**
	 * Default constructor
	 * 
	 */
	public StandardControl() {
		
		myControl = new HBox();
		
	}
	
	/**
	 * Creates the control panel using HBox
	 * 
	 * @return myControl
	 */
	public HBox createControl() {
	    // myControlox
	    myControl.setPadding(new Insets(15, 12, 15, 12)); //TODO: Magic number
	    myControl.setSpacing(10); //TODO: Magic number

	    // Buttons
	    Button speedUp = createButton("Speed Up"); //TODO: ResourceBundle
	    Button speedDown = createButton("Speed Down");
	    
	    // TODO: Assign click actions
//	    speedUp.setOnMouseClicked(value);
//	    speedDown.setOnMouseClicked(value);
	    
	    myControl.getChildren().addAll(speedUp, speedDown);

	    
//	    Button btn3 = new Button();
//	    btn3.setText("Button3");
//	    myControl.getChildren().add(btn3);
//
//	    Button btn4 = new Button();
//	    btn4.setText("Button4");
//	    myControl.getChildren().add(btn4);
	    
		return myControl;
	}
	
	/**
	 * Private method used to create a Button
	 * 
	 * @param name
	 * @return
	 */
	private Button createButton(String name) {
		Button button = new Button();
		button.setText(name);
		return button;
	}
	
}
