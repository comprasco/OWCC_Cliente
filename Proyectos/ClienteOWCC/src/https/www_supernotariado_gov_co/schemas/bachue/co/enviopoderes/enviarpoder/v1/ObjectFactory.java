
package https.www_supernotariado_gov_co.schemas.bachue.co.enviopoderes.enviarpoder.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the https.www_supernotariado_gov_co.schemas.bachue.co.enviopoderes.enviarpoder.v1 package. 
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

    private final static QName _EntradaEnviarPoder_QNAME = new QName("https://www.supernotariado.gov.co/schemas/bachue/co/enviopoderes/enviarpoder/v1", "entradaEnviarPoder");
    private final static QName _SalidaEnviarPoder_QNAME = new QName("https://www.supernotariado.gov.co/schemas/bachue/co/enviopoderes/enviarpoder/v1", "salidaEnviarPoder");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: https.www_supernotariado_gov_co.schemas.bachue.co.enviopoderes.enviarpoder.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TipoEntradaEnviarPoder }
     * 
     */
    public TipoEntradaEnviarPoder createTipoEntradaEnviarPoder() {
        return new TipoEntradaEnviarPoder();
    }

    /**
     * Create an instance of {@link TipoEntradaEnviarPoder.Parametros }
     * 
     */
    public TipoEntradaEnviarPoder.Parametros createTipoEntradaEnviarPoderParametros() {
        return new TipoEntradaEnviarPoder.Parametros();
    }

    /**
     * Create an instance of {@link TipoSalidaEnviarPoder }
     * 
     */
    public TipoSalidaEnviarPoder createTipoSalidaEnviarPoder() {
        return new TipoSalidaEnviarPoder();
    }

    /**
     * Create an instance of {@link TipoEntradaEnviarPoder.Parametros.Parametro }
     * 
     */
    public TipoEntradaEnviarPoder.Parametros.Parametro createTipoEntradaEnviarPoderParametrosParametro() {
        return new TipoEntradaEnviarPoder.Parametros.Parametro();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoEntradaEnviarPoder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/enviopoderes/enviarpoder/v1", name = "entradaEnviarPoder")
    public JAXBElement<TipoEntradaEnviarPoder> createEntradaEnviarPoder(TipoEntradaEnviarPoder value) {
        return new JAXBElement<TipoEntradaEnviarPoder>(_EntradaEnviarPoder_QNAME, TipoEntradaEnviarPoder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoSalidaEnviarPoder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/enviopoderes/enviarpoder/v1", name = "salidaEnviarPoder")
    public JAXBElement<TipoSalidaEnviarPoder> createSalidaEnviarPoder(TipoSalidaEnviarPoder value) {
        return new JAXBElement<TipoSalidaEnviarPoder>(_SalidaEnviarPoder_QNAME, TipoSalidaEnviarPoder.class, null, value);
    }

}
