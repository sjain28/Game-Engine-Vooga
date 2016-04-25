package authoring.gui.menubar.builders;

import java.util.Map;

import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tools.interfaces.VoogaData;

public class PropertiesTable extends TableView<Property> {

	private Map<String, VoogaData> properties;

	public PropertiesTable(Map<String, VoogaData> properties) {
		this.properties = properties;
		initializeTable();
	}

	@SuppressWarnings("unchecked")
	private void initializeTable () {
		this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		TableColumn<Property, String> nameColumn = new TableColumn<Property, String>("Name");
		TableColumn<Property, Node> valueColumn = new TableColumn<Property, Node>("Value");

		nameColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("name"));
		valueColumn.setCellValueFactory(new PropertyValueFactory<Property, Node>("value"));

		this.getColumns().setAll(nameColumn, valueColumn);
	}

	public void setTable(Map<String, VoogaData> properties) {
		this.properties = properties;
		displayTable();
	}

	public void addVariableToTable(String name, VoogaData value) {
		this.getItems().add(new Property(name, value));
		properties.put(name, value);
	}

	public void removeVariableFromTable(Property remove) {
		this.getItems().remove(remove);
		properties.remove(remove.getName());
	}

	public void displayTable() {
		this.getItems().clear();
		for (String key: properties.keySet()) {
			this.getItems().add(new Property(key, properties.get(key)));
		}
	}

}