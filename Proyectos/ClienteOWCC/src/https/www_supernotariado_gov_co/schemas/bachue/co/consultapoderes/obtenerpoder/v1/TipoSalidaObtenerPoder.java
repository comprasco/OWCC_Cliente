
package https.www_supernotariado_gov_co.schemas.bachue.co.consultapoderes.obtenerpoder.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para tipoSalidaObtenerPoder complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="tipoSalidaObtenerPoder"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="archivo" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *         &lt;element name="codigoMensaje" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="descripcionMensaje" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tipoSalidaObtenerPoder", propOrder = {
    "archivo",
    "codigoMensaje",
    "descripcionMensaje"
})
public class TipoSalidaObtenerPoder {

    @XmlElement(required = true)
    protected byte[] archivo;
    protected int codigoMensaje;
    @XmlElement(required = true)
    protected String descripcionMensaje;

    /**
     * Obtiene el valor de la propiedad archivo.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getArchivo() {
        return archivo;
    }

    /**
     * Define el valor de la propiedad archivo.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setArchivo(byte[] value) {
        this.archivo = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoMensaje.
     * 
     */
    public int getCodigoMensaje() {
        return codigoMensaje;
    }

    /**
     * Define el valor de la propiedad codigoMensaje.
     * 
     */
    public void setCodigoMensaje(int value) {
        this.codigoMensaje = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcionMensaje.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionMensaje() {
        return descripcionMensaje;
    }

    /**
     * Define el valor de la propiedad descripcionMensaje.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionMensaje(String value) {
        this.descripcionMensaje = value;
    }

}
