package com.mlesniak.amazon.backend;

import java.io.Serializable;

public class AmazonItem implements Serializable {
    private String asin;
    private String title;
    private int price;
    private String url;
    private String imageUrl;

    public AmazonItem(String asin, String title, int price, String url, String imageUrl) {
        this.asin = asin;
        this.title = title;
        this.price = price;
        this.url = url;
        this.imageUrl = imageUrl;
    }

    public String getAsin() {
        return asin;
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
        return "AmazonItem{" +
                "asin='" + asin + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", url='" + url + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AmazonItem that = (AmazonItem) o;

        if (!asin.equals(that.asin)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return asin.hashCode();
    }
}
