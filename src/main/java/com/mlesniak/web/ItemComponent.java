package com.mlesniak.web;

import com.mlesniak.amazon.AmazonItem;
import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.resource.PackageResourceReference;

public class ItemComponent extends Panel {
    private final AmazonItem amazonItem;

    public ItemComponent(String id, AmazonItem amazonItem) {
        super(id);
        this.amazonItem = amazonItem;

        add(new Label("title", amazonItem.getTitle()));
        add(new Label("price", amazonItem.getPrice()));

        Image image = new Image("image", "");
        if (StringUtils.isEmpty(amazonItem.getImageUrl())) {
            PackageResourceReference defaultReference = new PackageResourceReference(getClass(), "default.png");
            image = new Image("image", defaultReference);
        } else {
            image.add(new AttributeModifier("src", amazonItem.getImageUrl()));
        }

        ExternalLink link = new ExternalLink("link", amazonItem.getUrl());
        link.add(image);
        add(link);
    }
}
