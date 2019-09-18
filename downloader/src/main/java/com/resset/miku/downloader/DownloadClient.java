package com.resset.miku.downloader;

import java.util.ArrayList;
import java.util.List;

public class DownloadClient {
    private static DownloadClient client = null;

    private static List<DownloadEntry> downloadEntryList;

    public static DownloadClient getInstance() {
        if (client == null) {
            client = new DownloadClient();
        }
        return client;
    }

    private DownloadClient() {
        downloadEntryList = new ArrayList<>();
    }
    /* TODO
        make this one as generic as possible for plugin system
     */
    public static boolean createDownloadEntry() {
        try {
//            downloadEntryList.add();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
