package com.resset.miku.app.views;

import com.resset.miku.app.api.Session;
import com.resset.miku.app.api.models.SearchResult;
import com.resset.miku.app.views.components.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
        searchContent.getChildren().clear();
        sessions.forEach(session -> {
            Platform.runLater(() -> {
                SearchResult result = session.search(searchText.getText(),"ARTISTS,ALBUMS,TRACKS,PLAYLISTS",0 ,25);
                searchContent.getChildren().addAll(
                        ResultScrollPane.generateTiles(result.getAlbums(), AlbumTile.class, "Albums from " + session.getProviderName()),
                        ResultScrollPane.generateTiles(result.getArtists(), ArtistTile.class, "Artists from " + session.getProviderName()),
                        ResultScrollPane.generateTiles(result.getPlaylists(), PlaylistTile.class, "Playlists from " + session.getProviderName()));
            });
        });
    }
}
