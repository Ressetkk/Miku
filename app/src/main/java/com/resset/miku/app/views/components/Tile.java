package com.resset.miku.app.views.components;

import javafx.scene.layout.VBox;

public abstract class Tile<T> extends VBox {
    private T item;

    public void setItem(T item) {
        this.item = item;
    }
    public T getItem() {
        return item;
    }
    public abstract void load();
}
