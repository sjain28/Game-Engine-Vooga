package authoring;

import authoring.interfaces.model.CompleteAuthoringModelable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;


public class UIGridHousing extends TabPane{
	
	private SimpleStringProperty mySceneName;
	
    public UIGridHousing(CompleteAuthoringModelable manager){
        this.mySceneName = new SimpleStringProperty();
        addScene(manager);
    }
    
    public void addScene(CompleteAuthoringModelable manager){
        Tab scene = new Tab("Untitled Scene");
        UIGrid grid = new UIGrid(manager);
        Bindings.bindBidirectional(mySceneName, grid.getName());
        this.mySceneName.addListener((obs, old, n) -> {
            scene.setText(n);
        });
        scene.setContent(grid);
        this.getTabs().add(scene);
    }

    public CompleteAuthoringModelable getManager () {
        return ((UIGrid) this.getSelectionModel().getSelectedItem().getContent()).getModel();
    }
}
