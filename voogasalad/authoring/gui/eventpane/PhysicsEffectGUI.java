package authoring.gui.eventpane;

import authoring.gui.items.ArchetypeSpriteCombo;
import authoring.gui.items.NumberTextField;
import authoring.interfaces.model.EditEventable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import resources.VoogaBundles;
import tools.VoogaException;


public class PhysicsEffectGUI implements EventGUI {
    private ArchetypeSpriteCombo name;
    private ComboBox<String> actions;
    private NumberTextField amount;

    private EditEventable elementManager;
    private VBox node;

    public PhysicsEffectGUI (EditEventable elementManager) {
        this.elementManager = elementManager;
        node = new VBox();

        initialize();
    }

    private void onNameSelected () {
        addGUIElements(actions, amount);
    }

    private void initialize () {
        name = new ArchetypeSpriteCombo(elementManager, node, e -> onNameSelected(),true);
        name.display();
        actions = new ComboBox<>();
        actions.getItems().addAll(VoogaBundles.physicsEffectsToGUI.keySet());
        amount = new NumberTextField();

    }

    private void addGUIElements (Node ... elements) {
        node.getChildren().addAll(elements);
    }

    @Override
    public Node display () {
        return node;
    }

    @Override
    public String getDetails () throws VoogaException {
        String result = "events.PhysicsEffect,";
        result = result + name.getDetails() + ","  +
                 VoogaBundles.physicsEffectsToGUI.getString(actions.getValue()) + "," +
                 amount.getText();
        return result;
    }
}
