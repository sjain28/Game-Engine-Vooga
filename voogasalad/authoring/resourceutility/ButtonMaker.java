package authoring.resourceutility;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ButtonMaker {
	
	public ButtonMaker() {
		
	}
	
	public Button makeButton(String buttonName, EventHandler<ActionEvent> e) {
		Button button = new Button(buttonName);
		button.setOnAction(e);
		
		return button;
	}

}
