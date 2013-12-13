package com.mlesniak.web;

import com.mlesniak.amazon.*;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.Collections;
import java.util.List;

public class HomePage extends WebPage {
    public HomePage(final PageParameters parameters) {
        this(parameters, Collections.<AmazonItem>emptyList());
    }

    public HomePage(final PageParameters parameters, List<AmazonItem> items) {
        super(parameters);

        final Query query = new Query();
        query.setKeyword(parameters.get("keyword").toString(null));
        parameters.clearNamed();

        TextField<String> keyword = new TextField<>("keyword");
        Form<Query> form = new Form<Query>("form", new CompoundPropertyModel<Query>(query)) {
            @Override
            protected void onSubmit() {
                System.out.println(query);
                List<AmazonItem> amazonItems = performQuery(query);
                parameters.set("keyword", query.getKeyword());
                setResponsePage(new HomePage(parameters, amazonItems));
            }
        };

        add(form);
        form.add(keyword);

        // Display items, if we already have some.
        RepeatingView repeater = new RepeatingView("repeater");
        for (AmazonItem amazonItem : items) {
            repeater.add(new ItemComponent(repeater.newChildId(), amazonItem));
        }
        add(repeater);
    }

    public List<AmazonItem> performQuery(Query query) {
        AmazonRequest request = AmazonRequestBuilder.init()
                .addKeywords(query.getKeyword())
                .addSearchIndex(SearchIndex.Books)
                .addMaximumPrice(10000)
                .addMinimumPrice(1000)
                .build();

        List<AmazonItem> amazonItems = ItemConverter.convertFull(request);
        //        for (AmazonItem amazonItem : amazonItems) {
        //            System.out.println(amazonItem);
        //        }
        return amazonItems;
    }
}
