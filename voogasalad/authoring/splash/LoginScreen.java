package authoring.splash;

import authoring.VoogaScene;
import authoring.resourceutility.ButtonMaker;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import resources.VoogaBundles;
import stats.database.VoogaDataBase;
import tools.VoogaAlert;
import tools.VoogaFileChooser;

/**
 * Creates a login Screen to either create a new user or log in an existing one.  Requires a database and
 * database.xml file to log in existing users.
 * @author Nick
 *
 */
public class LoginScreen extends Stage {
    private static final double WIDTH = 500;
    private static final double HEIGHT = 200;
    private static final int TEXT_SPACING = 30;
    private static final int BUTTON_SPACING = 15;
    private String USR = "UserName";
    private String LOG = "Login";
    private String PS = "Password";
    
    
    private VoogaDataBase database = VoogaDataBase.getInstance();

    private TabPane myHouse;
    private TextField myMakeUsername;
    private TextField myMakeDisplayname;
    private TextField myMakePassword;
    private TextField myLoginUsername;
    private TextField myLoginPassword;
    private String myImagePath = "";

    /**
     * Inializes the login Screen, assembling the necessary text boxes and buttons necessary to store all the information.
     */
    public LoginScreen () {
        myHouse = new TabPane();
        VoogaScene scene = new VoogaScene(myHouse);
        Tab login = new Tab(LOG);
        VBox loginContent = new VBox();
        loginContent.getChildren().addAll(loginInput(), loginConfirm());
        login.setContent(loginContent);
        Tab user = new Tab("New User");
        VBox userContent = new VBox();
        userContent.getChildren().addAll(userInput(), imageSelect(), userConfirm());
        user.setContent(userContent);
        myHouse.getTabs().addAll(login, user);
        myHouse.setPrefSize(WIDTH, HEIGHT);
        this.setScene(scene);
    }

    private HBox loginConfirm () {
        HBox confirm = new HBox(BUTTON_SPACING);
        Button login = new Button(LOG);

        login.setOnAction(e -> {
            try {
                login(myLoginUsername.getText(), myLoginPassword.getText());
                this.close();
            }
            catch (Exception ee) {
                VoogaAlert alert =
                        new VoogaAlert("This user does not exist. Please enter a valid username or password.");
                alert.showAndWait();
            }
        });

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

    private void login (String user, String pass) {
        if (database.verifyLoginInfo(user, pass)) {
            VoogaBundles.preferences.setProperty(USR, user);
            // database.save();
            new Splash(new CreateCommand(), new LearnCommand(), new OpenCommand());
        }
        else {
            new VoogaAlert("UserName or Password is Incorrect!");
        }
    }

    private void newUser () {
        database.checkThenAddIfNewUser(myMakeDisplayname.getText(), myMakeUsername.getText(),
                                       myMakePassword.getText(), myImagePath);
        database.save();
        login(myMakeUsername.getText(), myMakePassword.getText());
    }

    private HBox loginInput () {
        HBox input = new HBox(TEXT_SPACING);

        myLoginUsername = new TextField();
        myLoginUsername.setPromptText(USR);
        myLoginPassword = new PasswordField();
        myLoginPassword.setPromptText(PS);

        input.getChildren().addAll(myLoginUsername, myLoginPassword);
        input.setAlignment(Pos.CENTER);
        input.setPrefHeight(HEIGHT / 2);

        return input;
    }

    private HBox userInput () {
        HBox input = new HBox(TEXT_SPACING);

        myMakeUsername = new TextField();
        myMakeUsername.setPromptText(USR);
        myMakeDisplayname = new TextField();
        myMakeDisplayname.setPromptText("Display Name");
        myMakePassword = new PasswordField();
        myMakePassword.setPromptText(PS);

        input.getChildren().addAll(myMakeUsername, myMakeDisplayname, myMakePassword);
        input.setAlignment(Pos.CENTER);
        input.setPrefHeight(HEIGHT / 2);

        return input;
    }

    private HBox imageSelect () {
        HBox box = new HBox(TEXT_SPACING);
        box.setAlignment(Pos.CENTER);

        ImageView image = new ImageView();
        image.setPreserveRatio(true);
        image.setFitWidth(50);

        Button imageChooser = new ButtonMaker().makeButton("Picture", e -> {
            VoogaFileChooser fileChooser = new VoogaFileChooser();
            try {
                myImagePath = fileChooser.launch();
                image.setImage(new Image("file:" + myImagePath));
            }
            catch (Exception e1) {
                new VoogaAlert(e1.getMessage());
            }
        });

        box.getChildren().addAll(image, imageChooser);
        return box;
    }

}