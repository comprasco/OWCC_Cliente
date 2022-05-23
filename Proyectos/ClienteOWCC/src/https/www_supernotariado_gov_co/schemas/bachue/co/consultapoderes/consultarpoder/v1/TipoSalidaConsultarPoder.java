
package https.www_supernotariado_gov_co.schemas.bachue.co.consultapoderes.consultarpoder.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para tipoSalidaConsultarPoder complex type.
 *
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 *
 * <pre>
 * &lt;complexType name="tipoSalidaConsultarPoder"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="documentos"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="documento" maxOccurs="unbounded" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="dID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="docName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="dDocType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="x_Justificacion_Revocado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="x_Tipo_Poder" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="xDepartamentos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="xMunicipios" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="xTip_Doc_Apoderado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="xNotaria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="xCC_Apoderado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="xNombre_Apoderado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="xTip_Doc_Poderdante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="xCC_Poderdante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="xNombre_Poderdante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="xNum_Matricula_Poder" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="xDireccion_Predio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="xDepartamento_Predio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="xCiudad_Predio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="xNumero_Instrumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="xFecha_Instrumento" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *                             &lt;element name="xEstado_Poder" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="xUso_Poder" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="xCirculo_Predio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="xFinalizacion_Poder" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *                             &lt;element name="xJustificacion_Usado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="xDiligenciaReconocimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="xFecha_cargue_poder" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *                             &lt;element name="xNotificaciones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="urlVisor" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="xTipo_Documento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="codigoMensaje" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                             &lt;element name="descripcionMensaje" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
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
@XmlType(name = "tipoSalidaConsultarPoder", propOrder = {
    "documentos"
})
public class TipoSalidaConsultarPoder {

    @XmlElement(required = true)
    protected TipoSalidaConsultarPoder.Documentos documentos;

    /**
     * Obtiene el valor de la propiedad documentos.
     *
     * @return
     *     possible object is
     *     {@link TipoSalidaConsultarPoder.Documentos }
     *
     */
    public TipoSalidaConsultarPoder.Documentos getDocumentos() {
        return documentos;
    }

    /**
     * Define el valor de la propiedad documentos.
     *
     * @param value
     *     allowed object is
     *     {@link TipoSalidaConsultarPoder.Documentos }
     *
     */
    public void setDocumentos(TipoSalidaConsultarPoder.Documentos value) {
        this.documentos = value;
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
     *         &lt;element name="documento" maxOccurs="unbounded" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="dID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="docName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="dDocType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="x_Justificacion_Revocado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="x_Tipo_Poder" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="xDepartamentos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="xMunicipios" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="xTip_Doc_Apoderado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="xNotaria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="xCC_Apoderado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="xNombre_Apoderado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="xTip_Doc_Poderdante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="xCC_Poderdante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="xNombre_Poderdante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="xNum_Matricula_Poder" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="xDireccion_Predio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="xDepartamento_Predio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="xCiudad_Predio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="xNumero_Instrumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="xFecha_Instrumento" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
     *                   &lt;element name="xEstado_Poder" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="xUso_Poder" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="xCirculo_Predio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="xFinalizacion_Poder" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
     *                   &lt;element name="xJustificacion_Usado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="xDiligenciaReconocimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="xFecha_cargue_poder" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
     *                   &lt;element name="xNotificaciones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="urlVisor" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="xTipo_Documento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="codigoMensaje" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *                   &lt;element name="descripcionMensaje" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    @XmlType(name = "", propOrder = {
        "documento"
    })
    public static class Documentos {

        protected List<TipoSalidaConsultarPoder.Documentos.Documento> documento;

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
         * {@link TipoSalidaConsultarPoder.Documentos.Documento }
         *
         *
         */
        public List<TipoSalidaConsultarPoder.Documentos.Documento> getDocumento() {
            if (documento == null) {
                documento = new ArrayList<TipoSalidaConsultarPoder.Documentos.Documento>();
            }
            return this.documento;
        }

        public void setDocumento(List<TipoSalidaConsultarPoder.Documentos.Documento> documentos) {
        	documento = documentos;
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
         *         &lt;element name="dID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="docName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="dDocType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="x_Justificacion_Revocado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="x_Tipo_Poder" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="xDepartamentos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="xMunicipios" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="xTip_Doc_Apoderado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="xNotaria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="xCC_Apoderado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="xNombre_Apoderado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="xTip_Doc_Poderdante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="xCC_Poderdante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="xNombre_Poderdante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="xNum_Matricula_Poder" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="xDireccion_Predio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="xDepartamento_Predio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="xCiudad_Predio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="xNumero_Instrumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="xFecha_Instrumento" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
         *         &lt;element name="xEstado_Poder" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="xUso_Poder" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="xCirculo_Predio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="xFinalizacion_Poder" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
         *         &lt;element name="xJustificacion_Usado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="xDiligenciaReconocimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="xFecha_cargue_poder" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
         *         &lt;element name="xNotificaciones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="urlVisor" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="xTipo_Documento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
        @XmlType(name = "", propOrder = {
            "did",
            "docName",
            "dDocType",
            "xJustificacionRevocado",
            "xTipoPoder",
            "xDepartamentos",
            "xMunicipios",
            "xTipDocApoderado",
            "xNotaria",
            "xccApoderado",
            "xNombreApoderado",
            "xTipDocPoderdante",
            "xccPoderdante",
            "xNombrePoderdante",
            "xNumMatriculaPoder",
            "xDireccionPredio",
            "xDepartamentoPredio",
            "xCiudadPredio",
            "xNumeroInstrumento",
            "xFechaInstrumento",
            "xEstadoPoder",
            "xUsoPoder",
            "xCirculoPredio",
            "xFinalizacionPoder",
            "xJustificacionUsado",
            "xDiligenciaReconocimiento",
            "xFechaCarguePoder",
            "xNotificaciones",
            "urlVisor",
            "xTipoDocumento",
            "codigoMensaje",
            "descripcionMensaje"
        })
        public static class Documento {

            @XmlElement(name = "dID", required = true)
            protected String did;
            @XmlElement(required = true)
            protected String docName;
            protected String dDocType;
            @XmlElement(name = "x_Justificacion_Revocado")
            protected String xJustificacionRevocado;
            @XmlElement(name = "x_Tipo_Poder")
            protected String xTipoPoder;
            protected String xDepartamentos;
            protected String xMunicipios;
            @XmlElement(name = "xTip_Doc_Apoderado")
            protected String xTipDocApoderado;
            protected String xNotaria;
            @XmlElement(name = "xCC_Apoderado")
            protected String xccApoderado;
            @XmlElement(name = "xNombre_Apoderado")
            protected String xNombreApoderado;
            @XmlElement(name = "xTip_Doc_Poderdante")
            protected String xTipDocPoderdante;
            @XmlElement(name = "xCC_Poderdante")
            protected String xccPoderdante;
            @XmlElement(name = "xNombre_Poderdante")
            protected String xNombrePoderdante;
            @XmlElement(name = "xNum_Matricula_Poder")
            protected String xNumMatriculaPoder;
            @XmlElement(name = "xDireccion_Predio")
            protected String xDireccionPredio;
            @XmlElement(name = "xDepartamento_Predio")
            protected String xDepartamentoPredio;
            @XmlElement(name = "xCiudad_Predio")
            protected String xCiudadPredio;
            @XmlElement(name = "xNumero_Instrumento")
            protected String xNumeroInstrumento;
            @XmlElement(name = "xFecha_Instrumento")
            @XmlSchemaType(name = "date")
            protected XMLGregorianCalendar xFechaInstrumento;
            @XmlElement(name = "xEstado_Poder")
            protected String xEstadoPoder;
            @XmlElement(name = "xUso_Poder")
            protected String xUsoPoder;
            @XmlElement(name = "xCirculo_Predio")
            protected String xCirculoPredio;
            @XmlElement(name = "xFinalizacion_Poder")
            @XmlSchemaType(name = "date")
            protected XMLGregorianCalendar xFinalizacionPoder;
            @XmlElement(name = "xJustificacion_Usado")
            protected String xJustificacionUsado;
            protected String xDiligenciaReconocimiento;
            @XmlElement(name = "xFecha_cargue_poder")
            @XmlSchemaType(name = "date")
            protected XMLGregorianCalendar xFechaCarguePoder;
            protected String xNotificaciones;
            @XmlElement(required = true)
            protected String urlVisor;
            @XmlElement(name = "xTipo_Documento")
            protected String xTipoDocumento;
            protected int codigoMensaje;
            @XmlElement(required = true)
            protected String descripcionMensaje;

            /**
             * Obtiene el valor de la propiedad did.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getDID() {
                return did;
            }

            /**
             * Define el valor de la propiedad did.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setDID(String value) {
                this.did = value;
            }

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
             * Obtiene el valor de la propiedad dDocType.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getDDocType() {
                return dDocType;
            }

            /**
             * Define el valor de la propiedad dDocType.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setDDocType(String value) {
                this.dDocType = value;
            }

            /**
             * Obtiene el valor de la propiedad xJustificacionRevocado.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getXJustificacionRevocado() {
                return xJustificacionRevocado;
            }

            /**
             * Define el valor de la propiedad xJustificacionRevocado.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setXJustificacionRevocado(String value) {
                this.xJustificacionRevocado = value;
            }

            /**
             * Obtiene el valor de la propiedad xTipoPoder.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getXTipoPoder() {
                return xTipoPoder;
            }

            /**
             * Define el valor de la propiedad xTipoPoder.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setXTipoPoder(String value) {
                this.xTipoPoder = value;
            }

            /**
             * Obtiene el valor de la propiedad xDepartamentos.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getXDepartamentos() {
                return xDepartamentos;
            }

            /**
             * Define el valor de la propiedad xDepartamentos.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setXDepartamentos(String value) {
                this.xDepartamentos = value;
            }

            /**
             * Obtiene el valor de la propiedad xMunicipios.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getXMunicipios() {
                return xMunicipios;
            }

            /**
             * Define el valor de la propiedad xMunicipios.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setXMunicipios(String value) {
                this.xMunicipios = value;
            }

            /**
             * Obtiene el valor de la propiedad xTipDocApoderado.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getXTipDocApoderado() {
                return xTipDocApoderado;
            }

            /**
             * Define el valor de la propiedad xTipDocApoderado.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setXTipDocApoderado(String value) {
                this.xTipDocApoderado = value;
            }

            /**
             * Obtiene el valor de la propiedad xNotaria.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getXNotaria() {
                return xNotaria;
            }

            /**
             * Define el valor de la propiedad xNotaria.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setXNotaria(String value) {
                this.xNotaria = value;
            }

            /**
             * Obtiene el valor de la propiedad xccApoderado.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getXCCApoderado() {
                return xccApoderado;
            }

            /**
             * Define el valor de la propiedad xccApoderado.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setXCCApoderado(String value) {
                this.xccApoderado = value;
            }

            /**
             * Obtiene el valor de la propiedad xNombreApoderado.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getXNombreApoderado() {
                return xNombreApoderado;
            }

            /**
             * Define el valor de la propiedad xNombreApoderado.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setXNombreApoderado(String value) {
                this.xNombreApoderado = value;
            }

            /**
             * Obtiene el valor de la propiedad xTipDocPoderdante.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getXTipDocPoderdante() {
                return xTipDocPoderdante;
            }

            /**
             * Define el valor de la propiedad xTipDocPoderdante.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setXTipDocPoderdante(String value) {
                this.xTipDocPoderdante = value;
            }

            /**
             * Obtiene el valor de la propiedad xccPoderdante.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getXCCPoderdante() {
                return xccPoderdante;
            }

            /**
             * Define el valor de la propiedad xccPoderdante.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setXCCPoderdante(String value) {
                this.xccPoderdante = value;
            }

            /**
             * Obtiene el valor de la propiedad xNombrePoderdante.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getXNombrePoderdante() {
                return xNombrePoderdante;
            }

            /**
             * Define el valor de la propiedad xNombrePoderdante.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setXNombrePoderdante(String value) {
                this.xNombrePoderdante = value;
            }

            /**
             * Obtiene el valor de la propiedad xNumMatriculaPoder.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getXNumMatriculaPoder() {
                return xNumMatriculaPoder;
            }

            /**
             * Define el valor de la propiedad xNumMatriculaPoder.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setXNumMatriculaPoder(String value) {
                this.xNumMatriculaPoder = value;
            }

            /**
             * Obtiene el valor de la propiedad xDireccionPredio.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getXDireccionPredio() {
                return xDireccionPredio;
            }

            /**
             * Define el valor de la propiedad xDireccionPredio.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setXDireccionPredio(String value) {
                this.xDireccionPredio = value;
            }

            /**
             * Obtiene el valor de la propiedad xDepartamentoPredio.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getXDepartamentoPredio() {
                return xDepartamentoPredio;
            }

            /**
             * Define el valor de la propiedad xDepartamentoPredio.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setXDepartamentoPredio(String value) {
                this.xDepartamentoPredio = value;
            }

            /**
             * Obtiene el valor de la propiedad xCiudadPredio.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getXCiudadPredio() {
                return xCiudadPredio;
            }

            /**
             * Define el valor de la propiedad xCiudadPredio.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setXCiudadPredio(String value) {
                this.xCiudadPredio = value;
            }

            /**
             * Obtiene el valor de la propiedad xNumeroInstrumento.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getXNumeroInstrumento() {
                return xNumeroInstrumento;
            }

            /**
             * Define el valor de la propiedad xNumeroInstrumento.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setXNumeroInstrumento(String value) {
                this.xNumeroInstrumento = value;
            }

            /**
             * Obtiene el valor de la propiedad xFechaInstrumento.
             *
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *
             */
            public XMLGregorianCalendar getXFechaInstrumento() {
                return xFechaInstrumento;
            }

            /**
             * Define el valor de la propiedad xFechaInstrumento.
             *
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *
             */
            public void setXFechaInstrumento(XMLGregorianCalendar value) {
                this.xFechaInstrumento = value;
            }

            /**
             * Obtiene el valor de la propiedad xEstadoPoder.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getXEstadoPoder() {
                return xEstadoPoder;
            }

            /**
             * Define el valor de la propiedad xEstadoPoder.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setXEstadoPoder(String value) {
                this.xEstadoPoder = value;
            }

            /**
             * Obtiene el valor de la propiedad xUsoPoder.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getXUsoPoder() {
                return xUsoPoder;
            }

            /**
             * Define el valor de la propiedad xUsoPoder.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setXUsoPoder(String value) {
                this.xUsoPoder = value;
            }

            /**
             * Obtiene el valor de la propiedad xCirculoPredio.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getXCirculoPredio() {
                return xCirculoPredio;
            }

            /**
             * Define el valor de la propiedad xCirculoPredio.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setXCirculoPredio(String value) {
                this.xCirculoPredio = value;
            }

            /**
             * Obtiene el valor de la propiedad xFinalizacionPoder.
             *
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *
             */
            public XMLGregorianCalendar getXFinalizacionPoder() {
                return xFinalizacionPoder;
            }

            /**
             * Define el valor de la propiedad xFinalizacionPoder.
             *
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *
             */
            public void setXFinalizacionPoder(XMLGregorianCalendar value) {
                this.xFinalizacionPoder = value;
            }

            /**
             * Obtiene el valor de la propiedad xJustificacionUsado.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getXJustificacionUsado() {
                return xJustificacionUsado;
            }

            /**
             * Define el valor de la propiedad xJustificacionUsado.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setXJustificacionUsado(String value) {
                this.xJustificacionUsado = value;
            }

            /**
             * Obtiene el valor de la propiedad xDiligenciaReconocimiento.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getXDiligenciaReconocimiento() {
                return xDiligenciaReconocimiento;
            }

            /**
             * Define el valor de la propiedad xDiligenciaReconocimiento.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setXDiligenciaReconocimiento(String value) {
                this.xDiligenciaReconocimiento = value;
            }

            /**
             * Obtiene el valor de la propiedad xFechaCarguePoder.
             *
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *
             */
            public XMLGregorianCalendar getXFechaCarguePoder() {
                return xFechaCarguePoder;
            }

            /**
             * Define el valor de la propiedad xFechaCarguePoder.
             *
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *
             */
            public void setXFechaCarguePoder(XMLGregorianCalendar value) {
                this.xFechaCarguePoder = value;
            }

            /**
             * Obtiene el valor de la propiedad xNotificaciones.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getXNotificaciones() {
                return xNotificaciones;
            }

            /**
             * Define el valor de la propiedad xNotificaciones.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setXNotificaciones(String value) {
                this.xNotificaciones = value;
            }

            /**
             * Obtiene el valor de la propiedad urlVisor.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getUrlVisor() {
                return urlVisor;
            }

            /**
             * Define el valor de la propiedad urlVisor.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setUrlVisor(String value) {
                this.urlVisor = value;
            }

            /**
             * Obtiene el valor de la propiedad xTipoDocumento.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getXTipoDocumento() {
                return xTipoDocumento;
            }

            /**
             * Define el valor de la propiedad xTipoDocumento.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setXTipoDocumento(String value) {
                this.xTipoDocumento = value;
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

    }

}
