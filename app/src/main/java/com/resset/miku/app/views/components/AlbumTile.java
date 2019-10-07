package com.resset.miku.app.views.components;

import com.resset.miku.app.api.models.Album;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public final class AlbumTile extends Tile<Album> {
    @FXML
    private Label titleLabel;
    @FXML
    private Label artistLabel;
    @FXML
    private ImageView coverView;
    public AlbumTile() {


    }
    public void initialize() {
        Image cover = new Image(this.getItem().getCover(), true);
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
