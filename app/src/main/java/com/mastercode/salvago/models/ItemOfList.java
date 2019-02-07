package com.mastercode.salvago.models;

public class ItemOfList {

    public String text;
    public String header;
    public int icon;

    public ItemOfList() {
    }


    public ItemOfList(String header, String text, int icon) {
        this.text = text;
        this.header = header;
        this.icon = icon;
    }
}
