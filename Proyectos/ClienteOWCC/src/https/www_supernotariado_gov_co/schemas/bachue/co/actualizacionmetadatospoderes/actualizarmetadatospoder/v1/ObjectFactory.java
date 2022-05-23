
package https.www_supernotariado_gov_co.schemas.bachue.co.actualizacionmetadatospoderes.actualizarmetadatospoder.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the https.www_supernotariado_gov_co.schemas.bachue.co.actualizacionmetadatospoderes.actualizarmetadatospoder.v1 package. 
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

    private final static QName _EntradaActualizarMetadatosPoder_QNAME = new QName("https://www.supernotariado.gov.co/schemas/bachue/co/actualizacionmetadatospoderes/actualizarmetadatospoder/v1", "entradaActualizarMetadatosPoder");
    private final static QName _SalidaActualizarMetadatosPoder_QNAME = new QName("https://www.supernotariado.gov.co/schemas/bachue/co/actualizacionmetadatospoderes/actualizarmetadatospoder/v1", "salidaActualizarMetadatosPoder");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: https.www_supernotariado_gov_co.schemas.bachue.co.actualizacionmetadatospoderes.actualizarmetadatospoder.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TipoEntradaActualizarMetadatosPoder }
     * 
     */
    public TipoEntradaActualizarMetadatosPoder createTipoEntradaActualizarMetadatosPoder() {
        return new TipoEntradaActualizarMetadatosPoder();
    }

    /**
     * Create an instance of {@link TipoEntradaActualizarMetadatosPoder.Parametros }
     * 
     */
    public TipoEntradaActualizarMetadatosPoder.Parametros createTipoEntradaActualizarMetadatosPoderParametros() {
        return new TipoEntradaActualizarMetadatosPoder.Parametros();
    }

    /**
     * Create an instance of {@link TipoSalidaActualizarMetadatosPoder }
     * 
     */
    public TipoSalidaActualizarMetadatosPoder createTipoSalidaActualizarMetadatosPoder() {
        return new TipoSalidaActualizarMetadatosPoder();
    }

    /**
     * Create an instance of {@link TipoEntradaActualizarMetadatosPoder.Parametros.Parametro }
     * 
     */
    public TipoEntradaActualizarMetadatosPoder.Parametros.Parametro createTipoEntradaActualizarMetadatosPoderParametrosParametro() {
        return new TipoEntradaActualizarMetadatosPoder.Parametros.Parametro();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoEntradaActualizarMetadatosPoder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/actualizacionmetadatospoderes/actualizarmetadatospoder/v1", name = "entradaActualizarMetadatosPoder")
    public JAXBElement<TipoEntradaActualizarMetadatosPoder> createEntradaActualizarMetadatosPoder(TipoEntradaActualizarMetadatosPoder value) {
        return new JAXBElement<TipoEntradaActualizarMetadatosPoder>(_EntradaActualizarMetadatosPoder_QNAME, TipoEntradaActualizarMetadatosPoder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoSalidaActualizarMetadatosPoder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/actualizacionmetadatospoderes/actualizarmetadatospoder/v1", name = "salidaActualizarMetadatosPoder")
    public JAXBElement<TipoSalidaActualizarMetadatosPoder> createSalidaActualizarMetadatosPoder(TipoSalidaActualizarMetadatosPoder value) {
        return new JAXBElement<TipoSalidaActualizarMetadatosPoder>(_SalidaActualizarMetadatosPoder_QNAME, TipoSalidaActualizarMetadatosPoder.class, null, value);
    }

}
