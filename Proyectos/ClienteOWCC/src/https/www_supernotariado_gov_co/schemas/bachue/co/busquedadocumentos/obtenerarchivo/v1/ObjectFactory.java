/*
 *
 */

package https.www_supernotariado_gov_co.schemas.bachue.co.busquedadocumentos.obtenerarchivo.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the https.www_supernotariado_gov_co.schemas.bachue.co.busquedadocumentos.obtenerarchivo.v1 package.
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

    /** The Constant _EntradaObtenerArchivo_QNAME. */
    private final static QName _EntradaObtenerArchivo_QNAME = new QName("https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/obtenerarchivo/v1", "entradaObtenerArchivo");

    /** The Constant _SalidaObtenerArchivo_QNAME. */
    private final static QName _SalidaObtenerArchivo_QNAME = new QName("https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/obtenerarchivo/v1", "salidaObtenerArchivo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: https.www_supernotariado_gov_co.schemas.bachue.co.busquedadocumentos.obtenerarchivo.v1
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TipoEntradaObtenerArchivo }.
     *
     * @return the tipo entrada obtener archivo
     */
    public TipoEntradaObtenerArchivo createTipoEntradaObtenerArchivo() {
        return new TipoEntradaObtenerArchivo();
    }

    /**
     * Create an instance of {@link TipoSalidaObtenerArchivo }.
     *
     * @return the tipo salida obtener archivo
     */
    public TipoSalidaObtenerArchivo createTipoSalidaObtenerArchivo() {
        return new TipoSalidaObtenerArchivo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoEntradaObtenerArchivo }{@code >}}.
     *
     * @param value the value
     * @return the JAXB element< tipo entrada obtener archivo>
     */
    @XmlElementDecl(namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/obtenerarchivo/v1", name = "entradaObtenerArchivo")
    public JAXBElement<TipoEntradaObtenerArchivo> createEntradaObtenerArchivo(TipoEntradaObtenerArchivo value) {
        return new JAXBElement<TipoEntradaObtenerArchivo>(_EntradaObtenerArchivo_QNAME, TipoEntradaObtenerArchivo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoSalidaObtenerArchivo }{@code >}}.
     *
     * @param value the value
     * @return the JAXB element< tipo salida obtener archivo>
     */
    @XmlElementDecl(namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/obtenerarchivo/v1", name = "salidaObtenerArchivo")
    public JAXBElement<TipoSalidaObtenerArchivo> createSalidaObtenerArchivo(TipoSalidaObtenerArchivo value) {
        return new JAXBElement<TipoSalidaObtenerArchivo>(_SalidaObtenerArchivo_QNAME, TipoSalidaObtenerArchivo.class, null, value);
    }

}
