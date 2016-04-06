package authoring.model.tests;

import authoring.gui.DesignBoard;
import authoring.model.VoogaText;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DragDropTest extends Application{

    @Override
    public void start (Stage primaryStage) throws Exception {
        DesignBoard designBoard = new DesignBoard();
        Scene scene = new Scene (designBoard);
        VoogaText vt = new VoogaText("id");
        vt.setMaxWidth(100);
        vt.setMaxHeight(100);
        vt.setTranslateX(100);
        vt.setTranslateY(100);
        designBoard.addElement(vt);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main (String args[]){
        launch(args);
    }
}
