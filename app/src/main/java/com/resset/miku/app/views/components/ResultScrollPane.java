package com.resset.miku.app.views.components;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    public void add(Tile tile) {
        this.container.getChildren().add(tile);
    }

    @FXML
    private void scrollPrev() {
        this.scrollPane.setHvalue(this.scrollPane.getHvalue() - 0.1);
    }

    @FXML
    private void scrollNext() {
        this.scrollPane.setHvalue(this.scrollPane.getHvalue() + 0.1);
    }

    @FXML
    private void showButtons() {
        prevButton.setOpacity(1.0);
        nextButton.setOpacity(1.0);
    }
    @FXML
    private void hideButtons() {
        prevButton.setOpacity(0.0);
        nextButton.setOpacity(0.0);
    }
}
