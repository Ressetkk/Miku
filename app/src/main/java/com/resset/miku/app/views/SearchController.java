package com.resset.miku.app.views;

import com.resset.miku.app.api.Session;
import com.resset.miku.app.api.models.SearchResult;
import com.resset.miku.app.views.components.AlbumTile;
import com.resset.miku.app.views.components.ArtistTile;
import com.resset.miku.app.views.components.PlaylistTile;
import com.resset.miku.app.views.components.Tile;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
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
            Task<SearchResult> getResultTask = new Task<SearchResult>() {
                @Override
                protected SearchResult call() throws Exception {
                    return session.search(searchText.getText(),"ARTISTS,ALBUMS,TRACKS,PLAYLISTS",0 ,25);
                }
            };
            getResultTask.setOnSucceeded(evt -> {
                SearchResult result = getResultTask.getValue();
                ScrollPane albumPane = generateTiles(result.getAlbums(), AlbumTile.class);
                ScrollPane artistPane = generateTiles(result.getArtists(), ArtistTile.class);
                ScrollPane playlistPane = generateTiles(result.getPlaylists(), PlaylistTile.class);

                searchContent.getChildren().addAll(artistPane, albumPane, playlistPane);
            });
            new Thread(getResultTask).start();
        });
    }

    // TODO this method is pointless when I want to use it in more places than Search.
    //  Figure out FXML for generic Search result views.
    private <ItemType> ScrollPane generateTiles(List<? extends ItemType> items, Class<? extends Tile> clazz) {
        HBox box = new HBox();
        ScrollPane pane = new ScrollPane(box);
        pane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        pane.setFitToHeight(true);
        for (ItemType item : items) {
            try {
                Tile tile = clazz.getDeclaredConstructor().newInstance();
                tile.setItem(item);
                tile.load();
                box.getChildren().add(tile);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return pane;
    }
}
