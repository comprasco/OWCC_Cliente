/*
 *
 */

package https.www_supernotariado_gov_co.services.bachue.co.enviodocumentos.v1;

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
@WebServiceClient(name = "SUT_CO_EnvioDocumentos", targetNamespace = "https://www.supernotariado.gov.co/services/bachue/co/enviodocumentos/v1", wsdlLocation = "file:/C:/Users/consultor/Documents/NetbeansProjects82/ClienteOWCC/src/conf/xml-resources/web-services/BeanEnvio/wsdl/BS_SUT_CO_EnvioDocumentos.wsdl")
public class SUTCOEnvioDocumentos_Service
    extends Service
{

    /** The Constant SUTCOENVIODOCUMENTOS_WSDL_LOCATION. */
    private final static URL SUTCOENVIODOCUMENTOS_WSDL_LOCATION;

    /** The Constant SUTCOENVIODOCUMENTOS_EXCEPTION. */
    private final static WebServiceException SUTCOENVIODOCUMENTOS_EXCEPTION;

    /** The Constant SUTCOENVIODOCUMENTOS_QNAME. */
    private final static QName SUTCOENVIODOCUMENTOS_QNAME = new QName("https://www.supernotariado.gov.co/services/bachue/co/enviodocumentos/v1", "SUT_CO_EnvioDocumentos");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/Users/consultor/Documents/NetbeansProjects82/ClienteOWCC/src/conf/xml-resources/web-services/BeanEnvio/wsdl/BS_SUT_CO_EnvioDocumentos.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SUTCOENVIODOCUMENTOS_WSDL_LOCATION = url;
        SUTCOENVIODOCUMENTOS_EXCEPTION = e;
    }

    /**
     * Instantiates a new SUTCO envio documentos service.
     */
    public SUTCOEnvioDocumentos_Service() {
        super(__getWsdlLocation(), SUTCOENVIODOCUMENTOS_QNAME);
    }

    /**
     * Instantiates a new SUTCO envio documentos service.
     *
     * @param features the features
     */
    public SUTCOEnvioDocumentos_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), SUTCOENVIODOCUMENTOS_QNAME, features);
    }

    /**
     * Instantiates a new SUTCO envio documentos service.
     *
     * @param wsdlLocation the wsdl location
     */
    public SUTCOEnvioDocumentos_Service(URL wsdlLocation) {
        super(wsdlLocation, SUTCOENVIODOCUMENTOS_QNAME);
    }

    /**
     * Instantiates a new SUTCO envio documentos service.
     *
     * @param wsdlLocation the wsdl location
     * @param features the features
     */
    public SUTCOEnvioDocumentos_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SUTCOENVIODOCUMENTOS_QNAME, features);
    }

    /**
     * Instantiates a new SUTCO envio documentos service.
     *
     * @param wsdlLocation the wsdl location
     * @param serviceName the service name
     */
    public SUTCOEnvioDocumentos_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    /**
     * Instantiates a new SUTCO envio documentos service.
     *
     * @param wsdlLocation the wsdl location
     * @param serviceName the service name
     * @param features the features
     */
    public SUTCOEnvioDocumentos_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * Gets the SUTCO envio documentos port.
     *
     * @return     returns SUTCOEnvioDocumentos
     */
    @WebEndpoint(name = "SUT_CO_EnvioDocumentosPort")
    public SUTCOEnvioDocumentos getSUTCOEnvioDocumentosPort() {
        return super.getPort(new QName("https://www.supernotariado.gov.co/services/bachue/co/enviodocumentos/v1", "SUT_CO_EnvioDocumentosPort"), SUTCOEnvioDocumentos.class);
    }

    /**
     * Gets the SUTCO envio documentos port.
     *
     * @param features     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return     returns SUTCOEnvioDocumentos
     */
    @WebEndpoint(name = "SUT_CO_EnvioDocumentosPort")
    public SUTCOEnvioDocumentos getSUTCOEnvioDocumentosPort(WebServiceFeature... features) {
        return super.getPort(new QName("https://www.supernotariado.gov.co/services/bachue/co/enviodocumentos/v1", "SUT_CO_EnvioDocumentosPort"), SUTCOEnvioDocumentos.class, features);
    }

    /**
     * Gets the wsdl location.
     *
     * @return the url
     */
    private static URL __getWsdlLocation() {
        if (SUTCOENVIODOCUMENTOS_EXCEPTION!= null) {
            throw SUTCOENVIODOCUMENTOS_EXCEPTION;
        }
        return SUTCOENVIODOCUMENTOS_WSDL_LOCATION;
    }

}
