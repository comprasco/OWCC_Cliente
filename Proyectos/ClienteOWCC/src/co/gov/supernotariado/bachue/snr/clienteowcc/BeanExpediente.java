package co.gov.supernotariado.bachue.snr.clienteowcc;



import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.xml.ws.BindingType;

import org.apache.log4j.Logger;


import https.www_supernotariado_gov_co.schemas.bachue.co.cierreaperturaexpediente.aperturaturno.v1.TipoEntradaAperturaTurno;
import https.www_supernotariado_gov_co.schemas.bachue.co.cierreaperturaexpediente.aperturaturno.v1.TipoSalidaAperturaTurno;
import https.www_supernotariado_gov_co.schemas.bachue.co.cierreaperturaexpediente.cierreturno.v1.TipoEntradaCierreTurno;
import https.www_supernotariado_gov_co.schemas.bachue.co.cierreaperturaexpediente.cierreturno.v1.TipoSalidaCierreTurno;
import https.www_supernotariado_gov_co.schemas.bachue.co.cierreaperturaexpediente.obtenerindiceelectronico.v1.TipoEntradaObtenerIndiceElectronico;
import https.www_supernotariado_gov_co.schemas.bachue.co.cierreaperturaexpediente.obtenerindiceelectronico.v1.TipoSalidaObtenerIndiceElectronico;
import oracle.stellent.ridc.IdcClientException;


/**
 * Contiene los metodos que permiten hacer gestion por parte del core Bachue
 * y otros sistemas de la solucion, de los expedientes almacenados en el servidor de Oracle WebCenter Contet
 *
 *
 * @author DataTools
 */
@WebService(serviceName = "SUT_CO_CierreAperturaExpediente", portName = "SUT_CO_CierreAperturaExpedientePort", endpointInterface = "https.www_supernotariado_gov_co.services.bachue.co.cierreaperturaexpediente.v1.SUTCOCierreAperturaExpediente", targetNamespace = "https://www.supernotariado.gov.co/services/bachue/co/cierreaperturaexpediente/v1", wsdlLocation = "WEB-INF/wsdl/BeanExpediente/SUT_CO_CierreAperturaExpediente.wsdl")
@BindingType(value = "http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/")
@HandlerChain(file = "handler-chain.xml")
public class BeanExpediente {


    private static final Logger log = Logger.getLogger(BeanExpediente.class);
    private static final String satisfactorio = "Satisfactorio";
    private static final String fallaTecnica = "Falla Tecnica";


    /**
     * Cierre turno. Este metodo recibe el identificador del turno a cerrar en Oracle WebCenter Content
     *
     * @param entrada, recibe un arreglo llave valor, con el xTURNO y el identificador del turno a cerrar
     * @return TipoSalidaCierrerTurno, genera la respuesta de cierre, si el turno fue cerrado satisactoriamente retorna 200,
     * si hubo un error 500 y una falla tecnica 409
     */
    @SuppressWarnings("unused")
	public TipoSalidaCierreTurno cierreTurno(TipoEntradaCierreTurno entrada) {

        TipoSalidaCierreTurno respuesta = new TipoSalidaCierreTurno();
        List<https.www_supernotariado_gov_co.schemas.bachue.co.cierreaperturaexpediente.cierreturno.v1.Parametro> listaMetadatos = new ArrayList<https.www_supernotariado_gov_co.schemas.bachue.co.cierreaperturaexpediente.cierreturno.v1.Parametro>();
        TipoEntradaCierreTurno.Parametros metadatos;
        String salidaContent = "";
        String vDocName = "";
        String vDid = "";
        String vExpediente = "0";
        String vNir = "";
        String vTurno = "";
        String vOrip = "";
        String vCodOrip = "";
        String vConsulta = "";
        String vExpedientes;
        int vValidacionSistema = 0;
        int vObligatorio = 0;

        metadatos = entrada.getParametros();

        listaMetadatos = metadatos.getParametro();

        log.debug("--------------------------------------------------");
        log.debug("Iniciando nueva operacion de Cierre de Expediente");
        log.debug("--------------------------------------------------");
        log.debug("Leyendo Propiedades");

        ClienteOwcc.cargarVariables();

        try {
            ClienteOwcc.RIDConnection();
        } catch (IdcClientException ex) {
            log.debug(ex);
        }

      //VALIDACION DE ATRIBUTOS

        if ( entrada.getSistemaOrigen().contentEquals("CORE") || entrada.getSistemaOrigen().contentEquals("CORRESPONDENCIA") || entrada.getSistemaOrigen().contentEquals("SEDE_ELECTRONICA") ) {
        	vValidacionSistema = 1;
        }else {
        	vValidacionSistema = 0;
        }

        if ( vValidacionSistema == 1) {
	        for (int i = 0; i < listaMetadatos.size(); i++) {
	             if ( listaMetadatos.get(i).getNombre().contentEquals("xNIR") ){
	               vNir = listaMetadatos.get(i).getValor();
	             }
	             else if ( listaMetadatos.get(i).getNombre().contentEquals("xTURNO") ){
	               vTurno = listaMetadatos.get(i).getValor();
	               vObligatorio++;
	             }
	             else if ( listaMetadatos.get(i).getNombre().contentEquals("xCODIGO_ORIP") ){
	                vCodOrip = listaMetadatos.get(i).getValor();
	             }
	             else if ( listaMetadatos.get(i).getNombre().contentEquals("xORIP") ){
	                vOrip = listaMetadatos.get(i).getValor();
	             }

	        }

	        if ( vObligatorio > 0 ) {

		        vConsulta = "dDocType <matches> `EXPEDIENTEELECTRONICO` <AND> dDocName <matches> `EXP-" +vTurno+ "`";

		        log.debug("Consulta a Realizar para traer expediente:" + vConsulta);

		        //retorno el ID del Folio
		        vExpedientes = ClienteOwcc.buscarResultadosExpedientes(vConsulta);
		        log.debug("Buscar el expediente retorna numero de expedientes:" + vExpedientes);

		        if ( vExpedientes.equalsIgnoreCase("0")) {
		        	salidaContent = "Expediente no encontrado";
		        }else {
			        String[] parts = vExpedientes.split("--");
			        vExpedientes = parts[0];
			        String vDDocNameFolio = parts[1];
			        String vDocNameIndice = "";
			        String vSalida = "";
			        log.debug("Variables vExpedientes, vDDocNameFolio, vDocNameIndice:" + vExpedientes +"," + vDDocNameFolio + "," +vDocNameIndice);
			        log.debug("Cerrando folio");

			        salidaContent = ClienteOwcc.cerrarTurno( vExpedientes );
			        vSalida = ClienteOwcc.cerrandoExpediente(vExpedientes, vDDocNameFolio);
			        log.debug("Folio Cerrado");
		        }
	        }else {
	        	salidaContent = "Falta especificar parametro Obligatorio";
	        }

	        if ( salidaContent.contains("-")){
	           String[] partes = salidaContent.split("-");
	           respuesta.setCodigoMensaje("200");
	           respuesta.setDescripcionMensaje(satisfactorio);
	        }
	        else if ( salidaContent.contains("null") ){
	            respuesta.setCodigoMensaje("409");
	            respuesta.setDescripcionMensaje(salidaContent.toString());
	        }
	        else if ( salidaContent.contains("Expediente no encontrado") ){
	            respuesta.setCodigoMensaje("409");
	            respuesta.setDescripcionMensaje(salidaContent.toString());
	        }
	        else {
	            respuesta.setCodigoMensaje("500");
	            respuesta.setDescripcionMensaje(fallaTecnica);
	        }
        }else {
        	respuesta.setCodigoMensaje("409");
        	respuesta.setDescripcionMensaje("Falta indicar sistema de Origen");
        }

        return respuesta;



    }

    /**
     * Apertura turno. Este metodo recibe el identificador del turno a reabrir en Oracle WebCenter Content
     *
     * @param entrada recibe un arreglo llave valor, con el xTURNO y el identificador del turno a reabir
     * @return TipoSalidaAperturaTurno genera la respuesta de reapertura, si el turno fue reabierto satisactoriamente retorna 200,
     * si hubo un error 500 y una falla tecnica 409
     */
    @SuppressWarnings("unused")
	public TipoSalidaAperturaTurno aperturaTurno(TipoEntradaAperturaTurno entrada) {

        TipoSalidaAperturaTurno respuesta = new TipoSalidaAperturaTurno();
        List<https.www_supernotariado_gov_co.schemas.bachue.co.cierreaperturaexpediente.aperturaturno.v1.Parametro> listaMetadatos = new ArrayList<https.www_supernotariado_gov_co.schemas.bachue.co.cierreaperturaexpediente.aperturaturno.v1.Parametro>();
        TipoEntradaAperturaTurno.Parametros metadatos;
        String salidaContent = "";
        String vDocName = "";
        String vDid = "";
        String vExpediente = "0";
        String vNir = "";
        String vTurno = "";
        String vOrip = "";
        String vCodOrip = "";
        String vConsulta = "";
        String vExpedientes = "";
        int vValidacionSistema = 0;
        int vObligatorio = 0;



        log.debug("--------------------------------------------------");
        log.debug("Iniciando nueva operacion de Apertura de Expediente");
        log.debug("--------------------------------------------------");
        log.debug("Leyendo Propiedades");

        ClienteOwcc.cargarVariables();

        try {
            ClienteOwcc.RIDConnection();
        } catch (IdcClientException ex) {
            log.debug(ex);
        }



        if ( entrada.getSistemaOrigen().contentEquals("CORE") || entrada.getSistemaOrigen().contentEquals("CORRESPONDENCIA") || entrada.getSistemaOrigen().contentEquals("SEDE_ELECTRONICA") ) {
        	vValidacionSistema = 1;
        	metadatos = entrada.getParametros();
            listaMetadatos = metadatos.getParametro();
        }else {
        	vValidacionSistema = 0;
        }

        if ( vValidacionSistema == 1) {

        	for (int i = 0; i < listaMetadatos.size(); i++){
		         if ( listaMetadatos.get(i).getNombre().contentEquals("xTURNO") ){
		           vTurno = listaMetadatos.get(i).getValor();
		           vObligatorio++;
		         }
		    }

        	if ( vObligatorio > 0 ) {

			    vConsulta = "dDocType <matches> `EXPEDIENTEELECTRONICO` <AND> dDocName <matches> `EXP-" +vTurno+ "`";
		        vExpedientes = ClienteOwcc.buscarResultadosExpedientes(vConsulta);
		        log.debug("Buscar el expediente retorna:" + vExpedientes);
		        if ( vExpedientes.equalsIgnoreCase("0")) {
		        	salidaContent = "Expediente no encontrado";
		        }else {
			        String[] parts = vExpedientes.split("--");
			        vExpedientes = parts[0];
			        String vDDocNameFolio = parts[1];
			        String vDocNameIndice = "";
			        log.debug("Variables vExpedientes, vDDocNameFolio, vDocNameIndice:" + vExpedientes +"," + vDDocNameFolio + "," +vDocNameIndice);
			        salidaContent = ClienteOwcc.reAbrirTurno( vExpedientes );
		        }
        	}else {
        		salidaContent = "Falta especificar parametro Obligatorio";
        	}
        }
        else {
        	salidaContent = "Falta Indicar Sistema de Origen";
        }

        if ( salidaContent.contains("-")){
           String[] partes = salidaContent.split("-");
           respuesta.setCodigoMensaje("200");
           respuesta.setDescripcionMensaje(satisfactorio);
        }
        else if ( salidaContent.contains("null") ){
            respuesta.setCodigoMensaje("409");
            respuesta.setDescripcionMensaje(salidaContent.toString());
        }
        else if ( salidaContent.contains("Falta")) {
        	respuesta.setCodigoMensaje("409");
            respuesta.setDescripcionMensaje(salidaContent.toString());
        }
        else if ( salidaContent.contains("Expediente no encontrado")) {
        	respuesta.setCodigoMensaje("409");
            respuesta.setDescripcionMensaje(salidaContent.toString());
        }
        else {
            respuesta.setCodigoMensaje("500");
            respuesta.setDescripcionMensaje(fallaTecnica);
        }
        return respuesta;
    }


    /**
     * Obtiene el archivo del indice electronico.
     *
     * Obtener Indice Electronico. Este metodo recibe el identificador del turno a traer el indice de Oracle WebCenter Content
     *
     * @param entrada recibe un arreglo llave valor, con el xTURNO y el identificador del turno a reabir
     * @return TipoSalidaObtenerIndiceElectronico genera la respuesta con el stream en bytes del archivo correspondiente al indice electronico del turno
     * si el indice fue generado satisactoriamente retorna 200, si hubo un error 500 y una falla tecnica 409
     *
     */
    public TipoSalidaObtenerIndiceElectronico obtenerIndiceElectronico(TipoEntradaObtenerIndiceElectronico entrada) {
    	TipoSalidaObtenerIndiceElectronico respuesta = new TipoSalidaObtenerIndiceElectronico();

    	byte[] base64Archivo = null;

    	List<https.www_supernotariado_gov_co.schemas.bachue.co.cierreaperturaexpediente.obtenerindiceelectronico.v1.Parametro> listaMetadatos = new ArrayList<https.www_supernotariado_gov_co.schemas.bachue.co.cierreaperturaexpediente.obtenerindiceelectronico.v1.Parametro>();
    	TipoEntradaObtenerIndiceElectronico.Parametros metadatos;
        String salidaContent = "";
 //       String vNir = "";
        String vTurno = "";
        String vConsulta = "";
        String vExpedientes;
        String vProceso = "";
        int vValidacionSistema = 0;



        log.debug("--------------------------------------------------");
        log.debug("Iniciando nueva operacion de Cierre de Expediente");
        log.debug("--------------------------------------------------");
        log.debug("Leyendo Propiedades");

        ClienteOwcc.cargarVariables();

        try {
            ClienteOwcc.RIDConnection();
        } catch (IdcClientException ex) {
            log.debug(ex);
        }

        if ( entrada.getSistemaOrigen().contentEquals("CORE") || entrada.getSistemaOrigen().contentEquals("CORRESPONDENCIA") || entrada.getSistemaOrigen().contentEquals("SEDE_ELECTRONICA") ) {
        	vValidacionSistema = 1;
        	metadatos = entrada.getParametros();
            listaMetadatos = metadatos.getParametro();
        }else {
        	vValidacionSistema = 0;
        }

        if ( vValidacionSistema == 1) {
	        for (int i = 0; i < listaMetadatos.size(); i++) {
	        	log.debug("Nombre :" + listaMetadatos.get(i).getNombre().toString());
	        	log.debug("Valor :" + listaMetadatos.get(i).getValor().toString());
	        	if ( listaMetadatos.get(i).getNombre().contentEquals("xTURNO") ){
	               vTurno = listaMetadatos.get(i).getValor();
	             }
	             else if ( listaMetadatos.get(i).getNombre().contentEquals("xPROCESO") ){
		               vProceso = listaMetadatos.get(i).getValor();
		         }

	        }

	        if ( vProceso.equalsIgnoreCase("COPIAS") || vProceso.equalsIgnoreCase("CONSULTAS")) {
	        	try {
					base64Archivo = ClienteOwcc.cargarArchivoComoBytesArray("IndiceDummy.pdf");
				} catch (IOException e) {
					log.debug("Error generando el indice electronico Dummy" + e);
				}
		        log.debug("Se genero el Indice electronico Dummy");

		        respuesta.setArchivo(base64Archivo);
		        respuesta.setCodigoMensaje("200");
		        respuesta.setDescripcionMensaje(satisfactorio);
	        }
	        else {

	        	vConsulta = "dDocType <matches> `EXPEDIENTEELECTRONICO` <AND> dDocName <matches> `EXP-" +vTurno+ "`";

		        log.debug("Consulta a Realizar para traer expediente:" + vConsulta);

		        //retorno el ID del Folio
		        vExpedientes = ClienteOwcc.buscarResultadosExpedientes(vConsulta);
		        log.debug("Buscar el expediente retorna numero de expedientes:" + vExpedientes);

		        if ( vExpedientes.equalsIgnoreCase("0")) {
		        	salidaContent = "Expediente no encontrado";
		        }else {

			        String[] parts = vExpedientes.split("--");
			        vExpedientes = parts[0];
			        String vDDocNameFolio = parts[1];
			        String vDocNameIndice = "";
			        log.debug("Variables vExpedientes, vDDocNameFolio, vDocNameIndice:" + vExpedientes +"," + vDDocNameFolio + "," +vDocNameIndice);
			        // Genera el archivo de indice electronico y hace checkin de el, obtiene el ddocname

			        base64Archivo = ClienteOwcc.obtenerIndiceElectronico ( vExpedientes , "TURNO" , vTurno );
			        log.debug("Se genero el Indice electronico con identificador:" + vDocNameIndice);

			        respuesta.setArchivo(base64Archivo);
			        respuesta.setCodigoMensaje("200");
			        respuesta.setDescripcionMensaje(satisfactorio);

			        salidaContent = ClienteOwcc.cerrarTurno( vExpedientes );
			        log.debug("Folio Cerrado");
		        }

		        if ( salidaContent.contains("-")){
			           respuesta.setCodigoMensaje("200");
			           respuesta.setDescripcionMensaje(satisfactorio);
			        }
			        else if ( salidaContent.contains("null") ){
			            respuesta.setCodigoMensaje("409");
			            respuesta.setDescripcionMensaje(salidaContent.toString());
			        }
			        else if ( salidaContent.contains("Expediente no encontrado") ){
			            respuesta.setCodigoMensaje("409");
			            respuesta.setDescripcionMensaje(salidaContent.toString());
			        }
			        else {
			            respuesta.setCodigoMensaje("500");
			            respuesta.setDescripcionMensaje(fallaTecnica);
			        }
	        }

        }
        else {
        	respuesta.setCodigoMensaje("409");
        	respuesta.setDescripcionMensaje("Falta diligenciar Sistema de Origen");
        }

    	return respuesta;

    }





}