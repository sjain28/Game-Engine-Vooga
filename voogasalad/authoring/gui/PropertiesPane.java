package authoring.gui;

/**
 * Properties window to see all the current characteristics of a Sprite
 * @author Harry Guo, Arjun Desai, Aditya Srininvasan, Nick Lockett
 * 
 */

import java.util.HashMap;
import java.util.Map;

import authoring.CustomText;
import authoring.VoogaScene;
import authoring.gui.items.NewPropertyFactory;
import authoring.interfaces.Elementable;

import authoring.interfaces.gui.Windowable;
import authoring.resourceutility.ButtonMaker;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tools.VoogaBoolean;
import tools.interfaces.VoogaData;

public class PropertiesPane extends TabPane implements Windowable {
	
	private static final double SPACING = 10;

	private VBox box;
	private Elementable myElementable;
	private Map<String, VoogaData> propertiesMap;
	private HBox propertiesHBox = new HBox(10);
	private ScrollPane myScrollPane;
	private Stage stage;
	
	/**
	 * Constructor to instantiate the properties Pane
	 */	
	// TEST CONSTRUCTOR
	public PropertiesPane() {
		Tab properties = new Tab("Properties");
		box = new VBox(SPACING);
		propertiesMap = new HashMap<String, VoogaData>();
		propertiesMap.put("Gravity", new VoogaBoolean());
		myScrollPane = new ScrollPane();
		myScrollPane.setContent(box);
		properties.setContent(myScrollPane);
		this.getTabs().add(properties);
		displayProperties();
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
		propertiesHBox.getChildren().clear();
		
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
		}
		
		propertiesHBox.getChildren().addAll(name, data);
		this.box.getChildren().add(propertiesHBox);
		
		createButtons();
	}
	
	/**
	 * Creates the Add, Apply, and Cancel Buttons for the Properties Pane.
	 */
	public void createButtons() {
		Button addProperty = new ButtonMaker().makeButton("Add Property", e -> addNewProperty());
		
		HBox buttonsPanel = new HBox(SPACING);
		Button apply = new ButtonMaker().makeButton("Apply", e -> updateProperties());
		Button cancel = new ButtonMaker().makeButton("Cancel", e -> this.stage.close());
		
		buttonsPanel.getChildren().addAll(apply, cancel);
		this.box.getChildren().addAll(addProperty, buttonsPanel);
	}
	
	public void removeProperty(String str) {
		myElementable.removeProperty(str);
		displayProperties();
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
	 * Creates the Dialog Box that allows new Properties to be added
	 */
	public void addNewProperty() {
		NewPropertyFactory factory = new NewPropertyFactory();
		VBox root = new VBox();
		stage = new Stage();
		Scene addPropScene = new VoogaScene(root);
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
		    myElementable.addProperty(propertyName.getText(), newVGData);
			displayProperties();
			this.stage.close();
		});

		root.getChildren().addAll(grid, add);		
		
	}

	@Override
	public Node getWindow() {
		return this;
	}

}
