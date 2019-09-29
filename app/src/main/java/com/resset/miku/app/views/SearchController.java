package com.resset.miku.app.views;

import com.resset.miku.app.api.Session;
import com.resset.miku.app.api.models.Album;
import com.resset.miku.app.api.models.SearchResult;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ServiceLoader;

public class SearchController {
    @FXML
    private VBox searchContent;
    @FXML
    private Button searchButton;
    @FXML
    private TextField searchText;

    private ServiceLoader<Session> sessions;

    public SearchController() {
        this.sessions = ServiceLoader.load(Session.class);
    }
    public void initialize() {

    }

    @FXML
    private void search() {
        sessions.forEach(session -> {
            SearchResult result = session.search(searchText.getText(),"ARTISTS,ALBUMS,TRACKS,PLAYLISTS",0 ,25);
            HBox box = new HBox();
            ScrollPane albumPane = new ScrollPane(box);
            albumPane.setFitToWidth(true);
            albumPane.setMaxHeight(300);
            for (Album album : result.getAlbums()) {
                TextField name = new TextField();
                name.setText(album.getTitle());
                box.getChildren().add(name);
            }
            searchContent.getChildren().add(albumPane);
        });
    }
}
