package com.mlesniak.amazon.backend;

import java.io.Serializable;

public class AmazonItem implements Serializable {
    private String asin;
    private String title;
    private int price;
    private String URL;
    private String imageURL;

    public AmazonItem(String asin, String title, int price, String URL, String imageURL) {
        this.asin = asin;
        this.title = title;
        this.price = price;
        this.URL = URL;
        this.imageURL = imageURL;
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

    public String getURL() {
        return URL;
    }

    public String getImageURL() {
        return imageURL;
    }

    @Override
    public String toString() {
        return "AmazonItem{" +
                "asin='" + asin + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", URL='" + URL + '\'' +
                ", imageURL='" + imageURL + '\'' +
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
