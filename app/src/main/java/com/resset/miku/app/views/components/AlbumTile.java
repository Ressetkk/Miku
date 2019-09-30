package com.resset.miku.app.views.components;

import com.resset.miku.app.api.models.Album;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class AlbumTile extends Tile<Album> {
    @FXML
    private Label titleLabel;
    @FXML
    private Label artistLabel;
    @FXML
    private ImageView coverView;
    @FXML
    private ProgressBar progressBar;
    public AlbumTile() {


    }
    public void initialize() {
        Image cover = new Image(this.getItem().getCover(), true);
        progressBar.progressProperty().bind(cover.progressProperty());
        cover.progressProperty().addListener((observableValue, number, t1) -> {
            if (t1.doubleValue() == 1) {
                progressBar.setVisible(false);
            }
        });
        coverView.setImage(cover);
        titleLabel.setText(this.getItem().getTitle());
        artistLabel.setText(getItem().getArtist().getName());
    }
    public void load() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AlbumTile.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void getAlbumView() {
        System.out.println(String.format("I'm a %s!", getItem().getTitle()));
    }
}
