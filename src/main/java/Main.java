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

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "http://webservices.amazon.com/onca/xml?AWSAccessKeyId=AKIAIRCWYMWRAZWBRPRQ&AssociateTag=%20michaellesnia-21%20&Condition=All&Keywords=scala&Operation=ItemSearch&ResponseGroup=Images&SearchIndex=All&Service=AWSECommerceService&Timestamp=2013-12-09T05%3A27%3A27.000Z&Version=2011-08-01&Signature=uH7pLYmIvxf5MbT1nj2Or4ztZoMa7IvGmXZmEGRMMAc%3D";

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        HttpResponse response = client.execute(request);
        StringWriter writer = new StringWriter();
        IOUtils.copy(response.getEntity().getContent(), writer);
        String s = writer.toString();
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
            throw new RuntimeException(e); // simple exception handling, please review it
        }
    }
}
