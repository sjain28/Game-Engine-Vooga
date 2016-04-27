package authoring.gui.eventpane;

import authoring.gui.items.NumberTextField;
import authoring.interfaces.model.EditEventable;
import events.AnimationFactory;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tools.VoogaException;

public class AnimationEffectGUI implements EventGUI{
    private NumberTextField rotation;
    private ComboBox paths;

    private EditEventable manager;
    private VBox node;
    
    public AnimationEffectGUI (EditEventable manager) {
        this.manager = manager;
        initialize();
    }

    private void initialize () {
        rotation = new NumberTextField();
        paths = new ComboBox();
        paths.getItems().addAll(AnimationFactory.getInstance().getPathNames());
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
        return "events.AnimationEffect,"+rotation.getText()+","+paths.getValue();
    }
}
