package com.resset.miku.app.views;

import com.resset.miku.app.api.Session;
import com.resset.miku.app.api.models.SearchResult;
import com.resset.miku.app.api.models.SearchResultTypes;
import com.resset.miku.app.views.components.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class SearchController {
    @FXML
    private BorderPane searchContent;
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
        VBox content = new VBox();
        ScrollPane pane = new ScrollPane(content);
        ListView<Parent> listView = new ListView<>();
        List<Parent> parentList = new ArrayList<>();
        pane.setFitToWidth(true);
        sessions.forEach(session -> {
            Platform.runLater(() -> {
                SearchResult result = session.search(searchText.getText(),0 ,25,
                        SearchResultTypes.ALBUMS, SearchResultTypes.ARTISTS, SearchResultTypes.PLAYLISTS, SearchResultTypes.TRACKS);
//                content.getChildren().addAll(
//                        SearchResultTile.generateTiles(searchContent, result.getAlbums(), AlbumTile.class,
//                                searchText.getText(), session.getProviderName(), SearchResultTypes.ALBUMS, session),
//                        SearchResultTile.generateTiles(searchContent, result.getArtists(), ArtistTile.class,
//                                searchText.getText(), session.getProviderName(), SearchResultTypes.ARTISTS, session),
//                        SearchResultTile.generateTiles(searchContent, result.getPlaylists(), PlaylistTile.class,
//                                searchText.getText(), session.getProviderName(), SearchResultTypes.PLAYLISTS, session));

                parentList.add(SearchResultTile.generateTiles(searchContent, result.getAlbums(), AlbumTile.class,
                                searchText.getText(), session.getProviderName(), SearchResultTypes.ALBUMS, session));

                parentList.add(SearchResultTile.generateTiles(searchContent, result.getArtists(), ArtistTile.class,
                                searchText.getText(), session.getProviderName(), SearchResultTypes.ARTISTS, session));

                parentList.add(SearchResultTile.generateTiles(searchContent, result.getPlaylists(), PlaylistTile.class,
                                searchText.getText(), session.getProviderName(), SearchResultTypes.PLAYLISTS, session));
                listView.getItems().addAll(parentList);
                searchContent.setCenter(listView);
            });
//            searchContent.setCenter(listView);
        });
    }
}
