package authoring.gui.menubar.builders;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import authoring.CustomText;
import authoring.VoogaScene;
import authoring.interfaces.model.EditSpritable;
import authoring.resourceutility.ButtonMaker;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tools.VoogaAlert;
import tools.VoogaException;

/**
 * Class that allows for importation of archetypes that have been stored.
 * 
 * @author Aditya Srinivasan, Harry Guo, Arjun Desai, Nick Lockett
 *
 */

public class ImportArchetype extends Stage {
	
	/**
	 * private instance variables
	 */
	private EditSpritable manager;
	private Scene scene;
	private BorderPane root;
	private List<String> selectedArchetypes;
	private VBox vbox;
	private HBox hbox;

	/**
	 * Initializes the GUI dialog box.
	 */
	public ImportArchetype(){
		ButtonMaker maker = new ButtonMaker();
		root = new BorderPane();
		scene = new VoogaScene(root);
		setSelectedArchetypes(new ArrayList<String>());
		vbox = new VBox();
		hbox = new HBox();

		Button apply = maker.makeButton("Apply",e->apply());
		Button cancel = maker.makeButton("Cancel",e->this.close());
		hbox.getChildren().addAll(apply,cancel);

		root.setBottom(hbox);
		root.setCenter(vbox);
		this.setScene(scene);
	}

	/**
	 * Second constructor to connect archetype importer to editor/manager.
	 * @param manager
	 */
	public ImportArchetype (EditSpritable manager) {
		this();

		this.manager = manager;
		addArchetypeNames(getArchetypeNames());
	}

	/**
	 * Get archetype names from saved archetypes folder.
	 * @return
	 */
	private List<String> getArchetypeNames(){
		File folder = new File("resources/saved_archetypes/");
		File[] listOfFiles = folder.listFiles();
		List<String> archetypeNames = new ArrayList<>();

		for (File file : listOfFiles){
			String name = file.getName();
			int index = name.indexOf('.');
			name = name.substring(0, index);
			archetypeNames.add(name);
		}
		return archetypeNames;
	}

	/**
	 * Adds new collection of archetypes.
	 * @param archetypeNames
	 */
	protected void addArchetypeNames (Collection<String> archetypeNames) {
		if(archetypeNames.isEmpty()) {
			vbox.getChildren().add(new CustomText("No archetypes available."));
		}
		for (String name : archetypeNames) {
			addArchetypeName(name);
		}
	}

	/**
	 * Adds a singular archetype.
	 * @param archetypeName
	 */
	protected void addArchetypeName (String archetypeName) {
		CheckBox checkbox = new CheckBox(archetypeName);
		checkbox.setOnAction(e -> manageSelectedArchetypes(checkbox));
		vbox.getChildren().add(checkbox);
	}

	/**
	 * Manages the check box for picking archetypes to import.
	 * @param checkbox
	 */
	protected void manageSelectedArchetypes (CheckBox checkbox) {
		if (checkbox.isSelected()) {
			getSelectedArchetypes().add(checkbox.getText());
		}
		else {
			if (getSelectedArchetypes().contains(checkbox.getText())) {
				getSelectedArchetypes().remove(checkbox.getText());
			}
		}
	}

	/**
	 * Import selected archetypes.
	 */
	protected void apply(){
		try {
			for (String name : getSelectedArchetypes()){
				manager.getSpriteFactory().importArchetype(name);
			}
			this.close();
		}
		catch (VoogaException e) {
			VoogaAlert alert = new VoogaAlert(e.getMessage());
			alert.showAndWait();
		}
	}

	/**
	 * sets the manager for importing archetypes and connecting to back end.
	 * @param manager
	 */
	protected void setManager(EditSpritable manager){
		this.manager=manager;
	}

	/**
	 * Gets the manager for this class.
	 * @return
	 */
	protected EditSpritable getManager(){
		return manager;
	}
	
	/**
	 * Returns the list of selected archetypes imported.
	 * @return
	 */
	protected List<String> getSelectedArchetypes () {
		return selectedArchetypes;
	}

	/**
	 * Sets the list of selected archetypes to import.
	 * @param selectedArchetypes
	 */
	protected void setSelectedArchetypes (List<String> selectedArchetypes) {
		this.selectedArchetypes = selectedArchetypes;
	}

}
