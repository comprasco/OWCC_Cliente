/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.supernotariado.bachue.snr.clienteowcc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.xml.ws.BindingType;

import org.apache.log4j.Logger;

import https.www_supernotariado_gov_co.schemas.bachue.co.consultapoderes.consultarpoder.v1.*;
import https.www_supernotariado_gov_co.schemas.bachue.co.consultapoderes.consultarpoder.v1.TipoEntradaConsultarPoder.Parametros.Parametro;
import https.www_supernotariado_gov_co.schemas.bachue.co.consultapoderes.consultarpoder.v1.TipoSalidaConsultarPoder.Documentos.Documento;
import https.www_supernotariado_gov_co.schemas.bachue.co.consultapoderes.obtenerpoder.v1.TipoSalidaObtenerPoder;
import oracle.stellent.ridc.IdcClientException;


/**
 *
 * @author consultor
 */
@WebService(serviceName = "BS_SAN_CO_ConsultaPoderes", portName = "BS_SAN_CO_ConsultaPoderesPort", endpointInterface = "https.www_supernotariado_gov_co.services.bachue.co.consultapoderes.v1.BSSANCOConsultaPoderes", targetNamespace = "https://www.supernotariado.gov.co/services/bachue/co/consultapoderes/v1", wsdlLocation = "WEB-INF/wsdl/BeanConsultaPoderes/BS_SAN_CO_ConsultaPoderes.wsdl")
@BindingType(value = "http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/")
@HandlerChain(file = "handler-chain.xml")
public class BeanConsultaPoderes {

	private static final Logger log = Logger.getLogger(BeanConsultaPoderes.class);
    private static final String satisfactorio = "Satisfactorio";
  //REM  private static final String fallaTecnica = "Falla Tecnica";
    private static final String errorServidor = "Error Servidor";
    private static final String noExiste = "No Existe el Archivo";

    public TipoSalidaConsultarPoder consultarPoder(https.www_supernotariado_gov_co.schemas.bachue.co.consultapoderes.consultarpoder.v1.TipoEntradaConsultarPoder entrada) {

    	TipoSalidaConsultarPoder respuesta = new TipoSalidaConsultarPoder();
        TipoEntradaConsultarPoder.Parametros parametrosBusqueda = new TipoEntradaConsultarPoder.Parametros();
        List<Parametro> listaMetadatos = new ArrayList<>();
        TipoSalidaConsultarPoder.Documentos listaDocumentos = new TipoSalidaConsultarPoder.Documentos();
        List<Documento> listadoResultado = new ArrayList<>();

        log.debug("--------------------------------------------------");
        log.debug("Iniciando nueva operacion de Busqueda de Poderes");
        log.debug("--------------------------------------------------");
        log.debug("Leyendo Propiedades");

        ClienteOwcc.cargarVariables();

        try {
        	ClienteOwcc.RIDConnection();
        } catch (IdcClientException ex) {
        	log.debug("Error en conexion" + ex);
        }

        parametrosBusqueda = entrada.getParametros();

        listaMetadatos = parametrosBusqueda.getParametro();

        log.debug("Numero de Metadatos de la Consulta : "  +  listaMetadatos.size());


        //sonar
        StringBuilder bld = new StringBuilder();
        String operador = " <AND> ";

        int con = 0;
        int revConector = 0;

        if (listaMetadatos.get(con).getDID() != null) {
        	if ( revConector == 0) {
        		operador = "";
        		revConector = 1;
        	}
        	else {
        		operador = " <AND> ";
        	}
        	bld.append(operador);
        	bld.append(" dID" );
        	bld.append(" <matches> ");
        	bld.append("`" + listaMetadatos.get(con).getDID() + "` " );
        }
        if (listaMetadatos.get(con).getDDocName() != null) {
        	if ( revConector == 0) {
        		operador = "";
        		revConector = 1;
        	}
        	else {
        		operador = " <AND> ";
        	}
        	bld.append(operador);
        	bld.append(" dDocName" );
        	bld.append(" <matches> ");
        	bld.append("`" + listaMetadatos.get(con).getDDocName() + "` " );
        }
        if (listaMetadatos.get(con).getXCCApoderado() != null) {
        	if ( revConector == 0) {
        		operador = "";
        		revConector = 1;
        	}
        	else {
        		operador = " <AND> ";
        	}
        	bld.append(operador);
        	bld.append(" xCCAPODERADO" );
        	bld.append(" <substring> ");
        	bld.append("`" + listaMetadatos.get(con).getXCCApoderado() + "` " );

        }
        if (listaMetadatos.get(con).getXCCPoderdante() != null) {
        	if ( revConector == 0) {
        		operador = "";
        		revConector = 1;
        	}
        	else {
        		operador = " <AND> ";
        	}
        	bld.append(operador);
        	bld.append(" xCCPODERDANTE" );
        	bld.append(" <substring> ");
        	bld.append("`" + listaMetadatos.get(con).getXCCPoderdante() + "` " );
        }
        if (listaMetadatos.get(con).getXNombreApoderado() != null) {
        	if ( revConector == 0) {
        		operador = "";
        		revConector = 1;
        	}
        	else {
        		operador = " <AND> ";
        	}
        	bld.append(operador);
        	bld.append(" xNOMBREAPODERADO" );
        	bld.append(" <substring> ");
        	bld.append("`" + listaMetadatos.get(con).getXNombreApoderado() + "` " );
        }
        if (listaMetadatos.get(con).getXNombrePoderdante() != null) {
        	if ( revConector == 0) {
        		operador = "";
        		revConector = 1;
        	}
        	else {
        		operador = " <AND> ";
        	}
        	bld.append(operador);
        	bld.append(" xNOMBREPODERDANTE" );
        	bld.append(" <substring> ");
        	bld.append("`" + listaMetadatos.get(con).getXNombrePoderdante() + "` " );
        }
        if (listaMetadatos.get(con).getXCirculoPredio() != null) {
        	if ( revConector == 0) {
        		operador = "";
        		revConector = 1;
        	}
        	else {
        		operador = " <AND> ";
        	}
        	bld.append(operador);
        	bld.append(" xCIRCULOPREDIO" );
        	bld.append(" <substring> ");
        	bld.append("`" + listaMetadatos.get(con).getXCirculoPredio() + "` " );
        }
        if (listaMetadatos.get(con).getXCiudadPredio() != null) {
        	if ( revConector == 0) {
        		operador = "";
        		revConector = 1;
        	}
        	else {
        		operador = " <AND> ";
        	}
        	bld.append(operador);
        	bld.append(" xCIUDADPREDIO" );
        	bld.append(" <substring> ");
        	bld.append("`" + listaMetadatos.get(con).getXCiudadPredio() + "` " );
        }
        if (listaMetadatos.get(con).getXNotificaciones() != null) {
        	if ( revConector == 0) {
        		operador = "";
        		revConector = 1;
        	}
        	else {
        		operador = " <AND> ";
        	}
        	bld.append(operador);
        	bld.append(" xNOTIFICACIONES" );
        	bld.append(" <substring> ");
        	bld.append("`" + listaMetadatos.get(con).getXNotificaciones() + "` " );
        }
        if (listaMetadatos.get(con).getXEstadoPoder() != null) {
        	if ( revConector == 0) {
        		operador = "";
        		revConector = 1;
        	}
        	else {
        		operador = " <AND> ";
        	}
        	bld.append(operador);
        	bld.append(" xESTADOPODER" );
        	bld.append(" <matches> ");
        	bld.append("`" + listaMetadatos.get(con).getXEstadoPoder() + "` " );
        }
        if (listaMetadatos.get(con).getXDepartamentoPredio() != null) {
        	if ( revConector == 0) {
        		operador = "";
        		revConector = 1;
        	}
        	else {
        		operador = " <AND> ";
        	}
        	bld.append(operador);
        	bld.append(" xDEPARTAMENTOPREDIO" );
        	bld.append(" <matches> ");
        	bld.append("`" + listaMetadatos.get(con).getXDepartamentoPredio() + "` " );
        }

        if (listaMetadatos.get(con).getXFechaCarguePoder() != null) {
        	if ( revConector == 0) {
        		operador = "";
        		revConector = 1;
        	}
        	else {
        		operador = " <AND> ";
        	}
        	bld.append(operador);
        	bld.append(" dInDate" );
        	bld.append(" <matches> ");
        	bld.append("`" + listaMetadatos.get(con).getXFechaCarguePoder() + "` " );
        }
        if (listaMetadatos.get(con).getXFinalizacionPoder() != null) {
        	if ( revConector == 0) {
        		operador = "";
        		revConector = 1;
        	}
        	else {
        		operador = " <AND> ";
        	}
        	bld.append(operador);
        	bld.append(" dOutDate" );
        	bld.append(" <matches> ");
        	bld.append("`" + listaMetadatos.get(con).getXFinalizacionPoder() + "` " );
        }
        if (listaMetadatos.get(con).getXNotaria() != null) {
        	if ( revConector == 0) {
        		operador = "";
        		revConector = 1;
        	}
        	else {
        		operador = " <AND> ";
        	}
        	bld.append(operador);
        	bld.append(" xNOTARIA" );
        	bld.append(" <substring> ");
        	bld.append("`" + listaMetadatos.get(con).getXNotaria() + "` " );
        }
        if (listaMetadatos.get(con).getXMunicipios() != null) {
        	if ( revConector == 0) {
        		operador = "";
        		revConector = 1;
        	}
        	else {
        		operador = " <AND> ";
        	}
        	bld.append(operador);
        	bld.append(" xMUNICIPIO" );
        	bld.append(" <substring> ");
        	bld.append("`" + listaMetadatos.get(con).getXMunicipios() + "` " );
        }
        if (listaMetadatos.get(con).getDDocType() != null) {
        	if ( revConector == 0) {
        		operador = "";
        		revConector = 1;
        	}
        	else {
        		operador = " <AND> ";
        	}
        	bld.append(operador);
        	bld.append(" dDocType" );
        	bld.append(" <matches> ");
        	bld.append("`" + listaMetadatos.get(con).getDDocType() + "` " );
        }
        if (listaMetadatos.get(con).getXNumeroInstrumento() != null) {
        	if ( revConector == 0) {
        		operador = "";
        		revConector = 1;
        	}
        	else {
        		operador = " <AND> ";
        	}
        	bld.append(operador);
        	bld.append(" xNUMEROINSTRUMENTO" );
        	bld.append(" <substring> ");
        	bld.append("`" + listaMetadatos.get(con).getXNumeroInstrumento() + "` " );
        }
        if (listaMetadatos.get(con).getXNumMatriculaPoder() != null) {
        	if ( revConector == 0) {
        		operador = "";
        		revConector = 1;
        	}
        	else {
        		operador = " <AND> ";
        	}
        	bld.append(operador);
        	bld.append(" xMATRICULA" );
        	bld.append(" <substring> ");
        	bld.append("`" + listaMetadatos.get(con).getXNumMatriculaPoder() + "` " );
        }
        if (listaMetadatos.get(con).getXTipDocApoderado() != null) {
        	if ( revConector == 0) {
        		operador = "";
        		revConector = 1;
        	}
        	else {
        		operador = " <AND> ";
        	}
        	bld.append(operador);
        	bld.append(" xTIPODOCAPODERADO" );
        	bld.append(" <substring> ");
        	bld.append("`" + listaMetadatos.get(con).getXTipDocApoderado() + "` " );
        }
        if (listaMetadatos.get(con).getXTipDocPoderdante() != null) {
        	if ( revConector == 0) {
        		operador = "";
        		revConector = 1;
        	}
        	else {
        		operador = " <AND> ";
        	}
        	bld.append(operador);
        	bld.append(" xTIPODOCPODERDANTE" );
        	bld.append(" <substring> ");
        	bld.append("`" + listaMetadatos.get(con).getXTipDocPoderdante() + "` " );
        }
        if (listaMetadatos.get(con).getXJustificacionRevocado() != null) {
        	if ( revConector == 0) {
        		operador = "";
        		revConector = 1;
        	}
        	else {
        		operador = " <AND> ";
        	}
        	bld.append(operador);
        	bld.append(" xJUSTIFICACIONREVOCADO" );
        	bld.append(" <substring> ");
        	bld.append("`" + listaMetadatos.get(con).getXJustificacionRevocado() + "` " );
        }
        if (listaMetadatos.get(con).getXJustificacionUsado() != null) {
        	if ( revConector == 0) {
        		operador = "";
        		revConector = 1;
        	}
        	else {
        		operador = " <AND> ";
        	}
        	bld.append(operador);
        	bld.append(" xJUSTIFICACIONUSADO" );
        	bld.append(" <substring> ");
        	bld.append("`" + listaMetadatos.get(con).getXJustificacionUsado() + "` " );
        }
        if (listaMetadatos.get(con).getXTipoPoder() != null) {
        	if ( revConector == 0) {
        		operador = "";
        		revConector = 1;
        	}
        	else {
        		operador = " <AND> ";
        	}
        	bld.append(operador);
        	bld.append(" xTIPOPODER" );
        	bld.append(" <matches> ");
        	bld.append("`" + listaMetadatos.get(con).getXTipoPoder()  + "` " );
        }
        if (listaMetadatos.get(con).getXDiligenciaReconocimiento() != null) {
        	if ( revConector == 0) {
        		operador = "";
        		revConector = 1;
        	}
        	else {
        		operador = " <AND> ";
        	}
        	bld.append(operador);
        	bld.append(" xDILIGENCIARECONOCIMIENTO" );
        	bld.append(" <matches> ");
        	bld.append("`" + listaMetadatos.get(con).getXDiligenciaReconocimiento()  + "` " );
        }
        if (listaMetadatos.get(con).getXFinalizacionPoder() != null) {
        	if ( revConector == 0) {
        		operador = "";
        		revConector = 1;
        	}
        	else {
        		operador = " <AND> ";
        	}
        	bld.append(operador);
        	bld.append(" xDILIGENCIARECONOCIMIENTO" );
        	bld.append(" <matches> ");
        	bld.append("`" + listaMetadatos.get(con).getXFinalizacionPoder()  + "` " );
        }
        if (listaMetadatos.get(con).getXTipoDocumento() != null) {
        	if ( revConector == 0) {
        		operador = "";
        		revConector = 1;
        	}
        	else {
        		operador = " <AND> ";
        	}
        	bld.append(operador);
        	bld.append(" xTIPODOCUMENTO" );
        	bld.append(" <substring> ");
        	bld.append("`" + listaMetadatos.get(con).getXTipoDocumento()  + "` " );
        }
        if ( revConector == 0) {
    		operador = "";
    		revConector = 1;
    	}
    	else {
    		operador = " <AND> ";
    	}
        bld.append(operador);
        bld.append(" xIdcProfile" );
    	bld.append(" <matches> ");
    	bld.append("`PODERES` " );

        String consulta = "";
        consulta = bld.toString();

        log.debug(" Consulta Armada : "  + consulta);

        //realiza la Busqueda y obtiene los resultados

        listadoResultado = ClienteOwcc.buscarResultadosPoderes(consulta);
        listaDocumentos.setDocumento(listadoResultado);

        if( listadoResultado.isEmpty()){
        	respuesta.setDocumentos(listaDocumentos);
  //      	respuesta.setCodigoMensaje(BigInteger.valueOf(409));
  //      	respuesta.setDescripcionMensaje(fallaTecnica);
        }
        else if ( listadoResultado.size() > 0) {
        	respuesta.setDocumentos(listaDocumentos);
 //       	respuesta.setCodigoMensaje(BigInteger.valueOf(200));
 //       	respuesta.setDescripcionMensaje(satisfactorio);
        }else {
        	respuesta.setDocumentos(listaDocumentos);
 //       	respuesta.setCodigoMensaje(BigInteger.valueOf(500));
 //       	respuesta.setDescripcionMensaje(errorServidor);
        }

        return respuesta;

    }


    public https.www_supernotariado_gov_co.schemas.bachue.co.consultapoderes.obtenerpoder.v1.TipoSalidaObtenerPoder obtenerPoder(https.www_supernotariado_gov_co.schemas.bachue.co.consultapoderes.obtenerpoder.v1.TipoEntradaObtenerPoder entrada) {

        TipoSalidaObtenerPoder respuesta = new TipoSalidaObtenerPoder();
        byte[] base64Archivo = null;
        int valor = 0;

        // Si las entradas son vacias o no

        log.debug("--------------------------------------------------");
        log.debug("Iniciando nueva operacion de Obtener Archivo");
        log.debug("--------------------------------------------------");
        log.debug("Leyendo Propiedades");

        ClienteOwcc.cargarVariables();

        try {
            ClienteOwcc.RIDConnection();
        } catch (IdcClientException ex) {
        	log.debug("Error en conexion" + ex);
        }

        log.debug("El Valor de entrada recibido ddocname es " + entrada.getDDocName() );
        log.debug("El Valor de entrada recibido ddid es " + entrada.getDID());

        if ( entrada.getDDocName().isEmpty() || entrada.getDDocName() == null ){
        	//dDocName es vacio
        	if ( entrada.getDID() == null || entrada.getDID().isEmpty() ){
        		//dID es vacio
        		log.debug("Entrada a valor = 0");
        		valor = 0;
        	}
        	else {
        		//dDocName es vacio y dID no es vacio
        		log.debug("Entrada a valor = 1");
        		valor = 1;
        	}
        }
        else {
        	// dDocName no vacio
		    if ( entrada.getDID() == null || entrada.getDID().isEmpty()){
		    	// dID vacio
		    	log.debug("Entrada a valor = 2");
				valor = 2;
			}
			else {
				//dDocName no vacio, dID no vacio
				log.debug("Entrada a valor = 3");
				valor = 3;
			}
        }

        // si el valor del DID no es nulo
        if ( valor == 1 || valor == 3 ) {
	        try {
	        	String idDoc = ClienteOwcc.buscarDocID("dID" ,entrada.getDID());
	        	log.debug("Buscando ID de " + entrada.getDID() + " es " + idDoc);
	            if (idDoc.equalsIgnoreCase("NOHAY") ){
		        	base64Archivo = null;
		        	respuesta.setArchivo(base64Archivo);
			        respuesta.setCodigoMensaje(409);
			        respuesta.setDescripcionMensaje(noExiste);
	            }
	            else {
	            	//obtiene el archivo
	            	base64Archivo = ClienteOwcc.obtenerBytesArchivo(idDoc);
	            	respuesta.setArchivo(base64Archivo);
	    	        respuesta.setCodigoMensaje(200);
	    	        respuesta.setDescripcionMensaje(satisfactorio);
	            }
	        } catch (IOException ex) {
	            log.debug("Error" + ex);
	        }

        }
        else if ( valor == 2 ) {
	        try {
	        	log.debug("El valor del Did es nulo, proceder a buscar el ddocName");
	        	String idDoc = ClienteOwcc.buscarDocID("dDocName" ,entrada.getDDocName());
	        	log.debug("Buscando ID de " + entrada.getDDocName() + " es " + idDoc);
	            if (idDoc.equalsIgnoreCase("NOHAY") ){
		        	base64Archivo = null;
		        	respuesta.setArchivo(base64Archivo);
			        respuesta.setCodigoMensaje(409);
			        respuesta.setDescripcionMensaje(noExiste);
	            }
	            else {
	            	//obtiene el archivo
	            	base64Archivo = ClienteOwcc.obtenerBytesArchivo(idDoc);
	            	respuesta.setArchivo(base64Archivo);
	    	        respuesta.setCodigoMensaje(200);
	    	        respuesta.setDescripcionMensaje(satisfactorio);
	            }
	        } catch (IOException ex) {
	            log.debug("Error" + ex);
	        }
        }
        else {
        	base64Archivo = null;
        	respuesta.setArchivo(base64Archivo);
	        respuesta.setCodigoMensaje(500);
	        respuesta.setDescripcionMensaje(errorServidor);
        }

        return respuesta;


    }

}
