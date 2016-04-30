package authoring.gui.eventpane;

import java.util.ArrayList;
import java.util.List;
import authoring.gui.items.ArchetypeComboBox;
import authoring.gui.items.ArchetypeSpriteCombo;
import authoring.gui.items.NumberTextField;
import authoring.gui.items.SpriteComboBox;
import authoring.gui.items.VariableComboBox;
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


public class VariableEffectGUI extends VariableGUIBasic implements EventGUI {
    public VariableEffectGUI (EditEventable elementManager) {
        super(elementManager,EventParts.EFFECT);
    }

}
