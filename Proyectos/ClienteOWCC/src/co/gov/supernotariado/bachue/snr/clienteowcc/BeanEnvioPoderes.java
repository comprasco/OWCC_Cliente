/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.supernotariado.bachue.snr.clienteowcc;

import org.apache.log4j.Logger;


import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.jws.HandlerChain;
import javax.jws.WebParam;

import javax.jws.WebService;
import javax.xml.ws.BindingType;

import https.www_supernotariado_gov_co.schemas.bachue.co.enviopoderes.enviarpoder.v1.*;

/**
 *
 * @author consultor
 */
@WebService(serviceName = "BS_SUT_CO_EnvioPoderes", portName = "BS_SUT_CO_EnvioPoderesPort", endpointInterface = "https.www_supernotariado_gov_co.services.bachue.co.enviopoderes.v1.BSSUTCOEnvioPoderes", targetNamespace = "https://www.supernotariado.gov.co/services/bachue/co/enviopoderes/v1", wsdlLocation = "WEB-INF/wsdl/BeanEnvioPoderes/BS_SUT_CO_EnvioPoderes.wsdl")
@BindingType(value = "http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/")
@HandlerChain(file = "handler-chain.xml")
public class BeanEnvioPoderes {

	/** Variable para la gestion del log de salida. */
    private static final Logger log = Logger.getLogger(BeanEnvioPoderes.class);
    private static final String satisfactorio = "Satisfactorio";
    private static final String errorServidor = "Error Servidor";

    /**
    * Enviar Poder. Realiza el envio de un documento a Oracle WebCenter Content,
    * se debe indicar el sistema que lo envia CORE, CORRESPONDENCIA, SEDEELECTRONICA,
    * se debe indicar si es repositorio FINAL O TEMPRAL
    * y el arreglo de datos con los metadatos de los documento
    *
    * @param entrada, objeto entrada donde se especifica sistema de origen, tipo de repositorio, documento, nombre de archivo, metadatos y stream64 del documento a ingresar a content
    * @return TipoSalidaEnviarDocumento envia el codigo 200 satisfactorio, codigo 409 Falla Tecnica, codigo 500 error servidor.
    */

    public TipoSalidaEnviarPoder enviarPoder(@WebParam(name = "entrada") TipoEntradaEnviarPoder entrada) {
        TipoSalidaEnviarPoder respuesta = new TipoSalidaEnviarPoder();

        String salidaContent = "";
        String vDocName = "";
        String vDid = "";
 //REM       String vExpediente ="";
 //REM       int vNumExpediente = 0;
        int vValidacionSistema = 0;
        int vValidacionArchivo = 0;
        int vValidacionNombreArchivo = 0;



        //obtiene el año
		Date fecha=new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);

        log.debug("--------------------------------------------------");
        log.debug("Iniciando nueva operacion de Chequeo Poder");
        log.debug("--------------------------------------------------");
        log.debug("Leyendo Propiedades");

      //VALIDACION DE ATRIBUTOS

        if ( entrada.getSistemaOrigen().contentEquals("SEDE_ELECTRONICA") ) {
        	vValidacionSistema = 1;
        }else {
        	vValidacionSistema = 0;
        }
        if ( !entrada.getNombreArchivo().isEmpty() || !entrada.getNombreArchivo().equals("") ) {
        	vValidacionNombreArchivo = 1;
        }else {
        	vValidacionNombreArchivo = 0;
        }
        if ( entrada.getArchivo().length > 0  ) {
        	vValidacionArchivo = 1;
        }else {
        	vValidacionArchivo = 0;
        }

        // Cargar las variables de la base de datos
        ClienteOwcc.cargarVariables();
        log.debug("Recibiendo Archivo a Procesar");
        try {
        	ClienteOwcc.recibeArchivo ( entrada.getNombreArchivo() ,entrada.getArchivo() );
        } catch (Exception ex) {
            log.debug(ex);
        }

        if ( vValidacionSistema ==  1 && vValidacionArchivo == 1 && vValidacionNombreArchivo == 1) {
            //si todas las validaciones son activas proceder a hacer el checkin del documento
        	log.debug("Entrando a Content Checkin Poder");
        	salidaContent = ClienteOwcc.checkinPoder( entrada.getNombreArchivo() , "PODERES" , entrada.getParametros() , "PODERES" );
        }
	    else {
	      	salidaContent = "Validar Sistema Origen, archivo, u otros parametros" ;
	    }

        log.debug("Salida de Content al checkin de poder es " + salidaContent );
        if ( salidaContent.contains("-")){
           String[] partes = salidaContent.split("-");
           vDocName = partes[0];
           vDid = partes[1];

           log.debug("Generando Respuesta ");
           respuesta.setDocName(vDocName);
           respuesta.setDID(Integer.parseInt(vDid));
           respuesta.setCodigoMensaje(200);
           respuesta.setDescripcionMensaje(satisfactorio);


           File archivoEliminar = new File(ClienteOwcc.getTmpDir() + "/" + entrada.getNombreArchivo());
            try {
            	ClienteOwcc.eliminarArchivo(archivoEliminar);
                log.debug("Archivo Eliminado");
            } catch (IOException ex) {
                log.debug(ex);
            }
        }
        else if ( salidaContent.contains("null") ){
        	log.debug("Se encontro un error");
            respuesta.setCodigoMensaje(409);
            respuesta.setDescripcionMensaje(salidaContent.toString());
            File archivoEliminar = new File(ClienteOwcc.getTmpDir() + "/" + entrada.getNombreArchivo());
            try {
            	ClienteOwcc.eliminarArchivo(archivoEliminar);
                log.debug("Archivo Eliminado");
            } catch (IOException ex) {
                log.debug(ex);
            }

        }
        else if ( salidaContent.contains("Atributo") ){
        	log.debug("Se encontro un error");
            respuesta.setCodigoMensaje(409);
            respuesta.setDescripcionMensaje(salidaContent.toString());
            File archivoEliminar = new File(ClienteOwcc.getTmpDir() + "/" + entrada.getNombreArchivo());
            try {
            	ClienteOwcc.eliminarArchivo(archivoEliminar);
                log.debug("Archivo Eliminado");
            } catch (IOException ex) {
                log.debug(ex);
            }

        }
        else if ( salidaContent.contains("FALTA") ){
        	log.debug("El archivo es por falta de atributos obligatorios");
            respuesta.setCodigoMensaje(409);
            respuesta.setDescripcionMensaje(salidaContent.toString());
            File archivoEliminar = new File(ClienteOwcc.getTmpDir() + "/" + entrada.getNombreArchivo());
            try {
            	ClienteOwcc.eliminarArchivo(archivoEliminar);
                log.debug("Archivo Eliminado");
            } catch (IOException ex) {
                log.debug(ex);
            }

        }
        else if ( salidaContent.contains("Validar") ){
        	log.debug("Falta validar atributos de entrada");
            respuesta.setCodigoMensaje(409);
            respuesta.setDescripcionMensaje(salidaContent.toString());
            File archivoEliminar = new File(ClienteOwcc.getTmpDir() + "/" + entrada.getNombreArchivo());
            try {
            	ClienteOwcc.eliminarArchivo(archivoEliminar);
                log.debug("Archivo Eliminado");
            } catch (IOException ex) {
                log.debug(ex);
            }

        }
        else if ( salidaContent.contains("Atributo") ){
        	log.debug("Se encontro un error");
            respuesta.setCodigoMensaje(409);
            respuesta.setDescripcionMensaje(salidaContent.toString());
            File archivoEliminar = new File(ClienteOwcc.getTmpDir() + "/" + entrada.getNombreArchivo());
            try {
            	ClienteOwcc.eliminarArchivo(archivoEliminar);
                log.debug("Archivo Eliminado");
            } catch (IOException ex) {
                log.debug(ex);
            }

        }
        else {
        	log.debug("Se encontro una falla tecnica");
            respuesta.setCodigoMensaje(500);
            respuesta.setDescripcionMensaje(errorServidor);
            File archivoEliminar = new File(ClienteOwcc.getTmpDir() + "/" + entrada.getNombreArchivo());
            try {
            	ClienteOwcc.eliminarArchivo(archivoEliminar);
                log.debug("Archivo Eliminado");
            } catch (IOException ex) {
                log.debug(ex);
            }
        }
       return respuesta;
    }


}
