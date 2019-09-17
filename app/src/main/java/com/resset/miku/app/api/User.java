package com.resset.miku.app.api;

import com.resset.miku.app.api.session.Session;

public interface User {
    User getInstance();
    String getImageURL();
    String getId();
    String getName();
    void setImageURL(String url);
    void setId(String id);
    void setName(String name);
}
