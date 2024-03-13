
package initial;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "MySourceService", targetNamespace = "http://Initial/", wsdlLocation = "http://localhost:3006/anna?wsdl")
public class MySourceService
    extends Service
{

    private final static URL MYSOURCESERVICE_WSDL_LOCATION;
    private final static WebServiceException MYSOURCESERVICE_EXCEPTION;
    private final static QName MYSOURCESERVICE_QNAME = new QName("http://Initial/", "MySourceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:3006/anna?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        MYSOURCESERVICE_WSDL_LOCATION = url;
        MYSOURCESERVICE_EXCEPTION = e;
    }

    public MySourceService() {
        super(__getWsdlLocation(), MYSOURCESERVICE_QNAME);
    }

    public MySourceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), MYSOURCESERVICE_QNAME, features);
    }

    public MySourceService(URL wsdlLocation) {
        super(wsdlLocation, MYSOURCESERVICE_QNAME);
    }

    public MySourceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, MYSOURCESERVICE_QNAME, features);
    }

    public MySourceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MySourceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns MySource
     */
    @WebEndpoint(name = "MySourcePort")
    public MySource getMySourcePort() {
        return super.getPort(new QName("http://Initial/", "MySourcePort"), MySource.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns MySource
     */
    @WebEndpoint(name = "MySourcePort")
    public MySource getMySourcePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://Initial/", "MySourcePort"), MySource.class, features);
    }

    private static URL __getWsdlLocation() {
        if (MYSOURCESERVICE_EXCEPTION!= null) {
            throw MYSOURCESERVICE_EXCEPTION;
        }
        return MYSOURCESERVICE_WSDL_LOCATION;
    }

}
