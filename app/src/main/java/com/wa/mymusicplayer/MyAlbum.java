package com.wa.mymusicplayer;

import java.util.List;

public class MyAlbum {
    private String image;
    private List<String> names;

    public MyAlbum(String image, List<String> names) {
        this.image = image;
        this.names = names;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
