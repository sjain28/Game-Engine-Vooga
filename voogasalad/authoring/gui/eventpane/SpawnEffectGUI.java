package authoring.gui.eventpane;

import java.util.ArrayList;
import authoring.gui.items.NumberTextField;
import authoring.interfaces.model.EditEventable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import resources.VoogaBundles;
import tools.VoogaException;


public class SpawnEffectGUI implements EventGUI {

    private ComboBox<String> archetypes;
    private ComboBox<String> targetDesired;
    private SpriteComboBox targetId;
    private NumberTextField x;
    private NumberTextField y;
    private EditEventable elementManager;
    private VBox node;

    public SpawnEffectGUI (EditEventable elementManager) {
        this.elementManager = elementManager;
        node = new VBox();

        initialize();
    }

    private void initialize () {

        archetypes = new ComboBox();
        archetypes.getItems().addAll(elementManager.getSpriteFactory().getAllArchetypeNames());
        targetDesired = new ComboBox();
        targetDesired.getItems().addAll("Relative Position", "Absolute Position");

        targetId = new SpriteComboBox(elementManager);
        x = new NumberTextField();
        x.setPadding(new Insets(5,5,5,5));
        y = new NumberTextField();
        y.setPadding(new Insets(5,5,5,5));

        targetDesired.setOnAction(e -> {
            if (targetDesired.getValue().equals("Relative Position")) {
                removeInactiveNodes(targetId, x, y);
                addGUIElements(targetId);
            }
            if (targetDesired.getValue().equals("Absolute Position")) {
                removeInactiveNodes(targetId, x, y);
                addGUIElements(x, y);
            }
        });
        
        targetId.setOnAction(e->{
            removeInactiveNodes(x,y);
            addGUIElements(x,y);
        });
        
        addGUIElements(archetypes, targetDesired);
    }

    private void addGUIElements (Node ... elements) {
        node.getChildren().addAll(elements);
    }

    private void removeInactiveNodes (Node ... elements) {
        for (Node element : elements) {
            if (node.getChildren().contains(element)) {
                node.getChildren().remove(element);
            }
        }

    }

    @Override
    public Node display () {
        return node;
    }

    @Override
    public String getDetails () throws VoogaException {
        String result = "events.SpawnEffect," + archetypes.getValue() + ",";
        if (targetDesired.getValue().equals("Relative Position")) {
            result += targetId.getSpriteId() + ",";
        }
        result += x.getText() + "," + y.getText();
        return result;
    }

}
