package authoring.gui.menubar.builders;

import java.util.Map;

import authoring.resourceutility.ButtonMaker;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tools.interfaces.VoogaData;

/**
 * Properties Tab for adding properties while creating archetypes.
 * 
 * 
 * @author Aditya Srinivasan, Harry Guo, Arjun Desai, Nick Lockett
 *
 */

public class PropertiesTab extends VBox {

	private PropertiesTable pTable;

	/**
	 * Initializes the tab with a the properties table.
	 * @param properties
	 */
	public PropertiesTab (Map<String, VoogaData> properties) {
		pTable = new PropertiesTable(properties);
		this.getChildren().addAll(pTable,
				makeControl());
	}

	/**
	 * Makes the controls for the tab.
	 * e.g. add and remove properites.
	 * @return
	 */
	private HBox makeControl () {
		HBox container = new HBox();
		Button add = new ButtonMaker().makeButton("+", e -> {
			PropertyBuilder pBuilder = new PropertyBuilder();
			pBuilder.showAndWait();
			if (pBuilder.compileStatus() && pBuilder.getName() != null && pBuilder.getValue() != null) {
				pTable.addVariableToTable(pBuilder.getName(), pBuilder.getValue());
			}

		});
		Button remove = new ButtonMaker().makeButton("-", e -> {
			Property toRemove = pTable.getSelectionModel().getSelectedItem();
			pTable.removeVariableFromTable(toRemove);
		});
		container.getChildren().addAll(add, remove);
		return container;
	}

}
