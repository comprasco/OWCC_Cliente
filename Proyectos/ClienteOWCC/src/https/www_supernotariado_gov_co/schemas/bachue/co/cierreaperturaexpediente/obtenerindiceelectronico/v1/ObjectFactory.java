/*
 *
 */

package https.www_supernotariado_gov_co.schemas.bachue.co.cierreaperturaexpediente.obtenerindiceelectronico.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the https.www_supernotariado_gov_co.schemas.bachue.co.cierreaperturaexpediente.cierreturno.v1 package.
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

    /** The Constant _EntradaCierreTurno_QNAME. */
    private final static QName _EntradaObtenerIndiceElectronico_QNAME = new QName("https://www.supernotariado.gov.co/schemas/bachue/co/cierreaperturaexpediente/obtenerindiceelectronico/v1", "entradaObtenerIndiceElectronico");

    /** The Constant _SalidaCierreTurno_QNAME. */
    private final static QName _SalidaObtenerIndiceElectronico_QNAME = new QName("https://www.supernotariado.gov.co/schemas/bachue/co/cierreaperturaexpediente/obtenerindiceelectronico/v1", "salidaObtenerIndiceElectronico");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: https.www_supernotariado_gov_co.schemas.bachue.co.cierreaperturaexpediente.cierreturno.v1
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TipoEntradaObtenerIndiceElectronico }.
     *
     * @return the tipo entrada cierre turno
     */
    public TipoEntradaObtenerIndiceElectronico createTipoEntradaObtenerIndiceElectronico() {
        return new TipoEntradaObtenerIndiceElectronico();
    }

    /**
     * Create an instance of {@link Parametro }.
     *
     * @return the parametro
     */
    public Parametro createParametro() {
        return new Parametro();
    }

    /**
     * Create an instance of {@link TipoSalidaObtenerIndiceElectronico }.
     *
     * @return the tipo salida cierre turno
     */
    public TipoSalidaObtenerIndiceElectronico createTipoSalidaObtenerIndiceElectronico() {
        return new TipoSalidaObtenerIndiceElectronico();
    }

    /**
     * Create an instance of {@link TipoEntradaObtenerIndiceElectronico.Parametros }
     *
     * @return the parametros
     */
    public TipoEntradaObtenerIndiceElectronico.Parametros createTipoEntradaObtenerIndiceElectronicoParametros() {
        return new TipoEntradaObtenerIndiceElectronico.Parametros();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoEntradaObtenerIndiceElectronico }{@code >}}.
     *
     * @param value the value
     * @return the JAXB element< tipo entrada cierre turno>
     */
    @XmlElementDecl(namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/cierreaperturaexpediente/obtenerindiceelectronico/v1", name = "entradaObtenerIndiceElectronico")
    public JAXBElement<TipoEntradaObtenerIndiceElectronico> createEntradaObtenerIndiceElectronico(TipoEntradaObtenerIndiceElectronico value) {
        return new JAXBElement<TipoEntradaObtenerIndiceElectronico>(_EntradaObtenerIndiceElectronico_QNAME, TipoEntradaObtenerIndiceElectronico.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoSalidaObtenerIndiceElectronico }{@code >}}.
     *
     * @param value the value
     * @return the JAXB element< tipo salida cierre turno>
     */
    @XmlElementDecl(namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/cierreaperturaexpediente/obtenerindiceelectronico/v1", name = "salidaObtenerIndiceElectronico")
    public JAXBElement<TipoSalidaObtenerIndiceElectronico> createSalidaObtenerIndiceElectronico(TipoSalidaObtenerIndiceElectronico value) {
        return new JAXBElement<TipoSalidaObtenerIndiceElectronico>(_SalidaObtenerIndiceElectronico_QNAME, TipoSalidaObtenerIndiceElectronico.class, null, value);
    }

}
