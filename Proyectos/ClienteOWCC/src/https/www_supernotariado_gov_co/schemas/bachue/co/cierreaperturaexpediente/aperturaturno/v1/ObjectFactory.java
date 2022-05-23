/*
 *
 */

package https.www_supernotariado_gov_co.schemas.bachue.co.cierreaperturaexpediente.aperturaturno.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the https.www_supernotariado_gov_co.schemas.bachue.co.cierreaperturaexpediente.aperturaturno.v1 package.
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

    /** The Constant _EntradaAperturaTurno_QNAME. */
    private final static QName _EntradaAperturaTurno_QNAME = new QName("https://www.supernotariado.gov.co/schemas/bachue/co/cierreaperturaexpediente/aperturaturno/v1", "entradaAperturaTurno");

    /** The Constant _SalidaAperturaTurno_QNAME. */
    private final static QName _SalidaAperturaTurno_QNAME = new QName("https://www.supernotariado.gov.co/schemas/bachue/co/cierreaperturaexpediente/aperturaturno/v1", "salidaAperturaTurno");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: https.www_supernotariado_gov_co.schemas.bachue.co.cierreaperturaexpediente.aperturaturno.v1
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TipoEntradaAperturaTurno }.
     *
     * @return the tipo entrada apertura turno
     */
    public TipoEntradaAperturaTurno createTipoEntradaAperturaTurno() {
        return new TipoEntradaAperturaTurno();
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
     * Create an instance of {@link TipoSalidaAperturaTurno }.
     *
     * @return the tipo salida apertura turno
     */
    public TipoSalidaAperturaTurno createTipoSalidaAperturaTurno() {
        return new TipoSalidaAperturaTurno();
    }

    /**
     * Create an instance of {@link TipoEntradaAperturaTurno.Parametros }
     *
     * @return the parametros
     */
    public TipoEntradaAperturaTurno.Parametros createTipoEntradaAperturaTurnoParametros() {
        return new TipoEntradaAperturaTurno.Parametros();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoEntradaAperturaTurno }{@code >}}.
     *
     * @param value the value
     * @return the JAXB element< tipo entrada apertura turno>
     */
    @XmlElementDecl(namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/cierreaperturaexpediente/aperturaturno/v1", name = "entradaAperturaTurno")
    public JAXBElement<TipoEntradaAperturaTurno> createEntradaAperturaTurno(TipoEntradaAperturaTurno value) {
        return new JAXBElement<TipoEntradaAperturaTurno>(_EntradaAperturaTurno_QNAME, TipoEntradaAperturaTurno.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoSalidaAperturaTurno }{@code >}}.
     *
     * @param value the value
     * @return the JAXB element< tipo salida apertura turno>
     */
    @XmlElementDecl(namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/cierreaperturaexpediente/aperturaturno/v1", name = "salidaAperturaTurno")
    public JAXBElement<TipoSalidaAperturaTurno> createSalidaAperturaTurno(TipoSalidaAperturaTurno value) {
        return new JAXBElement<TipoSalidaAperturaTurno>(_SalidaAperturaTurno_QNAME, TipoSalidaAperturaTurno.class, null, value);
    }

}
