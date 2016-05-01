package authoring.gui.eventpane;

import authoring.gui.items.ArchetypeComboBox;
import authoring.gui.items.NumberTextField;
import authoring.gui.items.SpriteComboBox;
import authoring.interfaces.model.EditEventable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import tools.VoogaException;


public class ProjectileEffectGUI implements EventGUI {

    private static final String REL_POS = "Relative Position";
    private static final String ABS_POS = "Absolute Position";
    private static final String SC_VEL = "Scaled Velocity";
    private static final String AB_VEL = "Absolute Velocity";

    private static final int PADDING = 5;

    private EditEventable elementManager;

    private SpriteComboBox archetypes;
    private ComboBox<String> targetDesired;
    private ComboBox<String> velocityScaledDesired;

    private SpriteComboBox targetId;
    private NumberTextField posx;
    private NumberTextField posy;
    private NumberTextField velx;
    private NumberTextField vely;
    private NumberTextField amount;

    private VBox node;

    public ProjectileEffectGUI (EditEventable elementManager) {
        this.elementManager = elementManager;
        node = new VBox();
        initialize();
    }

    private void createObjects () {
        archetypes = new ArchetypeComboBox(elementManager);
        archetypes.setPromptText("Select Archetype to Spawn");
        
        targetDesired = new ComboBox<>();
        targetDesired.setPromptText("Choose Position");
        targetDesired.getItems().addAll(REL_POS, ABS_POS);
        
        velocityScaledDesired = new ComboBox<>();
        velocityScaledDesired.setPromptText("Scale Velocity Desired");
        velocityScaledDesired.getItems().addAll(SC_VEL,AB_VEL);
        
        targetId = new SpriteComboBox(elementManager);
        
        posx = new NumberTextField();
        posx.setPadding(new Insets(PADDING));
        posx.setPromptText("Position X");
        
        posy = new NumberTextField();
        posy.setPadding(new Insets(PADDING));
        posy.setPromptText("Position Y");
        
        velx = new NumberTextField();
        velx.setPadding(new Insets(PADDING));
        velx.setPromptText("Velocity X");
        
        vely = new NumberTextField();
        vely.setPadding(new Insets(PADDING));
        vely.setPromptText("Velocity Y");
        
        amount = new NumberTextField();
        amount.setPadding(new Insets(PADDING));

        
    }

    private void initialize () {

        createObjects();

        targetDesired.setOnAction(e -> {
            if (targetDesired.getValue().equals(REL_POS)) {
                removeInactiveNodes(targetId, posx, posy);
                addGUIElements(targetId);
            }
            if (targetDesired.getValue().equals(ABS_POS)) {
                removeInactiveNodes(targetId, posx, posy, velx, vely, targetId, amount);
                addGUIElements(posx, posy, velx, vely);
            }
        });

        targetId.setOnAction(e -> {
            removeInactiveNodes(posx, posy);
            addGUIElements(posx, posy, velocityScaledDesired);
        });

        velocityScaledDesired.setOnAction(e -> {
            
            if (velocityScaledDesired.getValue().equals(SC_VEL)) {
                removeInactiveNodes(velx, vely,amount);
                addGUIElements(amount);
            }
            
            if (velocityScaledDesired.getValue().equals(AB_VEL)) {
                removeInactiveNodes(velx,vely,amount);
                addGUIElements(velx, vely);
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
        String result = "events.ProjectileEffect," + archetypes.getValue() + ",";
        if (targetDesired.getValue().equals(REL_POS)) {
            result += targetId.getSpriteId() + ",";
        }
        result += posx.getText() + "," + posy.getText() + ",";

        if (targetDesired.getValue().equals(ABS_POS)) {
            result += velx.getText() + "," + vely.getText() + ",";
            return result;
        }

        if (velocityScaledDesired.getValue().equals(SC_VEL)) {
            result += amount.getText();
        }
        else {
            result += velx.getText() + "," + vely.getText();
        }
        return result;
    }

}
