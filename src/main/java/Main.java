import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, InvalidKeyException, NoSuchAlgorithmException {
        AmazonRequestBuilder builder = AmazonRequestBuilder.init()
                .addKeywords("Scala")
                .addSearchIndex(SearchIndex.Books)
                .addResponseGroup(ResponseGroup.Images)
                .addMaximumPrice(10000)
                .addMinimumPrice(1000);

        while (builder.hasNextPage()) {
            String result = builder.nextPage();
            for (Map.Entry<String, String> entry : Filter.filterImages(result).entrySet()) {
                System.out.println(entry.getKey() + "\t" + entry.getValue());
            }
        }
    }
}
