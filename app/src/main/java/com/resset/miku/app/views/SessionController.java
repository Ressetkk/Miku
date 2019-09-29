package com.resset.miku.app.views;

import com.resset.miku.app.api.Session;
import com.resset.miku.app.api.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.kordamp.ikonli.javafx.FontIcon;

public class SessionController {
    @FXML
    private VBox content;
    private Session session;
    public SessionController(Session session) {
        this.session = session;
    }

    public void initialize() {
        // this method invokes with loader.load();
        updateView();

    }

    private void updateView() {

        content.getChildren().clear();
        if (!session.isLoggedIn()) {
            content.getChildren().setAll(setLoginView());
        }
        else {
            content.getChildren().setAll(setProfileView());
        }
    }
    private VBox setLoginView() {
        VBox box = new VBox();
        box.setMaxWidth(250);

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        Button loginButton = new Button("Login");
        Label status = new Label();
        status.setTextFill(Color.RED);
        loginButton.setOnMouseClicked(evt -> {
            boolean isLoginSucceeded;
            try {
                isLoginSucceeded = session.login(usernameField.getText(), passwordField.getText());
                updateView();
            }
            catch (RuntimeException e) {
                status.setGraphic(new FontIcon("fas-times-circle"));
                status.setText(e.getMessage());
            }
        });
        box.setOnKeyReleased(evt -> {
            boolean isLoginSucceeded;
            if (evt.getCode() == KeyCode.ENTER) {
                try {
                    isLoginSucceeded = session.login(usernameField.getText(), passwordField.getText());
                    updateView();
                }
                catch (RuntimeException e) {
                    status.setGraphic(new FontIcon("fas-times-circle"));
                    status.setText(e.getMessage());
                }
            }
        });
        Label title = new Label(String.format("Login to %s", session.getProviderName()));
        box.getChildren().addAll(title, usernameField, passwordField, loginButton, status);
        return box;
    }

    private VBox setProfileView() {
        // TODO make proper FXML profile View
        VBox box = new VBox();
        Label userFullName = new Label();
        Button logoutButton = new Button("Logout");
        logoutButton.setOnMouseClicked(evt -> {
            boolean isLogoutSucceeded = session.logout();
            if (isLogoutSucceeded) {
                updateView();
            }
        });
        Task<User> getUserTask = new Task<User>() {
            @Override
            protected User call() throws Exception {
                return session.getUser();
            }
        };
        getUserTask.setOnSucceeded(evt -> {
            User user = getUserTask.getValue();
            userFullName.setText(String.format("Logged in as %s", user.getName()));
        });
        box.getChildren().addAll(userFullName, logoutButton);
        new Thread(getUserTask).start();
        return box;
    }
}
