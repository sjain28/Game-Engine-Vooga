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
import tools.VoogaNumber;
import tools.interfaces.VoogaData;


public class VariableCauseGUI implements EventGUI{
    private ComboBox<String> level;
    private SpriteComboBox name;
    private VariableComboBox variables;
    private ComboBox<String> actions;
    private Node amount;

    private EditEventable elementManager;
    private VBox node;
    private List<Node> activeNodes;
    
    public VariableCauseGUI (EditEventable elementManager) {
        this.elementManager = elementManager;
        node = new VBox();
        activeNodes = new ArrayList<Node>();
        
        initialize(level, name, variables, actions);
    }

    private void initialize (ComboBox ... cbs) {
        level = new ComboBox<String>();
        name = new SpriteComboBox(elementManager);
        variables = new VariableComboBox(elementManager);
        actions = new ComboBox<String>();
        level.getItems().addAll("global", "local");
        addGUIElements(level);
        setChangeListeners();
    }

    private void setChangeListeners () {
        level.setOnAction(e -> {
            //System.out.println("level activated");
            resetNode();
            addGUIElements(level);

            if (level.getValue().equals("global")) {
                variables.resetVariables(elementManager.getGlobalVariables());
                addGUIElements(variables);
            }
            if (level.getValue().equals("local")) {
                addGUIElements(name);
            }
        });

        name.setOnAction(e -> {
            //System.out.println("name activated");
            removeInactiveNodes(variables, actions, amount);
            //System.out.println(elementManager.getVoogaElement(name.getSpriteId()).getVoogaProperties());
            variables.resetVariables(name.getSpriteId());
            addGUIElements(variables);
        });

        variables.setOnAction(e -> {
            //System.out.println("variables activated");
            removeInactiveNodes(actions, amount);
            actions.getItems().clear();
            VoogaData vd = variables.getProperty(variables.getValue());
            if (vd instanceof VoogaNumber) {
                actions.getItems().addAll("Equals", "<", ">");
                NumberTextField numField = new NumberTextField();
                amount = numField;
                addGUIElements(actions, amount);
            }
            if (vd instanceof VoogaBoolean) {
                actions.getItems().addAll(("Equals"));
                ComboBox<String> cb = new ComboBox<String>();
                cb.getItems().addAll("true", "false");
                amount = cb;
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
    
    private void removeInactiveNodes(Node...elements){
        for (Node element: elements){
            if (node.getChildren().contains(element)){
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
    public String getDetails(){
        String result="";
        if (level.getValue().contains("global")){
            result += "events.VariableCause,";
        }
        if (level.getValue().contains("local")){
            result += "events.SpriteVariableCause,"+name.getSpriteId()+",";
        }
        result+=variables.getValue()+
                " "+VoogaBundles.EventMethods.getString(actions.getValue())+",";
        
        if (amount instanceof NumberTextField){
            result+=((NumberTextField) amount).getText();
        } else {
            result += ((ComboBox) amount).getValue();
        }
        return result;
    }

}
