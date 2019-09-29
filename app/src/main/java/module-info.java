module com.resset.miku.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome;
    requires org.kordamp.ikonli.fontawesome5;

    opens com.resset.miku.app to javafx.fxml, javafx.graphics;
    opens com.resset.miku.app.views to javafx.fxml;
    opens com.resset.miku.app.views.components to javafx.fxml;
    exports com.resset.miku.app.api;
    exports com.resset.miku.app.api.models;
    uses com.resset.miku.app.api.Session;
}