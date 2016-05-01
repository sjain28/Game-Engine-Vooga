package authoring.gui.animation;

import authoring.CustomText;
import authoring.gui.items.NumberTextField;
import tools.GUIUtils;

/**
 * A subclass of the AbstractEffectSelector that specifies how much to scale during the animation, in
 * a relative sense to the original scale.
 * 
 * @author Aditya Srinivasan
 *
 */
public class ScaleEffectSelector extends AbstractEffectSelector {

	/**
	 * Constants
	 */
	private static final String SCALE_EFFECT = "Scale Effect";

	private NumberTextField scaleAmount;

	/**
	 * Initializes the selector and its heading.
	 */
	public ScaleEffectSelector() {
		super();
		initializeHeader(SCALE_EFFECT);
	}

	/**
	 * Initializes the subnode, in this case a number text field.
	 */
	@Override
	protected void initializeSubnode() {
		scaleAmount = new NumberTextField();
		subnode = GUIUtils.makeRow(new CustomText("Scale: "), scaleAmount, new CustomText("times as large."));
	}

	/**
	 * Returns the value of this selector, in this case an integer representing
	 * the relative scaling factor to be applied.
	 */
	@Override
	protected Object getValue() {
		return Double.parseDouble(scaleAmount.getText());
	}

}
