package authoring.gui.eventpane;

import java.util.ArrayList;
import java.util.List;
import authoring.gui.items.NumberTextField;
import authoring.interfaces.model.EditEventable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import resources.VoogaBundles;
import tools.VoogaBoolean;
import tools.VoogaException;
import tools.VoogaNumber;
import tools.interfaces.VoogaData;

public class PhysicsEffectGUI implements EventGUI{
    private SpriteComboBox name;
    private ComboBox<String> actions;
    private NumberTextField amount;

    private EditEventable elementManager;
    private VBox node;
    private List<Node> activeNodes;

    public PhysicsEffectGUI (EditEventable elementManager) {
        this.elementManager = elementManager;
        node = new VBox();
        activeNodes = new ArrayList<Node>();
        
        initialize();
    }

    private void initialize () {
        name = new SpriteComboBox(elementManager);
        actions = new ComboBox<String>();
        actions.getItems().addAll(VoogaBundles.physicsEffectsToGUI.keySet());
        amount = new NumberTextField();
        
        addGUIElements(name,actions,amount);
    }

    private void addGUIElements (Node ... elements) {
        node.getChildren().addAll(elements);
    }

    private void resetNode () {
        node.getChildren().clear();
        activeNodes.clear();
    }

    private void removeInactiveNodes (Node ... elements) {
        for (Node element : elements) {
            if (node.getChildren().contains(element)) {
                activeNodes.remove(element);
                node.getChildren().remove(element);
            }
        }

    }

    @Override
    public Node display () {
        return node;
    }

    @Override
    public String getDetails () throws VoogaException{
        String result = "events.PhysicsEffect,";
        if (name.getValue()!=null){
            result+=name.getSpriteId()+ ",";
        }
        result += VoogaBundles.physicsEffectsToGUI.getString(actions.getValue()) +","+amount.getText();
        return result;
    }
}
