package authoring.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import authoring.VoogaScene;
import authoring.interfaces.Elementable;
/**
 * Properties window to see all the current characteristics of a Sprite
 * 
 */
import authoring.interfaces.gui.Windowable;
import authoring.model.PropertiesKeyMouse;
import authoring.model.PropertiesVariable;
import javafx.collections.ObservableMap;
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
import tools.interfaces.VoogaData;
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
		
		
		

		
		this.getChildren().addAll(IDText, imageIDText, gravityHBox , keyCommands, keyMouseTable, addKeyCommand);
		
		Scene scene = new VoogaScene(this);
		stage.setScene(scene);
		stage.show();
	}

	public void getProperties(Elementable element){
		Map<Object, Object> propertiesMap = element.getVoogaProperties();
		for(Object o: propertiesMap.keySet()) {
			HBox temp = new HBox(10, new Text(o));
			
		}
		
		this.getChildren().add(e)
	}
	
	public void addButtons() {
		HBox buttonsPanel = new HBox(10);
		Button apply = new Button("Apply");
		Button cancel = new Button("Cancel");
		buttonsPanel.getChildren().addAll(apply, cancel);
	}


	@Override
	public Node getWindow() {
		return this;
	}

}
