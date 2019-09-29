package com.resset.miku.app.views;

import com.resset.miku.app.api.Session;
import com.resset.miku.app.api.User;
import com.resset.miku.app.views.ViewUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ServiceLoader;

public class SettingsController {

    @FXML
    private ToggleButton generalButton;
    @FXML
    private VBox sessionsMenu;
    @FXML
    private BorderPane content;

    public SettingsController() {

    }
    public void initialize() {
        ToggleGroup group = new ToggleGroup();
        ServiceLoader<Session> sessionLoader = ServiceLoader.load(Session.class);
        sessionLoader.forEach(session -> {
            ToggleButton sessionButton = new ToggleButton(session.getProviderName());
            group.getToggles().add(sessionButton);
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
            sessionsMenu.getChildren().add(sessionButton);
        });
    }

    @FXML
    private void loadGeneralMenu() {

    }

    @FXML
    private void loadAdvancedMenu() {

    }

    @FXML
    private void loadAboutMenu() {

    }
}
