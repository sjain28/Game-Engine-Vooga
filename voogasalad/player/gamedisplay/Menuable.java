package player.gamedisplay;

import authoring.interfaces.model.CompleteAuthoringModelable;
import authoring.model.ElementManager;

public interface Menuable {
    
    public CompleteAuthoringModelable getManager();
    
    public void addScene();
    
    public void addScene(CompleteAuthoringModelable manager);
    public void saveAll ();

	public void openScene(ElementManager unserialize);
}
