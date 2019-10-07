package com.resset.miku.app.views.components;

import com.resset.miku.app.api.models.Track;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TrackTile extends AnchorPane {
    @FXML
    private Label trackName;
    @FXML
    private Label albumName;
    @FXML
    private Label artistName;
    @FXML
    private ImageView albumCover;

    private Track track;

    public TrackTile(Track track) {
        this.track = track;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/search/TrackTile.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        try {
            // TODO add proper Threading
            trackName.setText(track.getTitle());
            albumName.setText(track.getAlbum().get("title").toString());
            albumCover.setImage(new Image(track.getCoverURL(), true));
//            artistName.setText(track.getArtists().get("name").toString());
        }
        catch (NullPointerException e) {

        }
    }
}
