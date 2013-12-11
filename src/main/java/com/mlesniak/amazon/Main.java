package com.mlesniak.amazon;

import com.mlesniak.Utils;
import org.apache.commons.codec.DecoderException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws IOException, InvalidKeyException, NoSuchAlgorithmException, DecoderException {
        AmazonRequest request = AmazonRequestBuilder.init()
                .addKeywords("Scala")
                .addSearchIndex(SearchIndex.Books)
                .addResponseGroup(ResponseGroup.Medium, ResponseGroup.Reviews)
                .addMaximumPrice(10000)
                .addMinimumPrice(1000)
                .build();

//        while (request.hasNextPage()) {
//            String result = request.nextPage();
//            for (Map.Entry<String, String> entry : Filter.filterImages(result).entrySet()) {
//                System.out.println(entry.getKey() + "\t" + entry.getValue());
//            }
//        }

        String page = request.nextPage();
        System.out.println(Utils.prettyFormatXML(page, 4));
//
//        String decode = URLDecoder.decode("http://www.amazon.de/reviews/iframe?akid=AKIAIRCWYMWRAZWBRPRQ&amp;alinkCode=xm2&amp;asin=3867020108&amp;atag=michaellesnia-21&amp;exp=2013-12-12T05%3A07%3A55Z&amp;v=2&amp;sig=x4x6K0cY9b3x3zhhvKcUTXVibW%2BUCOrHmK%2FCLlPQjUs%3D", "UTF-8");
//        System.out.println(decode.replaceAll("&amp;", "&"));

    }
}
