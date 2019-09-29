package com.resset.miku.app.api;

import com.resset.miku.app.api.models.SearchResult;

public interface Session {
     boolean isLoggedIn();
     boolean login(String username, String password);
     boolean logout();
     String getProviderName();
     User getUser();
     SearchResult search(String query, String types, Integer offset, Integer limit);
}
