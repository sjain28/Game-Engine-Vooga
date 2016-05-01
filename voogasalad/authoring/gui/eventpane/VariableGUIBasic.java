	package authoring.gui.eventpane;

import java.util.Collection;
import authoring.gui.items.ArchetypeSpriteCombo;
import authoring.gui.items.NumberTextField;
import authoring.gui.items.VariableComboBox;
import authoring.interfaces.model.EditEventable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import resources.VoogaBundles;
import tools.VoogaBoolean;
import tools.VoogaNumber;
import tools.VoogaString;
import tools.interfaces.VoogaData;


public abstract class VariableGUIBasic implements EventGUI {

    private static final String GLOBAL = "global";
    private static final String LOCAL = "local";

    private ComboBox<String> level;
    private ArchetypeSpriteCombo name;
    private VariableComboBox variables;
    private ComboBox<String> actions;
    private Node amount;

    private EditEventable elementManager;
    private VBox node;
    private EventParts type;

    public VariableGUIBasic (EditEventable elementManager, EventParts type) {
        this.elementManager = elementManager;
        node = new VBox();
        this.type = type;
        initialize();
    }

    protected void initialize () {
        level = new ComboBox<>();
        level.getItems().addAll(GLOBAL, LOCAL);

        name = new ArchetypeSpriteCombo(elementManager, node, e -> onNameSelected(), true);
        variables = new VariableComboBox(elementManager);
        actions = new ComboBox<>();

        setChangeListeners();
        addGUIElements(level);
    }

    protected void onNameSelected () {
        removeInactiveNodes(variables, actions, amount);
        variables.resetVariables(name.getSpriteComboBox().getSpriteId());
        addGUIElements(variables);
    }

    protected void setChangeListeners () {
        level.setOnAction(e -> {
            resetNode();
            addGUIElements(level);

            if (level.getValue().equals(GLOBAL)) {
                variables.resetVariables(elementManager.getGlobalVariables());
                addGUIElements(variables);
            }
            if (level.getValue().equals(LOCAL)) {
                name.display();
            }
        });

        variables.setOnAction(e -> {
            removeInactiveNodes(actions, amount);
            actions.getItems().clear();
            VoogaData vd = variables.getProperty(variables.getValue());
            if (vd instanceof VoogaNumber) {
                actions.getItems().addAll(voogaNumberProperties());
                NumberTextField numField = new NumberTextField();
                amount = numField;
                addGUIElements(actions, amount);
            }
            if (vd instanceof VoogaBoolean) {
                actions.getItems().addAll(voogaProperties());
                ComboBox<String> cb = new ComboBox<>();
                cb.getItems().addAll("true", "false");
                amount = cb;
                addGUIElements(actions, amount);
            }
            if (vd instanceof VoogaString) {
                actions.getItems().addAll(voogaProperties());
                TextField field = new TextField();
                amount = field;
                addGUIElements(actions, amount);
            }

            actions.getItems().addAll();
        });

    }

    protected void addGUIElements (Node ... elements) {
        node.getChildren().addAll(elements);
    }

    protected void removeInactiveNodes (Node ... elements) {
        for (Node element : elements) {
            if (node.getChildren().contains(element)) {
                node.getChildren().remove(element);
            }
        }

    }

    protected abstract Collection<String> voogaNumberProperties ();

    protected abstract Collection<String> voogaProperties ();

    private void resetNode () {
        node.getChildren().clear();
    }

    @Override
    public Node display () {
        return node;
    }

    public String getDetails () {
        String result = "";
        if (level.getValue().contains(GLOBAL)) {
            result += "events.Variable" + type.toString() + ",";
        }
        if (level.getValue().contains(LOCAL)) {
            result += "events.Sprite" + type.toString() + "," + name.getDetails() + ",";
        }
        result += variables.getValue() +
                  "," + VoogaBundles.EventMethods.getString(actions.getValue()) + ",";

        if (amount instanceof TextField) {
            result += ((TextField) amount).getText();
        }
        else if (amount instanceof ComboBox) {
            result += ((ComboBox<?>) amount).getValue();
        }
        return result;
    }
}
