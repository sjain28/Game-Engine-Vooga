package authoring.gui.animation;

import events.AnimationFactory;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import tools.GUIUtils;

/**
 * A subclass of the AbstractEffectSelector that allows users to specify the path that
 * the animation should follow from a list of predefined paths.
 * 
 * @author Aditya Srinivasan
 *
 */
public class PathEffectSelector extends AbstractEffectSelector {
	
	/**
	 * Constants
	 */
	private static final String PATH_EFFECT = "Path Effect";
	private static final String NO_PATHS = "<No paths have been declared, please do so in the other tab>";
	private static final String REVERSE = "Reverse";
	
	private ComboBox<String> paths;
	private CheckBox reverse;

	/**
	 * Initializes the selector and the heading with a title.
	 */
	PathEffectSelector() {
		super();
		initializeHeader(PATH_EFFECT);
	}

	/**
	 * Initializes the subnode, in this case a row of combobox (for path name) and checkbox (for
	 * reversing or not).
	 */
	@Override
	protected void initializeSubnode() {
		paths = new ComboBox<>();
		reverse = new CheckBox();
		reverse.setText(REVERSE);
		subnode = GUIUtils.makeRow(paths, reverse); 
	}
	
	/**
	 * Updates the listings in the path to reflect all paths created.
	 */
	public void updatePathListings() {
		if(AnimationFactory.getInstance().getPathNames().isEmpty()) {
			paths.getItems().add(NO_PATHS);
			reverse.setDisable(true);
			return;
		}
		AnimationFactory.getInstance().getPathNames().stream()
													 .forEach(paths.getItems()::add);
	}

	/**
	 * Returns the value of this selector, which is the name of the path.
	 */
	@Override
	protected Object getValue() {
		return paths.getValue();
	}
	
	
	/**
	 * Determines if the reverse checkbox is selected.
	 * @return
	 */
	public Boolean isReverse() {
		return reverse.isSelected();
	}
	
}
