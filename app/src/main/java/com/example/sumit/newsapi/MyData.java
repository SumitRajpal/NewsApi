package com.example.sumit.newsapi;

/**
 * Created by sumit on 12/6/2016.
 */

public class MyData {

    private String id;
    private String name,image_link;

    public MyData(String id, String name, String image_link) {
        this.id = id;
        this.name = name;
        this.image_link = image_link;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String description) {
        this.name = description;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }
}
