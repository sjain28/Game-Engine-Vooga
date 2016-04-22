package tests;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import voogasalad.util.facebookutil.JavaSocial;
import voogasalad.util.facebookutil.SocialType;
import voogasalad.util.facebookutil.applications.App;
import voogasalad.util.facebookutil.scores.ScoreOrder;
import voogasalad.util.facebookutil.user.Email;
import voogasalad.util.facebookutil.user.IUser;

/**
 * This class demos a working example of how the utility posts directly to Facebook
 * 
 *
 */
public class TestFacebook extends Application{
    
    private JavaSocial mySocial;
    private IUser myUser;
    
    private App myApp;

    @Override
    public void start (Stage stage) {
        mySocial = new JavaSocial();
        mySocial.loginUser(SocialType.FACEBOOK);
        addScores(mySocial);
        stage.setScene(testLogin());
        stage.show();
    }

    private void addScores (JavaSocial social) {
        IUser user1 = social.createNewUser(new Email("fake", "fake.com"));
        IUser user2 = social.createNewUser(new Email("other", "other.com"));
        IUser user3 = social.createNewUser(new Email("last", "last.com"));
        social.getHighScoreBoard().addNewScore("game1", user1, 50);
        social.getHighScoreBoard().addNewScore("game1", user2, 1000);
        social.getHighScoreBoard().addNewScore("game1", user3, 3000);
    }

    private Scene testLogin () {
        VBox box = new VBox(5);
        createFields (box);
        return new Scene(box, 500, 500);
    }

    private void createFields (VBox box) {
        box.getChildren().add(makePost());
        box.getChildren().add(makeNotify());
        box.getChildren().add(makeLogout());
    }

    private Node makeLogout () {
        Button button = new Button("Logout");
        button.setOnMouseClicked(e -> mySocial.saveState());
        return button;
    }

    private Node makePost () {
        HBox box = new HBox(5);
        TextField field = new TextField();
        box.getChildren().add(field);
        Button button = new Button("Post");
        button.setOnMouseClicked(e -> post(field));
        box.getChildren().add(button);
        return box;
    }
    
    private Node makeNotify () {
        HBox box = new HBox(5);
        TextField field = new TextField();
        box.getChildren().add(field);
        Button button = new Button("Notify");
        button.setOnMouseClicked(e -> notify(field));
        box.getChildren().add(button);
        return box;
    }

    private void notify (TextField field) {
        myApp = mySocial.getApplications().getAppByType(SocialType.FACEBOOK);
        if (myApp == null) {
            System.out.println("Something's wrong");
            return;
        }
        myApp.notifyUsers(mySocial.getUsers(), field.getText());
    }

    private void post (TextField field) {
        myUser = mySocial.getActiveUser();
        mySocial.getHighScoreBoard().addNewScore("game1", myUser, 299);
        if (myUser == null) {
            System.out.println("Login first");
            return;
        }
        myUser.getProfiles().getActiveProfile().customPost(field.getText());
        myUser.getProfiles().getActiveProfile().highScoreBoardPost(mySocial.getHighScoreBoard(),
                                                                   "game1", ScoreOrder.HIGHEST);
        myUser.getProfiles().getActiveProfile().highScorePost(mySocial.getHighScoreBoard(), "game1",
                                                              myUser, ScoreOrder.HIGHEST);
        myUser.getProfiles().getActiveProfile().challenge(myUser, myUser, "Hey come play tower defense");
    }

    public static void main (String[] args) {
        launch(args);
    }

}
