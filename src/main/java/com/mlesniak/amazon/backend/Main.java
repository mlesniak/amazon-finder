package com.mlesniak.amazon.backend;

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

        List<AmazonItem> amazonItems = ItemConverter.convertFull(request);
        for (AmazonItem amazonItem : amazonItems) {
            System.out.println(amazonItem);
        }

    }
}
