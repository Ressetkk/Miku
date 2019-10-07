package com.resset.miku.app.views.components;

import com.resset.miku.app.api.Session;
import com.resset.miku.app.api.models.Album;
import com.resset.miku.app.api.models.SearchResult;
import com.resset.miku.app.api.models.SearchResultTypes;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class SearchResultTile {
    @FXML
    private HBox container;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button prevButton;
    @FXML
    private Button nextButton;
    @FXML
    private Label resultName;

    private String query;
    private String provider;
    private SearchResultTypes type;
    private Session session;
    private List<Node> tiles;
    private BorderPane parentPane;
    private SearchResultTile(String query, BorderPane parentPane, String provider, SearchResultTypes type, Session session) {
        this.query = query;
        this.parentPane = parentPane;
        this.provider = provider;
        this.type = type;
        this.session = session;
        this.tiles = new ArrayList<>();
    }

    public void initialize() {
        resultName.setText(String.format("%s from %s", type.name(), provider));
        scrollPane.hvalueProperty().addListener((observableValue, number, t1) -> {
            if (t1.doubleValue() == 0.0) {
                prevButton.setVisible(false);
            } else {
                prevButton.setVisible(true);
            }
            if (t1.doubleValue() == 1.0) {
                nextButton.setVisible(false);
            } else {
                nextButton.setVisible(true);
            }
        });
    }

    private void add(Node tile) {
        this.tiles.add(tile);
        this.container.getChildren().add(tile);
    }

    @FXML
    private void scrollPrev() {
        double hvalue = scrollPane.getWidth() / scrollPane.getContent().getBoundsInLocal().getWidth();
        Animation animation = new Timeline(
                new KeyFrame(Duration.millis(300),
                        new KeyValue(scrollPane.hvalueProperty(), scrollPane.getHvalue() - hvalue))
        );
        animation.play();
    }

    @FXML
    private void scrollNext() {
        double hvalue = scrollPane.getWidth() / scrollPane.getContent().getBoundsInLocal().getWidth();
        Animation animation = new Timeline(
                new KeyFrame(Duration.millis(300),
                        new KeyValue(scrollPane.hvalueProperty(), scrollPane.getHvalue() + hvalue))
        );
        animation.play();
    }

    @FXML
    private void showButtons() {
        if ((scrollPane.getHvalue() > 0) && (scrollPane.getContent().getBoundsInLocal().getWidth() > scrollPane.getWidth()))
            prevButton.setVisible(true);
        if (scrollPane.getHvalue() < 1)
            nextButton.setVisible(true);

    }
    @FXML
    private void hideButtons() {
        prevButton.setVisible(false);
        nextButton.setVisible(false);
    }

    @FXML
    private void showAllResults() {
        // TODO program ListView to work with tiles
    }

    public static <ItemType> Parent generateTiles(BorderPane parentPane, List<? extends ItemType> items, Class<? extends Tile> clazz,
                                                  String query, String provider, SearchResultTypes type, Session session) {
        SearchResultTile controller = new SearchResultTile(query, parentPane, provider, type, session);
        Parent parent;
        try {
            FXMLLoader loader = new FXMLLoader(SearchResultTile.class.getResource("SearchResultTile.fxml"));
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
            if (items.isEmpty()) {
                Label noResultsLabel = new Label("No results");
                noResultsLabel.setStyle("-fx-text-fill: white;");
                controller.add(noResultsLabel);
            }
            return parent;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
