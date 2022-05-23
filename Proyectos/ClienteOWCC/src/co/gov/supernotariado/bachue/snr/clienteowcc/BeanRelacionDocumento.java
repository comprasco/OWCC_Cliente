/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.supernotariado.bachue.snr.clienteowcc;

import java.io.IOException;
import java.math.BigInteger;
import org.apache.log4j.Logger;

import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import https.www_supernotariado_gov_co.schemas.bachue.co.relacionesdocumento.relacionardocumento.v1.TipoEntradaRelacionarDocumento;
import https.www_supernotariado_gov_co.schemas.bachue.co.relacionesdocumento.relacionardocumento.v1.TipoSalidaRelacionarDocumento;
import oracle.stellent.ridc.IdcClientException;

/**
 * Contiene el metodo que permiten relacionar documentos,
 * existentes en content con uno o varios expedientes almacenados en el servidor de Oracle WebCenter Contet
 *
 *
 * @author DataTools
 */
@WebService(serviceName = "SUT_CO_RelacionesDocumento", portName = "SUT_CO_RelacionesDocumentoPort", endpointInterface = "https.www_supernotariado_gov_co.services.bachue.co.relacionesdocumento.v1.SUTCORelacionesDocumento", targetNamespace = "https://www.supernotariado.gov.co/services/bachue/co/relacionesdocumento/v1", wsdlLocation = "WEB-INF/wsdl/BeanRelacionDocumento/BS_SUT_CO_RelacionesDocumento.wsdl")
@BindingType(value = "http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/")
@HandlerChain(file = "handler-chain.xml")
public class BeanRelacionDocumento {

    /** The Constant log. */
    private static final Logger log = Logger.getLogger(BeanRelacionDocumento.class);
    private static final String satisfactorio = "Satisfactorio";
    private static final String errorServidor = "Error Servidor";


    /**
     * Relacionar documento.  Este metodo relaciona el dDocName de un documento con un xTURNO existente en Oracle WebCenter Content
     *
     * @param entrada, se debe especificar obligatoriamente el dDocName del documento y el xTURNO a asociar
     * @return TipoSalidaRelacionarDocumento retorna 200 si fue satisfactoria la operacion, 409 si fue falla tecnica o 500 si hubo error de servidor
     * @throws IdcClientException que se presento un error conectando a Oracle WebCenter Content
     * @throws IOException indica que una excepcion de I/O ha ocurrido.
     */
    @SuppressWarnings("unused")
	public TipoSalidaRelacionarDocumento relacionarDocumento(TipoEntradaRelacionarDocumento entrada) throws IdcClientException, IOException {

        TipoSalidaRelacionarDocumento respuesta = new TipoSalidaRelacionarDocumento();

        String salidaContent = "";
        String vDocName = "";
        String vDid = "";
        String vExpediente = "0";
        int vValidacionSistema = 0;
        int vValidacionGetDid = 0;
        int vValidacionGetDDocName = 0;

        log.debug("--------------------------------------------------");
        log.debug("Iniciando nueva operacion de Relacionar Documentos");
        log.debug("--------------------------------------------------");
        log.debug("Leyendo Propiedades");

        ClienteOwcc.cargarVariables();

        try {
            ClienteOwcc.RIDConnection();
        } catch (IdcClientException ex) {
            log.debug("Error" + ex);
        }

        if ( entrada.getSistemaOrigen().contentEquals("CORE") || entrada.getSistemaOrigen().contentEquals("CORRESPONDENCIA") || entrada.getSistemaOrigen().contentEquals("SEDE_ELECTRONICA") ) {
        	vValidacionSistema = 1;
        }else {
        	vValidacionSistema = 0;
        }
        if ( !entrada.getDID().isEmpty() || !entrada.getDID().equals("") ) {
        	vValidacionGetDid = 1;
        }else {
        	vValidacionGetDid = 0;
        }
        if ( !entrada.getDocName().isEmpty() || !entrada.getDocName().equals("") ) {
        	vValidacionGetDDocName = 1;
        }else {
        	vValidacionGetDDocName = 0;
        }

        log.debug("Entrando a Relacionar Documento");

        if (vValidacionSistema == 1 && vValidacionGetDDocName == 1 && vValidacionGetDid == 1) {
            salidaContent = ClienteOwcc.relacionDocumento (  entrada.getParametros() , entrada.getDID() , entrada.getDocName() , entrada.getSistemaOrigen() );
        }else {
        	salidaContent = "Falta Diligenciar atributos oblligatorios";
        }
        if ( salidaContent.contains("-")){
            String[] partes = salidaContent.split("-");
            vDocName = partes[0];
            vDid = partes[1];
            log.debug("Generando Respuesta ");
            respuesta.setCodigoMensaje(BigInteger.valueOf(200));
            respuesta.setDescripcionMensaje(satisfactorio);
         }
         else if ( salidaContent.contains("null") ){
         	log.debug("Se encontro un falla tecnica");
             respuesta.setCodigoMensaje(BigInteger.valueOf(409));
             respuesta.setDescripcionMensaje(salidaContent.toString());
         }
         else if ( salidaContent.contains("Falta") ){
          	log.debug("Se encontro un falla tecnica");
              respuesta.setCodigoMensaje(BigInteger.valueOf(409));
              respuesta.setDescripcionMensaje(salidaContent.toString());
          }
         else {
         	log.debug("Se encontro una falla Error");
             respuesta.setCodigoMensaje(BigInteger.valueOf(500));
             respuesta.setDescripcionMensaje(errorServidor);
         }
        return respuesta;

    }
}
