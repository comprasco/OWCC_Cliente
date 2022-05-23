
package https.www_supernotariado_gov_co.schemas.bachue.co.consultapoderes.obtenerpoder.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the https.www_supernotariado_gov_co.schemas.bachue.co.consultapoderes.obtenerpoder.v1 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _EntradaObtenerPoder_QNAME = new QName("https://www.supernotariado.gov.co/schemas/bachue/co/consultapoderes/obtenerpoder/v1", "entradaObtenerPoder");
    private final static QName _SalidaObtenerPoder_QNAME = new QName("https://www.supernotariado.gov.co/schemas/bachue/co/consultapoderes/obtenerpoder/v1", "salidaObtenerPoder");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: https.www_supernotariado_gov_co.schemas.bachue.co.consultapoderes.obtenerpoder.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TipoEntradaObtenerPoder }
     * 
     */
    public TipoEntradaObtenerPoder createTipoEntradaObtenerPoder() {
        return new TipoEntradaObtenerPoder();
    }

    /**
     * Create an instance of {@link TipoSalidaObtenerPoder }
     * 
     */
    public TipoSalidaObtenerPoder createTipoSalidaObtenerPoder() {
        return new TipoSalidaObtenerPoder();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoEntradaObtenerPoder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/consultapoderes/obtenerpoder/v1", name = "entradaObtenerPoder")
    public JAXBElement<TipoEntradaObtenerPoder> createEntradaObtenerPoder(TipoEntradaObtenerPoder value) {
        return new JAXBElement<TipoEntradaObtenerPoder>(_EntradaObtenerPoder_QNAME, TipoEntradaObtenerPoder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoSalidaObtenerPoder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/consultapoderes/obtenerpoder/v1", name = "salidaObtenerPoder")
    public JAXBElement<TipoSalidaObtenerPoder> createSalidaObtenerPoder(TipoSalidaObtenerPoder value) {
        return new JAXBElement<TipoSalidaObtenerPoder>(_SalidaObtenerPoder_QNAME, TipoSalidaObtenerPoder.class, null, value);
    }

}
