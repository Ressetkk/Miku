package com.resset.miku.app.views.components;

import com.resset.miku.app.api.models.Album;
import com.resset.miku.app.api.models.Artist;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class ArtistTile extends Tile<Artist> {
    @FXML
    private Label titleLabel;
    @FXML
    private ImageView coverView;
    public void initialize() {
        Circle clip = new Circle(coverView.getFitWidth()/2);
        clip.setCenterX(coverView.getFitWidth()/2);
        clip.setCenterY(coverView.getFitHeight()/2);
        coverView.setClip(clip);
        coverView.setImage(new Image(this.getItem().getPicture(), true));
        titleLabel.setText(this.getItem().getName());
//        artistLabel.setText(album.getArtist().toString());
    }
    public void load() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ArtistTile.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
