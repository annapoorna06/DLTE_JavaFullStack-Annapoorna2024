package rest.client;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class httpTest {
    public static void printResponse(HttpResponse response) throws IOException {
        System.out.println(response.getProtocolVersion());
        System.out.println(response.getStatusLine().getStatusCode());
        System.out.println(response.getStatusLine().getReasonPhrase());
        System.out.println(response.getStatusLine().toString());
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://localhost:7001/824TransactionModule/transaction/?Max=500000&Min=65");
        CloseableHttpResponse response = httpclient.execute(httpget);

        try {
            printResponse(response);
        } finally {
            response.close();
        }
    }
}
