package com.example.android.emsense3.Activity;

/**
 * Created by slzh645 on 7/11/2017.
 */

//Custom class to create the cards layout shown in the library and objects page

public class Album {
    private String name;
    private int numOfItems;
    private int thumbnail;

    public Album() {
    }

    public Album(String name, int numOfItems, int thumbnail) {
        this.name = name;
        this.numOfItems = numOfItems;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumofItems() {
        return numOfItems;
    }

    public void setNumOfItems(int numOfItems) {
        this.numOfItems = numOfItems;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}

