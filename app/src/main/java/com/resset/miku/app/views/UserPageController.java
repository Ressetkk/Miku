package com.resset.miku.app.views;

import com.resset.miku.app.api.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.ServiceLoader;

public class UserPageController {

    @FXML
    private VBox navbar;

    public void initialize() {
        ServiceLoader<Session> sessionLoader = ServiceLoader.load(Session.class);
        sessionLoader.forEach(session -> {
            Button sessionButton = new Button(session.getClass().toString()); //TODO get Session name dynamically
            sessionButton.setOnMouseClicked(mouseEvent -> {
                // TODO write logic for setting specific view
                System.out.println(session.toString());
            });
            navbar.getChildren().add(sessionButton);
        });
    }
}
