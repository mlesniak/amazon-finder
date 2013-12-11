package com.mlesniak.amazon;

import org.apache.commons.codec.DecoderException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InvalidKeyException, NoSuchAlgorithmException, DecoderException {
        AmazonRequest request = AmazonRequestBuilder.init()
                .addKeywords("Scala")
                .addSearchIndex(SearchIndex.Books)
                .addMaximumPrice(10000)
                .addMinimumPrice(1000)
                .build();

        List<Item> items = ItemConverter.convertFull(request);
        for (Item item : items) {
            System.out.println(item);
        }

    }
}
