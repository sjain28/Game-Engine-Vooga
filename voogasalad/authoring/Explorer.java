package authoring;

import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.resourceutility.ResourceUI;
import javafx.scene.control.TabPane;

public class Explorer extends TabPane {
	
	private ResourceUI resourceExplorer;
	private AssetUI assetExplorer;
	
	public Explorer(CompleteAuthoringModelable myManager) {
		resourceExplorer = new ResourceUI();
		assetExplorer = new AssetUI(myManager);
		this.getTabs().addAll(resourceExplorer,
						      assetExplorer);
	}

}
