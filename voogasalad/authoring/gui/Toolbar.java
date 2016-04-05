package authoring.gui;

import java.util.ResourceBundle;

/**
 * This is the class of the ToolBar that allows a user to Run, Save, or Add Object to the simulation.
 * @author Harry Guo, Aditya Srinivasan, Arjun Desai, Nick Lockett
 */

import authoring.interfaces.gui.Windowable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;

public class Toolbar extends MenuBar implements Windowable {
	
	private HBox myToolBar;
	private MenuBar myToolsMenu;
	private ResourceBundle myResources;
	
	public Toolbar() {
		myResources = ResourceBundle.getBundle("ToolBarProperties");
		createToolBar();
	}
	
	private void createToolBar() {
		myToolBar = new HBox();
		myToolsMenu = new MenuBar();
		
		Menu fileMenu = new Menu(myResources.getString("File"));
		MenuItem runSim = makeMenuItem("Run", e -> runSim());
		MenuItem saveSim = makeMenuItem("Save", e -> saveSim());
		MenuItem addObject = makeMenuItem("AddObject", e -> addObject());
		
		fileMenu.getItems().addAll(runSim, saveSim, addObject);
		myToolsMenu.getMenus().add(fileMenu);
		myToolBar.getChildren().add(myToolsMenu);
	}
	
	private void runSim() {
		
	}
	
	private void saveSim() {
		
	}
	
	private void addObject() {
		
	}
	
	private MenuItem makeMenuItem(String property, EventHandler<ActionEvent> handler) {
		MenuItem menuItem = new MenuItem();
		String label = myResources.getString(property);
		menuItem.setText(label);
		menuItem.setOnAction(handler);
		return menuItem;
	}

	@Override
	public Node getWindow() {
		return myToolBar;
	}
}
