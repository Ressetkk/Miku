package com.resset.miku.app.api;

import com.resset.miku.app.api.models.SearchResult;
import com.resset.miku.app.api.models.SearchResultTypes;

public interface Session {
     boolean isLoggedIn();
     boolean login(String username, String password);
     boolean logout();
     String getProviderName();
     User getUser();
     SearchResult search(String query, Integer offset, Integer limit, SearchResultTypes... types);
}
