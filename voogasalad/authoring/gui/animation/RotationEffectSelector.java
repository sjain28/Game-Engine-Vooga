package authoring.gui.animation;

import authoring.CustomText;
import authoring.gui.items.NumberTextField;
import tools.GUIUtils;

/**
 * A subclass of the AbstractEffectSelector that defines the rotation to be experiences in an animation.
 * 
 * @author Aditya Srinivasan
 *
 */
public class RotationEffectSelector extends AbstractEffectSelector {

	/**
	 * Constants
	 */
	private static final String ROTATION_EFFECT = "Rotation Effect";
	private static final String ROTATIONS = "Rotations";
	private static final String ANGLE = "Angle";
	private static final double DEGREES_IN_CIRCLE = 360;

	/**
	 * Private instance variables
	 */
	private NumberTextField numRotations;
	private NumberTextField modRotations;

	/**
	 * Initializes the rotation effect selector and its heading.
	 */
	RotationEffectSelector() {
		super();
		initializeHeader(ROTATION_EFFECT);
	}

	/**
	 * Initializes the subnode, which is a row containing number text fields.
	 */
	@Override
	protected void initializeSubnode() {
		numRotations = new NumberTextField();
		modRotations = new NumberTextField();
		subnode = GUIUtils.makeRow(new CustomText(ROTATIONS), numRotations, new CustomText(ANGLE), modRotations);
	}

	/**
	 * Returns the total number of degrees requested to be rotated.
	 */
	@Override
	protected Object getValue() {
		Double rotations = (modRotations.getText() == null) ? 0 : Double.parseDouble(modRotations.getText());
		Double cycles = (numRotations.getText() == null) ? 0 : Double.parseDouble(numRotations.getText());
		return DEGREES_IN_CIRCLE * cycles + rotations;
	}



}
