package com.mlesniak.amazon.backend;

import java.io.Serializable;

public class AmazonItem implements Serializable {
    private String asin;
    private String title;
    private int price;
    private String URL;
    private String imageURL;

    private String reviewURL;

    public AmazonItem(String asin, String title, int price, String URL, String imageURL, String reviewURL) {
        this.asin = asin;
        this.title = title;
        this.price = price;
        this.URL = URL;
        this.imageURL = imageURL;
        this.reviewURL = reviewURL;
    }

    public String getReviewURL() {
        return reviewURL;
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

    @Override
    public String toString() {
        return "AmazonItem{" +
                "\n  asin='" + asin + '\'' +
                "\n, title='" + title + '\'' +
                "\n, price=" + price +
                "\n, URL=" + URL +
                "\n, imageURL=" + imageURL +
                "\n, reviewURL=" + reviewURL +
                "\n}";
    }
}
