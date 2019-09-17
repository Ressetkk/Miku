package com.resset.miku.app.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;

public class ViewUtils {
    public static <T extends Pane> boolean setView(T content, URL location) {
        try {
            Parent view = FXMLLoader.load(location);
            content.getChildren().clear();
            AnchorPane.setRightAnchor(view,0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);
            AnchorPane.setTopAnchor(view, 0.0);
            content.getChildren().setAll(view);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
