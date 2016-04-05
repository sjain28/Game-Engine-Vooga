package authoring.resourceutility;
import authoring.VoogaScene;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NamePrompter extends VBox {

	private ResourceUI ui;
	private Stage stage;
	
	public NamePrompter(ResourceUI ui) {
		this.ui = ui;
		
		addElements();
		
		Scene scene = new VoogaScene(this);
		stage = new Stage();
		
		stage.setScene(scene);
		stage.show();
		
	}
	
	private void addElements() {
		ButtonMaker maker = new ButtonMaker();
		
		TextField nameField = new TextField();
		nameField.setPromptText("Enter a name...");
		
		Button addFolder = maker.makeButton("Add", e -> {
			ui.addFolder(nameField.getText());
			stage.close();
		});
		
		nameField.textProperty().addListener((obs, oldVal, newVal) -> {
			addFolder.setDisable(newVal.isEmpty());
		});
		
		this.getChildren().addAll(nameField, addFolder);
	}
	
}
