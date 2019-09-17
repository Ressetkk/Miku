package com.resset.miku.app.views;

import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AlbumViewController extends AnchorPane implements Initializable {
    public Label albumLabel;
    public Label artistLabel;
    public ImageView albumArt;

    public AlbumViewController(String url) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AlbumView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Task<Image> loadImageAsync = new Task<Image>() {
            @Override
            protected Image call() throws Exception {
                return new Image("https://images.unsplash.com/photo-1511367461989-f85a21fda167?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb", 250, 250, true, false);
            }

            @Override
            protected void running() {
                super.running();
            }

            @Override
            protected void updateMessage(String message) {
                super.updateMessage(message);
            }

            @Override
            protected void succeeded() {
                super.succeeded();
                albumArt.setImage(getValue());
            }

            @Override
            protected void failed() {
                super.failed();
            }
        };
//        progressBar.progressProperty().bind(loadImageAsync.progressProperty());
        new Thread(loadImageAsync).start();
    }
}
