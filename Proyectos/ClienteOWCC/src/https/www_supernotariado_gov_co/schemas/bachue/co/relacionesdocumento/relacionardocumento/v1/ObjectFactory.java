/*
 *
 */

package https.www_supernotariado_gov_co.schemas.bachue.co.relacionesdocumento.relacionardocumento.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the https.www_supernotariado_gov_co.schemas.bachue.co.relacionesdocumento.relacionardocumento.v1 package.
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

    /** The Constant _EntradaRelacionarDocumento_QNAME. */
    private final static QName _EntradaRelacionarDocumento_QNAME = new QName("https://www.supernotariado.gov.co/schemas/bachue/co/relacionesdocumento/relacionardocumento/v1", "entradaRelacionarDocumento");

    /** The Constant _SalidaRelacionarDocumento_QNAME. */
    private final static QName _SalidaRelacionarDocumento_QNAME = new QName("https://www.supernotariado.gov.co/schemas/bachue/co/relacionesdocumento/relacionardocumento/v1", "salidaRelacionarDocumento");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: https.www_supernotariado_gov_co.schemas.bachue.co.relacionesdocumento.relacionardocumento.v1
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TipoEntradaRelacionarDocumento }.
     *
     * @return the tipo entrada relacionar documento
     */
    public TipoEntradaRelacionarDocumento createTipoEntradaRelacionarDocumento() {
        return new TipoEntradaRelacionarDocumento();
    }

    /**
     * Create an instance of {@link TipoSalidaRelacionarDocumento }.
     *
     * @return the tipo salida relacionar documento
     */
    public TipoSalidaRelacionarDocumento createTipoSalidaRelacionarDocumento() {
        return new TipoSalidaRelacionarDocumento();
    }

    /**
     * Create an instance of {@link TipoParametroRD }.
     *
     * @return the tipo parametro RD
     */
    public TipoParametroRD createTipoParametroRD() {
        return new TipoParametroRD();
    }

    /**
     * Create an instance of {@link TipoEntradaRelacionarDocumento.Parametros }
     *
     * @return the parametros
     */
    public TipoEntradaRelacionarDocumento.Parametros createTipoEntradaRelacionarDocumentoParametros() {
        return new TipoEntradaRelacionarDocumento.Parametros();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoEntradaRelacionarDocumento }{@code >}}.
     *
     * @param value the value
     * @return the JAXB element< tipo entrada relacionar documento>
     */
    @XmlElementDecl(namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/relacionesdocumento/relacionardocumento/v1", name = "entradaRelacionarDocumento")
    public JAXBElement<TipoEntradaRelacionarDocumento> createEntradaRelacionarDocumento(TipoEntradaRelacionarDocumento value) {
        return new JAXBElement<TipoEntradaRelacionarDocumento>(_EntradaRelacionarDocumento_QNAME, TipoEntradaRelacionarDocumento.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoSalidaRelacionarDocumento }{@code >}}.
     *
     * @param value the value
     * @return the JAXB element< tipo salida relacionar documento>
     */
    @XmlElementDecl(namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/relacionesdocumento/relacionardocumento/v1", name = "salidaRelacionarDocumento")
    public JAXBElement<TipoSalidaRelacionarDocumento> createSalidaRelacionarDocumento(TipoSalidaRelacionarDocumento value) {
        return new JAXBElement<TipoSalidaRelacionarDocumento>(_SalidaRelacionarDocumento_QNAME, TipoSalidaRelacionarDocumento.class, null, value);
    }

}
