package com.resset.miku.app.views;

import com.resset.miku.app.api.Session;
import com.resset.miku.app.api.models.SearchResult;
import com.resset.miku.app.views.components.*;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
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
            Platform.runLater(() -> {
                SearchResult result = session.search(searchText.getText(),"ARTISTS,ALBUMS,TRACKS,PLAYLISTS",0 ,25);
//                ScrollPane albumPane = generateTiles(result.getAlbums(), AlbumTile.class);
//                ScrollPane artistPane = generateTiles(result.getArtists(), ArtistTile.class);
//                ScrollPane playlistPane = generateTiles(result.getPlaylists(), PlaylistTile.class);
                searchContent.getChildren().addAll(generateTiles(result.getAlbums(), AlbumTile.class),
                        generateTiles(result.getArtists(), ArtistTile.class),
                        generateTiles(result.getPlaylists(), PlaylistTile.class));
            });
        });
    }

    // TODO this method is pointless when I want to use it in more places than Search.
    //  Figure out FXML for generic Search result views.
    private <ItemType> Parent generateTiles(List<? extends ItemType> items, Class<? extends Tile> clazz) {
        ResultScrollPane controller = new ResultScrollPane();
        Parent parent;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("components/ResultScrollPane.fxml"));
            loader.setController(controller);
            parent = loader.load();
            for (ItemType item : items) {
                try {
                    Tile tile = clazz.getDeclaredConstructor().newInstance();
                    tile.setItem(item);
                    tile.load();
                    controller.add(tile);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
            return parent;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
