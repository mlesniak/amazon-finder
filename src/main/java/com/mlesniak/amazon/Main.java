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
                .addResponseGroup(ResponseGroup.Medium, ResponseGroup.Reviews)
                .addMaximumPrice(10000)
                .addMinimumPrice(1000)
                .build();

        String page = request.nextPage();
        //        System.out.println(Utils.prettyFormatXML(page, 4));
        List<Item> items = ItemConverter.convert(page);

        for (Item item : items) {
            System.out.println(item);
        }

    }
}
