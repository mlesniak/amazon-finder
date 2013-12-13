package com.mlesniak.amazon;

import java.io.Serializable;

public class Item implements Serializable {
    private String title;
    private int price;
    private String url;
    private String imageUrl;

    public Item(String title, int price, String url, String imageUrl) {
        this.title = title;
        this.price = price;
        this.url = url;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", url='" + url + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
