package authoring;

import authoring.gui.levelpreferences.DesignBoardPreferences;
import authoring.interfaces.model.CompleteAuthoringModelable;
import tools.VoogaNumber;
import tools.VoogaString;

public class GlobalPropertiesMapSetter {
    private static final String SCROLLING = "Scrolling";
    private static final String MAIN_UUID = "MainUUID";
    private static final String SCROLL_SPEED = "ScrollSpeed";
    private static final String SCROLL_ANGLE = "ScrollAngle";
    private static final String CONT_SCROLL_TYPE = "ContinuousScrollType";
    private static final String TRACK_DIR = "TrackingDirection";
    private static final String BGM = "BGM";
    
    private CompleteAuthoringModelable manager;
    private DesignBoardPreferences preferences;
    public GlobalPropertiesMapSetter(CompleteAuthoringModelable model, DesignBoardPreferences preferences){
        this.manager = model;
        this.preferences=preferences;
    }
    
    public void setGlobalProperties(){
        manager.getGlobalVariables().put(preferences.getName() + SCROLLING,
                                      new VoogaString(preferences.getScrollingType()));
        manager.getGlobalVariables().put(preferences.getName() + MAIN_UUID,
                                      new VoogaString(preferences.getMainSpriteID()));
        manager.getGlobalVariables()
                .put(preferences.getName() + SCROLL_SPEED,
                     new VoogaNumber(preferences.getContinuousScrollSpeed()));
        manager.getGlobalVariables().put(preferences.getName() + SCROLL_ANGLE,
                                      new VoogaNumber(preferences.getScrollAngle()));
        manager.getGlobalVariables()
                .put(preferences.getName() + CONT_SCROLL_TYPE,
                     new VoogaString(preferences.getContinuousScrollType()));
        manager.getGlobalVariables()
                .put(preferences.getName() + TRACK_DIR,
                     new VoogaString(preferences.getTrackingDirection()));
        manager.getGlobalVariables().put(preferences.getName() + BGM,
                                      new VoogaString(preferences.getBGM()));
    }
}
