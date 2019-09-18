package com.resset.miku.app.views;

import com.resset.miku.app.api.Session;
import com.resset.miku.app.api.User;
import com.resset.miku.app.views.ViewUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.util.ServiceLoader;

public class SettingsController {

    @FXML
    private Button generalButton;
    @FXML
    private AnchorPane content;

    public SettingsController() {

    }
    public void initialize() {

    }

    @FXML
    private void loadGeneralMenu() {
        ServiceLoader<Session> loader = ServiceLoader.load(Session.class);
        loader.forEach(session -> System.out.printf("Session: %s\n", session.isLoggedIn()));
//        ViewUtils.setView(content, getClass().getResource("GeneralSettingsView.fxml"));
    }

    @FXML
    private void loadAdvancedMenu() {

    }

    @FXML
    private void loadAboutMenu() {

    }
}
