package authoring.gui.eventpane;

import authoring.gui.items.NumberTextField;
import authoring.interfaces.model.EditEventable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import tools.VoogaException;


public class ProjectileEffectGUI implements EventGUI {
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
        archetypes = new SpriteComboBox(elementManager, "Archetype");
        archetypes.getItems().addAll(elementManager.getSpriteFactory().getAllArchetypeNames());
        targetDesired = new ComboBox();
        targetDesired.getItems().addAll("Relative Position", "Absolute Position");

        targetId = new SpriteComboBox(elementManager, "Sprite");
        posx = new NumberTextField();
        posx.setPadding(new Insets(5, 5, 5, 5));
        posy = new NumberTextField();
        posy.setPadding(new Insets(5, 5, 5, 5));
        velx = new NumberTextField();
        velx.setPadding(new Insets(5, 5, 5, 5));
        vely = new NumberTextField();
        vely.setPadding(new Insets(5, 5, 5, 5));
        amount = new NumberTextField();
        amount.setPadding(new Insets(5, 5, 5, 5));

        velocityScaledDesired = new ComboBox();
        velocityScaledDesired.getItems().addAll("Scaled Velocity", "Absolute Velocity");
    }

    private void initialize () {

        createObjects();

        targetDesired.setOnAction(e -> {
            if (targetDesired.getValue().equals("Relative Position")) {
                removeInactiveNodes(targetId, posx, posy);
                addGUIElements(targetId);
            }
            if (targetDesired.getValue().equals("Absolute Position")) {
                removeInactiveNodes(targetId, posx, posy, velx, vely, targetId, amount);
                addGUIElements(posx, posy, velx, vely);
            }
        });

        targetId.setOnAction(e -> {
            removeInactiveNodes(posx, posy);
            addGUIElements(posx, posy, velocityScaledDesired);
        });

        velocityScaledDesired.setOnAction(e -> {
            if (targetDesired.getValue().equals("Scaled Velocity")) {
                removeInactiveNodes(velx, vely);
                addGUIElements(amount);
            }
            if (targetDesired.getValue().equals("Absolute Velocity")) {
                removeInactiveNodes(amount);
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
        if (targetDesired.getValue().equals("Relative Position")) {
            result += targetId.getSpriteId() + ",";
        }
        result += posx.getText() + "," + posy.getText()+",";
        
        if (targetDesired.getValue().equals("Absolute Position")){
            result+= velx.getText()+","+vely.getText()+",";
            return result;
        }
        
        if (velocityScaledDesired.getValue().equals("Scaled Velocity")){
            result += amount.getText();
        } else {
            result += velx.getText()+","+vely.getText();
        }
        return result;
    }

}
