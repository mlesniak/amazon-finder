package com.mlesniak.web;

import com.mlesniak.amazon.AmazonItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.repeater.RepeatingView;

import java.util.List;

public class ResultPage extends WebPage {
    List<AmazonItem> amazonItems;

    public ResultPage(List<AmazonItem> amazonItems) {
        super();
        this.amazonItems = amazonItems;

        RepeatingView repeater = new RepeatingView("repeater");
        for (AmazonItem amazonItem : amazonItems) {
            repeater.add(new ItemComponent(repeater.newChildId(), amazonItem));
        }
        add(repeater);
    }
}
