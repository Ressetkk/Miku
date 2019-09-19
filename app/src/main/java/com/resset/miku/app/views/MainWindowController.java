package com.resset.miku.app.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainWindowController {
    @FXML
    private AnchorPane navbar;
    @FXML
    private ToggleButton searchButton;
    @FXML
    private ToggleButton downloaderButton;
    @FXML
    private ToggleButton preferencesButton;
    @FXML
    private BorderPane content;

    private ToggleGroup toggleGroup;
    public MainWindowController() {

    }


    public void initialize() {
        this.toggleGroup = new ToggleGroup();
        this.toggleGroup.getToggles().addAll(searchButton, downloaderButton, preferencesButton);
    }

    @FXML
    private void searchOnClick() {
        try {
            content.setCenter(FXMLLoader.load(getClass().getResource("SearchView.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void downloadsOnClick() {
        try {
            content.setCenter(FXMLLoader.load(getClass().getResource("DownloaderView.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void preferencesOnClick() {
        try {
            content.setCenter(FXMLLoader.load(getClass().getResource("SettingsView.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void sessionsOnClick() {
        try {
            content.setCenter(FXMLLoader.load(getClass().getResource("UserPageView.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
