package authoring.gui.animation;

import authoring.CustomText;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import tools.GUIUtils;

/**
 * An abstract template selector that serves the development of individual selectors for animation effects.
 * 
 * @author Aditya Srinivasan
 *
 */
public abstract class AbstractEffectSelector extends VBox {

	/**
	 * Constants
	 */
	private static final double SPACING = 10;

	protected CheckBox selectEffect;
	protected Node subnode;

	/**
	 * Initializes the checkbox for selection and the subnode. Also creates a listener to display or hide the
	 * particular subnode.
	 */
	public AbstractEffectSelector() {
		this.setSpacing(SPACING);
		initializeSubnode();
		selectEffect = new CheckBox();
		selectEffect.selectedProperty().addListener((obs, old, n) -> {
			if(n) {
				this.getChildren().add(subnode);
			}
			else {
				this.getChildren().remove(subnode);
			}
		});
	}

	/**
	 * Initializes the selector's title.
	 * @param name
	 */
	protected void initializeHeader(String name) {
		this.getChildren().add(GUIUtils.makeRow(selectEffect, new CustomText(name)));
	}

	/**
	 * Allows subclasses to define the subnode to display upon selection of the checkbox.
	 */
	protected abstract void initializeSubnode();

	/**
	 * Returns the particular value in question for a subclass.
	 * @return
	 */
	protected abstract Object getValue();

}
