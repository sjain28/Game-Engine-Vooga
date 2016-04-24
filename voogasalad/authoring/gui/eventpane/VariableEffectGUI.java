package authoring.gui.eventpane;

import java.util.ArrayList;
import java.util.List;
import authoring.gui.items.NumberTextField;
import authoring.interfaces.model.EditEventable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import resources.VoogaBundles;
import tools.VoogaBoolean;
import tools.VoogaException;
import tools.VoogaNumber;
import tools.VoogaString;
import tools.interfaces.VoogaData;


public class VariableEffectGUI implements EventGUI {
    private ComboBox<String> level;
    private SpriteComboBox name;
    private VariableComboBox variables;
    private ComboBox<String> actions;
    private Node amount;

    private EditEventable elementManager;
    private VBox node;
    private List<Node> activeNodes;

    public VariableEffectGUI (EditEventable elementManager) {
        this.elementManager = elementManager;
        node = new VBox();
        activeNodes = new ArrayList<Node>();

        initialize(level, name, variables, actions);
    }

    private void initialize (ComboBox ... cbs) {
        level = new ComboBox<String>();
        name = new SpriteComboBox(elementManager);
        variables = new VariableComboBox();
        actions = new ComboBox<String>();
        level.getItems().addAll("global", "local");
        addGUIElements(level);
        setChangeListeners();
    }

    private void setChangeListeners () {
        level.setOnAction(e -> {
            // System.out.println("level activated");
            resetNode();
            addGUIElements(level);

            if (level.getValue().equals("global")) {
                variables.onParentChanged(elementManager.getGlobalVariables());
                addGUIElements(variables);
            }
            if (level.getValue().equals("local")) {
                addGUIElements(name);
            }
        });

        name.setOnAction(e -> {
            // System.out.println("name activated");
            removeInactiveNodes(variables, actions, amount);
            variables.onParentChanged(elementManager.getVoogaElement(name.getSpriteId())
                    .getVoogaProperties());
            addGUIElements(variables);
        });

        variables.setOnAction(e -> {
            // System.out.println("variables activated");
            removeInactiveNodes(actions, amount);
            actions.getItems().clear();
            VoogaData vd = variables.getProperty(variables.getValue());
            if (vd instanceof VoogaNumber) {
                actions.getItems().addAll("Set", "Decrease", "Increase");
                NumberTextField numField = new NumberTextField();
                amount = numField;
                addGUIElements(actions, amount);
            }
            if (vd instanceof VoogaBoolean) {
                actions.getItems().addAll(("Set"));
                ComboBox<String> cb = new ComboBox<String>();
                cb.getItems().addAll("true", "false");
                amount = cb;
                addGUIElements(actions, amount);
            }
            if (vd instanceof VoogaString) {
                actions.getItems().addAll("Set");
                TextField field = new TextField();
                amount = field;
                addGUIElements(actions, amount);
            }

            actions.getItems().addAll();
        });

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
    public String getDetails () throws VoogaException {
        String result = "";
        if (level.getValue().contains("global")) {
            result += "events.VariableEffect ";
        }
        if (level.getValue().contains("local")) {
            result += "events.SpriteEffect " + name.getSpriteId() + " ";
        }
        result += variables.getValue() +
                  " " + VoogaBundles.EventMethods.getString(actions.getValue()) + " ";

        if (amount instanceof NumberTextField) {
            result += ((NumberTextField) amount).getText();
        }
        else if (amount instanceof ComboBox) {
            result += ((ComboBox) amount).getValue();
        }
        else if (amount instanceof TextField) {
            result += ((TextField) amount).getText();
        }

        System.out.println(result);

        return result;
    }

}
