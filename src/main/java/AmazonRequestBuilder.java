import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class AmazonRequestBuilder {
    private String operation = "ItemSearch";
    private String keywords;
    private SearchIndex searchIndex;
    private ResponseGroup responseGroup;
    private Integer maximumPrice;
    private Integer minimumPrice;
    private boolean finished = false;
    private Map<String, String> params = new HashMap<>();

    private AmazonRequestBuilder() {
        // Empty.
    }

    public static AmazonRequestBuilder init() {
        return new AmazonRequestBuilder();
    }

    public AmazonRequestBuilder addOperation(String arg) {
        this.operation = arg;
        return this;
    }

    public AmazonRequestBuilder addKeywords(String arg) {
        this.keywords = arg;
        return this;
    }

    public AmazonRequestBuilder addSearchIndex(SearchIndex arg) {
        this.searchIndex = arg;
        return this;
    }

    public AmazonRequestBuilder addResponseGroup(ResponseGroup arg) {
        this.responseGroup = arg;
        return this;
    }

    public AmazonRequestBuilder addMaximumPrice(int arg) {
        this.maximumPrice = arg;
        return this;
    }

    public AmazonRequestBuilder addMinimumPrice(int arg) {
        this.minimumPrice = arg;
        return this;
    }

    private AmazonRequestBuilder build() {
        finished = true;

        if (operation != null) {
            params.put("Operation", operation);
        }
        if (keywords != null) {
            params.put("Keywords", keywords);
        }
        if (searchIndex != null) {
            params.put("SearchIndex", searchIndex.toString());
        }
        if (responseGroup != null) {
            params.put("ResponseGroup", responseGroup.toString());
        }
        if (minimumPrice != null) {
            params.put("MinimumPrice", minimumPrice.toString());
        }
        if (maximumPrice != null) {
            params.put("MaximumPrice", maximumPrice.toString());
        }

        params.put("Version", "2011-08-01");
        params.put("AssociateTag", "michaellesnia-21");

        return this;
    }

    public String execute() {
        if (!finished) {
            build();
        }

        String result = "";
        try {
            SignedRequestsHelper helper = SignedRequestsHelper.getInstance(
                    "webservices.amazon.de",
                    "AKIAIRCWYMWRAZWBRPRQ",
                    "c33JOwt4tXCVDBxfpxs7YuceXv0U4LurFKxi6zNa");
            String url = helper.sign(params);
            result = executeRequest(url);
        } catch (Exception e) {
            // TODO Logging.
        }

        return result;
    }

    private String executeRequest(String url) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);
        StringWriter writer = new StringWriter();
        IOUtils.copy(response.getEntity().getContent(), writer);
        return writer.toString();
    }
}
