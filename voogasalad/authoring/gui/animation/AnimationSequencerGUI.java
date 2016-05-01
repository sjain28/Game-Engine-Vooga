package authoring.gui.animation;

import java.util.ArrayList;
import java.util.List;

import authoring.CustomText;
import authoring.resourceutility.ButtonMaker;
import events.AnimationEvent;
import events.AnimationFactory;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import tools.GUIUtils;

public class AnimationSequencerGUI extends Tab {
	
	/**
	 * Constants
	 */
	private static final String PROMPT_NAME = "Sequence Name";
	private static final double SPACING = 10;
	private static final String ADD = "Add";
	private static final String OK = "OK";
	
	/**
	 * Private instance variables
	 */
	private AnimationFactory factory;
	private VBox container;
	private VBox animationSequenceList;
	private List<String> events;
	private ComboBox<String> animations;
	private TextField name;
	
	/**
	 * Initializes the factory and GUI elements
	 */
	public AnimationSequencerGUI() {
		factory = AnimationFactory.getInstance();
		initializeContainer();
		initializeTab();
	}

	/**
	 * Initializes the container, including the combobox, "add" button, list of added sequences
	 * and controls to name and add the sequence.
	 */
	private void initializeContainer() {
		container = new VBox();
		container.setSpacing(SPACING);
		animationSequenceList = new VBox();
		name = new TextField();
		name.setPromptText(PROMPT_NAME);
		events = new ArrayList<>();
		initializeEventBox();
		container.getChildren().addAll(GUIUtils.makeRow(animations, new ButtonMaker().makeButton(ADD, e -> {
			events.add(animations.getValue());
			animationSequenceList.getChildren().add(new CustomText(animations.getValue()));
		})), animationSequenceList, name, new ButtonMaker().makeButton(OK, e -> {
			factory.makeAnimationSequence(name.getText(), events);
		}));
	}
	
	/**
	 * Initializes the tab content
	 */
	private void initializeTab() {
		this.setContent(container);
	}
	
	/**
	 * Initializes the events combo box
	 */
	private void initializeEventBox() {
		animations = new ComboBox<>();
		loadEvents();
	}
	
	/**
	 * Loads events into the combo box.
	 */
	private void loadEvents() {
		for(String eventName : factory.getMyAnimationEvents().keySet()) {
			animations.getItems().add(eventName);
		}
	}
	
	/**
	 * Updates event listings
	 */
	public void updateEventListings() {
		loadEvents();
	}
	
}
