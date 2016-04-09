package authoring.gui.eventpane;

import java.util.ArrayList;
import java.util.List;
import authoring.gui.items.NumberTextField;
import authoring.model.ElementManager;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import tools.VoogaBoolean;
import tools.VoogaNumber;
import tools.interfaces.VoogaData;


public class VariableCauseGUI implements EventGUI{
    private ComboBox<String> level;
    private ComboBox<String> name;
    private VariableComboBox variables;
    private ComboBox<String> actions;
    private Node amount;

    private ElementManager elementManager;
    private VBox node;
    private List<Node> activeNodes;
    
    public VariableCauseGUI (ElementManager elementManager) {
        this.elementManager = elementManager;
        node = new VBox();
        activeNodes = new ArrayList<Node>();
        
        initialize(level, name, variables, actions);
    }

    private void initialize (ComboBox ... cbs) {
        for (ComboBox cb : cbs) {
            cb = new ComboBox<String>();
        }
        level.getItems().addAll("global", "local");
    }

    private void setChangeListeners () {
        level.setOnAction(e -> {
            resetNode();
            addGUIElements(level);

            if (level.getValue().equals("global")) {
                variables.onParentChanged(elementManager.getGlobalVariables());
                addGUIElements(variables);
            }
            if (level.getValue().equals("local")) {
                name.getItems().addAll(elementManager.getMySpriteNames());
                addGUIElements(name);
            }
        });
        
        name.setOnAction(e->{
            removeInactiveNodes(variables,actions,amount);
            name.getItems().addAll(elementManager.getMySpriteNames());
        });
        
        variables.setOnAction(e -> {
            removeInactiveNodes(actions,amount);
            actions.getItems().clear();
            VoogaData vd = variables.getProperty(variables.getValue());
            if (vd instanceof VoogaNumber) {
                actions.getItems().addAll("equals");
                NumberTextField numField = new NumberTextField();
                amount = numField;
                addGUIElements(actions,amount);
            }
            if (vd instanceof VoogaBoolean) {
                actions.getItems().addAll(("equals"));
                ComboBox<String> cb = new ComboBox<String>();
                cb.getItems().addAll("true","false");
                amount = cb;
                addGUIElements(actions,amount);
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
    public String getDetails () {
        return "VariableCause "+level.getValue()+ " "+name.getValue()+" "+variables.getValue()+
                " "+actions.getValue()+" "+amount.getAccessibleText();
    }

}
