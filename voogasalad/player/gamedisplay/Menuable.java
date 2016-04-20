package player.gamedisplay;

import authoring.interfaces.model.CompleteAuthoringModelable;

public interface Menuable {
    
    public CompleteAuthoringModelable getManager();
    
    public void addScene();

    public void saveAll ();
}
