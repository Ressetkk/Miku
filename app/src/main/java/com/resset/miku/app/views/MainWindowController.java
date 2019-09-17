package com.resset.miku.app.views;

import com.resset.miku.app.downloader.DownloadClient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MainWindowController {
    @FXML
    private AnchorPane navbar;
    @FXML
    private Button preferencesButton;
    @FXML
    private AnchorPane content;

    private DownloadClient downloadClient;
    public MainWindowController() {

    }


    public void initialize() {

    }

    @FXML
    private void searchOnClick() {
        ViewUtils.setView(content, getClass().getResource("SearchView.fxml"));
    }

    @FXML
    private void downloadsOnClick() {
        ViewUtils.setView(content, getClass().getResource("DownloaderView.fxml"));

    }

    @FXML
    private void preferencesOnClick() {
        ViewUtils.setView(content, getClass().getResource("SettingsView.fxml"));
    }
}
