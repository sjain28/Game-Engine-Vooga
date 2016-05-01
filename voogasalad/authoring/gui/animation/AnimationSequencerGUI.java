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
	
	private AnimationFactory factory;
	private VBox container;
	private VBox animationSequenceList;
	private List<String> events;
	private ComboBox<String> animations;
	private TextField name;
	
	
	public AnimationSequencerGUI() {
		factory = AnimationFactory.getInstance();
		initializeContainer();
		initializeTab();
	}

	private void initializeContainer() {
		container = new VBox();
		animationSequenceList = new VBox();
		name = new TextField();
		name.setPromptText("Sequence Name");
		events = new ArrayList<>();
		initializeEventBox();
		container.getChildren().addAll(GUIUtils.makeRow(animations, new ButtonMaker().makeButton("Add", e -> {
			events.add(animations.getValue());
			animationSequenceList.getChildren().add(new CustomText(animations.getValue()));
		})), animationSequenceList, name, new ButtonMaker().makeButton("OK", e -> {
			//TODO: Saumya add logic here
		}));
	}
	
	private void initializeTab() {
		this.setContent(container);
	}
	
	private void initializeEventBox() {
		animations = new ComboBox<>();
		loadEvents();
	}
	
	private void loadEvents() {
		for(String eventName : factory.getMyAnimationEvents().keySet()) {
			animations.getItems().add(eventName);
		}
	}
	
	public void updateEventListings() {
		loadEvents();
	}
	
}
