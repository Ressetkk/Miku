package com.resset.miku.app.views;

import com.resset.miku.app.api.Session;
import com.resset.miku.app.api.User;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class SessionController {

    @FXML
    private Label fullnameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private ImageView picture;
    @FXML
    private VBox content;
    private Session session;
    private User user;

    private Task<User> getUserTask;

    public SessionController(Session session) {
        this.session = session;
        this.getUserTask = new Task<User>() {
            @Override
            protected User call() throws Exception {
                return session.getUser();
            }
        };
        this.getUserTask.setOnSucceeded(evt -> {
            user = getUserTask.getValue();
            fullnameLabel.setText(user.getName());
            emailLabel.setText(user.getEmail());
            picture.setImage(new Image(user.getImageURL(), true));
        });
    }

    public void initialize() {
        // this method invokes with loader.load();
        Circle clip = new Circle(50);
        clip.setCenterX(picture.getFitWidth()/2);
        clip.setCenterY(picture.getFitHeight()/2);
        picture.setClip(clip);
        updateView();

    }

    private void updateView() {

        content.getChildren().clear();
        if (!session.isLoggedIn()) {
            content.getChildren().setAll(setLoginView());
        }
        else {
            content.getChildren().setAll(setLogoutView());
        }
    }
    private VBox setLoginView() {
        fullnameLabel.setText("Log In");
        emailLabel.setText("");
        picture.setImage(new Image(getClass().getResource("emptyprofile.png").toString()));
        VBox box = new VBox();
        box.setMaxWidth(250);
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        loginButton.setOnMouseClicked(evt -> {
            // TODO write try/catch for wrong credentials handling
            boolean isLoginSucceeded = session.login(usernameField.getText(), passwordField.getText());
            if (isLoginSucceeded) {
                updateView();
            }
        });
        box.getChildren().addAll(usernameField, passwordField, loginButton);
        return box;
    }

    private VBox setLogoutView() {
        new Thread(getUserTask).start();
        VBox box = new VBox();
        Button logoutButton = new Button("Logout");
        logoutButton.setOnMouseClicked(evt -> {
            boolean isLogoutSucceeded = session.logout();
            if (isLogoutSucceeded) {
                updateView();
            }
        });
        box.getChildren().setAll(logoutButton);
        return box;
    }
}
