package authoring.gui.eventpane;

import authoring.gui.items.ArchetypeSpriteCombo;
import authoring.interfaces.model.EditEventable;
import events.AnimationFactory;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tools.VoogaException;

/**
 * Authoring representation of AnimationEffect
 * 
 * Added to the Cause and Event Pane
 * @author arjunaashna
 *
 */
public class AnimationEffectGUI implements EventGUI{
    
    private ArchetypeSpriteCombo names;
    private ComboBox animations;
    
    private EditEventable manager;
    private VBox node;
    
    public AnimationEffectGUI (EditEventable manager) {
        this.manager = manager;
        initialize();
    }

    private void initialize () {
        node = new VBox();
        animations = new ComboBox();
        animations.getItems().addAll(AnimationFactory.getInstance().getMyAnimationEvents().keySet());
        names = new ArchetypeSpriteCombo(manager,node,e->onNameSelected(),true);
        names.display();
    }
    
    private void onNameSelected () {
        addGUIElements(animations);
    }
    
    private void addGUIElements (Node ... elements) {
        node.getChildren().addAll(elements);
    }

    @Override
    public Node display () {
        return node;
    }

    private HBox generateHBox (Node ... nodes) {
        HBox hbox = new HBox();
        hbox.setSpacing(15);
        for (Node node : nodes) {
            hbox.getChildren().add(node);
        }
        return hbox;
    }

    @Override
    public String getDetails () throws VoogaException {
        return "events.AnimationEffect,"+animations.getValue()+","+names.getDetails();
    }
}
