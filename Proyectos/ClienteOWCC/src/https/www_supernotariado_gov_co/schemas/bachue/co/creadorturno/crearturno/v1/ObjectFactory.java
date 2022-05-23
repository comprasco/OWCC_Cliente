/*
 *
 */

package https.www_supernotariado_gov_co.schemas.bachue.co.creadorturno.crearturno.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the https.www_supernotariado_gov_co.schemas.bachue.co.creadorturno.crearturno.v1 package.
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

    /** The Constant _EntradaCrearTurno_QNAME. */
    private final static QName _EntradaCrearTurno_QNAME = new QName("https://www.supernotariado.gov.co/schemas/bachue/co/creadorturno/crearturno/v1", "entradaCrearTurno");

    /** The Constant _SalidaCrearTurno_QNAME. */
    private final static QName _SalidaCrearTurno_QNAME = new QName("https://www.supernotariado.gov.co/schemas/bachue/co/creadorturno/crearturno/v1", "salidaCrearTurno");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: https.www_supernotariado_gov_co.schemas.bachue.co.creadorturno.crearturno.v1
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TipoEntradaCrearTurno }.
     *
     * @return the tipo entrada crear turno
     */
    public TipoEntradaCrearTurno createTipoEntradaCrearTurno() {
        return new TipoEntradaCrearTurno();
    }

    /**
     * Create an instance of {@link TipoSalidaCrearTurno }.
     *
     * @return the tipo salida crear turno
     */
    public TipoSalidaCrearTurno createTipoSalidaCrearTurno() {
        return new TipoSalidaCrearTurno();
    }

    /**
     * Create an instance of {@link TipoParametroCT }.
     *
     * @return the tipo parametro CT
     */
    public TipoParametroCT createTipoParametroCT() {
        return new TipoParametroCT();
    }

    /**
     * Create an instance of {@link TipoEntradaCrearTurno.Parametros }
     *
     * @return the parametros
     */
    public TipoEntradaCrearTurno.Parametros createTipoEntradaCrearTurnoParametros() {
        return new TipoEntradaCrearTurno.Parametros();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoEntradaCrearTurno }{@code >}}.
     *
     * @param value the value
     * @return the JAXB element< tipo entrada crear turno>
     */
    @XmlElementDecl(namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/creadorturno/crearturno/v1", name = "entradaCrearTurno")
    public JAXBElement<TipoEntradaCrearTurno> createEntradaCrearTurno(TipoEntradaCrearTurno value) {
        return new JAXBElement<TipoEntradaCrearTurno>(_EntradaCrearTurno_QNAME, TipoEntradaCrearTurno.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoSalidaCrearTurno }{@code >}}.
     *
     * @param value the value
     * @return the JAXB element< tipo salida crear turno>
     */
    @XmlElementDecl(namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/creadorturno/crearturno/v1", name = "salidaCrearTurno")
    public JAXBElement<TipoSalidaCrearTurno> createSalidaCrearTurno(TipoSalidaCrearTurno value) {
        return new JAXBElement<TipoSalidaCrearTurno>(_SalidaCrearTurno_QNAME, TipoSalidaCrearTurno.class, null, value);
    }

}
