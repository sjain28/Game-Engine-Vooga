// This entire file is part of my masterpiece.
// Aditya Srinivasan

package authoring.gui.animation;

import java.util.Enumeration;
import java.util.Properties;

import authoring.Command;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import tools.Pair;
import tools.VoogaException;

/**
 * This class is the engine through which the trigger/cause pairs are created and registered into the mediator. The system
 * uses reflection on a properties file to instantiate the correct trigger/cause pairs. The trigger is then displayed on
 * the screen and both are sent to the mediator's register. The trigger and cause are oblivious of each other's existence.
 * 
 * @author Aditya Srinivasan
 *
 */
public class TriggerCommandGUIReflector {
	
	private static final String ERROR_IN_GENERATION = "There was an error in generating the triggers and causes.";
	
	private String packageLocation;
	private Properties triggersToCommands;
	private Pane region;
	
	public TriggerCommandGUIReflector(String packageLocation, Properties triggersToCommands, Pane region) {
		this.packageLocation = packageLocation;
		this.triggersToCommands = triggersToCommands;
		this.region = region;
	}
	
	public void initialize() throws VoogaException {
		Mediator mediator = new GUIMediator();
		for (Enumeration<?> enumer = triggersToCommands.propertyNames(); enumer.hasMoreElements();) {
			String key = (String) enumer.nextElement();
			try {
				VBox subcontainer = new VBox();
				Class<?> trigger = Class.forName(packageLocation + key);
				Trigger display = (Trigger) trigger.getConstructor().newInstance();
				display.initialize();
				subcontainer.getChildren().add((Node) display);
				Class<?> commandClass = Class.forName(packageLocation + triggersToCommands.getProperty(key));
				Command command = (Command) commandClass.getConstructor(Mediator.class, VBox.class).newInstance(mediator, subcontainer);
				mediator.register(new Pair<>(display, command));
				region.getChildren().add(subcontainer);
			} catch (Exception e) {
				throw new VoogaException(ERROR_IN_GENERATION);
			}
		}
	}

}
