import java.util.HashMap;
import java.util.Map;

public class AmazonRequestBuilder {
    private String operation = "ItemSearch";
    private String keywords;
    private SearchIndex searchIndex;
    private ResponseGroup responseGroup;
    private Integer maximumPrice;
    private Integer minimumPrice;
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

    public AmazonRequest build() {
        initParams();
        return new AmazonRequest(params);
    }

    private AmazonRequestBuilder initParams() {
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
}