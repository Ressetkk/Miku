package com.resset.miku.app.views.components;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ResultScrollPane {
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

    public void initialize() {
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

    public void add(Node tile) {
        this.container.getChildren().add(tile);
    }

    public void setLabel(String label) {
        this.resultName.setText(label);
    }

    @FXML
    private void scrollPrev() {
        var width = scrollPane.getWidth();
        var viepanewidth = scrollPane.getContent().getBoundsInLocal().getWidth();
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

    public static <ItemType> Parent generateTiles(List<? extends ItemType> items, Class<? extends Tile> clazz, String name) {
        ResultScrollPane controller = new ResultScrollPane();
        Parent parent;
        try {
            FXMLLoader loader = new FXMLLoader(ResultScrollPane.class.getResource("SearchResultTile.fxml"));
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
            controller.setLabel(name);
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
