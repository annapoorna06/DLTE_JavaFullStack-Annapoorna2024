package soap.dao;

import javax.xml.ws.Endpoint;
import java.io.IOException;
import java.sql.SQLException;

public class EndPoint {
    private static String url="http://localhost:1234/Account";
    public static void main(String[] args) throws IOException, SQLException {
        SOAPService soapService=new SOAPService();
        System.out.println("Webservice hosted @ "+url);
        Endpoint.publish(url,soapService);
    }
}
