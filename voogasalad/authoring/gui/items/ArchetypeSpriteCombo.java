package authoring.gui.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import authoring.interfaces.model.EditEventable;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Archetype Sprite Combo class that combines the both together that allows for easy flow and 
 * access to band end variables, collisions, and physics.
 * 
 * 
 * @author Aditya Srinivasan, Arjun Desai, Nick Lockett, Harry Guo
 *
 */

public class ArchetypeSpriteCombo extends Observable {
	private static final int PADDING = 15;
	private static final String SPRITE = "Sprite";
	private static final String ARCHETYPE = "Archetype";

	private SpriteComboBox spriteComboBox;
	private ComboBox<String> needsSprites;
	private HBox hbox;
	private VBox overallRegion;
	private boolean effect;

	/**
	 * Initialization
	 * @param manager
	 * @param overallRegion
	 * @param e
	 * @param effect
	 */
	public ArchetypeSpriteCombo (EditEventable manager, VBox overallRegion, EventHandler e, boolean effect) {
		this.overallRegion=overallRegion;
		this.effect=effect;
		needsSprites = new ComboBox<>();
		needsSprites.setPromptText("Get sprites from cause");
		needsSprites.getItems().addAll("true", "false");

		hbox = new HBox(PADDING);
		ToggleGroup tg = createToggleGroup(hbox, SPRITE, ARCHETYPE);


		tg.selectedToggleProperty().addListener((obs,old,n)->{
			clearAfterIndex(overallRegion);
			overallRegion.getChildren().addAll(hbox);
			if (((RadioButton) n).getText().equals(SPRITE)){
				spriteComboBox = new SpriteComboBox(manager);
			}
			if (((RadioButton) n).getText().equals(ARCHETYPE)){
				if (effect){
					overallRegion.getChildren().add(needsSprites);
				}
				spriteComboBox = new ArchetypeComboBox(manager);
			}
			spriteComboBox.setOnAction(e);

			overallRegion.getChildren().addAll(spriteComboBox);
		});

	}

	private void clearAfterIndex(VBox region){
		int index =region.getChildren().indexOf(hbox);
		List<Node> nodes = new ArrayList<>();

		for (int i = index;i<region.getChildren().size();i++){
			nodes.add(region.getChildren().get(i));
		}

		region.getChildren().removeAll(nodes);
	}

	/**
	 * Creates the toggle box.
	 * @param hbox
	 * @param names
	 * @return
	 */
	private ToggleGroup createToggleGroup (HBox hbox, String... names) {
		ToggleGroup tg = new ToggleGroup();
		for (String name : names){
			RadioButton rb = new RadioButton(name);
			rb.setToggleGroup(tg);
			hbox.getChildren().add(rb);
		}
		return tg;
	}

	/**
	 * Gets the Sprite ComboBox
	 * @return
	 */
	public SpriteComboBox getSpriteComboBox(){
		return spriteComboBox;
	}

	/**
	 * Displays the overall region this node.
	 */
	public void display(){
		overallRegion.getChildren().add(hbox);
	}

	/**
	 * Gets details of the 
	 * @return
	 */
	public String getDetails(){
		String result ="";
		result+=spriteComboBox.getSpriteId();
		if (spriteComboBox instanceof ArchetypeComboBox && effect){
			result=result+","+needsSprites.getValue();
		}
		return result;
	}

}
