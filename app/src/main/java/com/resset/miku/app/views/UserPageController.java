package com.resset.miku.app.views;

import com.resset.miku.app.api.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ServiceLoader;

public class UserPageController {

    @FXML
    private VBox navbar;

    @FXML
    private BorderPane content;

    public void initialize() {
        ServiceLoader<Session> sessionLoader = ServiceLoader.load(Session.class);
        sessionLoader.forEach(session -> {
            Button sessionButton = new Button(session.getProviderName());
            sessionButton.setOnMouseClicked(mouseEvent -> {
                // TODO write logic for setting specific view
                try {
                    SessionController controller = new SessionController(session);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("SessionView.fxml"));
                    loader.setController(controller);
                    Parent view = loader.load();
                    content.setCenter(view);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            navbar.getChildren().add(sessionButton);
        });
    }
}
