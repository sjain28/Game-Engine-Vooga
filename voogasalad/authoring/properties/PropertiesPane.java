package authoring.properties;

import java.util.ArrayList;

import authoring.interfaces.gui.Windowable;
import authoring.interfaces.model.CompleteAuthoringModelable;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * Properties window to see all the current characteristics of a Sprite(s).
 * 
 * @author Harry Guo, Arjun Desai, Aditya Srinivasan, Nick Lockett
 * 
 */

public class PropertiesPane extends TabPane implements Windowable {

	private PropertiesTabManager ptm;

	/**
	 * Constructor to instantiate the properties Pane and connect it with
	 * an element manager.
	 */
	public PropertiesPane (CompleteAuthoringModelable manager) {
		ptm = new PropertiesTabManager(manager);
		populateTabPane();
	}

	/**
	 * Populates the tabs of the current tabPane with property tabs
	 * 
	 * @param tab
	 */
	private void populateTabPane () {
		ArrayList<Tab> tabList = ptm.getTabList();
		tabList.stream().forEach(tab -> addTab(tab));
	}

	/**
	 * Adds a new tab to properties pane.
	 * 
	 * @param tab
	 */
	private void addTab (Tab tab) {
		this.getTabs().add(tab);
	}

	/**
	 * Properties Pane getter
	 * 
	 * @return
	 */
	public PropertiesTabManager getPropertiesTabManager () {
		return ptm;
	}

	/**
	 * Properties Pane setter
	 * 
	 * @param ptm
	 */
	public void setPropertiesTabManger (PropertiesTabManager ptm) {
		this.ptm = ptm;
		populateTabPane();
	}

	/**
	 * Node in which to return the pane to display on UI grid.
	 */
	@Override
	public Node getWindow () {
		return this;
	}

}
