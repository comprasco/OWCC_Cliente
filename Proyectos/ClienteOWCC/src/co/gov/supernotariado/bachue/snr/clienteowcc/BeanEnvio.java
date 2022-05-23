/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.supernotariado.bachue.snr.clienteowcc;

import org.apache.log4j.Logger;


import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.jws.HandlerChain;
import javax.jws.WebService;


import javax.xml.ws.BindingType;

import https.www_supernotariado_gov_co.schemas.bachue.co.enviodocumentos.enviardocumento.v1.TipoEntradaEnviarDocumento;
import https.www_supernotariado_gov_co.schemas.bachue.co.enviodocumentos.enviardocumento.v1.TipoSalidaEnviarDocumento;
import oracle.stellent.ridc.IdcClientException;

/**
 * Esta clase realiza el envio de un documento a Oracle WebCenter Content,
 * Utiliza el metodo enviarDocumento para dicho fin.
 *
 * @author DataTools
 */
@WebService(serviceName = "SUT_CO_EnvioDocumentos", portName = "SUT_CO_EnvioDocumentosPort", endpointInterface = "https.www_supernotariado_gov_co.services.bachue.co.enviodocumentos.v1.SUTCOEnvioDocumentos", targetNamespace = "https://www.supernotariado.gov.co/services/bachue/co/enviodocumentos/v1", wsdlLocation = "WEB-INF/wsdl/BeanEnvio/BS_SUT_CO_EnvioDocumentos.wsdl")
@BindingType(value = "http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/")
@HandlerChain(file = "handler-chain.xml")
public class BeanEnvio {

    /** Variable para la gestion del log de salida. */
    private static final Logger log = Logger.getLogger(BeanEnvio.class);
    private static final String satisfactorio = "Satisfactorio";
    private static final String errorServidor = "Error Servidor";

    /**
    * Enviar documento. Realiza el envio de un documento a Oracle WebCenter Content,
    * se debe indicar el sistema que lo envia CORE, CORRESPONDENCIA, SEDEELECTRONICA,
    * se debe indicar si es repositorio FINAL O TEMPRAL
    * y el arreglo de datos con los metadatos de los documento
    *
    * @param entrada, objeto entrada donde se especifica sistema de origen, tipo de repositorio, documento, nombre de archivo, metadatos y stream64 del documento a ingresar a content
    * @return TipoSalidaEnviarDocumento envia el codigo 200 satisfactorio, codigo 409 Falla Tecnica, codigo 500 error servidor.
    */
    public TipoSalidaEnviarDocumento enviarDocumento(TipoEntradaEnviarDocumento entrada) {

        TipoSalidaEnviarDocumento respuesta = new TipoSalidaEnviarDocumento();

        String salidaContent = "";
        String vDocName = "";
        String vDid = "";
        String vExpediente ="";
        int vNumExpediente = 0;
        int vValidacionSistema = 0;
        int vValidacionArchivo = 0;
        int vValidacionRepositorio = 0;
        int vValidacionNombreArchivo = 0;


        //obtiene el año
		Date fecha=new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		int anio = calendar.get(Calendar.YEAR);

        log.debug("--------------------------------------------------");
        log.debug("Iniciando nueva operacion de Chequeo");
        log.debug("--------------------------------------------------");
        log.debug("Leyendo Propiedades");

        //VALIDACION DE ATRIBUTOS

        if ( entrada.getSistemaOrigen().contentEquals("CORE") || entrada.getSistemaOrigen().contentEquals("CORRESPONDENCIA") || entrada.getSistemaOrigen().contentEquals("SEDE_ELECTRONICA") ) {
        	vValidacionSistema = 1;
        }else {
        	vValidacionSistema = 0;
        }
        if ( entrada.getRepositorio().contentEquals("FINAL") || entrada.getRepositorio().contentEquals("TEMPORAL") ) {
        	vValidacionRepositorio = 1;
        }else {
        	vValidacionRepositorio = 0;
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
        	Timestamp docTitle = new Timestamp(System.currentTimeMillis());
        	String extension = "";
            //Separa el nombre y la extension  
        	int i = entrada.getNombreArchivo().lastIndexOf('.');
        	if (i > 0) {
        	    extension = entrada.getNombreArchivo().substring(i+1);
        	}
        	String nombreArchivo = entrada.getNombreArchivo().substring(0, i).toUpperCase();        	
        	entrada.setNombreArchivo( nombreArchivo.concat(" - " + docTitle.getTime()) +"."+ extension);
        	 log.debug("Nombre de Archivo a procesar " + nombreArchivo.concat(" - " + docTitle.getTime()) +"."+ extension);
        	ClienteOwcc.recibeArchivo ( entrada.getNombreArchivo() ,entrada.getArchivo() );
        } catch (Exception ex) {
            log.debug(ex);
        }

        log.debug("Entrando a Content Checkin Documento");

        if ( vValidacionSistema ==  1 && vValidacionArchivo == 1 && vValidacionNombreArchivo == 1 && vValidacionRepositorio == 1) {
              //si todas las validaciones son activas proceder a hacer el checkin del documento
              salidaContent = ClienteOwcc.checkinDocumento( entrada.getNombreArchivo() , entrada.getSistemaOrigen() , entrada.getParametros() , entrada.getRepositorio() );
        }
        else {
        	salidaContent = "Validar Sistema Origen, Repositorio, archivo, u otros parametros" ;
        }
        log.debug("Salida de Content es " + salidaContent );
        if ( salidaContent.contains("-")){
           String[] partes = salidaContent.split("-");
           vDocName = partes[0];
           vDid = partes[1];

           //Determinacion de Expedientes

           if (entrada.getSistemaOrigen().equalsIgnoreCase("CORE")) {
          	 if (entrada.getRepositorio().equalsIgnoreCase("FINAL")) {
          		 //Evaluacion por Procesos
          		 // EXPEDIENTES DE CONCILIACION
          		 if (ClienteOwcc.getIdProceso().equalsIgnoreCase("CONCILIACION")) {

          			log.debug("Buscando Expediente con identificador EXP-CONCILIACION-" + anio);
          			//Buscar el ddid del expediente de asociado al archivo
                     log.debug("Buscando Expediente de Conciliacion con identificador " + ClienteOwcc.getIdExpediente());
                     vExpediente = ClienteOwcc.existeDocumento("EXPEDIENTEELECTRONICO" , "CONCILIACION-" + anio , "EXP-");
                     log.debug("la Salida de la busqueda del Expediente es " + vExpediente );
                     if ( vExpediente.equalsIgnoreCase("0")){
                    	//NO EXISTE EXPEDIENTE - CREARLO
                         try {
                      	   log.debug("Creando Expediente con identificador EXP-CONCILIACION-" + anio);
                             vExpediente = ClienteOwcc.crearExpedienteElectronico("CONCILIACION-"+anio,"Conciliacion","sgdDocumentoBachue",entrada.getSistemaOrigen(),ClienteOwcc.getIdProceso(),"CONCILIACION " + Integer.toString(anio));
                             ClienteOwcc.adicionarArchivoExpediente(vDocName, vExpediente );
                             log.debug("Adicionado Documento a Expediente Nuevo" + ClienteOwcc.getIdExpediente());
                         } catch (IdcClientException ex) {
                             log.debug( ex);
                         } catch (UnsupportedEncodingException ex) {
                             log.debug(ex);
                         }

                     }
                     else {
                     //ADICIONAR DOCUMENTO A EXPEDIENTE EXISTENTE
                         try {
                      	   log.debug("Adicionando Documento a Expediente en Turno" + ClienteOwcc.getIdExpediente());
                      	   ClienteOwcc.adicionarArchivoExpediente(vDocName, vExpediente );
                          } catch (UnsupportedEncodingException ex) {
                              log.debug(ex);
                          }
                     }

          		 }
          		 //CREACION DE EXPEDIENTE DE BACHUE POR DEFECTO
          		 else {
          			 log.debug("ID PROCESO" + ClienteOwcc.getIdProceso());
          			//Buscar el ddid del expediente de asociado al archivo
                     log.debug("Buscando Expediente con identificador " + ClienteOwcc.getIdExpediente());
                     vExpediente = ClienteOwcc.existeDocumento("EXPEDIENTEELECTRONICO" , ClienteOwcc.getIdExpediente() , "EXP-");
                     log.debug("la Salida Expediente es " + vExpediente );

                     if ( vExpediente.equalsIgnoreCase("0") ){
                         try {
                      	   log.debug("Creando Expediente con identificador " + ClienteOwcc.getIdExpediente() + " En Categoria "  + ClienteOwcc.getIdCategoriaRetencion());
                             vExpediente = ClienteOwcc.crearExpedienteElectronico(ClienteOwcc.getIdExpediente(),"Turno","sgdDocumentoBachue",entrada.getSistemaOrigen(),ClienteOwcc.getIdProceso(),"");
                             ClienteOwcc.adicionarArchivoExpediente(vDocName, vExpediente );
                             log.debug("Adicionado Documento a Expediente Nuevo" + ClienteOwcc.getIdExpediente());
                         } catch (IdcClientException ex) {
                             log.debug( ex);
                         } catch (UnsupportedEncodingException ex) {
                             log.debug(ex);
                         }

                     }
                     else {
                         try {
                      	   log.debug("Adicionando Documento a Expediente en Turno" + ClienteOwcc.getIdExpediente());
                      	   ClienteOwcc.adicionarArchivoExpediente(vDocName, vExpediente );
                          } catch (UnsupportedEncodingException ex) {
                              log.debug(ex);
                          }
                     }


          		 }
          	 }

           }else if ( entrada.getSistemaOrigen().equalsIgnoreCase("CORRESPONDENCIA")){
        	   if (entrada.getRepositorio().equalsIgnoreCase("FINAL")) {
		         // EXPEDIENTES DE CORRESPONDENCIA

		    	    log.debug("Buscando Expediente con identificador EXP-CORRESPONDENCIA-" + anio);
		 			//Buscar el ddid del expediente de asociado al archivo
		    	    String tipoCorExpediente = "";
		    	    if ( ClienteOwcc.getRadicado().contains("EE")) {
		    	    	tipoCorExpediente = "ENTREGADA";
		    	    }
		    	    else if ( ClienteOwcc.getRadicado().contains("ER")) {
		    	    	tipoCorExpediente = "RECIBIDA";
		    	    }
		    	    else {
		    	    	tipoCorExpediente = "ENTREGADA";
		    	    }
		            log.debug("Buscando Expediente de Correspondencia con identificador " + ClienteOwcc.getIdExpediente());
		            vExpediente = ClienteOwcc.existeDocumento("EXPEDIENTEELECTRONICO", "CORRESPONDENCIA-"+tipoCorExpediente+ "-" + anio , "EXP-");
		            log.debug("la Salida de la busqueda del Expediente Correspondencia es " + vNumExpediente );
		            if ( vExpediente.equalsIgnoreCase("0")){
		           	//NO EXISTE EXPEDIENTE - CREARLO
		                try {
		             	   log.debug("Creando Expediente con identificador EXP-CORRESPONDENCIA-" + anio);
		                    vExpediente = ClienteOwcc.crearExpedienteElectronico("CORRESPONDENCIA-"+tipoCorExpediente+"-"+anio,"Correspondencia","sgdCorrespondencia",entrada.getSistemaOrigen(),ClienteOwcc.getIdProceso(),"CORRESPONDENCIA " + Integer.toString(anio));
		                    ClienteOwcc.adicionarArchivoExpediente(vDocName, vExpediente );
		                    log.debug("Adicionado Documento a Expediente Nuevo CORRESPONDENCIA-"+anio);
		                } catch (IdcClientException ex) {
		                    log.debug( ex);
		                } catch (UnsupportedEncodingException ex) {
		                    log.debug(ex);
		                }
		            }
		            else {
		            //ADICIONAR DOCUMENTO A EXPEDIENTE EXISTENTE
		                try {
		             	   log.debug("Adicionando Documento a Expediente en Correspondencia");
		             	   ClienteOwcc.adicionarArchivoExpediente(vDocName, vExpediente );
		                 } catch (UnsupportedEncodingException ex) {
		                     log.debug(ex);
		                 }
		            }
        	  }

           }

           log.debug("Generando Respuesta ");
           respuesta.setDocName(vDocName);
           respuesta.setDID(vDid);
           respuesta.setCodigoMensaje(BigInteger.valueOf(200));
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
            respuesta.setCodigoMensaje(BigInteger.valueOf(409));
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
            respuesta.setCodigoMensaje(BigInteger.valueOf(409));
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
            respuesta.setCodigoMensaje(BigInteger.valueOf(409));
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
            respuesta.setCodigoMensaje(BigInteger.valueOf(409));
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
            respuesta.setCodigoMensaje(BigInteger.valueOf(500));
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
