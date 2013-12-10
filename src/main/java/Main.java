import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws IOException, InvalidKeyException, NoSuchAlgorithmException {
        String result = AmazonRequestBuilder.init()
                .addKeywords("Scala")
                .addSearchIndex(SearchIndex.Books)
                .addResponseGroup(ResponseGroup.Images)
                .addMaximumPrice(10000)
                .addMinimumPrice(1000)
                .execute();

        System.out.println(result);
        System.out.println(Utils.prettyFormatXML(result, 2));
        System.out.println(Filter.filterImages(result));
    }
}
