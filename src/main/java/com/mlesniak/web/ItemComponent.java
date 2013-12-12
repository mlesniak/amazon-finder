package com.mlesniak.web;

import com.mlesniak.amazon.Item;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;

public class ItemComponent extends Panel {
    private final Item item;

    public ItemComponent(String id, Item item) {
        super(id);
        this.item = item;

        Image image = new Image("image", "");
        image.add(new AttributeModifier("src", item.getImageUrl()));
        add(image);
        add(new Label("title", item.getTitle()));
    }
}
