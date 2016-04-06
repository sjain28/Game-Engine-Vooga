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

public class PropertiesPane extends VBox implements Windowable{
	
	private ArrayList<Node> myNodes;

	public PropertiesPane() {}

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
		this.getChildren().add(addProperty);
		HBox buttonsPanel = new HBox(10);
		Button apply = new Button("Apply");
		Button cancel = new Button("Cancel");
		buttonsPanel.getChildren().addAll(apply, cancel);
		return buttonsPanel;
	}


	@Override
	public Node getWindow() {
		return this;
	}

}
