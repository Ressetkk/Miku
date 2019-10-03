package com.resset.miku.app.views.components;

import com.resset.miku.app.api.models.Album;
import com.resset.miku.app.api.models.Artist;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
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
        Circle clip = new Circle(this.coverView.getFitWidth()/2);
        clip.setCenterX(this.coverView.getFitWidth()/2);
        clip.setCenterY(this.coverView.getFitHeight()/2);
        this.coverView.setClip(clip);
        Image image = new Image(this.getItem().getPicture(), true);

        this.coverView.setImage(image);
        this.titleLabel.setText(this.getItem().getName());
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
