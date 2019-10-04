package com.resset.miku.app.views.components;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

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

    public void add(Tile tile) {
        this.container.getChildren().add(tile);
    }

    public void setLabel(String label) {
        this.resultName.setText(label);
    }

    @FXML
    private void scrollPrev() {
        double hvalue = scrollPane.getViewportBounds().getWidth() / scrollPane.getContent().getBoundsInLocal().getWidth();
        Animation animation = new Timeline(
                new KeyFrame(Duration.millis(300),
                        new KeyValue(scrollPane.hvalueProperty(), scrollPane.getHvalue() - hvalue))
        );
        animation.play();
    }

    @FXML
    private void scrollNext() {
        double hvalue = scrollPane.getViewportBounds().getWidth() / scrollPane.getContent().getBoundsInLocal().getWidth();
        Animation animation = new Timeline(
                new KeyFrame(Duration.millis(400),
                        new KeyValue(scrollPane.hvalueProperty(), scrollPane.getHvalue() + hvalue))
        );
        animation.play();
    }

    @FXML
    private void showButtons() {
        if (scrollPane.getHvalue() > 0)
            prevButton.setVisible(true);
        if (scrollPane.getHvalue() < 1)
            nextButton.setVisible(true);

    }
    @FXML
    private void hideButtons() {
        prevButton.setVisible(false);
        nextButton.setVisible(false);
    }
}
