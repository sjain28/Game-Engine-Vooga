package authoring.Properties;
import java.util.HashMap;
import java.util.Map;

import authoring.CustomText;
import authoring.VoogaScene;
import authoring.gui.items.NewPropertyFactory;
import authoring.interfaces.Elementable;
import authoring.resourceutility.ButtonMaker;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tools.VoogaBoolean;
import tools.interfaces.VoogaData;

public abstract class AbstractPropertiesTab extends Tab {
	
	private static final double SPACING = 10;
	
	private VBox box = new VBox(SPACING);;
	protected Map<String, VoogaData> propertiesMap;
	protected Map<String, VoogaData> originalPropertiesMap;
	private HBox propertiesHBox = new HBox(10);
	protected ScrollPane myScrollPane = new ScrollPane();;
	private Stage stage;

	public AbstractPropertiesTab() {
		
		//THIS LIL CHUNK USED FOR TEST PURPOSES ONLY
		propertiesMap = new HashMap<String, VoogaData>();
			
		
		box.getChildren().add(myScrollPane);
		this.setContent(box);
		displayProperties();
		createButtons();
		
	}
	
	public void displayProperties() {
		propertiesHBox.getChildren().clear();
		
		VBox keyCol = new VBox(SPACING);
		VBox valCol = new VBox(SPACING);
		
		Text name = null; Node data = null;
		
		for(String property: propertiesMap.keySet()) {
			
			name = new CustomText(property);
			
			ContextMenu menu = new ContextMenu();
			MenuItem delete = new MenuItem("Delete");
			delete.setOnAction(e -> removeProperty(property));
			menu.getItems().add(delete);
		
			name.setOnMouseClicked(e -> {
				if (e.getButton() == MouseButton.SECONDARY) {
					menu.show(myScrollPane, e.getScreenX(), e.getScreenY());
				}
			});
			
			data = propertiesMap.get(property).display();
			
			keyCol.getChildren().add(name);
			valCol.getChildren().add(data);
		}
		
		propertiesHBox.getChildren().addAll(keyCol, valCol);
		myScrollPane.setContent(propertiesHBox);
	}
	
	/**
	 * Creates the Add, Apply, and Cancel Buttons for the Properties Pane.
	 */
	public void createButtons() {
		Button addProperty = new ButtonMaker().makeButton("Add Property", e -> addNewPropertyPrompt());
		
		HBox buttonsPanel = new HBox(SPACING);
		Button apply = new ButtonMaker().makeButton("Apply", e -> updateProperties());
		Button cancel = new ButtonMaker().makeButton("Cancel", e -> cancelUpdateProperties());
		
		buttonsPanel.getChildren().addAll(apply, cancel);
		this.box.getChildren().addAll(addProperty, buttonsPanel);
	}
	
	private void cancelUpdateProperties() {
		propertiesMap.clear();
		propertiesMap.putAll(originalPropertiesMap);
		displayProperties();
	}

	/**
	 * Creates the Dialog Box that allows new Properties to be added
	 */
	public void addNewPropertyPrompt() {
		
		NewPropertyFactory factory = new NewPropertyFactory();
		VBox root = new VBox();
		stage = new Stage();
		Scene addPropScene = new VoogaScene(root);
		stage.setX(500);
		stage.setY(200);
		stage.setScene(addPropScene);
		stage.setTitle("Add new property...");
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		
		TextField propertyName = new TextField();
		propertyName.setPromptText("Property Name");
		
		ChoiceBox<String> propertyType = new ChoiceBox<String>();
		propertyType.getItems().addAll(factory.getChoices());

		grid.add(new CustomText("Name:"), 0, 0);
		grid.add(propertyName, 1, 0);
		grid.add(new CustomText("Type:"), 0, 1);
		grid.add(propertyType, 1, 1);

		stage.show();
		
		Button add = new ButtonMaker().makeButton("Add", e -> {
			VoogaData newVGData = factory.createNewProperty(propertyType.getValue());
		    addNewProperty(propertyName.getText(), newVGData);
			displayProperties();
			this.stage.close();
		});

		root.getChildren().addAll(grid, add);		
		
	}
	
	/**
	 * Updates all the properties that the user changed
	 */
	public void updateProperties() {
		for (String s : propertiesMap.keySet()){
			//propertiesMap.get(s).update();
		}
	}
	
	/**
	 * Gets the properties map based off of generic object
	 * @param o
	 */
	public abstract void getPropertiesMap(Object o);
	
	/**
	 * Adds new property to properties map based on string and vooga Data
	 * @param s
	 * @param vgData
	 */
	public abstract void addNewProperty(String s, VoogaData vgData);
	
	/**
	 * Removes the property from the property map based on the string
	 * @param s
	 */
	public abstract void removeProperty(String s);

}
