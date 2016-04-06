package authoring;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class WorkspacePanel extends TabPane{
    private int mySceneCount;
    
    public WorkspacePanel(){
       mySceneCount = 1;
       newSpace();
    }
    
    public void newSpace(){
       Tab t = new Tab("Scene " + mySceneCount);
       mySceneCount++;
       t.setContent(new UIGrid());
       this.getTabs().add(t);
    }
}
