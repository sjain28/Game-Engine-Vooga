package authoring.gui.eventpane;

import java.util.ArrayList;
import java.util.Collection;
import authoring.interfaces.model.EditEventable;


public class VariableCauseGUI extends VariableGUIBasic implements EventGUI{
    
    public VariableCauseGUI (EditEventable elementManager) {
        super(elementManager,EventParts.CAUSE);
    }

    @Override
    protected Collection<String> voogaNumberProperties () {
        Collection<String> numberProperties = new ArrayList<String> ();
        numberProperties.add("Equals");
        numberProperties.add(">");
        numberProperties.add("<");
        return numberProperties;
    }

    @Override
    protected Collection<String> voogaProperties () {
        Collection<String> properties = new ArrayList<String> ();
        properties.add("Equals");
        return properties;
    }

}
