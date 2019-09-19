package com.resset.miku.app.api;

import javafx.scene.layout.AnchorPane;

public interface Session {
     boolean isLoggedIn();
     boolean login(String username, String password);
     boolean logout();
     String getProviderName();
     User getUser();
}
