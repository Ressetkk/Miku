package com.resset.miku.app.views;

import com.resset.miku.app.api.Session;
import com.resset.miku.app.api.User;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class SessionController {

    @FXML
    private Label fullnameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private ImageView picture;

    private Session session;
    private User user;

    public SessionController(Session session) {
        this.session = session;
    }

    public void initialize() {
        // this method invokes with loader.load();
        Circle clip = new Circle(50);
        clip.setCenterX(picture.getFitWidth()/2);
        clip.setCenterY(picture.getFitHeight()/2);
        picture.setClip(clip);
        if (session.isLoggedIn()) {
            // THis works really bad. Needs rewrite.
            Platform.runLater(() -> {
                this.user = session.getUser();
                picture.setImage(new Image(user.getImageURL(),true));
                this.fullnameLabel.setText(user.getName());
                this.emailLabel.setText(user.getEmail());
            });
        }
        else {
            this.fullnameLabel.setText("Log In");
            this.emailLabel.setText("");
        }
    }
}
