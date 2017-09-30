package com.example.android.todo;

/**
 * Created by tonynguyen on 9/29/17.
 */

public class Item {

    private String title;

    public Item() {}

    public Item(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}