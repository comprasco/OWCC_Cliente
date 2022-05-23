
package https.www_supernotariado_gov_co.schemas.bachue.co.actualizacionmetadatospoderes.actualizarmetadatospoder.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para tipoSalidaActualizarMetadatosPoder complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="tipoSalidaActualizarMetadatosPoder"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="docName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
@XmlType(name = "tipoSalidaActualizarMetadatosPoder", propOrder = {
    "docName",
    "did",
    "codigoMensaje",
    "descripcionMensaje"
})
public class TipoSalidaActualizarMetadatosPoder {

    @XmlElement(required = true)
    protected String docName;
    @XmlElement(name = "dID")
    protected int did;
    protected int codigoMensaje;
    @XmlElement(required = true)
    protected String descripcionMensaje;

    /**
     * Obtiene el valor de la propiedad docName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocName() {
        return docName;
    }

    /**
     * Define el valor de la propiedad docName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocName(String value) {
        this.docName = value;
    }

    /**
     * Obtiene el valor de la propiedad did.
     * 
     */
    public int getDID() {
        return did;
    }

    /**
     * Define el valor de la propiedad did.
     * 
     */
    public void setDID(int value) {
        this.did = value;
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
