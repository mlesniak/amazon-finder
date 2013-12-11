package com.mlesniak.amazon;

import com.mlesniak.Utils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class AmazonRequest {
    private Map<String, String> params = new HashMap<>();
    private int totalPages;
    private int currentPage = 1;
    private boolean totalPagesUpdated = false;

    // Package private.
    AmazonRequest(Map<String, String> params) {
        this.params = params;
    }

    private String execute() {
        String result = "";
        try {
            SignedRequestsHelper helper = SignedRequestsHelper.getInstance(
                    "webservices.amazon.de",
                    "AKIAIRCWYMWRAZWBRPRQ",
                    "c33JOwt4tXCVDBxfpxs7YuceXv0U4LurFKxi6zNa");
            String url = helper.sign(params);
            result = executeRequest(url);
            if (!totalPagesUpdated) {
                totalPagesUpdated = true;
                determineMaximumPages(result);
            }
        } catch (Exception e) {
            // TODO Logging.
        }

        return result;
    }

    private void determineMaximumPages(String xml) throws IOException, SAXException, ParserConfigurationException {
        Document doc = Utils.toDocument(xml);
        String pages = doc.getElementsByTagName("TotalPages").item(0).getTextContent();
        totalPages = Integer.parseInt(pages);
        currentPage = 1;
    }

    private String executeRequest(String url) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);
        StringWriter writer = new StringWriter();
        IOUtils.copy(response.getEntity().getContent(), writer);
        return writer.toString();
    }

    public String nextPage() {
        params.put("ItemPage", Integer.toString(currentPage));
        currentPage++;
        return execute();
    }

    public boolean hasNextPage() {
        // Amazon does only allow 10 pages in pagination.
        return currentPage <= 10;
    }
}
