package authoring.gui.eventpane;

import java.util.ArrayList;
import java.util.Collection;
import authoring.interfaces.model.EditEventable;


public class VariableCauseGUI extends VariableGUIBasic implements EventGUI{
    
	private static final String EQUALS = "Equals";
	
    public VariableCauseGUI (EditEventable elementManager) {
        super(elementManager,EventParts.CAUSE);
    }

    @Override
    protected Collection<String> voogaNumberProperties () {
        Collection<String> numberProperties = new ArrayList<> ();
        numberProperties.add(EQUALS);
        numberProperties.add(">");
        numberProperties.add("<");
        return numberProperties;
    }

    @Override
    protected Collection<String> voogaProperties () {
        Collection<String> properties = new ArrayList<> ();
        properties.add(EQUALS);
        return properties;
    }

}
