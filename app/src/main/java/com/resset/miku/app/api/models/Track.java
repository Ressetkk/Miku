package com.resset.miku.app.api.models;

import java.util.List;
import java.util.Map;

public interface Track {
    public String getTitle();
    public Map<String, Object> getAlbum();
    public String getCoverURL();
    public List<Map<String, String>> getArtists();
}
