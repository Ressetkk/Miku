package com.resset.miku.app.views.components;

import com.resset.miku.app.api.models.Album;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
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
    public AlbumTile() {


    }
    public void initialize() {
        coverView.setImage(new Image(this.getItem().getCover(), true));
        titleLabel.setText(this.getItem().getTitle());
//        artistLabel.setText(album.getArtist().toString());
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
}
