package authoring;

import java.util.ResourceBundle;

import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.model.ElementManager;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import resources.VoogaBundles;

/**
 * The Housing for the UI grid.
 * 
 * @author Harry Guo, Aditya Srinivasan, Nick Lockett, Arjun Desai
 *
 */

public class UIGridHousing extends TabPane{

	private UIGrid grid;

	private ResourceBundle gameDisplayProperties;

	/**
	 * Creates the scene in which the authoring environment is in.
	 * 
	 * @param manager: authoring for back end
	 */
	public UIGridHousing(CompleteAuthoringModelable manager){
		gameDisplayProperties = VoogaBundles.GameDisplayProperties;
		addScene(manager);
	}

	/**
	 * Adds the basic authoring environment
	 * 
	 * @param manager: interface for back end
	 */
	public void addScene(CompleteAuthoringModelable manager){
		Tab scene = new Tab(gameDisplayProperties.getString("Untitled"));
		grid = new UIGrid(manager, scene, false);
		scene.setContent(grid);
		scene.setClosable(false);
		this.getSelectionModel().select(scene);
		this.getTabs().add(scene);
	}

	/**
	 * Opens the current scene.
	 * 
	 * @param manager: interface for back end
	 */
	public void openScene(ElementManager manager) {
		Tab scene = new Tab(manager.getName());
		grid = new UIGrid(manager, scene, true);
		scene.setContent(grid);
		scene.setClosable(false);
		this.getSelectionModel().select(scene);
		this.getTabs().add(scene);
	}

	/**
	 * Removes the first tab.
	 */
	public void removeFirstTab() {
		this.getTabs().remove(0);
	}

}
