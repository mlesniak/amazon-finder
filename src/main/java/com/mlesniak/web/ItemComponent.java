package com.mlesniak.web;

import com.mlesniak.amazon.AmazonItem;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;

public class ItemComponent extends Panel {
    private final AmazonItem amazonItem;

    public ItemComponent(String id, AmazonItem amazonItem) {
        super(id);
        this.amazonItem = amazonItem;

        add(new Label("title", amazonItem.getTitle()));
        add(new Label("price", amazonItem.getPrice()));

        Image image = new Image("image", "");
        image.add(new AttributeModifier("src", amazonItem.getImageUrl()));
        ExternalLink link = new ExternalLink("link", amazonItem.getUrl());
        link.add(image);
        add(link);
    }
}
