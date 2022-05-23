/*
 *
 */

package https.www_supernotariado_gov_co.schemas.bachue.co.enviodocumentos.enviardocumento.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the https.www_supernotariado_gov_co.schemas.bachue.co.enviodocumentos.enviardocumento.v1 package.
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

    /** The Constant _EntradaEnviarDocumento_QNAME. */
    private final static QName _EntradaEnviarDocumento_QNAME = new QName("https://www.supernotariado.gov.co/schemas/bachue/co/enviodocumentos/enviardocumento/v1", "entradaEnviarDocumento");

    /** The Constant _SalidaEnviarDocumento_QNAME. */
    private final static QName _SalidaEnviarDocumento_QNAME = new QName("https://www.supernotariado.gov.co/schemas/bachue/co/enviodocumentos/enviardocumento/v1", "salidaEnviarDocumento");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: https.www_supernotariado_gov_co.schemas.bachue.co.enviodocumentos.enviardocumento.v1
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TipoEntradaEnviarDocumento }.
     *
     * @return the tipo entrada enviar documento
     */
    public TipoEntradaEnviarDocumento createTipoEntradaEnviarDocumento() {
        return new TipoEntradaEnviarDocumento();
    }

    /**
     * Create an instance of {@link TipoSalidaEnviarDocumento }.
     *
     * @return the tipo salida enviar documento
     */
    public TipoSalidaEnviarDocumento createTipoSalidaEnviarDocumento() {
        return new TipoSalidaEnviarDocumento();
    }

    /**
     * Create an instance of {@link TipoParametro }.
     *
     * @return the tipo parametro
     */
    public TipoParametro createTipoParametro() {
        return new TipoParametro();
    }

    /**
     * Create an instance of {@link TipoEntradaEnviarDocumento.Parametros }
     *
     * @return the parametros
     */
    public TipoEntradaEnviarDocumento.Parametros createTipoEntradaEnviarDocumentoParametros() {
        return new TipoEntradaEnviarDocumento.Parametros();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoEntradaEnviarDocumento }{@code >}}.
     *
     * @param value the value
     * @return the JAXB element< tipo entrada enviar documento>
     */
    @XmlElementDecl(namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/enviodocumentos/enviardocumento/v1", name = "entradaEnviarDocumento")
    public JAXBElement<TipoEntradaEnviarDocumento> createEntradaEnviarDocumento(TipoEntradaEnviarDocumento value) {
        return new JAXBElement<TipoEntradaEnviarDocumento>(_EntradaEnviarDocumento_QNAME, TipoEntradaEnviarDocumento.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoSalidaEnviarDocumento }{@code >}}.
     *
     * @param value the value
     * @return the JAXB element< tipo salida enviar documento>
     */
    @XmlElementDecl(namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/enviodocumentos/enviardocumento/v1", name = "salidaEnviarDocumento")
    public JAXBElement<TipoSalidaEnviarDocumento> createSalidaEnviarDocumento(TipoSalidaEnviarDocumento value) {
        return new JAXBElement<TipoSalidaEnviarDocumento>(_SalidaEnviarDocumento_QNAME, TipoSalidaEnviarDocumento.class, null, value);
    }

}
