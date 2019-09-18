package com.resset.miku.app.api;

public interface User {
    User getInstance();
    String getImageURL();
    String getId();
    String getName();
    void setImageURL(String url);
    void setId(String id);
    void setName(String name);
}
