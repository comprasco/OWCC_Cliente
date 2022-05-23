/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.supernotariado.bachue.snr.clienteowcc;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.jws.HandlerChain;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.BindingType;

import org.apache.log4j.Logger;

import https.www_supernotariado_gov_co.schemas.bachue.co.actualizacionmetadatospoderes.actualizarmetadatospoder.v1.TipoSalidaActualizarMetadatosPoder;
import https.www_supernotariado_gov_co.schemas.bachue.co.actualizacionmetadatospoderes.actualizarmetadatospoder.v1.TipoEntradaActualizarMetadatosPoder;


/**
 *
 * @author consultor
 */
@WebService(serviceName = "BS_SAN_CO_ActualizacionMetadatosPoderes", portName = "BS_SAN_CO_ActualizacionMetadatosPoderesPort", endpointInterface = "https.www_supernotariado_gov_co.services.bachue.co.actualizacionmetadatospoderes.v1.BSSANCOActualizacionMetadatosPoderes", targetNamespace = "https://www.supernotariado.gov.co/services/bachue/co/actualizacionmetadatospoderes/v1", wsdlLocation = "WEB-INF/wsdl/BeanActualizacionMetaPoderes/BS_SAN_CO_ActualizacionMetadatosPoderes.wsdl")
@BindingType(value = "http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/")
@HandlerChain(file = "handler-chain.xml")
public class BeanActualizacionMetaPoderes {

	/** Variable para la gestion del log de salida. */
    private static final Logger log = Logger.getLogger(BeanEnvio.class);
    private static final String satisfactorio = "Satisfactorio";
    private static final String errorServidor = "Error Servidor";

    public TipoSalidaActualizarMetadatosPoder actualizarMetadatosPoder( @WebParam(name = "entrada") TipoEntradaActualizarMetadatosPoder entrada) {

    	TipoSalidaActualizarMetadatosPoder respuesta = new TipoSalidaActualizarMetadatosPoder();

    	String salidaContent = "";
        String vDocName = "";
        String vDid = "";
        int vValidacionSistema = 0;
        int vValidacionDDocName = 0;

		Date fecha=new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);

        log.debug("--------------------------------------------------");
        log.debug("Iniciando nueva operacion de Actualizacion de Metadatos de Poderes");
        log.debug("--------------------------------------------------");
        log.debug("Leyendo Propiedades");

        // Cargar las variables de la base de datos
        ClienteOwcc.cargarVariables();

        log.debug("Entrando a Actualizar Metadatos Poder");

        if ( entrada.getSistemaOrigen().contentEquals("CORE") || entrada.getSistemaOrigen().contentEquals("CORRESPONDENCIA") || entrada.getSistemaOrigen().contentEquals("SEDE_ELECTRONICA") ) {
        	vValidacionSistema = 1;
        }else {
        	vValidacionSistema = 0;
        }
        if ( !entrada.getDDocName().isEmpty() ) {
        	vValidacionDDocName = 1;
        }else {
        	vValidacionDDocName = 0;
        }

        if ( vValidacionSistema == 1 && vValidacionDDocName == 1) {

	        try {
				salidaContent = ClienteOwcc.actualizarMetadatosPoder( entrada.getSistemaOrigen() , entrada.getParametros() , entrada.getDDocName() );
			} catch (IOException e) {
				log.debug("Excepcion en actualizacion de Metadatos  " + e );
			}
        }
        else {
        	salidaContent = "Falta diligenciar Sistema Origen o numero de documento";
        }

        log.debug("Salida de Content es " + salidaContent );
        if ( salidaContent.contains("-")){
           String[] partes = salidaContent.split("-");
           vDocName = partes[0];
           vDid = partes[1];

           //Determinacion de Expedientes

           log.debug("Generando Respuesta ");
           respuesta.setDocName(vDocName);;
           respuesta.setDID(Integer.valueOf(vDid));
           respuesta.setCodigoMensaje(200);
           respuesta.setDescripcionMensaje(satisfactorio);

        }
        else if ( salidaContent.contains("null") ){
        	log.debug("Se encontro un error");
            respuesta.setCodigoMensaje(409);
            respuesta.setDescripcionMensaje(salidaContent.toString());
        }
        else if ( salidaContent.contains("Atributo") ){
        	log.debug("Se encontro un error");
            respuesta.setCodigoMensaje(409);
            respuesta.setDescripcionMensaje(salidaContent.toString());
        }
        else if ( salidaContent.contains("Falta") ){
        	log.debug("Se encontro un error de atributos obligatorios");
            respuesta.setCodigoMensaje(409);
            respuesta.setDescripcionMensaje(salidaContent.toString());
        }
        else if ( salidaContent.equals("") ){
        	log.debug("Documento no existe");
        	salidaContent = "Documento no existe";
            respuesta.setCodigoMensaje(409);
            respuesta.setDescripcionMensaje(salidaContent);
        }
        else {
        	log.debug("Se encontro una falla tecnica");
            respuesta.setCodigoMensaje(500);
            respuesta.setDescripcionMensaje(errorServidor);
        }
       return respuesta;
    }

}
