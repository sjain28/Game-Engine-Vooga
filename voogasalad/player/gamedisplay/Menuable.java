package player.gamedisplay;

import authoring.interfaces.model.CompleteAuthoringModelable;

public interface Menuable {
    
    CompleteAuthoringModelable getManager();
    
    void addScene();
    
    void addScene(CompleteAuthoringModelable manager);
    
    void saveAll ();
}
