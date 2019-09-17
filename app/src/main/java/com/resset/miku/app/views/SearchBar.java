package com.resset.miku.app.views;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class SearchBar extends HBox {

    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchButton;

    public SearchBar() {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/search/SearchBar.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initialize() {
        searchTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    searchButton.setStyle("-fx-border-color: -fx-focus-color;");
                    searchButton.getGraphic().setStyle("-fx-text-fill: -fx-focus-color;");
                }
                else {
                    searchButton.setStyle("-fx-border-color: white;");
                    searchButton.getGraphic().setStyle("-fx-text-fill: white;");
                }
            }
        });
        searchTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {

                }
            }
        });
    }

}
