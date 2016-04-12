package authoring.properties;

/**
 * Properties window to see all the current characteristics of a Sprite
 * @author Harry Guo, Arjun Desai, Aditya Srininvasan, Nick Lockett
 * 
 */

import java.util.ArrayList;

import authoring.interfaces.gui.Windowable;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class PropertiesPane extends TabPane implements Windowable {

	/**
	 * Constructor to instantiate the properties Pane
	 */	
	public PropertiesPane() {
		PropertiesTabManager ptm = new PropertiesTabManager();
		ArrayList<Tab> tabList = ptm.getTabList();
		tabList.stream().forEach(tab -> populateTabPane(tab));
	}
	
	/**
	 * Populates the tabs of the current tabPane with tabs
	 * @param tab
	 */
	private void populateTabPane(Tab tab) {
		this.getTabs().add(tab);
	}

	@Override
	public Node getWindow() {
		return this;
	}

}
