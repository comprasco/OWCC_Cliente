/*
 *
 */

package https.www_supernotariado_gov_co.schemas.bachue.co.enviodocumentos.enviardocumento.v1;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para tipoEntradaEnviarDocumento complex type.
 *
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 *
 * <pre>
 * &lt;complexType name="tipoEntradaEnviarDocumento"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="sistemaOrigen" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="repositorio"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="TEMPORAL"/&gt;
 *               &lt;enumeration value="FINAL"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="parametros"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="parametro" type="{https://www.supernotariado.gov.co/schemas/bachue/co/enviodocumentos/enviardocumento/v1}tipoParametro" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="archivo" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *         &lt;element name="nombreArchivo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tipoEntradaEnviarDocumento", propOrder = {
    "sistemaOrigen",
    "repositorio",
    "parametros",
    "archivo",
    "nombreArchivo"
})
public class TipoEntradaEnviarDocumento {

    /** The sistema origen. */
    @XmlElement(required = true)
    protected String sistemaOrigen;

    /** The repositorio. */
    @XmlElement(required = true)
    protected String repositorio;

    /** The parametros. */
    @XmlElement(required = true)
    protected TipoEntradaEnviarDocumento.Parametros parametros;

    /** The archivo. */
    @XmlElement(required = true)
    protected byte[] archivo;

    /** The nombre archivo. */
    @XmlElement(required = true)
    protected String nombreArchivo;

    /**
     * Obtiene el valor de la propiedad sistemaOrigen.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSistemaOrigen() {
        return sistemaOrigen;
    }

    /**
     * Define el valor de la propiedad sistemaOrigen.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSistemaOrigen(String value) {
        this.sistemaOrigen = value;
    }

    /**
     * Obtiene el valor de la propiedad repositorio.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRepositorio() {
        return repositorio;
    }

    /**
     * Define el valor de la propiedad repositorio.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRepositorio(String value) {
        this.repositorio = value;
    }

    /**
     * Obtiene el valor de la propiedad parametros.
     *
     * @return
     *     possible object is
     *     {@link TipoEntradaEnviarDocumento.Parametros }
     *
     */
    public TipoEntradaEnviarDocumento.Parametros getParametros() {
        return parametros;
    }

    /**
     * Define el valor de la propiedad parametros.
     *
     * @param value
     *     allowed object is
     *     {@link TipoEntradaEnviarDocumento.Parametros }
     *
     */
    public void setParametros(TipoEntradaEnviarDocumento.Parametros value) {
        this.parametros = value;
    }

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
     * Obtiene el valor de la propiedad nombreArchivo.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    /**
     * Define el valor de la propiedad nombreArchivo.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNombreArchivo(String value) {
        this.nombreArchivo = value;
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
     *         &lt;element name="parametro" type="{https://www.supernotariado.gov.co/schemas/bachue/co/enviodocumentos/enviardocumento/v1}tipoParametro" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "parametro"
    })
    public static class Parametros {

        /** The parametro. */
        protected List<TipoParametro> parametro;

        /**
         * Gets the value of the parametro property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the parametro property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getParametro().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TipoParametro }
         *
         * @return the parametro
         */
        public List<TipoParametro> getParametro() {
            if (parametro == null) {
                parametro = new ArrayList<TipoParametro>();
            }
            return this.parametro;
        }

    }

}
