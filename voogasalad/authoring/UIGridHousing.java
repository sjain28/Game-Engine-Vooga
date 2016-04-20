package authoring;

import authoring.interfaces.model.CompleteAuthoringModelable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class UIGridHousing extends TabPane{
    public UIGridHousing(CompleteAuthoringModelable manager){
        addScene(manager);
    }
    
    public void addScene(CompleteAuthoringModelable manager){
        Tab scene = new Tab("Scene " + (this.getTabs().size() + 1));
        scene.setContent(new UIGrid(manager));
        this.getTabs().add(scene);
    }

    public CompleteAuthoringModelable getManager () {
        return ((UIGrid) this.getSelectionModel().getSelectedItem().getContent()).getModel();
    }
}
