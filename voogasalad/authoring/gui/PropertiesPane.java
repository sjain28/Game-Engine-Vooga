package authoring.gui;

/**
 * Properties window to see all the current characteristics of a Sprite
 * @author Harry Guo, Arjun Desai, Aditya Srininvasan, Nick Lockett
 * 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import authoring.interfaces.Elementable;

import authoring.interfaces.gui.Windowable;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import tools.interfaces.VoogaData;

public class PropertiesPane extends VBox implements Windowable{

	private ArrayList<Node> myNodes;

	public PropertiesPane(Stage stage) {
		this.setSpacing(30);
		this.addButtons();
		Scene scene = new Scene(this);
		stage.setScene(scene);
		stage.show();
	}

	public void getProperties(Elementable element){
		this.getChildren().clear();
		Map<String, VoogaData> propertiesMap = element.getVoogaProperties();
		for(String str: propertiesMap.keySet()) {
			HBox temp = new HBox(10, new Text(str), propertiesMap.get(str).display());
			this.getChildren().add(temp);
		}

		this.getChildren().add(addButtons());
	}

	public Node addButtons() {
		Button addProperty = new Button("Add Property");
		addProperty.setOnAction(e -> addBox());
		this.getChildren().add(addProperty);
		HBox buttonsPanel = new HBox(30);
		Button apply = new Button("Apply");
		Button cancel = new Button("Cancel");
		buttonsPanel.getChildren().addAll(apply, cancel);
		this.getChildren().add(buttonsPanel);
		return buttonsPanel;
	}

	public void addBox() {
		
		Dialog<Pair<String, Object>> dialog = new Dialog<>();
		dialog.setTitle("Login Dialog");
		dialog.setHeaderText("Look, a Custom Login Dialog");
		
		ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		
		TextField username = new TextField();
		username.setPromptText("Property Name");
		
		ChoiceBox cb = new ChoiceBox();
		
		cb.getItems().addAll("item1", "item2", "item3");
		
	

		grid.add(new Label("Username:"), 0, 0);
		grid.add(username, 1, 0);
		grid.add(new Label("Password:"), 0, 1);
		grid.add(cb, 1, 1);

		dialog.getDialogPane().setContent(grid);
		
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == loginButtonType) {
		        return new Pair<>(username.getText(), cb.getValue().toString());
		    }
		    return null;
		});

		Optional<Pair<String, Object>> result = dialog.showAndWait();

		result.ifPresent(usernamePassword -> {
		    System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
		});
	}
	
	public void addVoogaBoolean() {
		
	}
	
	public void addVoogaNumber() {
		
	}


	@Override
	public Node getWindow() {
		return this;
	}

}
