package authoring.splash;

import authoring.VoogaScene;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import resources.VoogaBundles;
import stats.database.VoogaDataBase;
import tools.VoogaAlert;

public class LoginScreen extends Stage {
	private static final double WIDTH = 500;
	private static final double HEIGHT = 200;
	private static final int TEXT_SPACING = 30;
	private static final int BUTTON_SPACING = 15;
	private VoogaDataBase database = VoogaDataBase.getInstance();

	private TabPane myHouse;
	private TextField myMakeUsername;
	private TextField myMakeDisplayname;
	private TextField myMakePassword;
	private TextField myLoginUsername;
	private TextField myLoginPassword;

	public LoginScreen() {
		myHouse = new TabPane();
		VoogaScene scene = new VoogaScene(myHouse);
		Tab login = new Tab("Login");
		VBox loginContent = new VBox();
		loginContent.getChildren().addAll(loginInput(), loginConfirm());
		login.setContent(loginContent);
		Tab user = new Tab("New User");
		VBox userContent = new VBox();
		userContent.getChildren().addAll(userInput(), userConfirm());
		user.setContent(userContent);
		myHouse.getTabs().addAll(login, user);
		myHouse.setPrefSize(WIDTH, HEIGHT);
		this.setScene(scene);
	}

	private HBox loginConfirm() {
		HBox confirm = new HBox(BUTTON_SPACING);
		Button login = new Button("Login");

		login.setOnAction(e -> {
			try {
				login(myLoginUsername.getText(), myLoginPassword.getText());
				this.close();
			} catch (Exception ee) {
				new VoogaAlert("This user does not exist. Please enter a valid username or password.");
			}
		});

		confirm.getChildren().addAll(login);
		confirm.setPrefHeight(HEIGHT / 4);
		confirm.setAlignment(Pos.BOTTOM_CENTER);

		return confirm;
	}

	private HBox userConfirm() {
		HBox confirm = new HBox(BUTTON_SPACING / 2);
		Button newUser = new Button("Make Account");

		newUser.setOnAction(e -> newUser());

		confirm.getChildren().addAll(newUser);
		confirm.setPrefHeight(HEIGHT / 4);
		confirm.setAlignment(Pos.BOTTOM_CENTER);

		return confirm;
	}

	private void login(String user, String pass) {
		if (database.verifyLoginInfo(user, pass)) {
			VoogaBundles.preferences.setProperty("UserName", user);
			database.save();
			new Splash(new CreateCommand(), new LearnCommand(), new OpenCommand());
		} else {
			new VoogaAlert("UserName or Password is Incorrect!");
		}
	}

	private void newUser() {
		database.checkThenAddIfNewUser(myMakeDisplayname.getText(), myMakeUsername.getText(), myMakePassword.getText(), null);
		login(myMakeUsername.getText(), myMakePassword.getText());
	}

	private HBox loginInput() {
		HBox input = new HBox(TEXT_SPACING);

		myLoginUsername = new TextField();
		myLoginUsername.setPromptText("UserName");
		myLoginPassword = new PasswordField();
		myLoginPassword.setPromptText("Password");

		input.getChildren().addAll(myLoginUsername, myLoginPassword);
		input.setAlignment(Pos.CENTER);
		input.setPrefHeight(HEIGHT / 2);

		return input;
	}

	private HBox userInput() {
		HBox input = new HBox(TEXT_SPACING);

		myMakeUsername = new TextField();
		myMakeUsername.setPromptText("UserName");
		myMakeDisplayname = new TextField();
		myMakeDisplayname.setPromptText("Display Name");
		myMakePassword = new PasswordField();
		myMakePassword.setPromptText("Password");

		input.getChildren().addAll(myMakeUsername, myMakeDisplayname, myMakePassword);
		input.setAlignment(Pos.CENTER);
		input.setPrefHeight(HEIGHT / 2);

		return input;
	}

}