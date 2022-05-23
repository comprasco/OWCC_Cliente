/*
 *
 */

package https.www_supernotariado_gov_co.schemas.bachue.co.cierreaperturaexpediente.cierreturno.v1;

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
    private final static QName _EntradaCierreTurno_QNAME = new QName("https://www.supernotariado.gov.co/schemas/bachue/co/cierreaperturaexpediente/cierreturno/v1", "entradaCierreTurno");

    /** The Constant _SalidaCierreTurno_QNAME. */
    private final static QName _SalidaCierreTurno_QNAME = new QName("https://www.supernotariado.gov.co/schemas/bachue/co/cierreaperturaexpediente/cierreturno/v1", "salidaCierreTurno");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: https.www_supernotariado_gov_co.schemas.bachue.co.cierreaperturaexpediente.cierreturno.v1
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TipoEntradaCierreTurno }.
     *
     * @return the tipo entrada cierre turno
     */
    public TipoEntradaCierreTurno createTipoEntradaCierreTurno() {
        return new TipoEntradaCierreTurno();
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
     * Create an instance of {@link TipoSalidaCierreTurno }.
     *
     * @return the tipo salida cierre turno
     */
    public TipoSalidaCierreTurno createTipoSalidaCierreTurno() {
        return new TipoSalidaCierreTurno();
    }

    /**
     * Create an instance of {@link TipoEntradaCierreTurno.Parametros }
     *
     * @return the parametros
     */
    public TipoEntradaCierreTurno.Parametros createTipoEntradaCierreTurnoParametros() {
        return new TipoEntradaCierreTurno.Parametros();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoEntradaCierreTurno }{@code >}}.
     *
     * @param value the value
     * @return the JAXB element< tipo entrada cierre turno>
     */
    @XmlElementDecl(namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/cierreaperturaexpediente/cierreturno/v1", name = "entradaCierreTurno")
    public JAXBElement<TipoEntradaCierreTurno> createEntradaCierreTurno(TipoEntradaCierreTurno value) {
        return new JAXBElement<TipoEntradaCierreTurno>(_EntradaCierreTurno_QNAME, TipoEntradaCierreTurno.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoSalidaCierreTurno }{@code >}}.
     *
     * @param value the value
     * @return the JAXB element< tipo salida cierre turno>
     */
    @XmlElementDecl(namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/cierreaperturaexpediente/cierreturno/v1", name = "salidaCierreTurno")
    public JAXBElement<TipoSalidaCierreTurno> createSalidaCierreTurno(TipoSalidaCierreTurno value) {
        return new JAXBElement<TipoSalidaCierreTurno>(_SalidaCierreTurno_QNAME, TipoSalidaCierreTurno.class, null, value);
    }

}
