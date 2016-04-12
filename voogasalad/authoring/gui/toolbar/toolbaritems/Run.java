package authoring.gui.toolbar.toolbaritems;

import authoring.gui.toolbar.ToolbarItemHandler;
import authoring.interfaces.model.CompleteAuthoringModelable;

public class Run extends ToolbarItemHandler {

	public Run(CompleteAuthoringModelable model) {
		
	}
	
	@Override
	public void handle() {
		System.out.println(this.getClass());
	}

}
