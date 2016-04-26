package authoring.splash;

import authoring.VoogaScene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginScreen extends Stage{
    private static final double WIDTH = 500;
    private static final double HEIGHT = 250;
    private static final int TEXT_SPACING = 30;
    private static final int BUTTON_SPACING = 15;
    private static final String PROMPT = "Please enter your Username and Password...";
    private VBox myHouse;
    private TextField myUsername;
    private TextField myPassword;

    public LoginScreen () {
       myHouse = new VBox();
       VoogaScene scene = new VoogaScene(myHouse);
       myHouse.getChildren().addAll(buildText(), buildInput(), buildConfirm());
       myHouse.setPrefSize(WIDTH, HEIGHT);
       this.setScene(scene);
    }

    private Text buildText () {
        HBox h = new HBox(TEXT_SPACING);
        Text l = new Text();
        
        l.setFont(Font.font( 20));
        l.setFill(Color.WHITE);
        l.setText(PROMPT);
        
        h.getChildren().add(l);
        h.setPrefHeight(HEIGHT/3);
        h.setAlignment(Pos.BASELINE_CENTER);
        
        return l;
    }
    
    private HBox buildConfirm () {
        HBox confirm = new HBox(BUTTON_SPACING);
        Button newUser = new Button("I'm New");
        Button login = new Button("Login");
        
        newUser.setOnAction(e -> newUser());
        login.setOnAction(e -> login());
        
        confirm.getChildren().addAll(newUser, login);
        confirm.setPrefHeight(HEIGHT / 4);
        confirm.setAlignment(Pos.BOTTOM_CENTER);
        
        return confirm;
    }

    private void login () {
        // TODO Auto-generated method stud
    }

    private void newUser () {
        // TODO Auto-generated method stub
    }

    private HBox buildInput () {
        HBox input = new HBox(TEXT_SPACING);
        
        myUsername = new TextField();
        myUsername.setPromptText("Username");
        myPassword = new PasswordField();
        myPassword.setPromptText("Password");
        
        input.getChildren().addAll(myUsername, myPassword);
        input.setAlignment(Pos.CENTER);
        input.setPrefHeight(HEIGHT / 2);
        
        return input;
    }

}