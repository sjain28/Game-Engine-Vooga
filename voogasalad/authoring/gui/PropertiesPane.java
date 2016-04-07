package authoring.gui;

/**
 * Properties window to see all the current characteristics of a Sprite
 * @author Harry Guo, Arjun Desai, Aditya Srininvasan, Nick Lockett
 * 
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import authoring.VoogaScene;
import authoring.gui.items.NewPropertyFactory;
import authoring.interfaces.Elementable;

import authoring.interfaces.gui.Windowable;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import tools.VoogaBoolean;
import tools.interfaces.VoogaData;

public class PropertiesPane extends VBox implements Windowable{

	private Elementable myElementable;
	private Map<String, VoogaData> propertiesMap;
	private VBox propertiesPane = new VBox(10);
	private VBox propertyName = new VBox(10);
	private VBox propertyVoogaData = new VBox(10);
	private HBox propertiesHBox = new HBox(10);
	private ScrollPane myScrollPane = new ScrollPane();
	
	/**
	 * Constructor to instantiate the properties Pane
	 */	
	// TEST CONSTRUCTOR
	public PropertiesPane(Stage stage) {
		
		propertiesMap = new HashMap<String, VoogaData>();
		propertiesMap.put("Gravity", new VoogaBoolean());
		myScrollPane.setPrefSize(300, 100);
		displayProperties();
		
		this.setSpacing(10);
		Scene scene = new VoogaScene(this);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * Gets the properties of an elementable
	 * @param element
	 */
	public void getProperties(Elementable element){
		myElementable = element;
		propertiesMap = element.getVoogaProperties();
		displayProperties();
	}
	
	/**
	 * Displays the properties on the Pane
	 */

	public void displayProperties() {
		this.getChildren().clear();
		propertyName.getChildren().clear();
		propertyVoogaData.getChildren().clear();
		propertiesHBox.getChildren().clear();
		
		for(String str: propertiesMap.keySet()) {
			//can refactor here
			Text name = new Text(str);
			name.setFill(Paint.valueOf("WHITE"));
			name.setFont(new Font(23));
			
			// can refactor here
			ContextMenu menu = new ContextMenu();
			MenuItem delete = new MenuItem("Delete");
			delete.setOnAction(e -> removeProperty(str));
			menu.getItems().add(delete);
		
			name.setOnMouseClicked(e -> {
				if (e.getButton() == MouseButton.SECONDARY) {
					menu.show(myScrollPane, e.getScreenX(), e.getScreenY());
				}
			});
			
			propertyName.getChildren().add(name);
			propertyVoogaData.getChildren().add(propertiesMap.get(str).display());
		}
		
		propertiesHBox.getChildren().addAll(propertyName, propertyVoogaData);
		myScrollPane.setContent(propertiesHBox);
		this.getChildren().add(myScrollPane);
		
		createButtons();
	}
	
	/**
	 * Creates the Add, Apply, and Cancel Buttons for the Properties Pane.
	 */
	//CAN PUT INTO DIFFERENT CLASS LATER
	public void createButtons() {
		
		Button addProperty = new Button("Add Property");
		addProperty.setOnAction(e -> addNewProperty());
		this.getChildren().add(addProperty);
		
		HBox buttonsPanel = new HBox(30);
		Button apply = new Button("Apply");
		apply.setOnAction(e -> updateProperties());
		Button cancel = new Button("Cancel"); //wtf to do here?
		
		buttonsPanel.getChildren().addAll(apply, cancel);
		this.getChildren().add(buttonsPanel);
	}
	
	public void removeProperty(String str) {
		myElementable.removeProperty(str);
		displayProperties();
	}
	
	/**
	 * Updates all the properties that the user changed
	 */
	public void updateProperties() {
		for (String s: propertiesMap.keySet()){
			//propertiesMap.get(s).update();
		}
	}
	
	/**
	 * Creates the Dialog Box that allows new Properties to be added
	 */
	public void addNewProperty() {
		
		NewPropertyFactory factory = new NewPropertyFactory();
		Stage stage = new Stage();
		Group root = new Group();
		
		root.getChildren().add(new Text("dafas"));
		
		
		Scene addPropScene = new VoogaScene(root);
		
		
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Add New Property");
		dialog.setHeaderText("Add New Property");
	
		ButtonType loginButtonType = new ButtonType("Add", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		
		TextField propertyName = new TextField();
		propertyName.setPromptText("Property Name");
		
		ChoiceBox propertyType = new ChoiceBox();
		propertyType.getItems().addAll(factory.getChoices());

		grid.add(new Label("Name:"), 0, 0);
		grid.add(propertyName, 1, 0);
		grid.add(new Label("Type:"), 0, 1);
		grid.add(propertyType, 1, 1);

		dialog.getDialogPane().setContent(grid);
		
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == loginButtonType) {
		        return new Pair<>(propertyName.getText(), propertyType.getValue().toString());
		    }
		    return null;
		});

		Optional<Pair<String, String>> result = dialog.showAndWait();

		result.ifPresent(entry -> {
		    VoogaData newVGData = factory.createNewProperty(entry.getValue());
		    myElementable.addProperty(entry.getKey(), newVGData);
		});
		
		displayProperties();
	}

	@Override
	public Node getWindow() {
		return this;
	}

}
