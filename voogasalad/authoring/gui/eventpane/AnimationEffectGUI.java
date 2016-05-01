package authoring.gui.eventpane;

import authoring.gui.items.ArchetypeSpriteCombo;
import authoring.interfaces.model.EditEventable;
import events.AnimationFactory;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import tools.VoogaException;

/**
 * Authoring representation of AnimationEffect, where the user can choose the animation event (path name) 
 * from current AnimationFactory
 * 
 * Added by EventWindow
 * 
 * @author Aditya Srinivasan, Arjun Desai, Harry GUO, Nick Lockett
 *
 */
public class AnimationEffectGUI implements EventGUI{

	private ArchetypeSpriteCombo names;
	private ComboBox<String> animations;
	private EditEventable manager;
	private VBox node;

	/**
	 * 
	 * @param manager: EditEventable Manager that gives access to current game objects and list of events
	 */
	public AnimationEffectGUI (EditEventable manager) {
		this.manager = manager;
		initialize();
	}

	/**
	 * Initializes the Animation 
	 */
	private void initialize () {
		node = new VBox();
		animations = new ComboBox<>();
		animations.getItems().addAll(AnimationFactory.getInstance().getMyAnimationEvents().keySet());
		animations.getItems().addAll(AnimationFactory.getInstance().getMyAnimationSequences().keySet());
		names = new ArchetypeSpriteCombo(manager,node,e->onNameSelected(),true);
		names.display();
	}

	private void onNameSelected () {
		addGUIElements(animations);
	}

	private void addGUIElements (Node ... elements) {
		node.getChildren().addAll(elements);
	}

	@Override
	public Node display () {
		return node;
	}

	@Override
	public String getDetails () throws VoogaException {
		return "events.AnimationEffect,"+animations.getValue()+","+names.getDetails();
	}

}
