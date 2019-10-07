package com.resset.miku.app.views.components;

import com.resset.miku.app.api.models.Playlist;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public final class PlaylistTile extends Tile<Playlist> {
    @FXML
    private Label titleLabel;
    @FXML
    private ImageView coverView;
    public PlaylistTile() {


    }
    public void initialize() {
        coverView.setImage(new Image(this.getItem().getSquareImage(), true));
        titleLabel.setText(this.getItem().getTitle());
    }
    public void load() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PlaylistTile.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
