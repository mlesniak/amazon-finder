import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, InvalidKeyException, NoSuchAlgorithmException {
        SignedRequestsHelper helper = SignedRequestsHelper.getInstance(
                "webservices.amazon.com",
                "AKIAIRCWYMWRAZWBRPRQ",
                "c33JOwt4tXCVDBxfpxs7YuceXv0U4LurFKxi6zNa");

        Map<String, String> query = new HashMap<>();
        query.put("Operation", "ItemSearch");
        query.put("Keywords", "Scala");
        query.put("SearchIndex", "Books");
        query.put("ResponseGroup", "BrowseNodes");
        query.put("Version", "2011-08-01");
        query.put("AssociateTag", "michaellesnia-21");

        String url = helper.sign(query);
        System.out.println(url);

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        HttpResponse response = client.execute(request);
        StringWriter writer = new StringWriter();
        IOUtils.copy(response.getEntity().getContent(), writer);
        String s = writer.toString();
        System.out.println(s);
        System.out.println(prettyFormat(s, 2));
    }

    // http://stackoverflow.com/questions/139076/how-to-pretty-print-xml-from-java/1264912#1264912
    public static String prettyFormat(String input, int indent) {
        try {
            Source xmlInput = new StreamSource(new StringReader(input));
            StringWriter stringWriter = new StringWriter();
            StreamResult xmlOutput = new StreamResult(stringWriter);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", indent);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString();
        } catch (Exception e) {
            //throw new RuntimeException(e); // simple exception handling, please review it
            return "<exception name=\"" + e.getMessage() + "\"/>";
        }
    }


}
