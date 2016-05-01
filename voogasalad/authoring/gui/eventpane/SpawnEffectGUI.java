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

public class SpawnEffectGUI implements EventGUI {
	
    private static final int PADDING = 5;

    private ArchetypeComboBox archetypes;
    private ComboBox<String> targetDesired;
    private SpriteComboBox targetId;
    private NumberTextField x;
    private NumberTextField y;
    private EditEventable elementManager;
    private VBox node;

    private static final String RELATIVE_POSITION = "Relative Position";
    private static final String ABSOLUTE_POSITION = "Absolute Position";
    
    public SpawnEffectGUI (EditEventable elementManager) {
        this.elementManager = elementManager;
        node = new VBox();

        initialize();
    }

    private void initialize () {

        archetypes = new ArchetypeComboBox(elementManager);
        archetypes.setPromptText("Select Archetype to Spawn");
        
        targetDesired = new ComboBox<>();
        targetDesired.setPromptText("Choose Position");
        targetDesired.getItems().addAll(RELATIVE_POSITION, ABSOLUTE_POSITION);

        targetId = new SpriteComboBox(elementManager);
        x = new NumberTextField();
        x.setPadding(new Insets(PADDING));
        y = new NumberTextField();
        y.setPadding(new Insets(PADDING));

        targetDesired.setOnAction(e -> {
            if (targetDesired.getValue().equals(RELATIVE_POSITION)) {
                removeInactiveNodes(targetId, x, y);
                addGUIElements(targetId);
            }
            if (targetDesired.getValue().equals(ABSOLUTE_POSITION)) {
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

    @Override
    public String getDetails () throws VoogaException {
        String result = "events.SpawnEffect," + archetypes.getValue() + ",";
        if (targetDesired.getValue().equals(RELATIVE_POSITION)) {
            result += targetId.getSpriteId() + ",";
        }
        result += x.getText() + "," + y.getText();
        return result;
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

    private void addGUIElements (Node ... elements) {
        node.getChildren().addAll(elements);
    }
}
