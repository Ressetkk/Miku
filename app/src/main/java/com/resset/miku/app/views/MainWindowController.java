package com.resset.miku.app.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

    private static Parent searchView;
    private static Parent downloaderView;
    private static Parent settingsView;
    public MainWindowController() {

    }


    public void initialize() {
        this.toggleGroup = new ToggleGroup();
        this.toggleGroup.getToggles().addAll(searchButton, downloaderButton, preferencesButton);
        try {
            searchView = FXMLLoader.load(getClass().getResource("SearchView.fxml"));
            downloaderView = FXMLLoader.load(getClass().getResource("DownloaderView.fxml"));
            settingsView = FXMLLoader.load(getClass().getResource("SettingsView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void searchOnClick() {
        content.setCenter(searchView);
    }

    @FXML
    private void downloadsOnClick() {
        content.setCenter(downloaderView);
    }

    @FXML
    private void preferencesOnClick() {
        content.setCenter(settingsView);
    }
}
