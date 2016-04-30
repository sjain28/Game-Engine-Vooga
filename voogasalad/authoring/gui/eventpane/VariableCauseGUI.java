package authoring.gui.eventpane;

import authoring.interfaces.model.EditEventable;


public class VariableCauseGUI extends VariableGUIBasic implements EventGUI{
    
    public VariableCauseGUI (EditEventable elementManager) {
        super(elementManager,EventParts.CAUSE);
    }

}
