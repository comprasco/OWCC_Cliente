
package https.www_supernotariado_gov_co.schemas.bachue.co.consultapoderes.consultarpoder.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the https.www_supernotariado_gov_co.schemas.bachue.co.consultapoderes.consultarpoder.v1 package. 
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

    private final static QName _EntradaConsultarPoder_QNAME = new QName("https://www.supernotariado.gov.co/schemas/bachue/co/consultapoderes/consultarpoder/v1", "entradaConsultarPoder");
    private final static QName _SalidaConsultarPoder_QNAME = new QName("https://www.supernotariado.gov.co/schemas/bachue/co/consultapoderes/consultarpoder/v1", "salidaConsultarPoder");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: https.www_supernotariado_gov_co.schemas.bachue.co.consultapoderes.consultarpoder.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TipoSalidaConsultarPoder }
     * 
     */
    public TipoSalidaConsultarPoder createTipoSalidaConsultarPoder() {
        return new TipoSalidaConsultarPoder();
    }

    /**
     * Create an instance of {@link TipoSalidaConsultarPoder.Documentos }
     * 
     */
    public TipoSalidaConsultarPoder.Documentos createTipoSalidaConsultarPoderDocumentos() {
        return new TipoSalidaConsultarPoder.Documentos();
    }

    /**
     * Create an instance of {@link TipoEntradaConsultarPoder }
     * 
     */
    public TipoEntradaConsultarPoder createTipoEntradaConsultarPoder() {
        return new TipoEntradaConsultarPoder();
    }

    /**
     * Create an instance of {@link TipoEntradaConsultarPoder.Parametros }
     * 
     */
    public TipoEntradaConsultarPoder.Parametros createTipoEntradaConsultarPoderParametros() {
        return new TipoEntradaConsultarPoder.Parametros();
    }

    /**
     * Create an instance of {@link TipoSalidaConsultarPoder.Documentos.Documento }
     * 
     */
    public TipoSalidaConsultarPoder.Documentos.Documento createTipoSalidaConsultarPoderDocumentosDocumento() {
        return new TipoSalidaConsultarPoder.Documentos.Documento();
    }

    /**
     * Create an instance of {@link TipoEntradaConsultarPoder.Parametros.Parametro }
     * 
     */
    public TipoEntradaConsultarPoder.Parametros.Parametro createTipoEntradaConsultarPoderParametrosParametro() {
        return new TipoEntradaConsultarPoder.Parametros.Parametro();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoEntradaConsultarPoder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/consultapoderes/consultarpoder/v1", name = "entradaConsultarPoder")
    public JAXBElement<TipoEntradaConsultarPoder> createEntradaConsultarPoder(TipoEntradaConsultarPoder value) {
        return new JAXBElement<TipoEntradaConsultarPoder>(_EntradaConsultarPoder_QNAME, TipoEntradaConsultarPoder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoSalidaConsultarPoder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/consultapoderes/consultarpoder/v1", name = "salidaConsultarPoder")
    public JAXBElement<TipoSalidaConsultarPoder> createSalidaConsultarPoder(TipoSalidaConsultarPoder value) {
        return new JAXBElement<TipoSalidaConsultarPoder>(_SalidaConsultarPoder_QNAME, TipoSalidaConsultarPoder.class, null, value);
    }

}
