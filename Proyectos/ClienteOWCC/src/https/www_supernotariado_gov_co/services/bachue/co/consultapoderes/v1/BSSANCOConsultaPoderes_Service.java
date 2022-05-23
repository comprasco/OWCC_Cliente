
package https.www_supernotariado_gov_co.services.bachue.co.consultapoderes.v1;

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
@WebServiceClient(name = "BS_SAN_CO_ConsultaPoderes", targetNamespace = "https://www.supernotariado.gov.co/services/bachue/co/consultapoderes/v1", wsdlLocation = "file:/C:/Users/consultor/Documents/NetbeansProjects82/ClienteOWCC/src/conf/xml-resources/web-services/BeanConsultaPoder/wsdl/BS_SAN_CO_ConsultaPoderes.wsdl")
public class BSSANCOConsultaPoderes_Service
    extends Service
{

    private final static URL BSSANCOCONSULTAPODERES_WSDL_LOCATION;
    private final static WebServiceException BSSANCOCONSULTAPODERES_EXCEPTION;
    private final static QName BSSANCOCONSULTAPODERES_QNAME = new QName("https://www.supernotariado.gov.co/services/bachue/co/consultapoderes/v1", "BS_SAN_CO_ConsultaPoderes");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/Users/consultor/Documents/NetbeansProjects82/ClienteOWCC/src/conf/xml-resources/web-services/BeanConsultaPoder/wsdl/BS_SAN_CO_ConsultaPoderes.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        BSSANCOCONSULTAPODERES_WSDL_LOCATION = url;
        BSSANCOCONSULTAPODERES_EXCEPTION = e;
    }

    public BSSANCOConsultaPoderes_Service() {
        super(__getWsdlLocation(), BSSANCOCONSULTAPODERES_QNAME);
    }

    public BSSANCOConsultaPoderes_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), BSSANCOCONSULTAPODERES_QNAME, features);
    }

    public BSSANCOConsultaPoderes_Service(URL wsdlLocation) {
        super(wsdlLocation, BSSANCOCONSULTAPODERES_QNAME);
    }

    public BSSANCOConsultaPoderes_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, BSSANCOCONSULTAPODERES_QNAME, features);
    }

    public BSSANCOConsultaPoderes_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public BSSANCOConsultaPoderes_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns BSSANCOConsultaPoderes
     */
    @WebEndpoint(name = "BS_SAN_CO_ConsultaPoderesPort")
    public BSSANCOConsultaPoderes getBSSANCOConsultaPoderesPort() {
        return super.getPort(new QName("https://www.supernotariado.gov.co/services/bachue/co/consultapoderes/v1", "BS_SAN_CO_ConsultaPoderesPort"), BSSANCOConsultaPoderes.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns BSSANCOConsultaPoderes
     */
    @WebEndpoint(name = "BS_SAN_CO_ConsultaPoderesPort")
    public BSSANCOConsultaPoderes getBSSANCOConsultaPoderesPort(WebServiceFeature... features) {
        return super.getPort(new QName("https://www.supernotariado.gov.co/services/bachue/co/consultapoderes/v1", "BS_SAN_CO_ConsultaPoderesPort"), BSSANCOConsultaPoderes.class, features);
    }

    private static URL __getWsdlLocation() {
        if (BSSANCOCONSULTAPODERES_EXCEPTION!= null) {
            throw BSSANCOCONSULTAPODERES_EXCEPTION;
        }
        return BSSANCOCONSULTAPODERES_WSDL_LOCATION;
    }

}
