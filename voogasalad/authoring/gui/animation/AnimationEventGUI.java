package authoring.gui.animation;

import java.util.List;

import authoring.CustomText;
import authoring.gui.items.NumberTextField;
import authoring.resourceutility.ButtonMaker;
import events.AnimationEvent;
import events.AnimationFactory;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tools.GUIUtils;

/**
 * The interface for allowing users to define animation events, defined by one or less of each the following:
 * 1) Path (the spatial transformation of the sprite)
 * 2) Rotation (the rotational transformation of the sprite)
 * 3) Scale (the sizing transformation of the sprite)
 * 4) Image (the sequence of images to flip through)
 * 5) Duration (the length of the animation)
 * 
 * @author Aditya Srinivasan
 *
 */
public class AnimationEventGUI extends Tab {

	/**
	 * Constants
	 */
	private static final double SPACING = 10;

	/**
	 * Private instance variables
	 */
	private VBox container;
	private PathEffectSelector pathSelector;
	private ScaleEffectSelector scaleSelector;
	private RotationEffectSelector rotationSelector;
	private ImageAnimationEffectSelector imageSelector;
	private NumberTextField duration;
	private TextField name;
	private AnimationFactory factory;
	private Button OK;
	private Button preview;

	/**
	 * Declares the factory, establishes the container VBox, and adds all relevant nodes to the pane and tab.
	 */
	AnimationEventGUI() {
		factory = AnimationFactory.getInstance();
		container = new VBox(SPACING);
		container.setPadding(new Insets(SPACING));
		initializeSelectors();
		duration = new NumberTextField();
		duration.setPromptText("Duration in seconds");
		duration.sanitizeForInteger();
		name = new TextField();
		container.getChildren().addAll(pathSelector,
				scaleSelector,
				rotationSelector,
				imageSelector,
				GUIUtils.makeRow(new CustomText("Duration: "), duration),
				GUIUtils.makeRow(new CustomText("Name: "), name),
				buttonRow());
		this.setContent(container);
	}

	/**
	 * Instantiates the selectors used to add complexity to the animation.
	 */
	private void initializeSelectors() {
		pathSelector = new PathEffectSelector();
		scaleSelector = new ScaleEffectSelector();
		rotationSelector = new RotationEffectSelector();
		imageSelector = new ImageAnimationEffectSelector();
	}

	/**
	 * Initializes the button row for adding the event and previewing it.
	 * @return the row of buttons in the form of an HBox.
	 */
	private HBox buttonRow() {
		OK = new ButtonMaker().makeButton("OK", e -> {
			//TODO: implement once animation factory has been finalized.
			AnimationEvent animationEvent = factory.makeAnimationEvent(name.getText(), Integer.parseInt(duration.getText()));
			//TODO: use reflection to automate this (but tricky with the extra parameter in path effect)
			if(pathSelector.selectEffect.isSelected()) {
				factory.makePathEffect((String) pathSelector.getValue(), pathSelector.isReverse(), animationEvent);
			}
			if(rotationSelector.selectEffect.isSelected()) {
				factory.makeRotateEffect((Double)rotationSelector.getValue(), animationEvent);
			}
			if(scaleSelector.selectEffect.isSelected()) {
				factory.makeScaleAnimationEffect((Double)scaleSelector.getValue(), animationEvent);
			}
			if(imageSelector.selectEffect.isSelected()) {
				factory.makeImageAnimationEffect((List<String>)imageSelector.getValue(), imageSelector.getNumberOfCycles(), animationEvent);
			}
		});
		preview = new ButtonMaker().makeButton("Preview", e -> {
			//TODO: allow users to preview their animation

		});
		return GUIUtils.makeRow(OK, preview);
	}

	/**
	 * Updates the path listings inside of the path ComboBox.
	 */
	void updatePathListings() {
		pathSelector.updatePathListings();
	}

}
