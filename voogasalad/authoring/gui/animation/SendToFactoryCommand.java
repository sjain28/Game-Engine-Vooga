// This entire file is part of my masterpiece.
// Aditya Srinivasan

package authoring.gui.animation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import authoring.Command;
import events.AnimationEvent;
import events.AnimationFactory;
import javafx.scene.layout.VBox;
import resources.VoogaBundles;
import tools.VoogaAlert;
import tools.VoogaException;

/**
 * This class represents the command that performs something useful with the GUI data. By composing the mediator, it
 * is able to fetch the Detailable elements in the system. These elements are then matched to the desired private fields
 * and populated with the appropriate values. The data is finally sent to the AnimationFactory to create the event
 * desired. The design segments the creation of the event from the front-end creation of fields and combo boxes. This
 * reduces the extent of coupling and allows for greater modularity.
 *
 */
public class SendToFactoryCommand implements Command {
	
	private static final String ERROR_IN_SENDING = "There was a problem in finalizing the event.";

	/**
	 * Private fields to be matched using reflection. DO NOT TOUCH even though "unused" warning.
	 */
	@SuppressWarnings("unused") private Double rotation;
	@SuppressWarnings("unused") private Double scale;
	private Integer duration;
	private String name;
	@SuppressWarnings("unused") private String path;

	private Mediator mediator;

	public SendToFactoryCommand(Mediator mediator, VBox container) {
		this.mediator = mediator;
	}

	@Override
	public void execute() {
		populateFieldsFromMediator();
		try {
			sendToFactory();
		} catch(VoogaException e) {
			VoogaAlert alert = new VoogaAlert(e.getMessage());
			alert.showAndWait();
		}
	}
	
	private void populateFieldsFromMediator() {
		for (Field field : this.getClass().getDeclaredFields()) {
			for (Detailable d : mediator.getDetailables()) {
				if (d.getDetails() != null && field.getName().equals(d.getDetails().getFirst())) {
					try {
						field.set(this, d.getDetails().getLast());
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private void sendToFactory() throws VoogaException{
		AnimationEvent event = AnimationFactory.getInstance().makeAnimationEvent(name, duration);
		for (Field field : this.getClass().getDeclaredFields()) {
			try {
				if (VoogaBundles.animationFactoryProperties.containsKey(field.getName()) && field.get(this) != null) {
					Method method;
					method = AnimationFactory.class.getDeclaredMethod(
							VoogaBundles.animationFactoryProperties.getProperty(field.getName()),
							field.get(this).getClass(), AnimationEvent.class);
					method.invoke(AnimationFactory.getInstance(), field.get(this), event);
				}
			} catch (Exception e) {
				throw new VoogaException(ERROR_IN_SENDING);
			}
		}
	}
}
