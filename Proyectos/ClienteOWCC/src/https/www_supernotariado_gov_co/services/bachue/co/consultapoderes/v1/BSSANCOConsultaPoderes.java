
package https.www_supernotariado_gov_co.services.bachue.co.consultapoderes.v1;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import https.www_supernotariado_gov_co.schemas.bachue.co.consultapoderes.consultarpoder.v1.TipoEntradaConsultarPoder;
import https.www_supernotariado_gov_co.schemas.bachue.co.consultapoderes.consultarpoder.v1.TipoSalidaConsultarPoder;
import https.www_supernotariado_gov_co.schemas.bachue.co.consultapoderes.obtenerpoder.v1.TipoEntradaObtenerPoder;
import https.www_supernotariado_gov_co.schemas.bachue.co.consultapoderes.obtenerpoder.v1.TipoSalidaObtenerPoder;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.11-b150120.1832
 * Generated source version: 2.2
 * 
 */
@WebService(name = "BS_SAN_CO_ConsultaPoderes", targetNamespace = "https://www.supernotariado.gov.co/services/bachue/co/consultapoderes/v1")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    https.www_supernotariado_gov_co.schemas.bachue.co.consultapoderes.consultarpoder.v1.ObjectFactory.class,
    https.www_supernotariado_gov_co.schemas.bachue.co.consultapoderes.obtenerpoder.v1.ObjectFactory.class
})
public interface BSSANCOConsultaPoderes {


    /**
     * 
     * @param entrada
     * @return
     *     returns https.www_supernotariado_gov_co.schemas.bachue.co.consultapoderes.consultarpoder.v1.TipoSalidaConsultarPoder
     */
    @WebMethod(operationName = "ConsultarPoder", action = "https://www.supernotariado.gov.co/services/bachue/co/consultapoderes/v1/ConsultarPoder")
    @WebResult(name = "salidaConsultarPoder", targetNamespace = "https://www.supernotariado.gov.co/schemas/bachue/co/consultapoderes/consultarpoder/v1", partName = "salida")
    public TipoSalidaConsultarPoder consultarPoder(
        @WebParam(name = "entradaConsultarPoder", targetNamespace = "https://www.supernotariado.gov.co/schemas/bachue/co/consultapoderes/consultarpoder/v1", partName = "entrada")
        TipoEntradaConsultarPoder entrada);

    /**
     * 
     * @param entrada
     * @return
     *     returns https.www_supernotariado_gov_co.schemas.bachue.co.consultapoderes.obtenerpoder.v1.TipoSalidaObtenerPoder
     */
    @WebMethod(operationName = "ObtenerPoder", action = "https://www.supernotariado.gov.co/services/bachue/co/consultapoderes/v1/ObtenerPoder")
    @WebResult(name = "salidaObtenerPoder", targetNamespace = "https://www.supernotariado.gov.co/schemas/bachue/co/consultapoderes/obtenerpoder/v1", partName = "salida")
    public TipoSalidaObtenerPoder obtenerPoder(
        @WebParam(name = "entradaObtenerPoder", targetNamespace = "https://www.supernotariado.gov.co/schemas/bachue/co/consultapoderes/obtenerpoder/v1", partName = "entrada")
        TipoEntradaObtenerPoder entrada);

}
