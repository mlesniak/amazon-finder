package com.mlesniak.amazon.backend;

import com.mlesniak.Utils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;

public class AmazonItemConverter {
    public static List<AmazonItem> convertFull(AmazonRequest request) {
        List<AmazonItem> amazonItems = new LinkedList<>();

        while (request.hasNextPage()) {
            String xml = request.nextPage();
            System.out.println(Utils.prettyFormatXML(xml, 4));
            amazonItems.addAll(convert(xml));
        }

        return amazonItems;
    }

    public static List<AmazonItem> convert(String xml) {
        List<AmazonItem> amazonItems = new LinkedList<>();

        try {
            Document doc = Utils.toDocument(xml);
            XPath xpath = XPathFactory.newInstance().newXPath();

            NodeList nodeList = (NodeList) xpath.compile("//Item").evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                AmazonItem amazonItem = transformItem(nodeList.item(i));
                if (amazonItem != null) {
                    amazonItems.add(amazonItem);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return amazonItems;
    }

    private static AmazonItem transformItem(Node node) throws XPathExpressionException, IOException {
        XPath xpath = XPathFactory.newInstance().newXPath();

        String asin = xpath.compile("./ASIN").evaluate(node);
        String detailsURL = toURL(xpath.compile("./DetailPageURL").evaluate(node));
        String title = xpath.compile("./ItemAttributes/Title").evaluate(node);
        String reviewURL = toURL(xpath.compile("./CustomerReviews/IFrameURL").evaluate(node));
        boolean hasReviews = Boolean.parseBoolean(xpath.compile("./CustomerReviews/HasReviews").evaluate(node));
        String imageURL = toURL(xpath.compile("./MediumImage/URL").evaluate(node));

        if (!hasReviews) {
            reviewURL = null;
        }

        int price = 0;
        String strPrice = xpath.compile("./ItemAttributes/ListPrice/Amount").evaluate(node);
        if (!strPrice.isEmpty()) {
            price = Integer.parseInt(strPrice);
        } else {
            // We ignore items without a list price.
            return null;
        }

        return new AmazonItem(asin, title, price, detailsURL, imageURL, reviewURL);
    }

    private static String toURL(String url) throws UnsupportedEncodingException {
        return URLDecoder.decode(url, "UTF-8").replaceAll("&amp;", "&");
    }


}
