package com.mlesniak.web;

import com.mlesniak.amazon.Item;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.repeater.RepeatingView;

import java.util.List;

public class ResultPage extends WebPage {
    List<Item> items;

    public ResultPage(List<Item> items) {
        super();
        this.items = items;

        RepeatingView repeater = new RepeatingView("repeater");
        for (Item item : items) {
            repeater.add(new ItemComponent(repeater.newChildId(), item));
        }
        add(repeater);
    }
}
