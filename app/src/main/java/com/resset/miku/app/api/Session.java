package com.resset.miku.app.api;

public interface Session {
     boolean isLoggedIn();
     Session login();
     Session login(String username, String password);
}
