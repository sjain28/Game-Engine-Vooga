package authoring.resourceutility;
import authoring.VoogaScene;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NamePrompter extends VBox {

	private ResourceUI ui;
	private Scene scene;
	private Stage stage;
	
	private TextField nameField;
	
	public NamePrompter(ResourceUI ui) {
		this.ui = ui;
		
		
		scene = new VoogaScene(this, 200, 75);		
		stage = new Stage();
		
		stage.setScene(scene);
		stage.show();

		addElements();
		establishShortcuts();
	}
	
	private void addElements() {
		ButtonMaker maker = new ButtonMaker();
		
		nameField = new TextField();
		nameField.setPromptText("Enter a name...");
		
		Button addFolder = maker.makeButton("Add", e -> {
			submitName();
		});
		
		nameField.textProperty().addListener((obs, oldVal, newVal) -> {
			addFolder.setDisable(newVal.isEmpty());
		});
		
		this.getChildren().addAll(nameField, addFolder);
	}
	
	private void submitName() {
		ui.addFolder(nameField.getText());
		stage.close();
	}
	
	private void establishShortcuts() {
		scene.setOnKeyPressed(k -> {
			KeyCode code = k.getCode();
			switch(code) {
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
