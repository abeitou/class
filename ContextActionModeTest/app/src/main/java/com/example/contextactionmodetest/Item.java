package com.example.contextactionmodetest;

/**
 * Created by 振丿Love on 2018/4/2.
 */

public class Item {
    private String Name;
    private int ImageId;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public Item(String name, int imageId) {
        Name = name;
        ImageId = imageId;
    }
}
