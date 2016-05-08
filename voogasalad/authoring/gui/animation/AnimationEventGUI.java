// This entire file is part of my masterpiece.
// Aditya Srinivasan

package authoring.gui.animation;

import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import resources.VoogaBundles;
import tools.VoogaAlert;
import tools.VoogaException;

/**
 * This is the GUI for designing animation events. Prior to the code masterpiece, this class initialized several
 * HBox objects and buttons for designing the event. Upon clicking the 'OK' button, each HBox was checked to see
 * if a checkbox was selected. If so, a command was issued to the AnimationFactory corresponding to the HBox that
 * was selected. This design was poor in many ways. Firstly, there were many if-statements required to manually
 * check whether each checkbox was selected. Further, the design only supported specific kinds of HBox objects,
 * namely those that had a checkbox. There was also no sense of closed-ness to modification, and adding new GUI
 * elements required editing this class in multiple locations. Finally, there was no separation between GUI elements
 * and the back-end, and GUI elements that spawned other GUI elements were not kept hidden from each other.
 * 
 * The code masterpiece attempts to solve one of the most pressing problems of front-end development, which is that
 * of simplifying GUI flow. Many sophisticated GUIs feature dependence among components, in that triggering one
 * element will bring up another related element. By programming this way naturally, there is a great amount of
 * coupling that is formed. Further, it becomes difficult in many cases to extend the GUI to incorporate more
 * elements without editing the GUI class itself and manually adding those Nodes to the Pane or VBox.
 * 
 * By design uses several design techniques, including reflection, Mediators, Command interfaces, and Templates in
 * order to solve this problem. The new design renders this class open to extension and closed to modification.
 * Adding new elements to the GUI is a matter of adding a line to an existing properties file (the only change necessary)
 * and creating brand new classes. The system takes care of the automation such that the key-value pairs specified
 * in the properties file are translated to dynamic visual components. I believe that this code truly demonstrates
 * a strong understanding of design concepts learned throughout the course and applies them to solve a tough challenge.
 *
 */
public class AnimationEventGUI extends Tab {

	/**
	 * Constants
	 */
	private static final double SPACING = 10;
	private static final String PACKAGE = "authoring.gui.animation.";

	/**
	 * Private instance variable
	 */
	private VBox container;

	/**
	 * Declares the factory, establishes the container VBox, and adds all relevant nodes to the pane and tab.
	 */
	AnimationEventGUI() {
		container = new VBox(SPACING);
		container.setPadding(new Insets(SPACING));
		try {
			TriggerCommandGUIReflector reflector = new TriggerCommandGUIReflector(PACKAGE, VoogaBundles.animationGUIProperties, container);
			reflector.initialize();
		} catch(VoogaException e) {
			VoogaAlert alert = new VoogaAlert(e.getMessage());
			alert.showAndWait();
		}
		this.setContent(container);
	}

}
