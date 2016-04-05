package authoring.gui;

import java.util.ArrayList;

/**
 * Properties window to see all the current characteristics of a Sprite
 * 
 */
import authoring.interfaces.gui.Windowable;
import authoring.model.PropertiesKeyMouse;
import authoring.model.PropertiesVariable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import usecases.Sprite;

public class SpriteProperties extends VBox implements Windowable{
	
	private ArrayList<Node> myNodes;

	public SpriteProperties(Stage stage) {
		/**
		 * For game object,
		 * Velocity, position, image
		 * properties
		 * 
		 * Vooga text inherits txt, voogabutton inherits a button
		 * 
		 */
		
		
		this.setSpacing(10);

		Text IDText = new Text("ID           = ");
		Text imageIDText = new Text("ImageID = ");
		
		Text gravityText = new Text("Gravity: ");
		CheckBox cb1 = new CheckBox();
		cb1.setText("On");
		cb1.setSelected(true);
		HBox gravityHBox = new HBox(10, gravityText, cb1);
		
		Text keyCommands = new Text("Key/Mouse Events:");
		keyCommands.setFont(new Font(20));
		TableView<PropertiesKeyMouse> keyMouseTable = new TableView<PropertiesKeyMouse>();
		TableColumn<PropertiesKeyMouse, String> keyMouseCol = new TableColumn<>("Key/Mouse");
		TableColumn<PropertiesKeyMouse, String> eventCol = new TableColumn<>("Event");
		keyMouseTable.getColumns().setAll(keyMouseCol, eventCol);
		keyMouseTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		Button addKeyCommand = new Button("Add UI");
		
		
		Text variablesText = new Text("Variables:");
		variablesText.setFont(new Font(20));
		TableView<PropertiesVariable> variablesTable = new TableView<PropertiesVariable>();
		TableColumn<PropertiesVariable, String> variablesNameCol = new TableColumn<>("Name");
		TableColumn<PropertiesVariable, Double> variablesValueCol = new TableColumn<>("Value");
		variablesTable.getColumns().setAll(variablesNameCol, variablesValueCol);
		variablesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		Button addVariable = new Button("Add Variable");
		
		this.getChildren().addAll(IDText, imageIDText, gravityHBox , keyCommands, keyMouseTable, addKeyCommand,
				variablesText, variablesTable, addVariable);
		
		Scene scene = new Scene(this);
		stage.setScene(scene);
		stage.show();
	}

	public void getProperties(Sprite sprite){

	}


	@Override
	public Node getWindow() {
		return this;
	}

}
