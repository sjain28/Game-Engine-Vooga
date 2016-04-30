package authoring.gui.eventpane;

import authoring.interfaces.model.EditEventable;


public class VariableEffectGUI extends VariableGUIBasic implements EventGUI {
    public VariableEffectGUI (EditEventable elementManager) {
        super(elementManager,EventParts.EFFECT);
    }

}
