package authoring.resourceutility;

import authoring.VoogaScene;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A simple popup to prompt the user for a valid folder name
 * 
 * @author DoovalSalad
 *
 */
public class NamePrompter extends VBox {

	/**
	 * Private instance variables
	 */
	private ResourceTreeView rtv;
	private Scene scene;
	private Stage stage;
	private TextField nameField;
	private boolean addIsInvalid;
	
	/**
	 * Constants
	 */
	private static final double SPACING = 10;
	private static final String ERROR_BORDER = "-fx-border-color: #FF5252";
	private static final String NORMAL_BORDER = "-fx-border-color: #FFFFFF";
	private static final String INSTRUCTION = "Enter a name for your folder:";
	private static final String ADD = "Add";
	private static final double WINDOW_WIDTH = 400;
	private static final double WINDOW_HEIGHT = 120;

	/**
	 * The constructor, which sets the stage for the name prompter, adds
	 * elements and establishes shortcuts (ENTER and ESCAPE) to interact with
	 * the prompter.
	 * 
	 * @param ui
	 */
	public NamePrompter(ResourceTreeView rtv) {
		this.rtv = rtv;
		this.setSpacing(SPACING);
		this.setPadding(new Insets(SPACING));

		scene = new VoogaScene(this, WINDOW_WIDTH, WINDOW_HEIGHT);
		stage = new Stage();

		stage.setScene(scene);
		stage.show();

		addElements();
		establishShortcuts();
	}

	/**
	 * Adds elements such as a label for instruction, text field for the name,
	 * and a button to add the folder. Listens to text field for valid inputs.
	 */
	private void addElements() {
		Label header = new Label(INSTRUCTION);
		nameField = new TextField();
		nameField.setStyle(NORMAL_BORDER);
		Button addFolder = new ButtonMaker().makeButton(ADD, e -> submitName());
		addFolder.setDisable(true);

		nameField.textProperty().addListener((obs, oldVal, newVal) -> {
			addIsInvalid = rtv.getFileNamesOfType(VoogaFileType.FOLDER).contains(nameField.getText()) ||
					   nameField.getText().isEmpty();
			if(addIsInvalid) {
				nameField.setStyle(ERROR_BORDER);
				addFolder.setDisable(true);
			} else {
				nameField.setStyle(NORMAL_BORDER);
				addFolder.setDisable(false);
			}
		});

		this.getChildren().addAll(header, nameField, addFolder);
	}

	/**
	 * Submits the name from the text field to the main UI if the name is
	 * valid.
	 */
	private void submitName() {
		if(!addIsInvalid) {
			rtv.addItem(new VoogaFile(VoogaFileType.FOLDER, nameField.getText()));
			stage.close();
		}
	}

	/**
	 * Establishes the keyboard shortcuts for adding (ENTER) and closing
	 * (ESCAPE).
	 */
	private void establishShortcuts() {
		scene.setOnKeyPressed(k -> {
			KeyCode code = k.getCode();
			switch (code) {
			case ENTER:
				submitName();
				break;
			case ESCAPE:
				stage.close();
			default:
				break;
			}
		});
	}
}
