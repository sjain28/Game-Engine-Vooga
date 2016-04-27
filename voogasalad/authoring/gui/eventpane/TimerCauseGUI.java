package authoring.gui.eventpane;

import authoring.gui.items.NumberTextField;
import authoring.interfaces.model.EditEventable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import tools.VoogaException;

public class TimerCauseGUI implements EventGUI{
    private ComboBox decimalPlace;
    private NumberTextField control;
    private NumberTextField startingTime;
    
    private EditEventable manager;
    public TimerCauseGUI(EditEventable elementManager){
        manager = elementManager;
        initialize();
    }
    
    public void initialize(){
        decimalPlace = new ComboBox();
    }
    @Override
    public Node display () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getDetails () throws VoogaException {
        // TODO Auto-generated method stub
        return null;
    }

}
