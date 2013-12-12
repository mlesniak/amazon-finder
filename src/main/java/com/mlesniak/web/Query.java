package com.mlesniak.web;

import java.io.Serializable;

public class Query implements Serializable {
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "Query{" +
                "keyword='" + keyword + '\'' +
                '}';
    }
}
