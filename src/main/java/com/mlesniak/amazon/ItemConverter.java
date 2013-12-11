package com.mlesniak.amazon;

import com.mlesniak.Utils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;

public class ItemConverter {
    public static List<Item> convert(String xml) {
        List<Item> items = new LinkedList<>();

        try {
            Document doc = Utils.toDocument(xml);
            XPath xpath = XPathFactory.newInstance().newXPath();

            NodeList nodeList = (NodeList) xpath.compile("//Item").evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                items.add(transformItem(nodeList.item(i)));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return items;
    }

    private static Item transformItem(Node node) throws XPathExpressionException, UnsupportedEncodingException {
        XPath xpath = XPathFactory.newInstance().newXPath();

        String detailsURL = toURL(xpath.compile("./DetailPageURL").evaluate(node));
        String title = xpath.compile("./Title").evaluate(node);
        String imageURL = toURL(xpath.compile("./MediumImage/URL").evaluate(node));

        int price = 0;
        String strPrice = xpath.compile("./ItemAttributes/ListPrice/Amount").evaluate(node);
        if (!strPrice.isEmpty()) {
            price = Integer.parseInt(strPrice);
        }

        return new Item(title, price, detailsURL, imageURL);
    }

    private static String toURL(String url) throws UnsupportedEncodingException {
        return URLDecoder.decode(url, "UTF-8").replaceAll("&amp;", "&");
    }


}
