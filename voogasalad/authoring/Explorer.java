package authoring;

/**
 * Explorer pane that contains the resource explorer tab and game assets tab.
 * 
 * @author Aditya Srinivasan
 */

import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.resourceutility.ResourceUI;
import javafx.scene.control.TabPane;

public class Explorer extends TabPane {

	private ResourceUI resourceExplorer;
	private AssetUI assetExplorer;

	/**
	 * Initializes the explorer pane and populates with resource explorer and asset explorer.
	 * 
	 * @param myManager: manager to pass on to the AssetUI class
	 */
	public Explorer(CompleteAuthoringModelable myManager) {
		resourceExplorer = new ResourceUI();
		assetExplorer = new AssetUI(myManager);
		this.getTabs().addAll(resourceExplorer,
				assetExplorer);
	}

}
