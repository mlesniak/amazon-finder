package com.mlesniak.amazon.web;

import com.mlesniak.amazon.backend.SearchIndex;

import java.io.Serializable;

public class Query implements Serializable {
    private SearchIndex searchIndex;

    private String keyword;
    private String minPrice;
    private String maxPrice;
    public SearchIndex getSearchIndex() {
        return searchIndex;
    }

    public void setSearchIndex(SearchIndex searchIndex) {
        this.searchIndex = searchIndex;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "Query{" +
                "searchIndex=" + searchIndex +
                ", keyword='" + keyword + '\'' +
                ", minPrice='" + minPrice + '\'' +
                ", maxPrice='" + maxPrice + '\'' +
                '}';
    }

}
