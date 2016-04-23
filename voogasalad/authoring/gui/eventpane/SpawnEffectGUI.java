package authoring.gui.eventpane;

import java.util.ArrayList;
import authoring.gui.items.NumberTextField;
import authoring.interfaces.model.EditEventable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import resources.VoogaBundles;
import tools.VoogaException;


public class SpawnEffectGUI implements EventGUI {

    private ComboBox archetypes;
    private ComboBox targetDesired;
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
        y = new NumberTextField();

        targetDesired.setOnAction(e -> {
            if (targetId.getValue().equals("Relative Position")) {
                removeInactiveNodes(targetId, x, y);
                addGUIElements(targetId);
            }
            if (targetId.getValue().equals("Absolute Position")) {
                removeInactiveNodes(targetId, x, y);
                addGUIElements(x, y);
            }
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
        String result = archetypes.getValue() + " ";
        if (targetDesired.getValue().equals("Relative Position")) {
            result += targetId.getValue() + " ";
        }
        result += x.getText() + " " + y.getText();
        return result;
    }

}
