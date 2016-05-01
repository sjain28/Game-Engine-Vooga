package authoring.gui.menubar.builders;

import java.util.Map;

import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tools.interfaces.VoogaData;

/**
 * Properties Table to display properties.
 * Incorporated into archetype builder.
 * 
 * @author Aditya Srinivasan, Harry Guo, Arjun Desai, Nick Lockett
 *
 */
public class PropertiesTable extends TableView<Property> {

	private Map<String, VoogaData> properties;

	/**
	 * Initialization
	 * @param properties
	 */
	public PropertiesTable(Map<String, VoogaData> properties) {
		this.properties = properties;
		initializeTable();
	}

	/**
	 * Initialize tableview table.
	 */
	@SuppressWarnings("unchecked")
	private void initializeTable () {
		this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		TableColumn<Property, String> nameColumn = new TableColumn<>("Name");
		TableColumn<Property, Node> valueColumn = new TableColumn<>("Value");

		nameColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("name"));
		valueColumn.setCellValueFactory(new PropertyValueFactory<Property, Node>("value"));

		this.getColumns().setAll(nameColumn, valueColumn);
	}

	/**
	 * Populate table with properties.
	 * @param properties
	 */
	public void setTable(Map<String, VoogaData> properties) {
		this.properties = properties;
		displayTable();
	}

	/**
	 * Add variable to property
	 * @param name
	 * @param value
	 */
	public void addVariableToTable(String name, VoogaData value) {
		this.getItems().add(new Property(name, value));
		properties.put(name, value);
	}

	/**
	 * Remove a variable from the property table.
	 * @param remove
	 */
	public void removeVariableFromTable(Property remove) {
		this.getItems().remove(remove);
		properties.remove(remove.getName());
	}

	/**
	 * Display the table.
	 */
	public void displayTable() {
		this.getItems().clear();
		for (String key: properties.keySet()) {
			this.getItems().add(new Property(key, properties.get(key)));
		}
	}

}