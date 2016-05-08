package player.leveldatamanager;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import authoring.interfaces.Elementable;
import events.AnimationEvent;
import gameengine.Sprite;
import javafx.scene.Node;
import physics.IPhysicsEngine;
import tools.Pair;
import tools.interfaces.VoogaData;

public interface ILevelGetter {
    String getNextLevelName();

    Boolean containsSprite(String id);

    Sprite getMainSprite();

    Sprite getSpriteByID(String id);
    
    List<Sprite> getSpritesByArch(String archA);

    Sprite addSprite(String archetype);
    
    void putSprite(Sprite s);

    IPhysicsEngine getPhysicsEngine();

    VoogaData getGlobalVar(String myVarName);

    AnimationEvent getAnimationFromFactory(String myAnimationName);

    EventsContainer getEventContainer();

    List<Pair<Node, Boolean>> getDisplayableNodes();

    Set<Entry<String, Elementable>> getElementables();

    Map<String,VoogaData> getGlobalVariables();
}
