package com.mastercode.salvago.models;

import com.plumillonforge.android.chipview.Chip;

public class Chiptag implements Chip {

    private String name;

    public Chiptag(String name) {
        this.name = name;
    }

    @Override
    public String getText() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.getText().equals(((Chiptag)obj).getText())) ? true : false;
    }
}
