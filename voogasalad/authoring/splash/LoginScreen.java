package authoring.splash;

import authoring.VoogaScene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
    private static final double HEIGHT = 200;
    private static final int TEXT_SPACING = 30;
    private static final int BUTTON_SPACING = 15;
    private static final String LOGIN_PROMPT = "Please enter your Username and Password...";
    private static final String USER_PROMPT = "Please enter New Account Information...";
    private TabPane myHouse;
    private TextField myUsername;
    private TextField myDisplayname;
    private TextField myPassword;

    public LoginScreen () {
       myHouse = new TabPane();
       VoogaScene scene = new VoogaScene(myHouse);
       Tab login = new Tab("Login");
       VBox loginContent = new VBox();
       loginContent.getChildren().addAll(buildText(LOGIN_PROMPT), loginInput(), loginConfirm() );
       login.setContent(loginContent);
       Tab user = new Tab("New User");
       VBox userContent = new VBox();
       userContent.getChildren().addAll(buildText(USER_PROMPT), userInput(), userConfirm() );
       user.setContent(userContent);
       myHouse.getTabs().addAll(login, user);
       myHouse.setPrefSize(WIDTH, HEIGHT);
       this.setScene(scene);
    }

    private Text buildText (String text) {
        HBox h = new HBox(TEXT_SPACING);
        Text l = new Text();
        
        l.setFont(Font.font( 20));
        l.setFill(Color.WHITE);
        l.setText(text);
        
        h.getChildren().add(l);
        h.setPrefHeight(HEIGHT/3);
        h.setAlignment(Pos.BASELINE_CENTER);
        
        return l;
    }
    
    private HBox loginConfirm () {
        HBox confirm = new HBox(BUTTON_SPACING);
        Button login = new Button("Login");
       
        login.setOnAction(e -> login());
        
        confirm.getChildren().addAll(login);
        confirm.setPrefHeight(HEIGHT / 4);
        confirm.setAlignment(Pos.BOTTOM_CENTER);
        
        return confirm;
    }
    private HBox userConfirm () {
        HBox confirm = new HBox(BUTTON_SPACING / 2);
        Button newUser = new Button("Make Account");
        
        newUser.setOnAction(e -> newUser());

        confirm.getChildren().addAll(newUser);
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

    private HBox loginInput () {
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
    
    private HBox userInput () {
        HBox input = new HBox(TEXT_SPACING);
        
        myUsername = new TextField();
        myUsername.setPromptText("Username");
        myDisplayname = new TextField();
        myDisplayname.setPromptText("Display Name");
        myPassword = new PasswordField();
        myPassword.setPromptText("Password");
        
        input.getChildren().addAll(myUsername, myDisplayname, myPassword);
        input.setAlignment(Pos.CENTER);
        input.setPrefHeight(HEIGHT / 2);
        
        return input;
    }

}