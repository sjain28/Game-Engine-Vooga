package authoring.gui.eventpane;

import authoring.gui.items.NumberTextField;
import authoring.interfaces.model.EditEventable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import tools.VoogaException;


public class TimerCauseGUI implements EventGUI {
    private ComboBox<String> decimalPlace;
    private NumberTextField control;
    private NumberTextField startingTime;
    private VBox node;
    
    
    //private EditEventable manager;

    private static final double NODE_SPACING = 15;
    public TimerCauseGUI (EditEventable elementManager) {
       // manager = elementManager;

        initialize();
    }

    public void initialize () {
        node = new VBox();
        node.setSpacing(NODE_SPACING);

        control = new NumberTextField();
        startingTime = new NumberTextField();
        decimalPlace = new ComboBox<>();
        decimalPlace.getItems().addAll("0.1", "0.2", "0.3", "0.4", "0.5", "0.6", "0.7", "0.8",
                                       "0.9");

        node.getChildren().addAll(generateHBox(new Text("Starting Time"), startingTime),
                                  generateHBox(new Text("Frequency"), control, decimalPlace));
    }

    private HBox generateHBox (Node ... nodes) {
        HBox hbox = new HBox();
        hbox.setSpacing(NODE_SPACING);
        for (Node node : nodes) {
            hbox.getChildren().add(node);
        }
        return hbox;
    }

    @Override
    public Node display () {
        return node;
    }

    @Override
    public String getDetails () throws VoogaException {
        return "events.TimerCause,"+ startingTime.getText() + "," +
               (Double.parseDouble(control.getText())+Double.parseDouble((String) decimalPlace.getValue()));
    }

}
