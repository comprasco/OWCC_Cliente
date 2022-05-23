/*
 *
 */

package https.www_supernotariado_gov_co.schemas.bachue.co.creadorturno.crearturno.v1;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para tipoEntradaCrearTurno complex type.
 *
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 *
 * <pre>
 * &lt;complexType name="tipoEntradaCrearTurno"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="sistemaOrigen" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="parametros"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="parametro" type="{https://www.supernotariado.gov.co/schemas/bachue/co/creadorturno/crearturno/v1}tipoParametroCT" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tipoEntradaCrearTurno", propOrder = {
    "sistemaOrigen",
    "parametros"
})
public class TipoEntradaCrearTurno {

    /** The sistema origen. */
    @XmlElement(required = true)
    protected String sistemaOrigen;

    /** The parametros. */
    @XmlElement(required = true)
    protected TipoEntradaCrearTurno.Parametros parametros;

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
     * Obtiene el valor de la propiedad parametros.
     *
     * @return
     *     possible object is
     *     {@link TipoEntradaCrearTurno.Parametros }
     *
     */
    public TipoEntradaCrearTurno.Parametros getParametros() {
        return parametros;
    }

    /**
     * Define el valor de la propiedad parametros.
     *
     * @param value
     *     allowed object is
     *     {@link TipoEntradaCrearTurno.Parametros }
     *
     */
    public void setParametros(TipoEntradaCrearTurno.Parametros value) {
        this.parametros = value;
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
     *         &lt;element name="parametro" type="{https://www.supernotariado.gov.co/schemas/bachue/co/creadorturno/crearturno/v1}tipoParametroCT" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        protected List<TipoParametroCT> parametro;

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
         * {@link TipoParametroCT }
         *
         * @return the parametro
         */
        public List<TipoParametroCT> getParametro() {
            if (parametro == null) {
                parametro = new ArrayList<TipoParametroCT>();
            }
            return this.parametro;
        }

    }

}
