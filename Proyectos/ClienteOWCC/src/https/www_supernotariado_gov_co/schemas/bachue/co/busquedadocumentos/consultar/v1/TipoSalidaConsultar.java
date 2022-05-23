/*
 *
 */

package https.www_supernotariado_gov_co.schemas.bachue.co.busquedadocumentos.consultar.v1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import https.www_supernotariado_gov_co.schemas.bachue.co.busquedadocumentos.consultar.v1.TipoDocumento;
import https.www_supernotariado_gov_co.schemas.bachue.co.busquedadocumentos.consultar.v1.TipoSalidaConsultar;


/**
 * <p>Clase Java para tipoSalidaConsultar complex type.
 *
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 *
 * <pre>
 * &lt;complexType name="tipoSalidaConsultar"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="documentos"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="documento" type="{https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1}tipoDocumento" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
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
@XmlType(name = "tipoSalidaConsultar", propOrder = {
    "documentos",
    "codigoMensaje",
    "descripcionMensaje"
})
public class TipoSalidaConsultar {

    /** The documentos. */
    @XmlElement(required = true)
    protected TipoSalidaConsultar.Documentos documentos;

    /** The codigo mensaje. */
    @XmlElement(required = true)
    protected BigInteger codigoMensaje;

    /** The descripcion mensaje. */
    protected String descripcionMensaje;

    /**
     * Obtiene el valor de la propiedad documentos.
     *
     * @return
     *     possible object is
     *     {@link TipoSalidaConsultar.Documentos }
     *
     */
    public TipoSalidaConsultar.Documentos getDocumentos() {
        return documentos;
    }

    /**
     * Define el valor de la propiedad documentos.
     *
     * @param value
     *     allowed object is
     *     {@link TipoSalidaConsultar.Documentos }
     *
     */
    public void setDocumentos(TipoSalidaConsultar.Documentos value) {
        this.documentos = value;
    }

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


    /**
     * <p>Clase Java para anonymous complex type.
     *
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     *
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="documento" type="{https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1}tipoDocumento" maxOccurs="unbounded" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "documento"
    })
    public static class Documentos {

        /** The documento. */
        protected List<TipoDocumento> documento;

        /**
         * Gets the value of the documento property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the documento property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDocumento().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TipoDocumento }
         *
         * @return the documento
         */
        public List<TipoDocumento> getDocumento() {
            if (documento == null) {
                documento = new ArrayList<TipoDocumento>();
            }
            return this.documento;
        }


        /**
         * Asigna valor a la variable documento.
         *
         * @param doc the new documento
         */
        public void setDocumento( List<TipoDocumento> doc ) {
            if (documento == null) {
                documento = new ArrayList<TipoDocumento>();
                documento = doc;
            }
            else {
               documento = doc;
            }
        }

    }

}
