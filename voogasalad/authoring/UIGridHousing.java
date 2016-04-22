package authoring;

import authoring.interfaces.model.CompleteAuthoringModelable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;


public class UIGridHousing extends TabPane{
	
	private UIGrid grid;
	
    public UIGridHousing(CompleteAuthoringModelable manager){
        addScene(manager);
    }
    
    public void addScene(CompleteAuthoringModelable manager){
        Tab scene = new Tab("Untitled Scene");
        grid = new UIGrid(manager, scene);
        scene.setContent(grid);
        scene.setClosable(false);
        this.getSelectionModel().select(scene);
        this.getTabs().add(scene);
    }
    
    public void setProjectName(String name) {
    	grid.setProjectName(name);
    }
    
}
