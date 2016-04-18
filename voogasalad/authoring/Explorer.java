package authoring;

import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.resourceutility.ResourceUI;
import javafx.scene.control.TabPane;

public class Explorer extends TabPane {
	
	public Explorer(CompleteAuthoringModelable myManager) {
		this.getTabs().addAll(new ResourceUI(),
						      new AssetUI(myManager));
	}

}
