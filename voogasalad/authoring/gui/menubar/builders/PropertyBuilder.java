package authoring.gui.menubar.builders;

import authoring.CustomText;
import authoring.gui.items.SwitchButton;
import authoring.interfaces.model.EditElementable;
import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tools.GUIUtils;
import tools.VoogaAlert;
import tools.VoogaNumber;
import tools.interfaces.VoogaData;

/**
 * Builder to create properties.
 * 
 * @author HarryGuo
 *
 */

public class PropertyBuilder extends Builder {

	private static final String VOOGA_NUMBER = "VoogaNumber";
	private static final String VOOGA_BOOLEAN = "VoogaBoolean";
	private static final String VALUE = "Value:";

	private VBox container;
	private TextField variableName;
	private TextField variableValue;
	private HBox numberSelector;
	private HBox boolSelector;
	private HBox buttons;
	private SwitchButton switchButton;
	private String chosenData;
	private boolean correctData;

	public PropertyBuilder () {
		this(null);
	}

	/**
	 * Initialization of the Builder in the GUI.
	 * @param editor
	 */
	public PropertyBuilder (EditElementable editor) {
		super(editor);
		this.variableName = new TextField();
		this.variableValue = new TextField();
		this.switchButton = new SwitchButton(true);
		this.numberSelector = makeInfo(VALUE, "Enter a value...", variableValue);
		this.boolSelector = GUIUtils.makeRow(new CustomText(VALUE), switchButton);
		this.buttons = makeButtons();
		this.chosenData = VOOGA_NUMBER;
		this.correctData = true;
		populate();
		load(this.container);
	}

	/**
	 * Populates the dialog box.
	 */
	private void populate () {
		this.container = new VBox();
		this.container.setSpacing(SPACING);
		this.container.setPadding(new Insets(SPACING));
		container.getChildren().addAll(makeDataSelector(),
				makeInfo("Property Name:", "Enter a name...", variableName),
				numberSelector, buttons);
	}

	/**
	 * Data selector between VoogaBoolean and VoogaNumber.
	 * @return
	 */
	private HBox makeDataSelector () {
		ToggleGroup group = new ToggleGroup();
		RadioButton number = new RadioButton("Number");
		number.setToggleGroup(group);
		number.setSelected(true);
		RadioButton bool = new RadioButton("Boolean");
		bool.setToggleGroup(group);
		number.selectedProperty().addListener( (obs, old, newVal) -> {
			container.getChildren().remove(buttons);
			if (newVal) {
				container.getChildren().add(numberSelector);
				container.getChildren().remove(boolSelector);
				chosenData = VOOGA_NUMBER;
			}
			else {
				container.getChildren().remove(numberSelector);
				container.getChildren().add(boolSelector);
				chosenData = VOOGA_BOOLEAN;
			}
			container.getChildren().add(buttons);
		});
		return GUIUtils.makeRow(number, bool);
	}

	/**
	 * Get and store name of the variable name from the user.
	 * @return
	 */
	public String getName () {
		if (variableName.getText().equals("")) {
			VoogaAlert alert = new VoogaAlert("Please input a variable name");
			alert.showAndWait();
			return null;
		}
		return this.variableName.getText();
	}

	/**
	 * Get and store name of the variable property from the user.
	 * @return
	 */
	public VoogaData getValue () {
		VoogaData data;
		Class<?> clazz;
		try {
			clazz = Class.forName("tools." + chosenData);
			data = (VoogaData) clazz.getConstructor().newInstance();
			if (data instanceof VoogaNumber) {
				try {
					data.setValue(Double.parseDouble(variableValue.getText()));
				}
				catch (NumberFormatException e) {
					numberError("Please input a valid number");
					data = null;
				}
			}
			else {
				data.setValue(switchButton.switchOnProperty());
			}
			return data;
		}
		catch (Exception ee) {
			return null;
		}
	}

	/**
	 * Returns whether the data is correct or not.
	 * @return
	 */
	public boolean dataCorrect () {
		return correctData;
	}

	/**
	 * Compiling by exiting out of the dialog box.
	 */
	@Override
	public void compile (){
		compile = true;
		quit();
	}

	/**
	 * Compile status for debugging.
	 */
	@Override
	public boolean compileStatus () {
		return compile;
	}

}
