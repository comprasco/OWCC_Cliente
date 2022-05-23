package co.gov.supernotariado.bachue.snr.clienteowcc;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import https.www_supernotariado_gov_co.schemas.bachue.co.busquedadocumentos.consultar.v1.TipoDocumento;
import https.www_supernotariado_gov_co.schemas.bachue.co.busquedadocumentos.consultar.v1.TipoEntradaConsultar;
import https.www_supernotariado_gov_co.schemas.bachue.co.busquedadocumentos.consultar.v1.TipoParametro;
import https.www_supernotariado_gov_co.schemas.bachue.co.busquedadocumentos.consultar.v1.TipoSalidaConsultar;
import https.www_supernotariado_gov_co.schemas.bachue.co.busquedadocumentos.obtenerarchivo.v1.TipoSalidaObtenerArchivo;
import oracle.stellent.ridc.IdcClientException;

/**
 * Contiene los metodos que permiten hacer busqueda por parte del core Bachue
 * y otros sistemas de la solucion, de los documentos almacenados en el servidor de Oracle WebCenter Contet
 *
 * @author DataTools
 */
@WebService(serviceName = "SUT_CO_BusquedaDocumentos", portName = "SUT_CO_BusquedaDocumentosPort", endpointInterface = "https.www_supernotariado_gov_co.services.bachue.co.busquedadocumentos.v1.SUTCOBusquedaDocumentos", targetNamespace = "https://www.supernotariado.gov.co/services/bachue/co/busquedadocumentos/v1", wsdlLocation = "WEB-INF/wsdl/BeanBuscar/BS_SUT_CO_BusquedaDocumentos.wsdl")
@BindingType(value = "http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/")
@HandlerChain(file = "handler-chain.xml")
public class BeanBuscar {


    private static final Logger log = Logger.getLogger(BeanBuscar.class);
    private static final String satisfactorio = "Satisfactorio";
    private static final String noHayDatos = "No hay resultados de documentos de la busqueda";
    private static final String errorServidor = "Error Servidor";
    private static final String noExiste = "No Existe el Archivo";


    /**
     * Consultar.  Este metodo permite consultar documentos registrados en Oracle WebCenter Content,
     * recibe principalmente un arreglo en forma llave valor ( parametros ) los cuales contienen
     * en la llave el metadatos a consultar y en el valor el valor a consultar
     * El nombre del metadato es como esta almacendo en Oracle Webcenter Content, comunmente es
     * xNOMBREPARAMETRO, el valor considerarlo como String
     *
     * Importante: La consulta se procesa utilizando el operador AND
     *
     * @param entrada, recibe el arreglo con los parametros de consulta
     * @return TipoSalidaConsultar retorna la salida con la informacion de todos los documentos encontrados en webcenter content
     * @throws IdcClientException
     */
    @SuppressWarnings("unused")
	public TipoSalidaConsultar consultar(TipoEntradaConsultar entrada) throws IdcClientException {
        TipoSalidaConsultar respuesta = new TipoSalidaConsultar();
        TipoEntradaConsultar.Parametros parametrosBusqueda = new TipoEntradaConsultar.Parametros();
        List<TipoParametro> listaMetadatos = new ArrayList<>();
        TipoSalidaConsultar.Documentos listaDocumentos = new TipoSalidaConsultar.Documentos();
        List<TipoDocumento> listadoResultado = new ArrayList<>();
        String nirTurno = "";
        String nir = "";
        String turno = "";
        String mensaje = "";
        int vValidacionSistema = 0;
        int vValidacionArchivo = 0;
        int vValidacionRepositorio = 0;
        int vValidacionParametros = 0;
        int vValidacionNombreArchivo = 0;
        int vAtributos = 0;
        String vTurno = "";
        String vNIR = "";
        String vNIRVinculado = "";
        String vTURNOVinculado = "";


        log.debug("--------------------------------------------------");
        log.debug("Iniciando nueva operacion de Busqueda");
        log.debug("--------------------------------------------------");
        log.debug("Leyendo Propiedades");

        ClienteOwcc.cargarVariables();

        try {
        	ClienteOwcc.RIDConnection();
        } catch (IdcClientException ex) {
        	log.debug("Error en conexion" + ex);
        }

        if ( entrada.getSistemaOrigen().contentEquals("CORE") || entrada.getSistemaOrigen().contentEquals("CORRESPONDENCIA") || entrada.getSistemaOrigen().contentEquals("SEDE_ELECTRONICA") ) {
        	vValidacionSistema = 1;
        }else {
        	vValidacionSistema = 0;
        }
        if ( entrada.getRepositorio().contentEquals("FINAL") || entrada.getRepositorio().contentEquals("TEMPORAL") || entrada.getRepositorio().contentEquals("MIXTO") ) {
        	vValidacionRepositorio = 1;
        }else {
        	vValidacionRepositorio = 0;
        }


        parametrosBusqueda = entrada.getParametros();

        listaMetadatos = parametrosBusqueda.getParametro();

        int numParametros = listaMetadatos.size();

        log.debug("Numero de Metadatos de la Consulta : "  +  listaMetadatos.size());


        //sonar
        StringBuilder bld = new StringBuilder();
        String str = bld.toString();
        String operador = " <AND> ";
        String conector = "";
        int revConector = 0;

        for (int i = 0; i < listaMetadatos.size(); i++) {

        	//SE BUSCA VALIDAR OPERADOR PARA HACER BUSQUEDA
        	if ( listaMetadatos.get(i).getNombre().equalsIgnoreCase("xOPERADOR")){
        	    if (listaMetadatos.get(i).getValor().equalsIgnoreCase("AND")){
        	    	operador = " <AND> ";
        	    }
        	    else if (listaMetadatos.get(i).getValor().equalsIgnoreCase("OR")){
        	    	operador = " <OR> ";
        	    }
        	    else {
        	    	operador = " <AND> ";
        	    }
        	}
        	//VALIDAR SI SOLO SE BUSCA POR TURNO Y NIR ESPECIFICAMENTE
        	else if ( listaMetadatos.get(i).getNombre().equalsIgnoreCase("xTURNO")) {
        		 vAtributos ++;
        		 vTurno = listaMetadatos.get(i).getValor();
        		 if ( revConector == 0) {
             		conector = "";
             		revConector = 1;
             	 }
        		 else {
        			 conector = operador;
        		 }
        		 bld.append(conector);
        		 bld.append(" xTURNO ");
        		 bld.append(" <substring> ");
        		 bld.append("`" + listaMetadatos.get(i).getValor());
	        	 bld.append("`");
        	}
            else if ( listaMetadatos.get(i).getNombre().equalsIgnoreCase("xNIR")) {
            	vAtributos ++;
            	vNIR = listaMetadatos.get(i).getValor();
            	if ( revConector == 0) {
             		conector = "";
             		revConector = 1;
             	}
            	else {
       			   conector = operador;
       		    }
            	bld.append(conector);
            	bld.append(" xNIR ");
	       		bld.append(" <substring> ");
	       		bld.append("`" + listaMetadatos.get(i).getValor());
		        bld.append("`");
        	}
            else if ( listaMetadatos.get(i).getNombre().equalsIgnoreCase("xNIR_VINCULADO")) {
            	vAtributos ++;
            	vNIRVinculado = listaMetadatos.get(i).getValor();
            	if ( revConector == 0) {
             		conector = "";
             		revConector = 1;
             	}
            	else {
       			   conector = operador;
       		    }
            	bld.append(conector);
            	bld.append(" xNIR ");
	       		bld.append(" <substring> ");
	       		bld.append("`" + listaMetadatos.get(i).getValor());
		        bld.append("`");
        	}
            else if ( listaMetadatos.get(i).getNombre().equalsIgnoreCase("xTURNO_VINCULADO")) {
            	vAtributos ++;
            	vTURNOVinculado = listaMetadatos.get(i).getValor();
            	if ( revConector == 0) {
             		conector = "";
             		revConector = 1;
             	}
            	else {
       			   conector = operador;
       		    }
            	bld.append(conector);
            	bld.append(" xTURNO ");
	       		bld.append(" <substring> ");
	       		bld.append("`" + listaMetadatos.get(i).getValor());
		        bld.append("`");
        	}
        	else {
	        	 bld.append( listaMetadatos.get(i).getNombre());
	        	 if ( listaMetadatos.get(i).getNombre().equalsIgnoreCase("xNIR") ) {
	        		 nir = listaMetadatos.get(i).getValor();
	        	 }
	        	 if ( listaMetadatos.get(i).getNombre().equalsIgnoreCase("xTURNO")) {
	        		 turno = listaMetadatos.get(i).getValor();
	        	 }
	        	 if ( listaMetadatos.get(i).getNombre().equalsIgnoreCase("xMATRICULA") || listaMetadatos.get(i).getNombre().equalsIgnoreCase("xINTERVINIENTE_IDENTIFICACION") || listaMetadatos.get(i).getNombre().equalsIgnoreCase("xINTERVINIENTE_NOMBRE")  ) {
	        		 bld.append(" <substring> ");
	        		 bld.append("`" + listaMetadatos.get(i).getValor());
		        	 bld.append("` ");
	        		 bld.append("<OR> xMATRICULA_1 <substring> ");
	        		 bld.append("`" + listaMetadatos.get(i).getValor());
		        	 bld.append("` ");
		        	 bld.append("<OR> xMATRICULA_2 <substring> ");
	        		 bld.append("`" + listaMetadatos.get(i).getValor());
		        	 bld.append("` ");
		        	 bld.append("<OR> xMATRICULA_3 <substring> ");
	        		 bld.append("`" + listaMetadatos.get(i).getValor());
		        	 bld.append("` ");
		        	 bld.append("<OR> xMATRICULA_4 <substring> ");
	        	 }
	        	 if ( listaMetadatos.get(i).getNombre().equalsIgnoreCase("xINTERVINIENTE_IDENTIFICACION") || listaMetadatos.get(i).getNombre().equalsIgnoreCase("xINTERVINIENTE_NOMBRE")  ) {
	        		 bld.append(" <substring> ");
	        		 bld.append("`" + listaMetadatos.get(i).getValor());
		        	 bld.append("` ");
	        		 bld.append("<OR> xINTERVINIENTEIDENTIFICACION1 <substring> ");
	        		 bld.append("`" + listaMetadatos.get(i).getValor());
		        	 bld.append("` ");
		        	 bld.append("<OR> xINTERVINIENTEIDENTIFICACION2 <substring> ");
	        		 bld.append("`" + listaMetadatos.get(i).getValor());
		        	 bld.append("` ");
		        	 bld.append("<OR> xINTERVINIENTEIDENTIFICACION3 <substring> ");
	        		 bld.append("`" + listaMetadatos.get(i).getValor());
		        	 bld.append("` ");
		        	 bld.append("<OR> xINTERVINIENTEIDENTIFICACION4 <substring> ");
	        	 }
	        	 if ( listaMetadatos.get(i).getNombre().equalsIgnoreCase("xINTERVINIENTE_NOMBRE")  ) {
	        		 bld.append(" <substring> ");
	        		 bld.append("`" + listaMetadatos.get(i).getValor());
		        	 bld.append("` ");
	        		 bld.append("<OR> xINTERVINIENTE_NOMBRE_1 <substring> ");
	        		 bld.append("`" + listaMetadatos.get(i).getValor());
		        	 bld.append("` ");
		        	 bld.append("<OR> xINTERVINIENTE_NOMBRE_2 <substring> ");
	        		 bld.append("`" + listaMetadatos.get(i).getValor());
		        	 bld.append("` ");
		        	 bld.append("<OR> xINTERVINIENTE_NOMBRE_3 <substring> ");
	        		 bld.append("`" + listaMetadatos.get(i).getValor());
		        	 bld.append("` ");
		        	 bld.append("<OR> xINTERVINIENTE_NOMBRE_4 <substring> ");
	        	 }
	        	 else {
	        		 bld.append(" <matches> ");
	        	 }
	        	 bld.append("`" + listaMetadatos.get(i).getValor());
	        	 bld.append("`");
	             log.debug("i: " + i  + "-" + listaMetadatos.size());
	             if ( (i + 1) < listaMetadatos.size()){
	                log.debug("i entro : " + i  + "-" + listaMetadatos.size());
	                bld.append(operador);
	             }
        	}


        }


        String consulta = "";
        // SI LA CONSULTA ES DE NIR Y TURNO AJUSTE LA CONSULTA
        if ( vAtributos == 2 && !vTurno.equals("") && !vNIR.equals("")) {
        	log.debug("Consultando documentos de NIR "+ vNIR +" y TURNO " + vTurno);
        	consulta = "xNIR <starts> `"+ vNIR +"` <AND> xTURNO <starts> `000` <OR> xNIR <starts> `" + vNIR + "` <AND> xTURNO <starts> `" + vTurno + "`";
        }
        else {
        	consulta = bld.toString();
        }

        // String consulta = bld.toString();

        log.debug(" Consulta Armada : "  + consulta);

        //realiza la Busqueda y obtiene los resultados si todas las validaciones fueron correctas
        if ( vValidacionSistema ==  1 && vValidacionRepositorio == 1) {
        	listadoResultado = ClienteOwcc.buscarResultados(consulta);
        	listaDocumentos.setDocumento(listadoResultado);
        }
        else {
        	mensaje = "Validar Sistema Origen O Repositorio" ;
        }

        if ( mensaje.contains("Validar Sistema")) {
        	respuesta.setDocumentos(listaDocumentos);
        	respuesta.setCodigoMensaje(BigInteger.valueOf(409));
        	respuesta.setDescripcionMensaje(mensaje);
        }
        else if ( listadoResultado.isEmpty()) {
        	respuesta.setDocumentos(listaDocumentos);
        	respuesta.setCodigoMensaje(BigInteger.valueOf(409));
        	respuesta.setDescripcionMensaje(noHayDatos);
        }
        else if ( listadoResultado.size() > 0) {
        	respuesta.setDocumentos(listaDocumentos);
        	respuesta.setCodigoMensaje(BigInteger.valueOf(200));
        	respuesta.setDescripcionMensaje(satisfactorio);
        }else {
        	respuesta.setDocumentos(listaDocumentos);
        	respuesta.setCodigoMensaje(BigInteger.valueOf(500));
        	respuesta.setDescripcionMensaje(errorServidor);
        }

        return respuesta;
    }

    /**
     * Obtener archivo. Este metodo permite obtener el stream de bytes de un documento registrado en Oracle WebCenter Content,
     * recibe principalmente el Did y/o DDocName del documento a descargar
     *
     * @param  entrada recibe el dID y/o dDocName del archivo a descargar
     * @return TipoSalidaObtenerArchivo el stream base64 de salida
     */
    public TipoSalidaObtenerArchivo obtenerArchivo(https.www_supernotariado_gov_co.schemas.bachue.co.busquedadocumentos.obtenerarchivo.v1.TipoEntradaObtenerArchivo entrada) {

        TipoSalidaObtenerArchivo respuesta = new TipoSalidaObtenerArchivo();
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
			        respuesta.setCodigoMensaje(BigInteger.valueOf(409));
			        respuesta.setDescripcionMensaje(noExiste);
	            }
	            else {
	            	//obtiene el archivo
	            	base64Archivo = ClienteOwcc.obtenerBytesArchivo(idDoc);
	            	respuesta.setArchivo(base64Archivo);
	    	        respuesta.setCodigoMensaje(BigInteger.valueOf(200));
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
			        respuesta.setCodigoMensaje(BigInteger.valueOf(409));
			        respuesta.setDescripcionMensaje(noExiste);
	            }
	            else {
	            	//obtiene el archivo
	            	base64Archivo = ClienteOwcc.obtenerBytesArchivo(idDoc);
	            	respuesta.setArchivo(base64Archivo);
	    	        respuesta.setCodigoMensaje(BigInteger.valueOf(200));
	    	        respuesta.setDescripcionMensaje(satisfactorio);
	            }
	        } catch (IOException ex) {
	            log.debug("Error" + ex);
	        }
        }
        else {
        	base64Archivo = null;
        	respuesta.setArchivo(base64Archivo);
	        respuesta.setCodigoMensaje(BigInteger.valueOf(500));
	        respuesta.setDescripcionMensaje(errorServidor);
        }

        return respuesta;

    }


    }