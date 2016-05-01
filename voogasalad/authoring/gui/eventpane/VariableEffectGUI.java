package authoring.gui.eventpane;

import java.util.ArrayList;
import java.util.Collection;
import authoring.interfaces.model.EditEventable;


public class VariableEffectGUI extends VariableGUIBasic implements EventGUI {
    public VariableEffectGUI (EditEventable elementManager) {
        super(elementManager,EventParts.EFFECT);
    }

    @Override
    protected Collection<String> voogaNumberProperties () {
        Collection<String> numberProperties = new ArrayList<> ();
        numberProperties.add("Set");
        numberProperties.add("Increase");
        numberProperties.add("Decrease");
        return numberProperties;
    }

    @Override
    protected Collection<String> voogaProperties () {
        Collection<String> properties = new ArrayList<> ();
        properties.add("Set");
        return properties;
    }

}
