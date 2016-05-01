package authoring.gui.items;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;

/**
 * Custom Switchbutton class that allows for toggle of properties to be "on" or "off".
 * Mostly used for VoogaBooleans.
 * 
 * @author Harry Guo, Aditya Srinivasan, Arjun Desai, Nick Lockett
 */
 

public class SwitchButton extends Label{
	
	private static final int WIDTH = 40;
	
    private SimpleBooleanProperty switchedOn;

    /**
     * Instantiates the display of the SwitchButton.
     * @param on
     */
    public SwitchButton (boolean on) {
        switchedOn = new SimpleBooleanProperty(on);
        Button switchBtn = new Button();
        switchBtn.setPrefWidth(WIDTH);
        switchBtn.setPrefHeight(this.getHeight());
        switchBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent t) {
                switchedOn.set(!switchedOn.get());
            }
        });
        setGraphic(switchBtn);
        switchedOn.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed (ObservableValue<? extends Boolean> ov,
                                 Boolean t,
                                 Boolean t1) {
                if (t1) {
                    setText("ON");
                    setStyle("-fx-background-color: #64B5F6;-fx-text-fill:white;");
                    setContentDisplay(ContentDisplay.RIGHT);
                } else {
                    setText("OFF");
                    setStyle("-fx-background-color: grey;-fx-text-fill:black;");
                    setContentDisplay(ContentDisplay.LEFT);
                }
            }
        });
        switchedOn.set(false);
    }

    /**
     * Get value of the switch.
     * @return
     */
    public boolean switchOnProperty () {
        return switchedOn.getValue();
    }
    
    /**
     * Set the button to be value of boolean.
     * @param on
     */
    public void setOn(boolean on){
        switchedOn.setValue(on);
    }
    
    /**
     * Returns the boolean value of the button.
     * @return
     */
    public BooleanProperty booleanProperty(){
    	return switchedOn;
    }
}
