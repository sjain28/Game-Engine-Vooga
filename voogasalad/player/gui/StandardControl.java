/**
 * 
 */
package player.gui;

import javafx.scene.layout.HBox;

/**
 * Class that generates a set of controls that becomes a component of StandardDisplay
 * Speed control, volume muting, etc.
 * 
 * @author Hunter Lee
 *
 */
public class StandardControl {
	
	HBox myControl;
	
	public StandardControl() {
		
		myControl = new HBox();
		
	}
	
	public HBox createControl() {
		
		return myControl;
	}
	
}
