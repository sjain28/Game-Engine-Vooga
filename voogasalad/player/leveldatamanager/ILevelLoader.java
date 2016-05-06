package player.leveldatamanager;

import events.VoogaEvent;

public interface ILevelLoader {
    
    void refreshLevelData(String levelfilename);

    void setNextLevelName(String levelNumber);

    void removeSpriteByID(String id);

    void saveProgress(String filePath);

    void addEvent(VoogaEvent event);

    void updatedGlobalTimer(double time);

    boolean getSaveNow();
}
