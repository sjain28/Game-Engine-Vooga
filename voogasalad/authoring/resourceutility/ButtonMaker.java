package authoring.resourceutility;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * An auxiliary button maker class used to make buttons easily
 * @author DoovalSalad
 *
 */
public class ButtonMaker {

	/**
	 * Makes a button
	 * @param buttonName: the text to be displayed in the button
	 * @param e: the event handler to control the outcome of a button click
	 * @return the button
	 */
	public Button makeButton(String buttonName, EventHandler<ActionEvent> e) {
		Button button = new Button(buttonName);
		button.setOnAction(e);
		
		return button;
	}

}
