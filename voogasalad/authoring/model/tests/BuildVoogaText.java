package authoring.model.tests;

import authoring.model.VoogaFrontEndText;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class BuildVoogaText extends Application{

    
    @Override
    public void start (Stage primaryStage) throws Exception {
        BorderPane pane = new BorderPane();
        Group group = new Group();
        pane.setCenter(group);
        Circle circle = new Circle();
        circle.setCenterX(100.0f);
        circle.setCenterY(100.0f);
        circle.setRadius(100);
        circle.setFill(Color.RED);
        group.getChildren().addAll(circle,new VoogaFrontEndText("hello"));
        
        Scene scene = new Scene(pane);
        scene.getStylesheets().add("text.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main (String[] args){
        launch(args);
    }
}
