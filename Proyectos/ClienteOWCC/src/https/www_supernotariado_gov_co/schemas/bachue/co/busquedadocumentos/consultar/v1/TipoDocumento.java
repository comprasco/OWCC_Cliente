/*
 *
 */

package https.www_supernotariado_gov_co.schemas.bachue.co.busquedadocumentos.consultar.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para tipoDocumento complex type.
 *
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 *
 * <pre>
 * &lt;complexType name="tipoDocumento"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="docName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="tipoDocumental" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="orip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nir" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codOrip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="turno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="documentoRegistrado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numeroDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fechaDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="entidadOrigen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="departamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="municipio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="matricula" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="anotacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numeroPagina" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numeroFolios" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nombreInterviniente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificacionInterviniente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fechaPublicacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fechaVigencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipoOficina" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="actoNaturalezaJuridica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="proceso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nirVinculado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="turnoVinculado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="urlVisor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tipoDocumento", propOrder = {
    "did",
    "docName",
    "tipoDocumental",
    "orip",
    "nir",
    "codOrip",
    "turno",
    "documentoRegistrado",
    "numeroDocumento",
    "fechaDocumento",
    "entidadOrigen",
    "pais",
    "departamento",
    "municipio",
    "matricula",
    "anotacion",
    "numeroPagina",
    "numeroFolios",
    "nombreInterviniente",
    "identificacionInterviniente",
    "fechaPublicacion",
    "fechaVigencia",
    "tipoOficina",
    "actoNaturalezaJuridica",
    "proceso",
    "nirVinculado",
    "turnoVinculado",
    "urlVisor"
})
public class TipoDocumento {

    /** The did. */
    @XmlElement(name = "dID", required = true)
    protected String did;

    /** The doc name. */
    @XmlElement(required = true)
    protected String docName;

    /** The tipo documental. */
    @XmlElementRef(name = "tipoDocumental", namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", type = JAXBElement.class, required = false)
    protected String tipoDocumental;

    /** The orip. */
    @XmlElementRef(name = "orip", namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", type = JAXBElement.class, required = false)
    protected String orip;

    /** The nir. */
    @XmlElementRef(name = "nir", namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", type = JAXBElement.class, required = false)
    protected String nir;

    /** The cod orip. */
    @XmlElementRef(name = "codOrip", namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", type = JAXBElement.class, required = false)
    protected String codOrip;

    /** The turno. */
    @XmlElementRef(name = "turno", namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", type = JAXBElement.class, required = false)
    protected String turno;

    /** The documento registrado. */
    @XmlElementRef(name = "documentoRegistrado", namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", type = JAXBElement.class, required = false)
    protected String documentoRegistrado;

    /** The numero documento. */
    @XmlElementRef(name = "numeroDocumento", namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", type = JAXBElement.class, required = false)
    protected String numeroDocumento;

    /** The fecha documento. */
    @XmlElementRef(name = "fechaDocumento", namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", type = JAXBElement.class, required = false)
    protected String fechaDocumento;

    /** The entidad origen. */
    @XmlElementRef(name = "entidadOrigen", namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", type = JAXBElement.class, required = false)
    protected String entidadOrigen;

    /** The pais. */
    @XmlElementRef(name = "pais", namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", type = JAXBElement.class, required = false)
    protected String pais;

    /** The departamento. */
    @XmlElementRef(name = "departamento", namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", type = JAXBElement.class, required = false)
    protected String departamento;

    /** The municipio. */
    @XmlElementRef(name = "municipio", namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", type = JAXBElement.class, required = false)
    protected String municipio;

    /** The matricula. */
    @XmlElementRef(name = "matricula", namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", type = JAXBElement.class, required = false)
    protected String matricula;

    /** The anotacion. */
    @XmlElementRef(name = "anotacion", namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", type = JAXBElement.class, required = false)
    protected String anotacion;

    /** The numero pagina. */
    @XmlElementRef(name = "numeroPagina", namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", type = JAXBElement.class, required = false)
    protected String numeroPagina;

    /** The numero folios. */
    @XmlElementRef(name = "numeroFolios", namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", type = JAXBElement.class, required = false)
    protected String numeroFolios;

    /** The nombre interviniente. */
    @XmlElementRef(name = "nombreInterviniente", namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", type = JAXBElement.class, required = false)
    protected String nombreInterviniente;

    /** The identificacion interviniente. */
    @XmlElementRef(name = "identificacionInterviniente", namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", type = JAXBElement.class, required = false)
    protected String identificacionInterviniente;

    /** The fecha publicacion. */
    @XmlElementRef(name = "fechaPublicacion", namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", type = JAXBElement.class, required = false)
    protected String fechaPublicacion;

    /** The fecha vigencia. */
    @XmlElementRef(name = "fechaVigencia", namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", type = JAXBElement.class, required = false)
    protected String fechaVigencia;

    /** The tipo oficina. */
    @XmlElementRef(name = "tipoOficina", namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", type = JAXBElement.class, required = false)
    protected String tipoOficina;

    /** The acto naturaleza juridica. */
    @XmlElementRef(name = "actoNaturalezaJuridica", namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", type = JAXBElement.class, required = false)
    protected String actoNaturalezaJuridica;

    /** The proceso. */
    @XmlElementRef(name = "proceso", namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", type = JAXBElement.class, required = false)
    protected String proceso;

    /** The nir vinculado. */
    @XmlElementRef(name = "nirVinculado", namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", type = JAXBElement.class, required = false)
    protected String nirVinculado;

    /** The turno vinculado. */
    @XmlElementRef(name = "turnoVinculado", namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", type = JAXBElement.class, required = false)
    protected String turnoVinculado;

    /** The url visor. */
    @XmlElementRef(name = "urlVisor", namespace = "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", type = JAXBElement.class, required = false)
    protected String urlVisor;

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
     * Obtiene el valor de la propiedad tipoDocumental.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public String getTipoDocumental() {
        return tipoDocumental;
    }

    /**
     * Define el valor de la propiedad tipoDocumental.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setTipoDocumental(String value) {
        this.tipoDocumental = value;
    }

    /**
     * Obtiene el valor de la propiedad orip.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public String getOrip() {
        return orip;
    }

    /**
     * Define el valor de la propiedad orip.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setOrip(String value) {
        this.orip = value;
    }

    /**
     * Obtiene el valor de la propiedad nir.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public String getNir() {
        return nir;
    }

    /**
     * Define el valor de la propiedad nir.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setNir(String value) {
        this.nir = value;
    }

    /**
     * Obtiene el valor de la propiedad codOrip.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public String getCodOrip() {
        return codOrip;
    }

    /**
     * Define el valor de la propiedad codOrip.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setCodOrip(String value) {
        this.codOrip = value;
    }

    /**
     * Obtiene el valor de la propiedad turno.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public String getTurno() {
        return turno;
    }

    /**
     * Define el valor de la propiedad turno.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setTurno(String value) {
        this.turno = value;
    }

    /**
     * Obtiene el valor de la propiedad documentoRegistrado.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public String getDocumentoRegistrado() {
        return documentoRegistrado;
    }

    /**
     * Define el valor de la propiedad documentoRegistrado.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setDocumentoRegistrado(String value) {
        this.documentoRegistrado = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroDocumento.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * Define el valor de la propiedad numeroDocumento.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setNumeroDocumento(String value) {
        this.numeroDocumento = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaDocumento.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public String getFechaDocumento() {
        return fechaDocumento;
    }

    /**
     * Define el valor de la propiedad fechaDocumento.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setFechaDocumento(String value) {
        this.fechaDocumento = value;
    }

    /**
     * Obtiene el valor de la propiedad entidadOrigen.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public String getEntidadOrigen() {
        return entidadOrigen;
    }

    /**
     * Define el valor de la propiedad entidadOrigen.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setEntidadOrigen(String value) {
        this.entidadOrigen = value;
    }

    /**
     * Obtiene el valor de la propiedad pais.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public String getPais() {
        return pais;
    }

    /**
     * Define el valor de la propiedad pais.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setPais(String value) {
        this.pais = value;
    }

    /**
     * Obtiene el valor de la propiedad departamento.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Define el valor de la propiedad departamento.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setDepartamento(String value) {
        this.departamento = value;
    }

    /**
     * Obtiene el valor de la propiedad municipio.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * Define el valor de la propiedad municipio.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setMunicipio(String value) {
        this.municipio = value;
    }

    /**
     * Obtiene el valor de la propiedad matricula.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Define el valor de la propiedad matricula.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setMatricula(String value) {
        this.matricula = value;
    }

    /**
     * Obtiene el valor de la propiedad anotacion.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public String getAnotacion() {
        return anotacion;
    }

    /**
     * Define el valor de la propiedad anotacion.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setAnotacion(String value) {
        this.anotacion = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroPagina.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public String getNumeroPagina() {
        return numeroPagina;
    }

    /**
     * Define el valor de la propiedad numeroPagina.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setNumeroPagina(String value) {
        this.numeroPagina = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroFolios.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public String getNumeroFolios() {
        return numeroFolios;
    }

    /**
     * Define el valor de la propiedad numeroFolios.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setNumeroFolios(String value) {
        this.numeroFolios = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreInterviniente.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public String getNombreInterviniente() {
        return nombreInterviniente;
    }

    /**
     * Define el valor de la propiedad nombreInterviniente.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setNombreInterviniente(String value) {
        this.nombreInterviniente = value;
    }

    /**
     * Obtiene el valor de la propiedad identificacionInterviniente.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public String getIdentificacionInterviniente() {
        return identificacionInterviniente;
    }

    /**
     * Define el valor de la propiedad identificacionInterviniente.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setIdentificacionInterviniente(String value) {
        this.identificacionInterviniente = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaPublicacion.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    /**
     * Define el valor de la propiedad fechaPublicacion.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setFechaPublicacion(String value) {
        this.fechaPublicacion = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaVigencia.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public String getFechaVigencia() {
        return fechaVigencia;
    }

    /**
     * Define el valor de la propiedad fechaVigencia.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setFechaVigencia(String value) {
        this.fechaVigencia = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoOficina.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public String getTipoOficina() {
        return tipoOficina;
    }

    /**
     * Define el valor de la propiedad tipoOficina.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setTipoOficina(String value) {
        this.tipoOficina = value;
    }

    /**
     * Obtiene el valor de la propiedad actoNaturalezaJuridica.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public String getActoNaturalezaJuridica() {
        return actoNaturalezaJuridica;
    }

    /**
     * Define el valor de la propiedad actoNaturalezaJuridica.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setActoNaturalezaJuridica(String value) {
        this.actoNaturalezaJuridica = value;
    }

    /**
     * Obtiene el valor de la propiedad proceso.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public String getProceso() {
        return proceso;
    }

    /**
     * Define el valor de la propiedad proceso.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setProceso(String value) {
        this.proceso = value;
    }

    /**
     * Obtiene el valor de la propiedad nirVinculado.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public String getNirVinculado() {
        return nirVinculado;
    }

    /**
     * Define el valor de la propiedad nirVinculado.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setNirVinculado(String value) {
        this.nirVinculado = value;
    }

    /**
     * Obtiene el valor de la propiedad turnoVinculado.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public String getTurnoVinculado() {
        return turnoVinculado;
    }

    /**
     * Define el valor de la propiedad turnoVinculado.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setTurnoVinculado(String value) {
        this.turnoVinculado = value;
    }

    /**
     * Obtiene el valor de la propiedad urlVisor.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setUrlVisor(String value) {
        this.urlVisor = value;
    }

}
