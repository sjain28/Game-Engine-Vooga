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

    Sprite addSprite(String archetype);

    IPhysicsEngine getPhysicsEngine();

    VoogaData getGlobalVar(String myVarName);

    AnimationEvent getAnimationFromFactory(String myAnimationName);

    KeyEventContainer getKeyEventContainer();

    List<Pair<Node, Boolean>> getDisplayableNodes();

    List<Sprite> getSpritesByArch(String archA);

    Set<Entry<String, Elementable>> getElementables();

    Map<String,VoogaData> getGlobalVariables();

    Map<String, Elementable> getElements();
}
