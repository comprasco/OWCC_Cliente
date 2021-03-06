
package https.www_supernotariado_gov_co.services.bachue.co.enviopoderes.v1;

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
 * JAX-WS RI 2.2.11-b150120.1832
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "BS_SUT_CO_EnvioPoderes", targetNamespace = "https://www.supernotariado.gov.co/services/bachue/co/enviopoderes/v1", wsdlLocation = "file:/C:/Users/consultor/Documents/NetbeansProjects82/ClienteOWCC/src/conf/xml-resources/web-services/BeanEnviarPoder/wsdl/BS_SUT_CO_EnvioPoderes.wsdl")
public class BSSUTCOEnvioPoderes_Service
    extends Service
{

    private final static URL BSSUTCOENVIOPODERES_WSDL_LOCATION;
    private final static WebServiceException BSSUTCOENVIOPODERES_EXCEPTION;
    private final static QName BSSUTCOENVIOPODERES_QNAME = new QName("https://www.supernotariado.gov.co/services/bachue/co/enviopoderes/v1", "BS_SUT_CO_EnvioPoderes");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/Users/consultor/Documents/NetbeansProjects82/ClienteOWCC/src/conf/xml-resources/web-services/BeanEnviarPoder/wsdl/BS_SUT_CO_EnvioPoderes.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        BSSUTCOENVIOPODERES_WSDL_LOCATION = url;
        BSSUTCOENVIOPODERES_EXCEPTION = e;
    }

    public BSSUTCOEnvioPoderes_Service() {
        super(__getWsdlLocation(), BSSUTCOENVIOPODERES_QNAME);
    }

    public BSSUTCOEnvioPoderes_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), BSSUTCOENVIOPODERES_QNAME, features);
    }

    public BSSUTCOEnvioPoderes_Service(URL wsdlLocation) {
        super(wsdlLocation, BSSUTCOENVIOPODERES_QNAME);
    }

    public BSSUTCOEnvioPoderes_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, BSSUTCOENVIOPODERES_QNAME, features);
    }

    public BSSUTCOEnvioPoderes_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public BSSUTCOEnvioPoderes_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns BSSUTCOEnvioPoderes
     */
    @WebEndpoint(name = "BS_SUT_CO_EnvioPoderesPort")
    public BSSUTCOEnvioPoderes getBSSUTCOEnvioPoderesPort() {
        return super.getPort(new QName("https://www.supernotariado.gov.co/services/bachue/co/enviopoderes/v1", "BS_SUT_CO_EnvioPoderesPort"), BSSUTCOEnvioPoderes.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns BSSUTCOEnvioPoderes
     */
    @WebEndpoint(name = "BS_SUT_CO_EnvioPoderesPort")
    public BSSUTCOEnvioPoderes getBSSUTCOEnvioPoderesPort(WebServiceFeature... features) {
        return super.getPort(new QName("https://www.supernotariado.gov.co/services/bachue/co/enviopoderes/v1", "BS_SUT_CO_EnvioPoderesPort"), BSSUTCOEnvioPoderes.class, features);
    }

    private static URL __getWsdlLocation() {
        if (BSSUTCOENVIOPODERES_EXCEPTION!= null) {
            throw BSSUTCOENVIOPODERES_EXCEPTION;
        }
        return BSSUTCOENVIOPODERES_WSDL_LOCATION;
    }

}
