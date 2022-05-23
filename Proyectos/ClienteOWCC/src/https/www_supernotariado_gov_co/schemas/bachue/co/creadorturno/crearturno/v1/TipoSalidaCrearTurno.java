/*
 *
 */

package https.www_supernotariado_gov_co.schemas.bachue.co.creadorturno.crearturno.v1;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para tipoSalidaCrearTurno complex type.
 *
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 *
 * <pre>
 * &lt;complexType name="tipoSalidaCrearTurno"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codigoMensaje" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="descripcionMensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tipoSalidaCrearTurno", propOrder = {
    "codigoMensaje",
    "descripcionMensaje"
})
public class TipoSalidaCrearTurno {

    /** The codigo mensaje. */
    @XmlElement(required = true)
    protected BigInteger codigoMensaje;

    /** The descripcion mensaje. */
    protected String descripcionMensaje;

    /**
     * Obtiene el valor de la propiedad codigoMensaje.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
    public BigInteger getCodigoMensaje() {
        return codigoMensaje;
    }

    /**
     * Define el valor de la propiedad codigoMensaje.
     *
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *
     */
    public void setCodigoMensaje(BigInteger value) {
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
