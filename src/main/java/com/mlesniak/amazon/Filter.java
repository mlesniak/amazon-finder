package com.mlesniak.amazon;

import com.mlesniak.Utils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Map;

/**
 * Response filter, to be refactored.
 */
public class Filter {
    public static Map<String, String> filterImages(String xml) {
        Map<String, String> map = new HashMap<>();

        try {
            Document doc = Utils.toDocument(xml);
            NodeList items = doc.getElementsByTagName("Item");
            for (int i = 0; i < items.getLength(); i++) {
                Node node = items.item(i);
                String asin = node.getFirstChild().getTextContent();
                if (asin != null) {
                    String url = getMediumImage(node);
                    map.put(asin, url);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return map;
    }

    private static String getMediumImage(Node node) {
        Node mediumImage = node.getChildNodes().item(2);
        if (mediumImage == null) {
            return null;
        }
        return mediumImage.getFirstChild().getTextContent();
    }
}
