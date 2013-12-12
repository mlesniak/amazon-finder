package com.mlesniak.web;

import com.mlesniak.amazon.Item;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.repeater.RepeatingView;

import java.util.List;

public class ResultPage extends WebPage {
    List<Item> items;

    public ResultPage(List<Item> items) {
        super();
        this.items = items;

        RepeatingView repeater = new RepeatingView("repeater");
        for (Item item : items) {
            String imgId = repeater.newChildId();
            Image image = new Image(imgId, "");
            image.add(new AttributeModifier("src", item.getImageUrl()));
            repeater.add(image);
        }
        add(repeater);
    }
}
