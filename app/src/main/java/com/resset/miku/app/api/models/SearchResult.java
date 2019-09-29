package com.resset.miku.app.api.models;

import java.util.List;

public interface SearchResult {
    List<? extends Album> getAlbums();
    List<? extends Artist> getArtists();
    List<? extends Playlist> getPlaylists();
    List<? extends Track> getTracks();
}
