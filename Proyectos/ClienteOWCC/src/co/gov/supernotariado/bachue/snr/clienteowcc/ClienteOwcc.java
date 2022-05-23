/*
 *
 */
package co.gov.supernotariado.bachue.snr.clienteowcc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.XMLConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.OutputKeys;

import org.apache.commons.codec.binary.Base64;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.xmlgraphics.util.MimeConstants;

import https.www_supernotariado_gov_co.schemas.bachue.co.busquedadocumentos.consultar.v1.Documento;
import https.www_supernotariado_gov_co.schemas.bachue.co.busquedadocumentos.consultar.v1.TipoDocumento;
import https.www_supernotariado_gov_co.schemas.bachue.co.creadorturno.crearturno.v1.TipoEntradaCrearTurno;
import https.www_supernotariado_gov_co.schemas.bachue.co.creadorturno.crearturno.v1.TipoParametroCT;
import https.www_supernotariado_gov_co.schemas.bachue.co.enviodocumentos.enviardocumento.v1.TipoEntradaEnviarDocumento;
import https.www_supernotariado_gov_co.schemas.bachue.co.enviodocumentos.enviardocumento.v1.TipoParametro;
import https.www_supernotariado_gov_co.schemas.bachue.co.enviopoderes.enviarpoder.v1.TipoEntradaEnviarPoder;
import https.www_supernotariado_gov_co.schemas.bachue.co.enviopoderes.enviarpoder.v1.TipoEntradaEnviarPoder.Parametros.Parametro;
import https.www_supernotariado_gov_co.schemas.bachue.co.relacionesdocumento.relacionardocumento.v1.TipoEntradaRelacionarDocumento.Parametros;
import https.www_supernotariado_gov_co.schemas.bachue.co.actualizacionmetadatospoderes.actualizarmetadatospoder.v1.TipoEntradaActualizarMetadatosPoder;
//import https.www_supernotariado_gov_co.schemas.bachue.co.actualizacionmetadatospoderes.actualizarmetadatospoder.v1.TipoEntradaActualizarMetadatosPoder.Parametros;

import oracle.stellent.ridc.IdcClient;
import oracle.stellent.ridc.IdcClientException;
import oracle.stellent.ridc.IdcClientManager;
import oracle.stellent.ridc.IdcContext;
import oracle.stellent.ridc.model.DataBinder;
import oracle.stellent.ridc.model.DataObject;
import oracle.stellent.ridc.model.DataResultSet;
import oracle.stellent.ridc.model.impl.DataObjectEncodingUtils;
import oracle.stellent.ridc.model.serialize.HdaBinderSerializer;
import oracle.stellent.ridc.protocol.ServiceResponse;

/**
 * Esta clase contiene todos los metodos operacionales
 * que son utilizados en las cinco operaciones del cliente OWCC
 * del proyecto bachue.
 */
//@SuppressWarnings("unused")
public class ClienteOwcc {

	/** Gestionar el archivo de logs. */
    private static final Logger log = Logger.getLogger(ClienteOwcc.class);

      /** Url de conexión con el WCC. */
    private static  String urlContent = "";

    /** Almacena la url correspondiente al visor de webcenter content. */
    private static String urlVisor = "";

    /** Usuario de conexión al WCC. */
    private static String usuarioContent = "";

    /** Password de conexión al WCC. */
    private static String passwordContent = "";

    /** Gestiona la conexion a WCC. */
    @SuppressWarnings("rawtypes")
	private static IdcClient idClient;

    /** Gestiona el Contexto de conexión a WCC. */
    private static IdcContext userContext;

    /**  Gestiona los tipos mimes que estan en Content. */
    private static HashMap<String, String> types = new HashMap<>();

    /** El contexto de conexion hacia webcenter Content. */
    private static Context context = null;

    /** Conexion a la base de datos. */
    private static java.sql.Connection conn = null;

    /**  gestiona resultSet de la base de datos. */
    private static ResultSet rs = null;

	/** directorio temporal donde se procesaran los archivos. */
	private static String tmpDir = "";

	/**   Almacena el codigo del folder en Oracle WEbcenter content la transaccion. */
    private static String idFolderHijo = "";

    /**  Almacena el codigo del turno generado por la transaccion. */
    private static String idTurno = "";

    /**  Almacena el codigo del expediente generado por la transaccion. */
    private static String idExpediente = "";

    /**  Almacena el codigo del nir generado por la transaccion. */
    private static String idNir  = "";

    /** Almacena el codigo del folder asociado al nir en la transaccion. */
    private static String idFolderPadre = "";

    /** Si se para un proceso lo alamcena. */
    private static String idProceso = "";

    /** Si se pasa un radicado lo procesa. */
    private static String radicado = "";

    /** Carga el valor de la tablas de retencion . */
    private static String trd = "";

    /**  Carga el valor del prefijo tablas de retencion. */
    private static String trdPrefijo = "";

    /**  Carga el valor del prefijo tablas de retencion. */
    private static String idCategoriaRetencion = "";

    /** Almacena la carpeta donde se encuentran los expedientes de bachue en OWCC. */
    private static String carpetaBaseBachue = "";

    /** Almacena la carpeta donde se encuentran los expedientes de correspondencia en OWCC. */
    private static String carpetaBaseCorrespondencia = "";

    /** Almacena la carpeta donde se encuentran los expedientes de correspondencia en OWCC. */
    private static String carpetaBaseSedeElectronica = "";

    /** Almacena la carpeta donde se encuentran los expedientes de correspondencia en OWCC. */
    private static String carpetaBaseSedeElectronicaExpediente = "";

    /** Almacena la carpeta donde se encuentran los expedientes temporals de bachue en OWCC. */
    private static String carpetaBaseTemporal = "";

    /**  Carpeta Base donde Guardar las Conciliaciones. */
    private static String carpetaBaseConciliacion = "";

    /**  Carpeta Base donde Guardar los poderes. */
    private static String carpetaBasePoderes = "/PODERES/";

    /**  Carpeta Base donde Guardar Expedientes de Conciliaciones. */
    private static String carpetaBaseConciliacionExpediente = "";

    /**  Carpeta Base donde Guardar Expedientes de Correspondencia. */
    private static String carpetaBaseCorrespondenciaExpediente = "";

    /**  Carpeta Base donde Guardar Expedientes de Bachue. */
    private static String carpetaBaseBachueExpediente = "";


    /**
     * Adicionar archivo expediente, ingresa un archivo en un expediente o folio.
     *
     * @param vDDocName el identificador dDocName del documento a adicionar
     * @param vFolio el identificador dDocName del expediente en el que se adicional
     * @return el resultado de la operacion
     * @throws IOException
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    @SuppressWarnings("rawtypes")
	public static String actualizarMetadatosPoder( String sistemaOrigen ,  TipoEntradaActualizarMetadatosPoder.Parametros metadatos , String documento) throws IOException {

         List<TipoEntradaActualizarMetadatosPoder.Parametros.Parametro> listaMetadatos = new ArrayList<TipoEntradaActualizarMetadatosPoder.Parametros.Parametro>();

         listaMetadatos = metadatos.getParametro();

         String respuesta = "";
         String vDocName ="";
         String vDid = "";

         // Crear un nuevo IdcClientManager


         IdcClientManager manager = new IdcClientManager ();
         try{
                 // Crea una conexion IdcClient usando protocolo  idc (i.e. socket connection to Content Server)
                 IdcClient idcClient = manager.createClient (ClienteOwcc.urlContent);

                 // Crea un nuevo contexto usando el usuario 'sysadmin'
                 IdcContext userContext = new IdcContext (ClienteOwcc.usuarioContent,ClienteOwcc.passwordContent);

                // Databinder para solicitud de checkin
                 DataBinder dataBinder = idcClient.createBinder();
                 HdaBinderSerializer serializer = new HdaBinderSerializer ("UTF-8", idcClient.getDataFactory ());
                 //indicar la operacion a ejecutar

                 if ( documento.contains("-")){
                     String[] partes = documento.split("-");
                     vDocName = partes[0];
                     vDid = partes[1];
                 }

                 //ARMADO DEL DATABINDER A ENVIAR A CONTENT

                 dataBinder.putLocal("IdcService", "UPDATE_DOCINFO");
                 dataBinder.putLocal("dDocName", vDocName);
                 dataBinder.putLocal("dID", vDid);

                 //CAPTURANDO LOS METADATOS QUE SON ENVIADOS
                 int con = 0;

                 if (listaMetadatos.get(con).getXCCApoderado() != null) {
               	  dataBinder.putLocal("xCCAPODERADO", listaMetadatos.get(con).getXCCApoderado());
                 }
                 if (listaMetadatos.get(con).getXCCPoderdante() != null) {
               	  dataBinder.putLocal("xCCPODERDANTE", listaMetadatos.get(con).getXCCPoderdante());
                 }
                 if (listaMetadatos.get(con).getXNombreApoderado() != null) {
               	  dataBinder.putLocal("xNOMBREAPODERADO", listaMetadatos.get(con).getXNombreApoderado());
                 }
                 if (listaMetadatos.get(con).getXNombrePoderdante() != null) {
               	  dataBinder.putLocal("xNOMBREPODERDANTE", listaMetadatos.get(con).getXNombrePoderdante());
                 }
                 if (listaMetadatos.get(con).getXCirculoPredio() != null) {
               	  dataBinder.putLocal("xCIRCULOPREDIO", listaMetadatos.get(con).getXCirculoPredio());
                 }
                 if (listaMetadatos.get(con).getXCiudadPredio() != null) {
               	  dataBinder.putLocal("xCIUDADPREDIO", listaMetadatos.get(con).getXCiudadPredio());
                 }
                 if (listaMetadatos.get(con).getXEstadoPoder() != null) {
               	  dataBinder.putLocal("xESTADOPODER", listaMetadatos.get(con).getXEstadoPoder());
                 }
                 if (listaMetadatos.get(con).getXDepartamentoPredio() != null) {
               	  dataBinder.putLocal("xDEPARTAMENTOPREDIO", listaMetadatos.get(con).getXDepartamentoPredio());
                 }
                 if (listaMetadatos.get(con).getXNumeroInstrumento() != null) {
               	  dataBinder.putLocal("xNUMEROINSTRUMENTO", listaMetadatos.get(con).getXNumeroInstrumento());
                 }
                 if (listaMetadatos.get(con).getXTipDocApoderado() != null) {
               	    dataBinder.putLocal("xTIPODOCAPODERADO", listaMetadatos.get(con).getXTipDocApoderado());
                 }
                 if (listaMetadatos.get(con).getXTipDocPoderdante() != null) {
               	    dataBinder.putLocal("xTIPODOCPODERDANTE", listaMetadatos.get(con).getXTipDocPoderdante());
                 }
                 if (listaMetadatos.get(con).getXJustificacionRevocado() != null) {
               	    dataBinder.putLocal("xJUSTIFICACIONREVOCADO", listaMetadatos.get(con).getXJustificacionRevocado());
                 }
                 if (listaMetadatos.get(con).getXJustificacionUsado() != null) {
               	    dataBinder.putLocal("xJUSTIFICACIONUSADO", listaMetadatos.get(con).getXJustificacionUsado());
                 }
                 if (listaMetadatos.get(con).getXDireccionPredio() != null) {
                  	dataBinder.putLocal("xDIRECCIONPREDIO", listaMetadatos.get(con).getXDireccionPredio());
                 }
                 if (listaMetadatos.get(con).getXDiligenciaReconocimiento() != null) {
                 	 dataBinder.putLocal("xDILIGENCIARECONOCIMIENTO", listaMetadatos.get(con).getXDiligenciaReconocimiento());
                 }
                 if (listaMetadatos.get(con).getXNotificaciones() != null) {
                	  dataBinder.putLocal("xNOTIFICACIONES", listaMetadatos.get(con).getXNotificaciones());
                 }
                 if (listaMetadatos.get(con).getXTipoDocumento() != null) {
                	 dataBinder.putLocal("xTIPODOCUMENTO", listaMetadatos.get(con).getXTipoDocumento());
                 }
                 if (listaMetadatos.get(con).getXTipoDocumento() != null) {
                	 dataBinder.putLocal("xTIPODOCUMENTO", listaMetadatos.get(con).getXTipoDocumento());
                 }
                 if (listaMetadatos.get(con) != null) {
                	 dataBinder.putLocal("xTIPODOCUMENTO", listaMetadatos.get(con).getXTipoDocumento());
                 }
                 if (listaMetadatos.get(con).getXUsoPoder() != null) {
                	 dataBinder.putLocal("xUSOPODER", listaMetadatos.get(con).getXUsoPoder());
                 }
                 if (listaMetadatos.get(con).getDDocType() != null) {
               	     dataBinder.putLocal("dDocType", listaMetadatos.get(con).getDDocType());
                 }
                 if (listaMetadatos.get(con).getXComments() != null) {
               	     dataBinder.putLocal("xComments", listaMetadatos.get(con).getXComments());
                 }

                 // Envia la peticion al Content Server
                 ServiceResponse response = idcClient.sendRequest(userContext,dataBinder);
                 // Obtiene el data binder con la respuesta desde el Content Server
                 DataBinder responseData = response.getResponseAsBinder();
                 // Escribe los datos de respuesta en el stdout
                 serializer.serializeBinder (System.out, responseData);

                 log.debug("Retornado dDocName " + responseData.getLocal("dDocName" ));
                 log.debug("Retornado dId " + responseData.getLocal("dID") );

                 respuesta = responseData.getLocal("dDocName") + "-" + responseData.getLocal("dID");
                 System.out.println("La respuesta de la operacion es : " + respuesta.toString());

        } catch (IdcClientException ex) {
            log.debug(ex);
        }
        return respuesta;
    }


    /**
     * Adicionar archivo expediente, ingresa un archivo en un expediente o folio.
     *
     * @param vDDocName el identificador dDocName del documento a adicionar
     * @param vFolio el identificador dDocName del expediente en el que se adicional
     * @return el resultado de la operacion
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    @SuppressWarnings("rawtypes")
	public static String adicionarArchivoExpediente(String vDDocName , String vFolio) throws UnsupportedEncodingException {
        try {
            // Iniciar una nueva conexion al UCM
            IdcClientManager manager = new IdcClientManager();
            // construir un cliente que se comunique con el UCM
            IdcClient idcClient = manager.createClient(ClienteOwcc.urlContent);
            // create a trusted user connection to UCM
            IdcContext userContext = new IdcContext(ClienteOwcc.usuarioContent,ClienteOwcc.passwordContent);

            // crea el binder para asiganar los GUIDs necesarios despues
            DataBinder guidBinder = idcClient.createBinder();
            // populate the binder with the parameters
            guidBinder.putLocal("IdcService", "GENERATE_GUIDS");

            // send the request and get the response back from local data and result set
            ServiceResponse guidResponse = idcClient.sendRequest(userContext, guidBinder);
            DataBinder folderData = guidResponse.getResponseAsBinder ();
            DataResultSet guidResults = folderData.getResultSet ("GUID_SET");

            // put the set of GUIDs in an array
            String guids[] = new String[50];
            int n = 0;
            for (DataObject dataObject : guidResults.getRows ()) {
                guids[n] = dataObject.get("id");
            }

            // create the binder to get the content information of the document to add
            DataBinder docInfoBinder = idcClient.createBinder();
            // populate the binder with the parameters
            docInfoBinder.putLocal("IdcService", "DOC_INFO_BY_NAME");
            docInfoBinder.putLocal("dDocName", vDDocName );

            // send the request and get the response back from local data and result set
            ServiceResponse infoResponse = idcClient.sendRequest(userContext, docInfoBinder);
            DataBinder infoData = infoResponse.getResponseAsBinder ();
            DataResultSet docInfoResults = infoData.getResultSet ("DOC_INFO");

            // create the binder to get the content information of the folio to update
            DataBinder folioInfoBinder = idcClient.createBinder();
            // populate the binder with the parameters
            folioInfoBinder.putLocal("IdcService", "DOC_INFO_BY_NAME");
            folioInfoBinder.putLocal("dDocName", vFolio);

            // send the request and get the response back from local data and result set
            ServiceResponse folioInfoResponse = idcClient.sendRequest(userContext, folioInfoBinder);
            DataBinder folioInfoData = folioInfoResponse.getResponseAsBinder ();
            DataResultSet folioInfoResults = folioInfoData.getResultSet ("DOC_INFO");
            String foliodID = folioInfoData.getLocal("dID");

            // build the change data string for the content
            String newItemStr = "";
            for (DataObject dataObject : docInfoResults.getRows ()) {
            	// build the path to the document
                String urlStr = "/cs/groups/" + dataObject.get ("dSecurityGroup") + "/documents/" + dataObject.get ("dDocType") + "/" + dataObject.get ("dDocName") + "." + dataObject.get ("dWebExtension");
                // assemble the string with the parameters needed for the content folio service change string
                newItemStr = "xcsd^dFormat:" + dataObject.get ("dFormat") + ",";
                newItemStr += "id:" + guids[0] + ",";
                newItemStr += "xcsd^dDocName:" + dataObject.get ("dDocName") + ",";
                newItemStr += "xcsd^dID:" + dataObject.get ("dID") + ",";
                newItemStr += "xcsd^dDocTitle:" + dataObject.get ("dDocTitle") + ",";
                newItemStr += "xcsd^dDocType:" + dataObject.get ("dDocType") + ",";
                newItemStr += "xcsd^dDocAuthor:" + dataObject.get ("dDocAuthor") + ",";
                newItemStr += "xcsd^xFUNCION_RESUMEN:" + dataObject.get ("xFUNCION_RESUMEN") + ",";
                newItemStr += "xcsd^xVALOR_HUELLA:" + dataObject.get ("xVALOR_HUELLA") + ",";
                newItemStr += "xcsd^xIdcProfile:" + dataObject.get ("xIdcProfile") + ",";
                newItemStr += "xcsd^xNRO_FOLIOS:" + dataObject.get ("xNRO_FOLIOS") + ",";
                newItemStr += "xcsd^xTAMANO_ARCHIVO:" + dataObject.get ("xTAMANO_ARCHIVO") + ",";
                newItemStr += "xcsd^dOriginalName:" + dataObject.get ("dOriginalName") + ",";
                newItemStr += "xcst^description:";
            }

            // create the binder to get the folio root GUID
            DataBinder rootBinder = idcClient.createBinder();
            // populate the binder with the parameters
            rootBinder.putLocal("IdcService", "LOAD_FOLIO_NODE");
            rootBinder.putLocal("dDocName", vFolio);
            rootBinder.putLocal("RevisionSelectionMethod", "Latest");

            // send the request and get the response back from local data
            ServiceResponse rootGUIDResponse = idcClient.sendRequest(userContext, rootBinder);
            DataBinder rootGUIDData = rootGUIDResponse.getResponseAsBinder ();
            String rootGUID = rootGUIDData.getLocal("RootNode");

            // assemble the first change parameter for the service.  This will tell the content folio service
            // to add a slot
            String change0 = foliodID + ":addItem:" + guids[0] + ":" + rootGUID;

            // assemble the second change parameter for the service.  This will tell the content folio service
            // to add a content item to the new slot
            String change1 = foliodID + ":addContent:" + guids[0];

            // create the binder to run the UPDATE_FOLIO service
            DataBinder folioBinder = idcClient.createBinder();
            // populate the binder with the parameters
            folioBinder.putLocal("IdcService", "UPDATE_FOLIO");
            folioBinder.putLocal("dDocName", vFolio);
            folioBinder.putLocal("RevisionSelectionMethod", "Latest");
            folioBinder.putLocal("NumChanges", "2");
            folioBinder.putLocal("change0", change0);
            folioBinder.putLocal("change_data0", newItemStr);
            folioBinder.putLocal("change1", change1);
            folioBinder.putLocal("change_data1", newItemStr);

            // send the request and get the response back from local data
            ServiceResponse folioResponse = idcClient.sendRequest(userContext, folioBinder);
            DataBinder folioData = folioResponse.getResponseAsBinder ();
        } catch (IdcClientException ex) {
            log.debug(ex);
        }
        return null;
    }

    /**
     * De acuerdo al metadato retorna el dID de Oracle WebCenter Contet.
     *
     * @param atributos, dDocName, dID o metadato a buscar
     * @param valor, valor del metadato a buscar
     * @return el identificador dID del documento a buscar o NOHAY si no existe.
     */
   public static String buscarDocID(String atributo , String valor) {

       try {
           String rowCount = "2000";

           DataBinder binder = idClient.createBinder();

           String queryText = "" + atributo + "";
           queryText = queryText + " <matches> ";
           queryText = queryText + "`" + valor + "`";

           log.debug("la consulta a realizar es " + queryText );
           log.debug( "Query Text : {0}" + queryText);
           binder.putLocal("IdcService", "GET_SEARCH_RESULTS");
           binder.putLocal("searchFormType", "standard");
           binder.putLocal("SearchQueryFormat", "UNIVERSAL");
           binder.putLocal("AdvSearch", "True");
           binder.putLocal("SortField", "dInDate");
           binder.putLocal("SortOrder", "Desc");
           binder.putLocal("QueryText", queryText);
           binder.putLocal("ResultCount", rowCount);
           binder.putLocal("StartRow", "1");
           binder.putLocal("UseSearchCache", "false");
           ServiceResponse response = idClient.sendRequest(userContext, binder);
           DataBinder binder2 = response.getResponseAsBinder();
           DataResultSet resultSet = binder2.getResultSet("SearchResults");
           String docId = "";

           if( resultSet.getRows().isEmpty()) {
        	   // si el resultado es vacio de la busqueda
               docId = "NOHAY";
           }
           else {

		       for (DataObject dataObject : resultSet.getRows()) {
		           dataObject.get("dDocName");
		           docId = dataObject.get("dID");
		       }
           }
           response.close();
           log.debug("El docId obtenido es " + docId);

           return docId;

       } catch (Exception ex) {
           log.debug("Error realizando consulta" + ex);
       }

       return null;

   }

    /**
     * Dependiendo de los valores de entrada retorna el numero de documentos
     * con ese criterio existentes en OWCC.
     *
     * @param vTipoDocumental recibe el tipo documental del documento a buscar en OWCC
     * @param vIdDocumento recibe el dDocName del documento a buscar en OWCC
     * @param vPrefijo Si el dDocName tiene algun prefijo indicarlo sino dejarlo en blanco
     * @return 0 si no encuentra expediente - el docname si lo encuentra
     */
     @SuppressWarnings("rawtypes")
	public static String existeDocumento(String vTipoDocumental , String vIdDocumento , String vPrefijo) {

         int num_resultados = 0;
         IdcClientManager manager = new IdcClientManager ();
         try {
             String rowCount = "10";

             // Crea una conexion IdcClient usando protocolo  idc (i.e. socket connection to Content Server)
             IdcClient idcClient = manager.createClient (ClienteOwcc.urlContent);

             // Crea un nuevo contexto usando el usuario 'sysadmin'
             IdcContext userContext = new IdcContext (ClienteOwcc.usuarioContent,ClienteOwcc.passwordContent);

             DataBinder binder = idcClient.createBinder();

             String vConsulta = "";

             vConsulta = vConsulta + " dDocType";
             vConsulta = vConsulta + " <matches> ";
             vConsulta = vConsulta + "`" + vTipoDocumental + "`";
             vConsulta = vConsulta + " <AND> ";
             vConsulta = vConsulta + " dDocName";
             vConsulta = vConsulta + " <matches> ";
             vConsulta = vConsulta + "`" + vPrefijo + "" + vIdDocumento + "`";


             String queryText = vConsulta;
             log.debug("la consulta de expediente a realizar es " + queryText );
             log.debug("Consulta : " + queryText);
             binder.putLocal("IdcService", "GET_SEARCH_RESULTS");
             binder.putLocal("searchFormType", "standard");
             binder.putLocal("SearchQueryFormat", "UNIVERSAL");
             binder.putLocal("AdvSearch", "True");
             binder.putLocal("SortField", "dInDate");

             binder.putLocal("SortOrder", "Desc");
             binder.putLocal("QueryText", queryText);
             binder.putLocal("ResultCount", rowCount);
             binder.putLocal("StartRow", "1");
             binder.putLocal("UseSearchCache", "false");
             ServiceResponse response = idcClient.sendRequest(userContext, binder);
             DataBinder binder2 = response.getResponseAsBinder();
             DataResultSet resultSet = binder2.getResultSet("SearchResults");
             List<Documento> documentoPreview = new ArrayList<>();

             for (DataObject dataObject : resultSet.getRows()) {
                 Documento docTemp = new Documento();
                 docTemp.setDocName(dataObject.get("dDocName"));
                 docTemp.setDID(dataObject.get("dID"));
                 docTemp.setCodOrip(dataObject.get("xCODIGO_ORIP"));
                 docTemp.setOrip(dataObject.get("xORIP"));
                 docTemp.setTipoDocumental(dataObject.get("dDocType"));
                 docTemp.setNir(dataObject.get("xNIR"));
                 docTemp.setTurno(dataObject.get("xTURNO"));
                 docTemp.setEntidadOrigen(dataObject.get("xENTIDAD_ORIGEN"));
                 docTemp.setIdentificacionInterviniente(dataObject.get("xINTERVINIENTE_IDENTIFICACION"));
                 docTemp.setMatricula(dataObject.get("xMATRICULA"));
                 docTemp.setNombreInterviniente(dataObject.get("xINTERVINIENTE_NOMBRE"));
                 documentoPreview.add(docTemp);
                 num_resultados++;
             }

             response.close();
             System.out.println("la Salida es " + num_resultados );

             if ( num_resultados  == 0){
                 return "0";
              }else if ( num_resultados == 1 ){
                 return (documentoPreview.get(num_resultados-1).getDocName());
              }else{
                 return (documentoPreview.get(num_resultados-1).getDocName());
              }


         } catch (Exception ex) {
             System.err.println("Error realizando consulta" + ex.getMessage());
             log.debug(ex);

         }

         return "0";

     }

     /**
      * Realiza una busqueda de documentos en  Oracle Webcenter Content
      * y retorna el listado con los documentos que cumplen con el criterio de busqueda.
      * @param consulta recibe la consulta en formto CMIS utilizando los metadatos a buscar
      * @return Una lista TipoDocumento con el resultado de la busqueda
      */
    public static List<TipoDocumento> buscarResultados(String consulta) {

    	List<TipoDocumento> documentoPreview = new ArrayList<>();
        try {
            String rowCount = "2000";

            DataBinder binder = idClient.createBinder();
            String queryText = consulta;
            System.out.println("la consulta a realizar es " + queryText );
            log.debug( "Query Text : {0}" + queryText);
            binder.putLocal("IdcService", "GET_SEARCH_RESULTS");
            binder.putLocal("searchFormType", "standard");
            binder.putLocal("SearchQueryFormat", "UNIVERSAL");
            binder.putLocal("AdvSearch", "True");
            binder.putLocal("SortField", "dInDate");
            binder.putLocal("SortOrder", "Desc");
            binder.putLocal("QueryText", queryText);
            binder.putLocal("ResultCount", rowCount);
            binder.putLocal("StartRow", "1");
            binder.putLocal("UseSearchCache", "false");
            ServiceResponse response = idClient.sendRequest(userContext, binder);
            DataBinder binder2 = response.getResponseAsBinder();
            DataResultSet resultSet = binder2.getResultSet("SearchResults");


            for (DataObject dataObject : resultSet.getRows()) {
                TipoDocumento docTemp = new TipoDocumento();
                docTemp.setDocName(dataObject.get("dDocName"));
                docTemp.setDID(dataObject.get("dID"));
                docTemp.setCodOrip(dataObject.get("xCODIGO_ORIP"));
                docTemp.setOrip(dataObject.get("xORIP"));
                docTemp.setTipoDocumental(dataObject.get("dDocType"));
                docTemp.setNir(dataObject.get("xNIR"));
                docTemp.setTurno(dataObject.get("xTURNO"));
                docTemp.setEntidadOrigen(dataObject.get("xENTIDAD_ORIGEN"));
                docTemp.setTipoOficina(dataObject.get("xTIPOOFICINA"));
                docTemp.setIdentificacionInterviniente(dataObject.get("xINTERVINIENTE_IDENTIFICACION"));
                docTemp.setMatricula(dataObject.get("xMATRICULA"));
                docTemp.setNumeroDocumento(dataObject.get("xNUMERODOC"));
                docTemp.setNumeroPagina(dataObject.get("xNUMERO_PAG"));
                docTemp.setDocumentoRegistrado(dataObject.get("xDOCREGISTRADO"));
                docTemp.setNombreInterviniente(dataObject.get("xINTERVINIENTE_NOMBRE"));
                docTemp.setNumeroPagina(dataObject.get("xPAGINADO"));
                docTemp.setAnotacion(dataObject.get("xANOTACION"));
                docTemp.setPais(dataObject.get("xPAIS"));
                docTemp.setDepartamento(dataObject.get("xDEPARTAMENTO"));
                docTemp.setMunicipio(dataObject.get("xMUNICIPIO"));
                docTemp.setProceso(dataObject.get("xPROCESO"));
                docTemp.setNirVinculado(dataObject.get("xNIR_VINCULADO"));
                docTemp.setTurnoVinculado(dataObject.get("xTURNO_VINCULADO"));
                docTemp.setActoNaturalezaJuridica(dataObject.get("xACTO"));
                docTemp.setNumeroFolios(dataObject.get("xNRO_FOLIOS"));
                docTemp.setUrlVisor(urlVisor + dataObject.get("dID"));
                docTemp.setFechaDocumento(dataObject.get("xFECHA_DOC"));
                docTemp.setFechaPublicacion(dataObject.get("dInDate"));
                docTemp.setFechaVigencia(dataObject.get("dOutDate"));
                documentoPreview.add(docTemp);
            }
            response.close();
            return documentoPreview;
        } catch (Exception ex) {
            log.debug("Error realizando consulta" + ex);
        }

        return documentoPreview;

    }


    /**
     * Realiza una busqueda de documentos en  Oracle Webcenter Content
     * y retorna el listado con los documentos que cumplen con el criterio de busqueda.
     * @param consulta recibe la consulta en formto CMIS utilizando los metadatos a buscar
     * @return Una lista TipoDocumento con el resultado de la busqueda
     */
    public static List<https.www_supernotariado_gov_co.schemas.bachue.co.consultapoderes.consultarpoder.v1.TipoSalidaConsultarPoder.Documentos.Documento> buscarResultadosPoderes(String consulta) {
    List <https.www_supernotariado_gov_co.schemas.bachue.co.consultapoderes.consultarpoder.v1.TipoSalidaConsultarPoder.Documentos.Documento> documentoPreview = new ArrayList<>();
    try {
           String rowCount = "2000";

           DataBinder binder = idClient.createBinder();
           String queryText = consulta;
           System.out.println("la consulta a realizar para buscar poderes es " + queryText );
           log.debug( "Query Text Poderes: {0}" + queryText);
           binder.putLocal("IdcService", "GET_SEARCH_RESULTS");
           binder.putLocal("searchFormType", "standard");
           binder.putLocal("SearchQueryFormat", "UNIVERSAL");
           binder.putLocal("AdvSearch", "True");
           binder.putLocal("SortField", "dInDate");
           binder.putLocal("SortOrder", "Desc");
           binder.putLocal("QueryText", queryText);
           binder.putLocal("ResultCount", rowCount);
           binder.putLocal("StartRow", "1");
           binder.putLocal("UseSearchCache", "false");
           ServiceResponse response = idClient.sendRequest(userContext, binder);
           DataBinder binder2 = response.getResponseAsBinder();
           DataResultSet resultSet = binder2.getResultSet("SearchResults");



           for (DataObject dataObject : resultSet.getRows()) {
        	   https.www_supernotariado_gov_co.schemas.bachue.co.consultapoderes.consultarpoder.v1.TipoSalidaConsultarPoder.Documentos.Documento docTemp = new https.www_supernotariado_gov_co.schemas.bachue.co.consultapoderes.consultarpoder.v1.TipoSalidaConsultarPoder.Documentos.Documento();
               docTemp.setDocName(dataObject.get("dDocName"));
               docTemp.setDID(dataObject.get("dID"));
               docTemp.setDDocType(dataObject.get("dDocType"));
               docTemp.setXCirculoPredio(dataObject.get("xCIRCULOPREDIO"));
               docTemp.setXCCApoderado(dataObject.get("xCCAPODERADO"));
               docTemp.setXCCPoderdante(dataObject.get("xCCPODERDANTE"));
               docTemp.setXCiudadPredio(dataObject.get("xCIUDADPREDIO"));
               docTemp.setXDepartamentoPredio(dataObject.get("xDEPARTAMENTOPREDIO"));
               docTemp.setXDepartamentos(dataObject.get("xDEPARTAMENTO"));
               docTemp.setXDireccionPredio(dataObject.get("xDIRECCIONPREDIO"));
               docTemp.setXEstadoPoder(dataObject.get("xESTADOPODER"));
               docTemp.setXJustificacionRevocado(dataObject.get("xJUSTIFICACIONREVOCADO"));
               docTemp.setXJustificacionUsado(dataObject.get("xJUSTIFICACIONUSADO"));
               docTemp.setXMunicipios(dataObject.get("xMUNICIPIO"));
               docTemp.setXNombreApoderado(dataObject.get("xNOMBREAPODERADO"));
               docTemp.setXNombrePoderdante(dataObject.get("xNOMBREPODERDANTE"));
               docTemp.setXNotaria(dataObject.get("xNOTARIA"));
               docTemp.setXNumeroInstrumento(dataObject.get("xNUMEROINSTRUMENTO"));
               docTemp.setXNumMatriculaPoder(dataObject.get("xNUMMATRICULAPODER"));
               docTemp.setXTipDocApoderado(dataObject.get("xTIPODOCAPODERADO"));
               docTemp.setXTipDocPoderdante(dataObject.get("xTIPODOCPODERDANTE"));
               docTemp.setXTipoPoder(dataObject.get("xTIPOPODER"));
               docTemp.setXUsoPoder(dataObject.get("xUSOPODER"));
               docTemp.setXDiligenciaReconocimiento(dataObject.get("xDILIGENCIARECONOCIMIENTO"));
               docTemp.setXTipoDocumento(dataObject.get("xTIPODOCUMENTO"));
               docTemp.setXNotificaciones(dataObject.get("xNOTIFICACIONES"));
               docTemp.setUrlVisor(urlVisor + dataObject.get("dID"));
               docTemp.setCodigoMensaje(200);
               log.debug("FECHA BUSQUEDA " + dataObject.get("dInDate") );

               GregorianCalendar cal = new GregorianCalendar();
               if ( dataObject.get("dInDate").length() > 0  ) {
            	   log.debug("LONGITUD BUSQUEDA " + dataObject.get("dInDate").length() + " - ");
	               Date date1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss'Z'").parse(dataObject.get("dInDate"));
	               cal.setTime(date1);
	               XMLGregorianCalendar xmlGregCal =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
	               docTemp.setXFechaCarguePoder(xmlGregCal);
               }

               if ( dataObject.get("xFECHAINSTRUMENTO").length() > 0 ) {
	               log.debug("FECHA BUSQUEDA 2 " + dataObject.get("xFECHAINSTRUMENTO"));
	               Date date2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss'Z'").parse(dataObject.get("xFECHAINSTRUMENTO"));
	               cal.setTime(date2);
	               XMLGregorianCalendar xmlGregCal2 =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
	               docTemp.setXFechaInstrumento(xmlGregCal2);
               }

               if ( dataObject.get("dOutDate").length() > 0 ){
            	   log.debug("FECHA BUSQUEDA 3 " + dataObject.get("xFECHAINSTRUMENTO"));
            	   Date date3=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss'Z'").parse(dataObject.get("dOutDate"));
	               cal.setTime(date3);
	               XMLGregorianCalendar xmlGregCal3 =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
	               docTemp.setXFinalizacionPoder(xmlGregCal3);

               }
               documentoPreview.add(docTemp);
           }
           response.close();
           return documentoPreview;
       } catch (Exception ex) {
           log.debug("Error realizando consulta de poderes " + ex);
       }

       return documentoPreview;

   }




      /**
       * Realiza una busqueda de expedientes en  Oracle Webcenter Content
       *
       * @param consulta - Consulta en formato CMIS asociada a los expedientes
       * @return String retorna el formato "did--dDocName", si no encuentra nada retorna "0"
       */
     public static String buscarResultadosExpedientes(String consulta) {

         int num_resultados = 0;
         String resultado = "";
         try {
             String rowCount = "2000";

             DataBinder binder = idClient.createBinder();

             String queryText = consulta;
             log.debug("la consulta a realizar para traer folios es " + queryText );
             log.debug("Query Text : {0}" + queryText);
             binder.putLocal("IdcService", "GET_SEARCH_RESULTS");
             binder.putLocal("searchFormType", "standard");
             binder.putLocal("SearchQueryFormat", "UNIVERSAL");
             binder.putLocal("AdvSearch", "True");
             binder.putLocal("SortField", "dInDate");
             binder.putLocal("SortOrder", "Desc");
             binder.putLocal("QueryText", queryText);
             binder.putLocal("ResultCount", rowCount);
             binder.putLocal("StartRow", "1");
             binder.putLocal("UseSearchCache", "false");
             ServiceResponse response = idClient.sendRequest(userContext, binder);
             DataBinder binder2 = response.getResponseAsBinder();
             DataResultSet resultSet = binder2.getResultSet("SearchResults");
             List<Documento> documentoPreview = new ArrayList<>();


             for (DataObject dataObject : resultSet.getRows()) {
                 Documento docTemp = new Documento();
                 docTemp.setDocName(dataObject.get("dDocName"));
                 docTemp.setDID(dataObject.get("dID"));
                 docTemp.setCodOrip(dataObject.get("xCODIGO_ORIP"));
                 docTemp.setOrip(dataObject.get("xORIP"));
                 docTemp.setTipoDocumental(dataObject.get("dDocType"));
                 docTemp.setNir(dataObject.get("xNIR"));
                 docTemp.setTurno(dataObject.get("xTURNO"));
                 docTemp.setEntidadOrigen(dataObject.get("xENTIDAD_ORIGEN"));
                 docTemp.setIdentificacionInterviniente(dataObject.get("xINTERVINIENTE_IDENTIFICACION"));
                 docTemp.setMatricula(dataObject.get("xMATRICULA"));
                 docTemp.setNumeroDocumento(dataObject.get("xNUMERO_DOC"));
                 docTemp.setNombreInterviniente(dataObject.get("xINTERVINIENTE_NOMBRE"));
                 documentoPreview.add(docTemp);
                 num_resultados++;
             }
             response.close();

             log.debug("la Salida de busqueda de expediente es " + num_resultados );
             String vID = "";
             String vDName = "";

             if ( num_resultados  == 0){
                resultado = "0";
             }else if ( num_resultados == 1 ){
                vID = (documentoPreview.get(num_resultados-1).getDID());
                log.debug("El resultado de busqueda del id es " + vID );
                vDName = documentoPreview.get(num_resultados-1).getDocName();
                log.debug("El resultado de busqueda del ddocname es " + vDName );
                resultado = vID + "--" + vDName;
                log.debug("El valor de resultado es : " +  resultado );
             }else{
             	vID = (documentoPreview.get(num_resultados-1).getDID());
                 log.debug("El resultado de busqueda del id es " + vID );
                 vDName = documentoPreview.get(num_resultados-1).getDocName();
                 log.debug("El resultado de busqueda del ddocname es " + vDName );
                 resultado = vID + "--" + vDName;
                 log.debug("El valor de resultado es : " +  resultado );
             }


         } catch (Exception ex) {
             System.err.println("Error realizando consulta"+ ex.getMessage());
             log.debug("Error realizando la consulta" + ex.getMessage());

         }

         return resultado;

     }

    /**
     * Este método carga un archivo del sistema de archivos y devuelve la matriz de bytes del contenido.
     *
     * @param nombreArchivo La ruta archivo a convertir en base 64
     * @return  byte[] Salida del archivo en base 64
     * @throws IOException indica una excepcion de I/O ha ocurrido.
     */
    public static byte[] cargarArchivoComoBytesArray(String nombreArchivo) throws IOException {

    	File archivo = null;
        BufferedInputStream reader = null;
    	byte[] bytes = null;
    	FileInputStream fis = null;

        try {
        	archivo = new File(nombreArchivo);
        	int length = (int) archivo.length();
        	bytes = new byte[length];
        	fis = new FileInputStream(archivo);
        	reader = new BufferedInputStream(fis);
			reader.read(bytes, 0, length);
		} catch (FileNotFoundException e1) {
			log.error("Error "  + e1.getMessage());
		} finally {
			if ( fis != null) {
				fis.close();
			}
			if ( reader != null) {
			    reader.close();
			}
		}
        return bytes;
    }

    /**
     * Cargar las variables de operacion que se encuentran en la base de datos
     * El acceso se hace a traves del pool de conexion con jdni jdbc/ClienteOWCC.
     * El cual previamente debe ser creado en el servidor de aplicacionees JEE
     */
    public static void cargarVariables (){

        try {
          context = new InitialContext();
          log.debug("Obteniendo conexion jdbc/ClienteOWCC");
          DataSource ds = (javax.sql.DataSource) context.lookup("jdbc/ClienteOWCC");
          log.debug("Conectado a Base de datos ");
          conn = ds.getConnection();
          log.debug("Obtener Constantes");

          PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement("select caracter from sdb_pgn_constantes where id_constante = 'WCC_URL_GESTOR_DOCUMENTAL'");
			  rs = stmt.executeQuery();
			  while(rs.next()){
				  ClienteOwcc.urlContent = rs.getString("caracter");
			     log.debug( "BD URL :"  + rs.getString("caracter"));
			  }
		} catch (Exception e) {
			log.error("Error "  + e.getMessage());
		}

          try {
			stmt = conn.prepareStatement("select caracter from sdb_pgn_constantes where id_constante = 'WCC_USUARIO_GESTOR_DOCUMENTAL'");
			  rs = stmt.executeQuery();
			  while(rs.next()){
				  ClienteOwcc.usuarioContent = rs.getString("caracter");
			     log.debug( "BD URL USUARIO :" +rs.getString("caracter"));
			  }
		} catch (Exception e) {
			log.error("Error "  + e.getMessage());
		}

          try {
			stmt = conn.prepareStatement("select caracter from sdb_pgn_constantes where id_constante = 'WCC_PASSWORD_GESTOR_DOCUMENTAL'");
			  rs = stmt.executeQuery();
			  while(rs.next()){
				  ClienteOwcc.passwordContent = rs.getString("caracter");
			     log.debug(rs.getString("caracter"));
			  }
		} catch (Exception e) {
			log.error("Error "  + e.getMessage());
		}

          try {
			stmt = conn.prepareStatement("select caracter from sdb_pgn_constantes where id_constante = 'WCC_VISOR_GESTOR_DOCUMENTAL'");
			  rs = stmt.executeQuery();
			  while(rs.next()){
				  ClienteOwcc.urlVisor = rs.getString("caracter");
			     log.debug(rs.getString("caracter"));
			  }
		} catch (Exception e) {
			log.error("Error "  + e.getMessage());
		}

          try {
			stmt = conn.prepareStatement("select caracter from sdb_pgn_constantes where id_constante = 'TRD_ANO_GESTOR_DOCUMENTAL'");
			  rs = stmt.executeQuery();
			  while(rs.next()){
				  ClienteOwcc.trd = rs.getString("caracter");
			     log.debug(rs.getString("caracter"));
			  }
		} catch (Exception e) {
			log.error("Error "  + e.getMessage());
		}

          try {
			stmt = conn.prepareStatement("select caracter from sdb_pgn_constantes where id_constante = 'TRD_PREF_GESTOR_DOCUMENTAL'");
			  rs = stmt.executeQuery();
			  while(rs.next()){
				  ClienteOwcc.trdPrefijo = rs.getString("caracter");
			     log.debug(rs.getString("caracter"));
			  }
		} catch (Exception e) {
			log.error("Error "  + e.getMessage());
		}

          try {
			stmt = conn.prepareStatement("select caracter from sdb_pgn_constantes where id_constante = 'TMP_DIR_GESTOR_DOCUMENTAL'");
			  rs = stmt.executeQuery();
			  while(rs.next()){
				  ClienteOwcc.tmpDir = rs.getString("caracter");
			     log.debug(rs.getString("caracter"));
			  }
		} catch (Exception e) {
			log.error("Error "  + e.getMessage());
		}

          try {
			stmt = conn.prepareStatement("select caracter from sdb_pgn_constantes where id_constante = 'DIR_BACHUE_GESTOR_DOCUMENTAL'");
			  rs = stmt.executeQuery();
			  while(rs.next()){
				  ClienteOwcc.carpetaBaseBachue = rs.getString("caracter");
			     log.debug(rs.getString("caracter"));
			  }
		} catch (Exception e) {
			log.error("Error "  + e.getMessage());
		}

          try {
			stmt = conn.prepareStatement("select caracter from sdb_pgn_constantes where id_constante = 'DIR_CORRESPONDENCIA_GESTOR_DOCUMENTAL'");
			  rs = stmt.executeQuery();
			  while(rs.next()){
				  ClienteOwcc.carpetaBaseCorrespondencia = rs.getString("caracter");
			     log.debug(rs.getString("caracter"));
			  }
		} catch (Exception e) {
			log.error("Error "  + e.getMessage());
		}

          try {
			stmt = conn.prepareStatement("select caracter from sdb_pgn_constantes where id_constante = 'DIR_CONCILIACIONES_GESTOR_DOCUMENTAL'");
			  rs = stmt.executeQuery();
			  while(rs.next()){
				  ClienteOwcc.carpetaBaseConciliacion = rs.getString("caracter");
			     log.debug(rs.getString("caracter"));
			  }
		} catch (Exception e) {
			log.debug(e.getMessage());
		}

          try {
			stmt = conn.prepareStatement("select caracter from sdb_pgn_constantes where id_constante = 'DIR_CARPTEMPORAL_GESTOR_DOCUMENTAL'");
			  rs = stmt.executeQuery();
			  while(rs.next()){
				  ClienteOwcc.carpetaBaseTemporal = rs.getString("caracter");
			     log.debug(rs.getString("caracter"));
			  }
		} catch (Exception e) {
			log.error("Error "  + e.getMessage());
		}

          try {
			stmt = conn.prepareStatement("select caracter from sdb_pgn_constantes where id_constante = 'DIR_CONCILI_EXPED_GESTOR_DOCUMENTAL'");
			  rs = stmt.executeQuery();
			  while(rs.next()){
				  ClienteOwcc.carpetaBaseConciliacionExpediente = rs.getString("caracter");
			     log.debug(rs.getString("caracter"));
			  }
		} catch (Exception e) {
			log.error("Error "  + e.getMessage());
		}

          try {
			stmt = conn.prepareStatement("select caracter from sdb_pgn_constantes where id_constante = 'DIR_SEDEELEC_EXPED_GESTOR_DOCUMENTAL'");
			  rs = stmt.executeQuery();
			  while(rs.next()){
				  ClienteOwcc.carpetaBaseCorrespondenciaExpediente = rs.getString("caracter");
			     log.debug(rs.getString("caracter"));
			  }
		} catch (Exception e) {
			log.error("Error "  + e.getMessage());
		}

        try {
  			stmt = conn.prepareStatement("select caracter from sdb_pgn_constantes where id_constante = 'DIR_SEDEELEC_EXPED_GESTOR_DOCUMENTAL'");
  			  rs = stmt.executeQuery();
  			  while(rs.next()){
  				  ClienteOwcc.carpetaBaseSedeElectronicaExpediente = rs.getString("caracter");
  			     log.debug(rs.getString("caracter"));
  			  }
  		} catch (Exception e) {
  			log.error("Error "  + e.getMessage());
  		}

        try {
  			stmt = conn.prepareStatement("select caracter from sdb_pgn_constantes where id_constante = 'DIR_SEDEELEC_GESTOR_DOCUMENTAL'");
  			  rs = stmt.executeQuery();
  			  while(rs.next()){
  				  ClienteOwcc.carpetaBaseSedeElectronica = rs.getString("caracter");
  			     log.debug(rs.getString("caracter"));
  			  }
  		} catch (Exception e) {
  			log.error("Error "  + e.getMessage());
  		}

          stmt = conn.prepareStatement("select caracter from sdb_pgn_constantes where id_constante = 'DIR_BACHUE_EXPED_GESTOR_DOCUMENTAL'");
          rs = stmt.executeQuery();
          while(rs.next()){
        	  ClienteOwcc.carpetaBaseBachueExpediente = rs.getString("caracter");
             log.debug( "BD URL USUARIO :" +rs.getString("caracter"));
          }
          stmt.close();
          conn.close();
        }

        catch (NamingException e) {
        	log.error("Error "  + e.getMessage());
        }catch (SQLException e) {
        	log.error("Error "  + e.getMessage());
        }
        finally {

          try {

             conn.close();
             rs.close();
             context.close();
          }
          catch (Exception e) {
        	  log.error("Error "  + e.getMessage());
          }

        }

    }

    /**
     * Cierra un expediente basado en identificador del expediente ( dID ).
     *
     * @param vDidExpediente el dID del expediente a cerrar
     * @return String el resultado de la operacion
     */
     @SuppressWarnings("rawtypes")
     public static  String cerrarTurno ( String vDidExpediente ){

         String respuesta = "";

         IdcClientManager manager = new IdcClientManager ();

         log.debug("Procediendo a Cerrar Expediente con Operacion Lock Folio");
         try{
                 // Crea una conexion IdcClient usando protocolo  idc (i.e. socket connection to Content Server)
                 IdcClient idcClient = manager.createClient (ClienteOwcc.urlContent);

                 // Crea un nuevo contexto usando el usuario 'sysadmin'
                 IdcContext userContext = new IdcContext (ClienteOwcc.usuarioContent,ClienteOwcc.passwordContent);

                 // Databinder para solicitud de checkin
                 DataBinder dataBinder = idcClient.createBinder();
                 HdaBinderSerializer serializer = new HdaBinderSerializer ("UTF-8", idcClient.getDataFactory ());
                 //indicar la operacion a ejecutar

                 dataBinder.putLocal("IdcService", "LOCK_FOLIO");

                 dataBinder.putLocal("dID", vDidExpediente);
                 dataBinder.putLocal("RevisionSelectionMethod", "Specific");

                 // Escribre el data binder de la solicitud
                 serializer.serializeBinder (System.out, dataBinder);
                 // Envia la peticion al Content Server
                 ServiceResponse response = idcClient.sendRequest(userContext,dataBinder);
                 // Obtiene el data binder con la respuesta desde el Content Server
                 DataBinder responseData = response.getResponseAsBinder();
                 // Escribe los datos de respuesta en el stdout
                 serializer.serializeBinder (System.out, responseData);

                 respuesta = responseData.getLocal("dID") + "-" + responseData.getLocal("dDocName");
               } catch (IdcClientException ice){
                 respuesta = ice.getLocalizedMessage();
               } catch (IOException ioe){
                 respuesta = ioe.getLocalizedMessage();
         }
         log.debug("Respuesta de Operacion :" + respuesta);

         return respuesta;

     }

     @SuppressWarnings("rawtypes")
     public static  String cerrandoExpediente ( String vDidExpediente , String vDocNameExpediente ){

         String respuesta = "";

         IdcClientManager manager = new IdcClientManager ();

         log.debug("Procediendo a Cerrar Expediente con Operacion Lock Folio");
         try{
                 // Crea una conexion IdcClient usando protocolo  idc (i.e. socket connection to Content Server)
                 IdcClient idcClient = manager.createClient (ClienteOwcc.urlContent);

                 // Crea un nuevo contexto usando el usuario 'sysadmin'
                 IdcContext userContext = new IdcContext (ClienteOwcc.usuarioContent,ClienteOwcc.passwordContent);

                 // Databinder para solicitud de checkin
                 DataBinder dataBinder = idcClient.createBinder();
                 HdaBinderSerializer serializer = new HdaBinderSerializer ("UTF-8", idcClient.getDataFactory ());
                 //indicar la operacion a ejecutar

               //ARMADO DEL DATABINDER A ENVIAR A CONTENT

                 dataBinder.putLocal("IdcService", "UPDATE_DOCINFO");
                 dataBinder.putLocal("dDocName", vDocNameExpediente);
                 dataBinder.putLocal("dID", vDidExpediente);
                 dataBinder.putLocal("xESTADOEXPEDIENTE", "CERRADO");

                 // Escribre el data binder de la solicitud
                 serializer.serializeBinder (System.out, dataBinder);
                 // Envia la peticion al Content Server
                 ServiceResponse response = idcClient.sendRequest(userContext,dataBinder);
                 // Obtiene el data binder con la respuesta desde el Content Server
                 DataBinder responseData = response.getResponseAsBinder();
                 // Escribe los datos de respuesta en el stdout
                 serializer.serializeBinder (System.out, responseData);

                 respuesta = responseData.getLocal("dID") + "-" + responseData.getLocal("dDocName");
               } catch (IdcClientException ice){
                 respuesta = ice.getLocalizedMessage();
               } catch (IOException ioe){
                 respuesta = ioe.getLocalizedMessage();
         }
         log.debug("Respuesta de Operacion :" + respuesta);

         return respuesta;

     }

    /**
     * Este método carga un archivo en el sistema content y devuelve el dDocName y dDocID con el que fue registrado.
     *
     * @param archivoDestino the archivo destino
     * @param sistemaOrigen el sistema que envia la informacion CORE,CORRESPONDENCIA,
     * @param metadatos una lista llave valor con los metadatos del documento a hacer registros en content
     * @param repositorio al cual se dirige la creacion ( FINAL, TEMPORAL).
     * @return  String resultado de la operacion en formato dId-dDocName
     */
     @SuppressWarnings("rawtypes")
	public static  String checkinDocumento ( String archivoDestino , String sistemaOrigen ,  TipoEntradaEnviarDocumento.Parametros metadatos , String repositorio){

         List<TipoParametro> listaMetadatos = new ArrayList<TipoParametro>();

         listaMetadatos = metadatos.getParametro();

         String respuesta = "";
         String md5 = "";
         String orip = "";
         String nir  = "";
         String turno = "";
         String codorip = "";
         String proceso = "";
         String catRetencion = "";
         String idPerJuridica = "";
         String carpetaCorrespondencia = "";
         String actualizar = "NO";
         String dDocName = "";
         boolean campoVacio = false;
         String cuenta = "SNR";
         double bytes = 0;
         double kilobytes = 0;
         int obligatorios = 0;


         // Crear un nuevo IdcClientManager


         IdcClientManager manager = new IdcClientManager ();
         try{
                 // Crea una conexion IdcClient usando protocolo  idc (i.e. socket connection to Content Server)
                 IdcClient idcClient = manager.createClient (ClienteOwcc.urlContent);

                 // Crea un nuevo contexto usando el usuario 'sysadmin'
                 IdcContext userContext = new IdcContext (ClienteOwcc.usuarioContent,ClienteOwcc.passwordContent);

                 System.out.println("Generando MD5");

                 try {
                    md5 = obtenerMD5Checksum( tmpDir + "/" + archivoDestino );
                 } catch (Exception ex) {
                     log.debug(ex);
                 }

                 log.debug("Generado MD5");

                 // CALCULA EL TAMAÑO DEL ARCHIVO
                 try {
                     File archivo = new File ( tmpDir + "/" + archivoDestino );
                     bytes = archivo.length();
                     kilobytes = ( bytes / 1024 );
                  } catch (Exception ex) {
                      log.debug(ex);
                  }

                 // CALCULA EL NUMERO DE PAGINAS SOLO APLICA A ARCHIVOS PDF.
                 int paginas = 0;

                 if (obtenerExtensionArchivo(new File(tmpDir + "/" + archivoDestino)).equalsIgnoreCase("PDF")){
                 	log.debug("ES un archivo de extension pdf");
                 	PDDocument documento = PDDocument.load(new File(tmpDir + "/" + archivoDestino));
                     paginas = documento.getNumberOfPages();
                     documento.close();
                 }

                 // Databinder para solicitud de checkin
                 DataBinder dataBinder = idcClient.createBinder();
                 HdaBinderSerializer serializer = new HdaBinderSerializer ("UTF-8", idcClient.getDataFactory ());
                 //indicar la operacion a ejecutar

                 //ARMADO DEL DATABINDER A ENVIAR A CONTENT

                 dataBinder.putLocal("IdcService", "CHECKIN_UNIVERSAL");
                 dataBinder.putLocal("xFUNCION_RESUMEN", "MD5");
                 dataBinder.putLocal("xVALOR_HUELLA", md5);
                 
                 
                 dataBinder.putLocal("xNRO_FOLIOS", Integer.toString(paginas));
                 dataBinder.putLocal("xPAGINADO", Integer.toString(paginas));
                 dataBinder.putLocal("xTAMANO_ARCHIVO", Double.toString(kilobytes));

                 //CAPTURANDO LOS METADATOS QUE SON ENVIADOS

                 String validacionObligatorios = "";
                 String validacionOpcionales = "";

                 //validacion de atributos de los servicios
                 validacionObligatorios = validarEnvioObligatorios( listaMetadatos , sistemaOrigen );
                 validacionOpcionales = validarEnvioOpcionales (listaMetadatos);

                 if ( !validacionObligatorios.equalsIgnoreCase("VALIDOS") || !validacionOpcionales.equalsIgnoreCase("")) {
                      respuesta = validacionObligatorios + validacionOpcionales;
                 }	else  { //validos

	                 for (int i = 0; i < listaMetadatos.size(); i++) {
	                       dataBinder.putLocal(listaMetadatos.get(i).getNombre(), listaMetadatos.get(i).getValor());
	                       //METADATOS DE BACHUE Y CORRESPONDENCIA
	                       if ( listaMetadatos.get(i).getNombre().equals("xACTUALIZAR") ){
	                           actualizar = listaMetadatos.get(i).getValor();
	                           if ( actualizar.equalsIgnoreCase("SI")) {
	                         	  actualizar = "SI";
	                           }
	                           else {
	                        	  actualizar = "NO";
	                           }
	                       }
	                       if ( listaMetadatos.get(i).getNombre().equals("dDocType") ){
	                           obligatorios ++;
	                       }

	                       if ( listaMetadatos.get(i).getNombre().equals("dDocName") ){
	                           dDocName = listaMetadatos.get(i).getValor();
	                           if ( orip.equalsIgnoreCase("NA")) {
	                         	  orip = "GENERALES";
	                         	  campoVacio = true;
	                           }
	                       }
	                       if ( listaMetadatos.get(i).getNombre().equals("xORIP") ){
	                           orip = listaMetadatos.get(i).getValor();
	                           if ( orip.equalsIgnoreCase("NA") || orip.equalsIgnoreCase("ORIP NACIONAL") ) {
	                         	  orip = "GENERALES";
	                         	  campoVacio = true;
	                           }
	                       }
	                       if ( listaMetadatos.get(i).getNombre().equals("xCODIGO_ORIP") ){
	                           codorip = listaMetadatos.get(i).getValor();
	                           if ( codorip.equalsIgnoreCase("NA") || codorip.equalsIgnoreCase("SNR")) {
	                         	  codorip = "000";
	                         	  campoVacio = true;
	                         	  	if (sistemaOrigen.equalsIgnoreCase("CORE")){
	                                    cuenta = cuenta.concat("/BACHUE");
	                         	  	}else {
	                         	  		cuenta = cuenta.concat("/CORR");
	                         	  	}
	                           }
	                           else {
	                        	   if (sistemaOrigen.equalsIgnoreCase("CORE")){
	                                  cuenta = cuenta.concat("/BACHUE/" + codorip);
	                        	   }else {
	                        		  cuenta = cuenta.concat("/CORR/" + codorip);
	                        	   }
	                          	  log.debug("GENERANDO CUENTA : " + cuenta);
	                            }
	                       }

	                       if ( listaMetadatos.get(i).getNombre().equals("xNIR") ){
	                           nir = listaMetadatos.get(i).getValor();
	                       }
	                       if ( listaMetadatos.get(i).getNombre().equals("xTURNO") ){
	                           turno = listaMetadatos.get(i).getValor();
	                           if ( turno.equalsIgnoreCase("NA")) {
	                         	  turno = "000";
	                         	  campoVacio = true;
	                           }
	                           ClienteOwcc.idTurno = turno;
	                       }
	                       if ( listaMetadatos.get(i).getNombre().equals("xPROCESO") ){
	                           proceso = listaMetadatos.get(i).getValor();
	                           ClienteOwcc.idProceso = proceso;
	                       }
	                       if ( listaMetadatos.get(i).getNombre().equals("xINTERVINIENTE_NOMBRE") ){
	                           //Parte la cadena
	                    	   String cadenaInterviniente = listaMetadatos.get(i).getValor();
	                           String campoInterviniente = "xINTERVINIENTE_NOMBRE";
	                    	   int tamano = 3990;
	                           for (int a = 0; a <= listaMetadatos.get(i).getValor().length() / tamano ; a++) {
	                               if ( a != 0) {
	                                    campoInterviniente = "xINTERVINIENTE_NOMBRE_"+ a;
	                               }
	                               dataBinder.putLocal(campoInterviniente, cadenaInterviniente.substring(a * tamano , Math.min((a + 1) * tamano, cadenaInterviniente.length())));
	                           }
	                       }
	                       if ( listaMetadatos.get(i).getNombre().equals("xINTERVINIENTE_IDENTIFICACION") ){
	                           //Parte la cadena
	                    	   String cadenaInterviniente = listaMetadatos.get(i).getValor();
	                           String campoInterviniente = "xINTERVINIENTE_IDENTIFICACION";
	                    	   int tamano = 3990;
	                           for (int a = 0; a <= listaMetadatos.get(i).getValor().length() / tamano ; a++) {
	                               if ( a != 0) {
	                                    campoInterviniente = "xINTERVINIENTEIDENTIFICACION"+ a;
	                               }
	                               dataBinder.putLocal(campoInterviniente, cadenaInterviniente.substring(a * tamano , Math.min((a + 1) * tamano, cadenaInterviniente.length())));
	                           }
	                       }
	                       if ( listaMetadatos.get(i).getNombre().equals("xMATRICULA") ){
	                           //Parte la cadena
	                    	   String cadenaMatricula = listaMetadatos.get(i).getValor();
	                           String campoMatricula = "xMATRICULA";
	                    	   int tamano = 3990;
	                           ArrayList<String> matriculas = new ArrayList<>();
	                           for (int a = 0; a <= listaMetadatos.get(i).getValor().length() / tamano ; a++) {
	                               matriculas.add(cadenaMatricula.substring(a * tamano , Math.min((a + 1) * tamano, cadenaMatricula.length())));
	                               if ( a != 0) {
	                                    campoMatricula = "xMATRICULA_" + a;
	                               }
	                               dataBinder.putLocal(campoMatricula, cadenaMatricula.substring(a * tamano , Math.min((a + 1) * tamano, cadenaMatricula.length())));
	                               log.debug(campoMatricula +" :: "+ cadenaMatricula.substring(a * tamano , Math.min((a + 1) * tamano, cadenaMatricula.length())) );
	                           }
	                       }
	                       // METADATOS DE CORRESPONDENCIA
	                       if ( listaMetadatos.get(i).getNombre().equals("xRADICADO") ){
	                           ClienteOwcc.radicado = listaMetadatos.get(i).getValor();
	                       }
	                       if ( listaMetadatos.get(i).getNombre().equals("xIDPERJURIDICA") ){
	                           idPerJuridica = listaMetadatos.get(i).getValor();
	                           ClienteOwcc.idProceso = proceso;
	                       }

	                 }
	                 //Si los campos orip y codigo orip son vacios.
	                 if (orip.equalsIgnoreCase("") || orip.equalsIgnoreCase("ORIP NACIONAL")) {
	                     orip = "GENERALES";
	                 }
	                 if (codorip.equalsIgnoreCase("") || codorip.equalsIgnoreCase("SNR")) {
	                     codorip = "000";
	                 }

	                 // SI ES UNA ACTUALIZACION DE DOCUMENTO
	                 if (actualizar.equalsIgnoreCase("SI")){
	                	 //desgloquear el documento
	                	 String salida = "";
	                	 salida = desBloquearDocumento(dDocName);
	                	 log.debug("SALIDA DESBLOQUEO" +  salida);
	                	 //indicar el dDocName a chequear
	                	 dataBinder.putLocal("dDocName", dDocName);
	                 }

	                 // DETERMINAR CATEGORIAS DE RETENCION DE LOS DOCUMENTOS
	                 // Asignacion de categoria de retencion
	                 if (sistemaOrigen.equalsIgnoreCase("CORE")) {
	                	 if (repositorio.equalsIgnoreCase("FINAL")) {
	                		 //Evaluacion por Procesos
	                		 if (proceso.equalsIgnoreCase("CONCILIACION")) {
	                			  //Dirección financiera.
	                			  //Contabilidad y Costos.
	                			  //SERIE: 410-CONCILIACIONES BANCARIAS.
	                			  // A NIVEL CENTRAL
	                			 if (orip.equalsIgnoreCase("GENERALES")){
	                				 catRetencion = "CAT-" + ClienteOwcc.trd + "-4.1.2-410-0";
	                				 setIdCategoriaRetencion (catRetencion);
	                			 }
	                			 else {
	                				 catRetencion = "CAT-" + ClienteOwcc.trd + "-4.1.2-410-0";
	                				 setIdCategoriaRetencion (catRetencion);
	                			 }
	                				 dataBinder.putLocal("xCategoryID", catRetencion );
	                    		 log.debug("ES UNA CONCILIACION DE CATEGORIA" + catRetencion );

	                		 }
	                		 else if (proceso.equalsIgnoreCase("CERTIFICADOS")) {
	                			 if (( orip.equalsIgnoreCase("GENERALES")) || turno.equalsIgnoreCase("000") ){
	                				 catRetencion = "CAT-" + ClienteOwcc.trd + "-7-370-370.20";
	                				 setIdCategoriaRetencion (catRetencion);                			 }
	                			 else {
	                			 // Categoria de retencion SERIE: 370.20 Certificados de Tradición. a nivel de ORIP
	                			     catRetencion = "CAT-" + ClienteOwcc.trd + "-" + codorip + "-370-370.20";
	                			     setIdCategoriaRetencion (catRetencion);
	                			 }
	                    		 dataBinder.putLocal("xCategoryID", catRetencion );
	                    		 log.debug("ES EMISION DE CERTIFICADOS " + catRetencion );
	                		 }
	                		 else if (proceso.equalsIgnoreCase("REPORTESOPERATIVOS")) {

	                			 // Categoria de retencion SERIE: Registro Matrícula Inmobiliaria Por ORIP.
	                			 catRetencion = "CAT-" + ClienteOwcc.trd +"-" + codorip + "-540-0";
	                			 setIdCategoriaRetencion (catRetencion);
	                			 dataBinder.putLocal("xCategoryID", catRetencion );
	                    		 log.debug("ES UNA REPORTE OPERATIVO DE CATEGORIA" + catRetencion );
	                		 }
	                		 else {
	                			 // Categoria de retencion matricula inmobiliria
	                			 if (orip.equalsIgnoreCase("GENERALES")){
	                				 catRetencion = "CAT-" + ClienteOwcc.trd + "-7-540-0";
	                				 setIdCategoriaRetencion (catRetencion);
	                			 }
	                			 else {
	                			    catRetencion = "CAT-" + ClienteOwcc.trd +"-" + codorip + "-540-0";
	                			    setIdCategoriaRetencion (catRetencion);
	                			 }
	                			 log.debug("ES UN DOCUMENTO DE REGISTRO INMOBILIARIA " + catRetencion );
	                			 dataBinder.putLocal("xCategoryID", catRetencion );
	                		 }
	                	 }
	                 }
	                 // ASIGNA CATEGORIAS DE RETENCION
	                 else if ( sistemaOrigen.equalsIgnoreCase("CORRESPONDENCIA")){
	                	 if ( ClienteOwcc.radicado.contains("ER")) {
	                	 	 catRetencion = "CAT-" + ClienteOwcc.trd + "-4.0.7-350-350.10";
	                	 	 setIdCategoriaRetencion (catRetencion);
	                	 	 dataBinder.putLocal("xCategoryID", catRetencion );
	                	 }
	                	 else if (ClienteOwcc.radicado.contains("EE")) {
	                	 	 catRetencion = "CAT-" + ClienteOwcc.trd + "-4.0.7-350-350.05";
	                	 	 setIdCategoriaRetencion (catRetencion);
	                	 	 dataBinder.putLocal("xCategoryID", catRetencion );
	                	 }
	                	 
	                	 //REVISAR - MEDIDA TEMPORAL
	                	 else {
	                	 	 catRetencion = "CAT-" + ClienteOwcc.trd + "-4.0.7-350-350.05";
	                	 	 setIdCategoriaRetencion (catRetencion);
	                	 	 dataBinder.putLocal("xCategoryID", catRetencion );
	                	 }
	                 }
	                 //DETERMINAR LA SEGURIDAD DEL DOCUMENTO


	                 if ( sistemaOrigen.equalsIgnoreCase("CORRESPONDENCIA")){
	                	// Si es un radicado de ingreso
	                     if ( radicado.contains("ER")) {
	                  	    carpetaCorrespondencia = "COMUNICACIONES RECIBIDAS";
	                  	    dataBinder.putLocal("dSecurityGroup", "sgdCorrespondencia");
	                  	    if (codorip == null || codorip.equals("") || codorip.equalsIgnoreCase("NA")){
	                      	    dataBinder.putLocal("dDocAccount", "SNR/CORR" );
	                 	    }else {
	                  	        dataBinder.putLocal("dDocAccount", "SNR/CORR/" + codorip );
	                 	    }
	                     }
	                     // si es un radicado de salida
	                     else if ( radicado.contains("EE")) {
	                  	    carpetaCorrespondencia = "COMUNICACIONES ENVIADAS";
	                  	    dataBinder.putLocal("dSecurityGroup", "sgdCorrespondencia");
	                  	    if (codorip == null || codorip.equals("") || codorip.equalsIgnoreCase("NA")){
	                    	    dataBinder.putLocal("dDocAccount", "SNR/CORR" );
	                  	    }else {
	                	        dataBinder.putLocal("dDocAccount", "SNR/CORR/" + codorip );
	                  	    }

	                     }
	                     // Si es otro tipo de radicado
	                     else {
	                    	 carpetaCorrespondencia = "COMUNICACIONES RECIBIDAS";
	                    	// carpetaCorrespondencia = "COMUNICACIONES INTERNAS";
	                  	    dataBinder.putLocal("dDocAccount", "SNR/CORR/" + codorip );
	                  	    dataBinder.putLocal("dSecurityGroup", "sgdCorrespondencia");
	                     }

	                 }else {
	                	 if(proceso.equalsIgnoreCase("CONCILIACION")) {
	                		 dataBinder.putLocal("dSecurityGroup", "sgdConciliacion");
		                	 dataBinder.putLocal("dDocAccount", cuenta );
	                	 }else {
		                	 dataBinder.putLocal("dSecurityGroup", "sgdDocumentoBachue");
		                	 dataBinder.putLocal("dDocAccount", cuenta );
	                	 }
	                 }

	                 //DETERMINAR LAS CARPETAS DONDE DISPONER FISICAMENTE LOS DOCUMENTOS

	                 Date fecha = new Date();

	                 Calendar calendario = Calendar.getInstance();
	                 calendario.setTime(fecha);
	                 int anio  = calendario.get(Calendar.YEAR);
	                 SimpleDateFormat formatoContent = new SimpleDateFormat("yyyy-MM-DD HH:MM:SS");
	                 System.out.println(calendario.getTime());
	                 String formateado = formatoContent.format(calendario.getTime());
	                 System.out.println("Fecha Formateada " + formateado);


	                 String folderID = "";
	                 String folderBasePadreID = "";
	                 String folderPadreID = "";
	                 String dirValidarNIR = "";
	                 String dirValidarTurno = "";
	                 String dirValidarBaseNIR = "";
	                 String dirValidarBaseTurno = "";
	                 String tmpDocName = "";

	                 //DETERMINAR EL DDOCNAME PARA DOCUMENTOS TEMPORALES

	                 if (repositorio.equalsIgnoreCase("TEMPORAL")) {
	                	 if ( actualizar.equalsIgnoreCase("NO")) {
	                		 //GENERE UN DOCNAME NUEVO
		                	 Timestamp docName = new Timestamp(System.currentTimeMillis());
		                	 tmpDocName = "TMP" + docName.getTime();
		                	 dataBinder.putLocal("dDocName", tmpDocName);
	                	 }
	                	 else
	                		 //ACTUALICE EL EXISTENTE
	                		 tmpDocName = dDocName;
	                	 	 dataBinder.putLocal("dDocName", tmpDocName);
	                 }
	                 /*// esto debe ser borrado
	                 else {
	                	//GENERE UN DOCNAME NUEVO AMBIEnte Alterno
	                	 Timestamp docName = new Timestamp(System.currentTimeMillis());
	                	 tmpDocName = "SNRALT" + docName.getTime();
	                	 dataBinder.putLocal("dDocName", tmpDocName);
	                	 
	                 }*/

	                 //DETERMINAR CARPETAS Y FECHAS DE EXPIRACION ( EN EL CASO DE TEMPORALES )
	                 if (sistemaOrigen.equalsIgnoreCase("CORE")) {
	                	 if (repositorio.equalsIgnoreCase("TEMPORAL")) {
	                		 //Evaluacion por Procesos
	                		 if (proceso.equalsIgnoreCase("CONSULTAS")) {
	                			 dirValidarBaseNIR = carpetaBaseTemporal + "CONSULTAS/";
	                	         dirValidarNIR = carpetaBaseTemporal + "CONSULTAS/" + nir + "/";
	                			 dirValidarBaseTurno = carpetaBaseTemporal + "CONSULTAS/"  + nir + "/";
	                			 dirValidarTurno = carpetaBaseTemporal + "CONSULTAS/" + nir + "/" + turno + "/";
	                			 calendario.add(Calendar.MONTH, 2);
	                			 formateado = formatoContent.format(calendario.getTime());
	                			 dataBinder.putLocal("dOutDate", DataObjectEncodingUtils.encodeDate(calendario.getTime()));
	                		 }
	                		 else if (proceso.equalsIgnoreCase("COPIAS")) {
	                			 dirValidarBaseNIR = carpetaBaseTemporal + "COPIAS/";
	                	         dirValidarNIR = carpetaBaseTemporal + "COPIAS/" + nir + "/";
	                			 dirValidarBaseTurno = carpetaBaseTemporal + "COPIAS/"  + nir + "/";
	                			 dirValidarTurno = carpetaBaseTemporal + "COPIAS/" + nir + "/" + turno + "/";
	                			 calendario.add(Calendar.MONTH, 2);
	                			 formateado = formatoContent.format(calendario.getTime());
	                			 dataBinder.putLocal("dOutDate", DataObjectEncodingUtils.encodeDate(calendario.getTime()));
	                		 }
	                		 else if (proceso.equalsIgnoreCase("CUENTACUPOS")) {
	                			 dirValidarBaseNIR = carpetaBaseTemporal + "CUENTACUPOS/";
	                	         dirValidarNIR = carpetaBaseTemporal + "CUENTACUPOS/" + nir + "/";
	                			 dirValidarBaseTurno = carpetaBaseTemporal + "CUENTACUPOS/"  + nir + "/";
	                			 dirValidarTurno = carpetaBaseTemporal + "CUENTACUPOS/" + nir + "/" + turno + "/";
	                			 calendario.add(Calendar.MONTH, 120);
	                			 formateado = formatoContent.format(calendario.getTime());
	                			 dataBinder.putLocal("dOutDate", DataObjectEncodingUtils.encodeDate(calendario.getTime()));
	                		 }
	                		 else if (proceso.equalsIgnoreCase("REGISTROPERSONAJURIDICA")) {
	                			 dirValidarBaseNIR = carpetaBaseTemporal + "REGISTROPERSONAJURIDICA/";
	                	         dirValidarNIR = carpetaBaseTemporal + "REGISTROPERSONAJURIDICA/" + idPerJuridica + "/";
	                			 dirValidarBaseTurno = carpetaBaseTemporal + "REGISTROPERSONAJURIDICA/"  + idPerJuridica + "/";
	                			 dirValidarTurno = carpetaBaseTemporal + "REGISTROPERSONAJURIDICA/" + idPerJuridica + "/";
	                			 calendario.add(Calendar.MONTH, 2);
	                			 formateado = formatoContent.format(calendario.getTime());
	                			 dataBinder.putLocal("dOutDate", DataObjectEncodingUtils.encodeDate(calendario.getTime()));
	                	     }
	                		 else {
	                			 dirValidarBaseNIR = carpetaBaseTemporal + "OTROS/";
	                	         dirValidarNIR = carpetaBaseTemporal + "OTROS/" + nir + "/";
	                			 dirValidarBaseTurno = carpetaBaseTemporal + "OTROS/"  + nir + "/";
	                			 dirValidarTurno = carpetaBaseTemporal + "OTROS/" + nir + "/" + turno + "/";
	                		 }
	                	 }else {
	                		 //NO ES TEMPORAL EL REPOSITORIO PARA EL CORE BACHUE
	                		//Evaluacion por Procesos CONCILIACION
	                		 if (proceso.equalsIgnoreCase("CONCILIACION")) {
	                			 LocalDate current_date = LocalDate.now();
	                			 int current_Year = current_date.getYear();
	                			 dirValidarBaseNIR = carpetaBaseConciliacion;
	                	         dirValidarNIR = carpetaBaseConciliacion  + current_Year + "/";
	                			 dirValidarBaseTurno = carpetaBaseConciliacion   + current_Year + "/";
	                			 dirValidarTurno = carpetaBaseConciliacion + current_Year + "/" + current_date + "/";
	                		 }
	                		 else {
		                		 dirValidarBaseNIR = carpetaBaseBachue + codorip +"-"+ orip.toUpperCase() + "/";
		                         dirValidarNIR = carpetaBaseBachue + codorip +"-"+ orip.toUpperCase() + "/" + nir + "/";
		                         dirValidarBaseTurno = carpetaBaseBachue + codorip +"-"+ orip.toUpperCase() + "/"  + nir + "/";
		                         dirValidarTurno = carpetaBaseBachue + codorip +"-"+ orip.toUpperCase() + "/" + nir + "/" + turno + "/";
	                		 }
	                	 }
	                 }else if ( sistemaOrigen.equalsIgnoreCase("CORRESPONDENCIA")){
	                	 if (repositorio.equalsIgnoreCase("TEMPORAL")) {
		        			 dirValidarBaseNIR = carpetaBaseTemporal + "CORRESPONDENCIA/";
		        	         dirValidarNIR = carpetaBaseTemporal + "CORRESPONDENCIA/" + carpetaCorrespondencia + "/";
		        			 dirValidarBaseTurno = carpetaBaseTemporal + "CORRESPONDENCIA/"  + carpetaCorrespondencia + "/";
		        			 dirValidarTurno = carpetaBaseTemporal + "CORRESPONDENCIA/" + carpetaCorrespondencia + "/" + Integer.toString(anio) + "/";
		        			 log.debug("ASIGNACION DE CARPETA TEMPORAL CORRESPONDENCIA ");
		        			 calendario.add(Calendar.MONTH, 3);
	            			 formateado = formatoContent.format(calendario.getTime());
	            			 dataBinder.putLocal("dOutDate", formateado );
	                	 }
		        	     else {
		        			 dirValidarBaseNIR = carpetaBaseCorrespondencia + carpetaCorrespondencia + "/";
		        	         dirValidarNIR = carpetaBaseCorrespondencia + carpetaCorrespondencia + "/" + Integer.toString(anio) + "/";
		        			 dirValidarBaseTurno = carpetaBaseCorrespondencia  + carpetaCorrespondencia + "/" + Integer.toString(anio) + "/";
		        			 dirValidarTurno = carpetaBaseCorrespondencia + carpetaCorrespondencia + "/" + Integer.toString(anio) + "/";
	                         log.debug("ASIGNACION DE CARPETA FINAL CORRESPONDENCIA " + dirValidarTurno);
	        			 }
	                 }else if (sistemaOrigen.equalsIgnoreCase("SEDE_ELECTRONICA")) {

	                	 if (repositorio.equalsIgnoreCase("TEMPORAL")) {
		        			 dirValidarBaseNIR = carpetaBaseTemporal + "SEDE_ELECTRONICA/";
		        	         dirValidarNIR = carpetaBaseTemporal + "SEDE_ELECTRONICA/" + Integer.toString(anio) + "/";
		        			 dirValidarBaseTurno = carpetaBaseTemporal + "SEDE_ELECTRONICA/"  + Integer.toString(anio) + "/";
		        			 dirValidarTurno = carpetaBaseTemporal + "SEDE_ELECTRONICA/"  + Integer.toString(anio) + "/" +  nir +"/";;
		        			 log.debug("ASIGNACION DE CARPETA TEMPORAL SEDE ELECTRONUCA ");
		        			 calendario.add(Calendar.MONTH, 3);
	            			 dataBinder.putLocal("dOutDate", DataObjectEncodingUtils.encodeDate(calendario.getTime()));
	                	 }
		        	     else {
		        			 dirValidarBaseNIR = carpetaBaseSedeElectronica + codorip +"-"+ orip.toUpperCase() + "/";
	                         dirValidarNIR = carpetaBaseSedeElectronica + codorip +"-"+ orip.toUpperCase() + "/" + nir + "/";
	                         dirValidarBaseTurno = carpetaBaseSedeElectronica + codorip +"-"+ orip.toUpperCase() + "/"  + nir + "/";
	                         dirValidarTurno = carpetaBaseSedeElectronica + codorip +"-"+ orip.toUpperCase() + "/" + nir + "/" + turno + "/";
		        			 log.debug("ASIGNACION DE CARPETA FINAL SEDE ELECTRONICA " + dirValidarTurno);
	        			 }

	                 }

	                 // CREACION DE CARPETAS FISICAS EN ORACLE WEBCENTER CONTET
	                 if (sistemaOrigen.equalsIgnoreCase("CORE")) {
	                	 if( proceso.equalsIgnoreCase("CONCILIACION")) {
	                		
	                         LocalDate current_date = LocalDate.now();
	                         int current_Year  = current_date.getYear();
	                         nir = String.valueOf(current_Year);
	                         turno = current_date.toString();
	                	 }
	                     if ( !nir.isEmpty()){
	                         folderBasePadreID = obtenerFolderGUID(dirValidarBaseNIR);
	                         System.out.println("El UID de " + dirValidarBaseNIR + " es " + folderBasePadreID);
	                         folderPadreID = obtenerFolderGUID(dirValidarNIR);
	                         // Si no hay carpeta NIR Crearla
	                         if ( folderPadreID.equalsIgnoreCase("NOHAY")){
	                             log.debug("NO HAY CARPETA PADRE (NIR) SE PROCEDE A CREARLA");
	                             folderPadreID = crearFolder (usuarioContent , nir , folderBasePadreID , "cBachue");
	                             log.debug("El UID de la nueva carpeta padre " + nir + " es " + folderPadreID);
	                             folderID = folderPadreID;
	                             ClienteOwcc.idNir = nir;
	                             ClienteOwcc.idFolderPadre = folderPadreID;
	                             ClienteOwcc.idExpediente = nir;
	                         }
	                         else{
	                             folderID = folderPadreID;
	                             ClienteOwcc.idNir = nir;
	                             ClienteOwcc.idExpediente = nir;
	                             ClienteOwcc.idFolderPadre = folderPadreID;
	                         }
	                         dataBinder.putLocal("xCODIGO_ORIP", codorip);
	  	                   	 dataBinder.putLocal("xORIP", orip.toUpperCase());
	                      }
	                      if ( !turno.isEmpty()){
	                         String folderBaseHijoID = obtenerFolderGUID(dirValidarBaseTurno);
	                         System.out.println("El UID de " + dirValidarBaseTurno+ " es " + folderBaseHijoID);
	                         String folderHijoID = obtenerFolderGUID ( dirValidarTurno);
	                         System.out.println("El UID de " + dirValidarTurno+ " es " + folderHijoID);
	                         if( folderHijoID.equalsIgnoreCase("NOHAY")){
	                             System.out.println("NO HAY CARPETA HIJA SE PROCEDE A CRERAR");
	                             folderHijoID = crearFolder (usuarioContent , turno  , folderBaseHijoID , "cBachue");
	                             System.out.println("El UID de turno " + turno + " es " + folderHijoID);
	                             folderID = folderHijoID;
	                             ClienteOwcc.idTurno = turno;
	                             ClienteOwcc.idExpediente = turno;
	                             ClienteOwcc.idFolderHijo = folderHijoID;
	                         }
	                         else{
	                             folderID = folderHijoID;
	                             ClienteOwcc.idTurno = turno;
	                             ClienteOwcc.idExpediente = turno;
	                             ClienteOwcc.idFolderHijo = folderHijoID;
	                         }
	  	                   	 dataBinder.putLocal("xTURNO", turno.toUpperCase());
	                      }
	                      // Asigna el identificador de carpeta apropiado
	                      dataBinder.putLocal("fParentGUID", folderID);
	                 }else if ( sistemaOrigen.equalsIgnoreCase("CORRESPONDENCIA")){

	                		 folderBasePadreID = obtenerFolderGUID(dirValidarBaseNIR);
	                         log.debug("El UID de CARPETA DE CORRESPONDENCIA " + dirValidarBaseNIR + " es " + folderBasePadreID);
	                         folderPadreID = obtenerFolderGUID(dirValidarNIR);
	                         // Si no hay carpeta del Año Crearla
	                         if ( folderPadreID.equalsIgnoreCase("NOHAY")){
	                             log.debug("NO HAY CARPETA PADRE (NIR) SE PROCEDE A CREARLA");
	                             folderPadreID = crearFolder (usuarioContent , Integer.toString(anio) , folderBasePadreID , "cBachue");
	                             log.debug("El UID de la nueva carpeta padre " + nir + " es " + folderPadreID);
	                             folderID = folderPadreID;
	                             ClienteOwcc.idNir = nir;
	                             ClienteOwcc.idFolderPadre = folderPadreID;
	                             ClienteOwcc.idExpediente = nir;
	                             dataBinder.putLocal("fParentGUID", folderPadreID);
	                         }
	                         else {
	                             dataBinder.putLocal("fParentGUID", folderPadreID);
	                         }
	               	 }
	                 else if ( sistemaOrigen.equalsIgnoreCase("SEDE_ELECTRONICA")){

	                	 if ( !nir.isEmpty()){
	                         folderBasePadreID = obtenerFolderGUID(dirValidarBaseNIR);
	                         System.out.println("El UID de " + dirValidarBaseNIR + " es " + folderBasePadreID);
	                         folderPadreID = obtenerFolderGUID(dirValidarNIR);
	                         // Si no hay carpeta NIR Crearla
	                         if ( folderPadreID.equalsIgnoreCase("NOHAY")){
	                             log.debug("NO HAY CARPETA PADRE (NIR) SE PROCEDE A CREARLA");
	                             folderPadreID = crearFolder (usuarioContent , nir , folderBasePadreID , "cBachue");
	                             log.debug("El UID de la nueva carpeta padre " + nir + " es " + folderPadreID);
	                             folderID = folderPadreID;
	                             ClienteOwcc.idNir = nir;
	                             ClienteOwcc.idFolderPadre = folderPadreID;
	                             ClienteOwcc.idExpediente = nir;
	                         }
	                         else{
	                             folderID = folderPadreID;
	                             ClienteOwcc.idNir = nir;
	                             ClienteOwcc.idExpediente = nir;
	                             ClienteOwcc.idFolderPadre = folderPadreID;
	                         }
	                         dataBinder.putLocal("xCODIGO_ORIP", codorip);
	  	                   	 dataBinder.putLocal("xORIP", orip.toUpperCase());
	                      }
	                      if ( !turno.isEmpty()){
	                         String folderBaseHijoID = obtenerFolderGUID(dirValidarBaseTurno);
	                         System.out.println("El UID de " + dirValidarBaseTurno+ " es " + folderBaseHijoID);
	                         String folderHijoID = obtenerFolderGUID ( dirValidarTurno);
	                         System.out.println("El UID de " + dirValidarTurno+ " es " + folderHijoID);
	                         if( folderHijoID.equalsIgnoreCase("NOHAY")){
	                             System.out.println("NO HAY CARPETA HIJA SE PROCEDE A CRERAR");
	                             folderHijoID = crearFolder (usuarioContent , turno  , folderBaseHijoID , "cBachue");
	                             System.out.println("El UID de turno " + turno + " es " + folderHijoID);
	                             folderID = folderHijoID;
	                             ClienteOwcc.idTurno = turno;
	                             ClienteOwcc.idExpediente = turno;
	                             ClienteOwcc.idFolderHijo = folderHijoID;
	                         }
	                         else{
	                             folderID = folderHijoID;
	                             ClienteOwcc.idTurno = turno;
	                             ClienteOwcc.idExpediente = turno;
	                             ClienteOwcc.idFolderHijo = folderHijoID;
	                         }
	  	                   	 dataBinder.putLocal("xTURNO", turno.toUpperCase());
	                      }
	                      // Asigna el identificador de carpeta apropiado
	                      dataBinder.putLocal("fParentGUID", folderID);

	           	     }
	                 dataBinder.addFile("primaryFile", new File(tmpDir + "/" + archivoDestino ));
	                 if ( obligatorios == 0 ) {
	                	 respuesta = "Atributo dDocType es Obligatorio y no esta incluido";
	                 }else {
	                	// Escribe el data binder de la solicitud
	                     serializer.serializeBinder (System.out, dataBinder);
	                     // Envia la peticion al Content Server
	                     ServiceResponse response = idcClient.sendRequest(userContext,dataBinder);
	                     // Obtiene el data binder con la respuesta desde el Content Server
	                     DataBinder responseData = response.getResponseAsBinder();
	                     // Escribe los datos de respuesta en el stdout
	                     serializer.serializeBinder (System.out, responseData);

	                     log.debug("Retornado dDocName" + responseData.getLocal("dDocName" ));
	                     log.debug("Retornado dId" + responseData.getLocal("dID") );

	                     respuesta = responseData.getLocal("dDocName") + "-" + responseData.getLocal("dID");
	                     System.out.println("La respuesta de la operacion es : " + respuesta.toString());

	                 }

                 }

               } catch (IdcClientException ice){
                 respuesta = ice.getLocalizedMessage();
               } catch (IOException ioe){
                 respuesta = ioe.getLocalizedMessage();
               }

         return respuesta;

     }

     /**
      * Este método carga un archivo en el sistema content y devuelve el dDocName y dDocID con el que fue registrado.
      *
      * @param archivoDestino the archivo destino
      * @param sistemaOrigen el sistema que envia la informacion CORE,CORRESPONDENCIA,
      * @param metadatos una lista llave valor con los metadatos del documento a hacer registros en content
      * @param repositorio al cual se dirige la creacion ( FINAL).
      * @return  String resultado de la operacion en formato dId-dDocName
      */
    @SuppressWarnings("rawtypes")
 	public static  String checkinPoder ( String archivoDestino , String sistemaOrigen ,  TipoEntradaEnviarPoder.Parametros metadatos , String repositorio){

          List<Parametro> listaMetadatos = new ArrayList<Parametro>();

          listaMetadatos = metadatos.getParametro();

          String respuesta = "";
          String md5 = "";
          String actualizar = "NO";
          double bytes = 0;
          double kilobytes = 0;
          String salida = "";
          String podDocName = "";

          // Crear un nuevo IdcClientManager


          IdcClientManager manager = new IdcClientManager ();
          try{
                  // Crea una conexion IdcClient usando protocolo  idc (i.e. socket connection to Content Server)
                  IdcClient idcClient = manager.createClient (ClienteOwcc.urlContent);

                  // Crea un nuevo contexto usando el usuario 'sysadmin'
                  IdcContext userContext = new IdcContext (ClienteOwcc.usuarioContent,ClienteOwcc.passwordContent);

                  System.out.println("Generando MD5");

                  try {
                     md5 = obtenerMD5Checksum( tmpDir + "/" + archivoDestino );
                  } catch (Exception ex) {
                      log.debug(ex);
                  }

                  log.debug("Generado MD5");

                  // CALCULA EL TAMAÑO DEL ARCHIVO
                  try {
                      File archivo = new File ( tmpDir + "/" + archivoDestino );
                      bytes = archivo.length();
                      kilobytes = ( bytes / 1024 );
                   } catch (Exception ex) {
                       log.debug(ex);
                   }

                  // CALCULA EL NUMERO DE PAGINAS SOLO APLICA A ARCHIVOS PDF.
                  int paginas = 0;

                  if (obtenerExtensionArchivo(new File(tmpDir + "/" + archivoDestino)).equalsIgnoreCase("PDF")){
                  	log.debug("ES un archivo de extension pdf");
                  	PDDocument documento = PDDocument.load(new File(tmpDir + "/" + archivoDestino));
                      paginas = documento.getNumberOfPages();
                      documento.close();
                  }

                  // Databinder para solicitud de checkin
                  DataBinder dataBinder = idcClient.createBinder();
                  HdaBinderSerializer serializer = new HdaBinderSerializer ("UTF-8", idcClient.getDataFactory ());
                  //indicar la operacion a ejecutar

                  //ARMADO DEL DATABINDER A ENVIAR A CONTENT

                  dataBinder.putLocal("IdcService", "CHECKIN_UNIVERSAL");
                  dataBinder.putLocal("xFUNCION_RESUMEN", "MD5");
                  dataBinder.putLocal("xVALOR_HUELLA", md5);
                  dataBinder.putLocal("xIdcProfile", sistemaOrigen );
                  dataBinder.putLocal("xNRO_FOLIOS", Integer.toString(paginas));
                  dataBinder.putLocal("xTAMANO_ARCHIVO", Double.toString(kilobytes));
                  dataBinder.putLocal("dSecurityGroup", "sgdPoder");


                  //CAPTURANDO LOS METADATOS QUE SON ENVIADOS
                  int con = 0;
                  // VERIFICAR SI ES ACTUALIZACION DE PODER O NO
                  actualizar = "NO";

                  if ( listaMetadatos.get(con).getXActualizarDocumento() != null) {
                	  if ( listaMetadatos.get(con).getXActualizarDocumento().equals("SI") ){
                          actualizar = "SI";
                      }else {
                       	  actualizar = "NO";
                     }
                  }



            	 if ( actualizar.equalsIgnoreCase("NO")) {
            		 //GENERAR UN DOCNAME NUEVO PARA EL PODER
                	 Timestamp docName = new Timestamp(System.currentTimeMillis());
                	 podDocName = "PD" + docName.getTime();
                	 dataBinder.putLocal("dDocName", podDocName);

            	 }
            	 else {
            		 //ACTUALICE EL EXISTENTE
            		 if (listaMetadatos.get(con).getDDocName() != null) {

                    	 salida = desBloquearDocumento(listaMetadatos.get(con).getDDocName());
                   	     dataBinder.putLocal("dDocName", listaMetadatos.get(con).getDDocName());
                     }
                  }

            	  //Validar otros atributos
                  if (listaMetadatos.get(con).getXCCApoderado() != null) {
                	  dataBinder.putLocal("xCCAPODERADO", listaMetadatos.get(con).getXCCApoderado());
                  }
                  if (listaMetadatos.get(con).getDDocType() != null) {
                	  dataBinder.putLocal("dDocType", listaMetadatos.get(con).getDDocType());
                  }
                  if (listaMetadatos.get(con).getDDocTittle() != null) {
                	  dataBinder.putLocal("dDocTitle", listaMetadatos.get(con).getDDocTittle());
                  }
                  if (listaMetadatos.get(con).getXComments() != null) {
                	  dataBinder.putLocal("xComments", listaMetadatos.get(con).getXComments());
                  }
                  if (listaMetadatos.get(con).getXCCPoderdante() != null) {
                	  dataBinder.putLocal("xCCPODERDANTE", listaMetadatos.get(con).getXCCPoderdante());
                  }
                  if (listaMetadatos.get(con).getXNombreApoderado() != null) {
                	  dataBinder.putLocal("xNOMBREAPODERADO", listaMetadatos.get(con).getXNombreApoderado());
                  }
                  if (listaMetadatos.get(con).getXNombrePoderdante() != null) {
                	  dataBinder.putLocal("xNOMBREPODERDANTE", listaMetadatos.get(con).getXNombrePoderdante());
                  }
                  if (listaMetadatos.get(con).getXCirculoPredio() != null) {
                	  dataBinder.putLocal("xCIRCULOPREDIO", listaMetadatos.get(con).getXCirculoPredio());
                  }
                  if (listaMetadatos.get(con).getXCiudadPredio() != null) {
                	  dataBinder.putLocal("xCIUDADPREDIO", listaMetadatos.get(con).getXCiudadPredio());
                  }
                  if (listaMetadatos.get(con).getXEstadoPoder() != null) {
                	  dataBinder.putLocal("xESTADOPODER", listaMetadatos.get(con).getXEstadoPoder());
                  }
                  if (listaMetadatos.get(con).getDDocType() != null) {
                	  dataBinder.putLocal("dDocType", listaMetadatos.get(con).getDDocType());
                  }
                  if (listaMetadatos.get(con).getDDocTittle() != null) {
                	  dataBinder.putLocal("dDocTitle", listaMetadatos.get(con).getDDocTittle());
                  }
                  if (listaMetadatos.get(con).getXDepartamentoPredio() != null) {
                	  dataBinder.putLocal("xDEPARTAMENTOPREDIO", listaMetadatos.get(con).getXDepartamentoPredio());
                  }
                  if (listaMetadatos.get(con).getXDiligenciaReconocimiento() != null) {
                	  dataBinder.putLocal("xDILIGENCIARECONOCIMIENTO", listaMetadatos.get(con).getXDiligenciaReconocimiento());
                  }
                  if (listaMetadatos.get(con).getXFechaCarguePoder() != null) {
                	  dataBinder.putLocal("dInDate", DataObjectEncodingUtils.encodeDate(listaMetadatos.get(con).getXFechaCarguePoder().toGregorianCalendar().getTime()));
                  }
                  if (listaMetadatos.get(con).getXNotaria() != null) {
                	  dataBinder.putLocal("xNOTARIA", listaMetadatos.get(con).getXNotaria());
                  }
                  if (listaMetadatos.get(con).getXMunicipios() != null) {
                	  dataBinder.putLocal("xMUNICIPIO", listaMetadatos.get(con).getXMunicipios());
                  }
                  if (listaMetadatos.get(con).getXDepartamentos() != null) {
                	  dataBinder.putLocal("xDEPARTAMENTO", listaMetadatos.get(con).getXDepartamentos());
                  }
                  if (listaMetadatos.get(con).getXNumeroInstrumento() != null) {
                	  dataBinder.putLocal("xNUMEROINSTRUMENTO", listaMetadatos.get(con).getXNumeroInstrumento());
                  }
                  if (listaMetadatos.get(con).getXNumMatriculaPoder() != null) {
                	  dataBinder.putLocal("xMATRICULA", listaMetadatos.get(con).getXNumMatriculaPoder());
                  }
                  if (listaMetadatos.get(con).getXTipDocApoderado() != null) {
                	  dataBinder.putLocal("xTIPODOCAPODERADO", listaMetadatos.get(con).getXTipDocApoderado());
                  }
                  if (listaMetadatos.get(con).getXTipDocPoderdante() != null) {
                	  dataBinder.putLocal("xTIPODOCPODERDANTE", listaMetadatos.get(con).getXTipDocPoderdante());
                  }
                  if (listaMetadatos.get(con).getXJustificacionRevocado() != null) {
                	  dataBinder.putLocal("xJUSTIFICACIONREVOCADO", listaMetadatos.get(con).getXJustificacionRevocado());
                  }
                  if (listaMetadatos.get(con).getXJustificacionUsado() != null) {
                	  dataBinder.putLocal("xJUSTIFICACIONUSADO", listaMetadatos.get(con).getXJustificacionUsado());
                  }
                  if (listaMetadatos.get(con).getXDireccionPredio() != null) {
                	  dataBinder.putLocal("xDIRECCIONPREDIO", listaMetadatos.get(con).getXDireccionPredio());
                  }
                  if (listaMetadatos.get(con).getXUsoPoder() != null) {
                	  dataBinder.putLocal("xUSOPODER", listaMetadatos.get(con).getXUsoPoder());
                  }
                  if (listaMetadatos.get(con).getXTipoPoder() != null) {
                	  dataBinder.putLocal("xTIPOPODER", listaMetadatos.get(con).getXTipoPoder());
                  }
                  if (listaMetadatos.get(con).getXFinalizacionPoder() != null) {
                	  dataBinder.putLocal("dOutDate", DataObjectEncodingUtils.encodeDate(listaMetadatos.get(con).getXFinalizacionPoder().toGregorianCalendar().getTime()));
                  }
                  if (listaMetadatos.get(con).getXTipoDocumento() != null) {
                	  dataBinder.putLocal("xTIPODOCUMENTO", listaMetadatos.get(con).getXTipoDocumento());
                  }
                  if (listaMetadatos.get(con).getXNotificaciones() != null) {
                	  dataBinder.putLocal("xNOTIFICACIONES", listaMetadatos.get(con).getXNotificaciones());
                  }

                  //DETERMINAR LAS CARPETAS DONDE DISPONER FISICAMENTE LOS DOCUMENTOS

                  Date fecha = new Date();

                  Calendar calendario = Calendar.getInstance();
                  calendario.setTime(fecha);
                  //SimpleDateFormat formatoContent = new SimpleDateFormat("yyyy-MM-DD HH:MM:SS");
                  //String formateado = formatoContent.format(calendario.getTime());

                  String dirValidarPoder = carpetaBasePoderes;
                  String folderBasePadreID = "";


                  // CREACION DE CARPETAS FISICAS EN ORACLE WEBCENTER CONTET
                  if (sistemaOrigen.equalsIgnoreCase("PODERES")) {

                          folderBasePadreID = obtenerFolderGUID(dirValidarPoder);
                          System.out.println("El UID de " + dirValidarPoder + " es " + folderBasePadreID);
                          // Si no hay carpeta NIR Crearla
                          // Asigna el identificador de carpeta apropiado
                          dataBinder.putLocal("fParentGUID", folderBasePadreID);
                  }
                  dataBinder.addFile("primaryFile", new File(tmpDir + "/" + archivoDestino ));
                  // Envia la peticion al Content Server
                  ServiceResponse response = idcClient.sendRequest(userContext,dataBinder);
                  // Obtiene el data binder con la respuesta desde el Content Server
                  DataBinder responseData = response.getResponseAsBinder();
                  // Escribe los datos de respuesta en el stdout
                  serializer.serializeBinder (System.out, responseData);

                  log.debug("Retornado dDocName" + responseData.getLocal("dDocName" ));
                  log.debug("Retornado dId" + responseData.getLocal("dID") );

                  respuesta = responseData.getLocal("dDocName") + "-" + responseData.getLocal("dID");
                  System.out.println("La respuesta de la operacion es : " + respuesta.toString());


                  // Escribe el data binder de la solicitud



                } catch (IdcClientException ice){
                  respuesta = ice.getLocalizedMessage();
                } catch (IOException ioe){
                  respuesta = ioe.getLocalizedMessage();
          }

          return respuesta;

      }

    /**
      * Permite la creacion de un turno asociado a un nir existente
      *
      * @param sistemaOrigen el sistema origen que solicita la creacion del turno
      * @param metadatos listado llave valor con el metadato del xTURNO a crear, comunmente xTURNO, xNIR, xCODIGO_ORIP, xORIP
      * @return String resultado de la operacion.
      */
     public static String creacionTurno ( String sistemaOrigen ,  TipoEntradaCrearTurno.Parametros metadatos ){

         List<TipoParametroCT> listaMetadatos = new ArrayList<TipoParametroCT>();

         listaMetadatos = metadatos.getParametro();

         String respuesta = "";
         String orip = "";
         String nir  = "";
         String turno = "";
         String codorip = "";

         for (int i = 0; i < listaMetadatos.size(); i++) {
               log.debug("CAPTURANDO METADATO :" + listaMetadatos.get(i).getNombre());
               log.debug("CAPTURANDO VALOR : " + listaMetadatos.get(i).getValor());

               if ( listaMetadatos.get(i).getNombre().equals("xORIP") ){
                   orip = listaMetadatos.get(i).getValor();
               }
               if ( listaMetadatos.get(i).getNombre().equals("xCODIGO_ORIP") ){
                   codorip = listaMetadatos.get(i).getValor();
               }
               if ( listaMetadatos.get(i).getNombre().equals("xNIR") ){
                   nir = listaMetadatos.get(i).getValor();
               }
               if ( listaMetadatos.get(i).getNombre().equals("xTURNO") ){
                   turno = listaMetadatos.get(i).getValor();
               }
         }

         String turnoBasePath = carpetaBaseBachue + codorip +"-"+ orip.toUpperCase() + "/"  + nir + "/";
         String turnoPath = carpetaBaseBachue + codorip +"-"+ orip.toUpperCase() + "/" + nir + "/" + turno + "/";
         String folderID = "";

         // Si en la trama viene especifcado el turno

         // verificar que el nir no sea vacio
         if ( !nir.isEmpty()){
             if ( !turno.isEmpty()){
                 try {
                     String folderBaseTurnoID = ClienteOwcc.obtenerFolderGUID(turnoBasePath);
                     System.out.println("El UID de " + turnoBasePath+ " es " + folderBaseTurnoID);
                     String folderTurnoID = ClienteOwcc.obtenerFolderGUID ( turnoPath);
                     System.out.println("El UID de " + turnoPath+ " es " + folderTurnoID);
                     if( folderTurnoID.equalsIgnoreCase("NOHAY")){
                         log.debug("NO HAY CARPETA TURNO SE PROCEDE A CRERAR");
                         folderTurnoID = ClienteOwcc.crearFolder ("weblogic" , turno  , folderBaseTurnoID , "public");
                         log.debug("El UID de turno " + turno + " es " + folderTurnoID);
                         folderID = folderTurnoID;
                         respuesta = "Satisfactorio - " + folderTurnoID;
                     }
                     else{
                         respuesta = "Ya Existe Turno";
                     }} catch (IdcClientException ex) {
                     log.debug("Error" + ex);
                 }
             }
         }else{
             respuesta = "No existe NIR";
         }
         return respuesta;

     }


    /**
      * Calcula el checksum de una archivo.
      *
      * @param nombreArchivo la ruta del archivo a calcular el checksum
      * @return  byte[] la secuencia generada.
      * @throws Exception the exception
      */
     public static byte[] crearChecksum(String nombreArchivo) throws Exception {
        InputStream fis = null;
        fis = new FileInputStream(nombreArchivo);
        byte[] buffer = new byte[1024];
		MessageDigest complete = MessageDigest.getInstance("MD5");
		int numRead;
        try {


			do {
			    numRead = fis.read(buffer);
			    if (numRead > 0) {
			        complete.update(buffer, 0, numRead);
			    }
			} while (numRead != -1);

			fis.close();
			return complete.digest();
		} catch (Exception e) {
			log.debug("error", e);
			return complete.digest();
		}finally{
			fis.close();
		}
    }


    /**
     * Este metodo realizar la creacion de un expediente electronico o Folio en Oracle WebCenter Content.
     *
     * @param idExpediente el identificador del expediente por ejemplo "EXP-XXXXX"
     * @param nombreExpediente el nombre del expediente
     * @param grupoSeguridad el grupo de seguridad de Oracle WebCenter Content
     * @param sistemaOrigen el sistema origen que crea el expediente
     * @param proceso si pertenece a algun proceso de bachue.
     * @param cadenaExpediente titulo del expediente.
     * @return String retorna el dDocName del expediente generado.
     * @throws IdcClientException una excepcion en la invocacion a Oracle WebCenter Content
     * @throws UnsupportedEncodingException uan excepcion de encoding no soportado
     */
    @SuppressWarnings("rawtypes")
	public static String crearExpedienteElectronico ( String idExpediente , String nombreExpediente , String grupoSeguridad , String sistemaOrigen , String proceso , String cadenaExpediente ) throws IdcClientException, UnsupportedEncodingException {

            // Start a new client connection to UCM
            IdcClientManager manager = new IdcClientManager();
            // build a client that will communicate using the intradoc protocol
            IdcClient idcClient = manager.createClient(ClienteOwcc.urlContent);
            // create a trusted user connection to UCM
            IdcContext userContext = new IdcContext(ClienteOwcc.usuarioContent,ClienteOwcc.passwordContent);

            DataBinder folioBinder = idcClient.createBinder();
            // populate the binder with the parameters
            folioBinder.putLocal("IdcService", "CHECKIN_NEW_FOLIO");
            folioBinder.putLocal("dSecurityGroup", grupoSeguridad);
            folioBinder.putLocal("xIdcProfile", sistemaOrigen);
            folioBinder.putLocal("xPROCESO", proceso);
            folioBinder.putLocal("xCategoryID", ClienteOwcc.idCategoriaRetencion);

            if ( sistemaOrigen.equalsIgnoreCase("CORE")){
	            if ( proceso.equalsIgnoreCase("CONCILIACION")){
	            	String idFolderConciliacion = obtenerFolderGUID(ClienteOwcc.carpetaBaseConciliacionExpediente);
	            	folioBinder.putLocal("fParentGUID", idFolderConciliacion );
	                folioBinder.putLocal("dDocName", "EXP-"+idExpediente);
	                folioBinder.putLocal("dDocTitle", "EXPEDIENTE ELECTRONICO CONCILIACION " + cadenaExpediente );
	            }else {
	                String idFolderExpedienteBachue =  obtenerFolderGUID(ClienteOwcc.carpetaBaseBachueExpediente);
	            	folioBinder.putLocal("dDocName", "EXP-"+idExpediente);
	                folioBinder.putLocal("dDocTitle", "EXPEDIENTE ELECTRONICO BACHUE " + idExpediente );
	                folioBinder.putLocal("fParentGUID", idFolderExpedienteBachue);
	            }
            }
            else if ( sistemaOrigen.equalsIgnoreCase("CORRESPONDENCIA")){
            	String idFolderCorrespondencia = obtenerFolderGUID(ClienteOwcc.carpetaBaseCorrespondenciaExpediente);
            	folioBinder.putLocal("fParentGUID", idFolderCorrespondencia );
                folioBinder.putLocal("dDocName", "EXP-"+idExpediente);
                folioBinder.putLocal("dDocTitle", "EXPEDIENTE ELECTRONICO CORRESPONDENCIA " + cadenaExpediente );
            }

            folioBinder.putLocal("StatusCode", "0");
            folioBinder.putLocal("RevisionSelectionMethod", "Latest");
            folioBinder.putLocal("dDocType", "EXPEDIENTEELECTRONICO");
            folioBinder.putLocal("CheckinType", "simple");

            // send the request and get the response back from local data
            ServiceResponse folioResponse = idcClient.sendRequest(userContext, folioBinder);
            DataBinder folioData = folioResponse.getResponseAsBinder ();

            log.debug("Retornado dDocName del Expediente Creado" + folioData.getLocal("dDocName" ));
            log.debug("Retornado dId del Expediente Creado" + folioData.getLocal("dID") );

            return folioData.getLocal("dDocName");
    }


    /**
     * Este metodo realizar la creacion de un framewokr folder en Oracle WebCenter Content.
     *
     * @param duenoFolder El usuario dueño del folder
     * @param nombreFolder el nombre dado al folder
     * @param idFolderPadreID el identificador del folder padre
     * @param securityGroup el grupo de seguridad de este folder
     * @return String el resultado de la operacion.
     */
    @SuppressWarnings("rawtypes")
	public static String crearFolder(String duenoFolder, String nombreFolder, String idFolderPadreID, String securityGroup) {
        ServiceResponse serviceResponse = null;
        String idFolder = "";

        try {

            IdcClientManager manager = new IdcClientManager ();
            // Create a new IdcClient Connection using idc protocol (i.e. socket connection to Content Server)
            System.out.println("URL Content " + ClienteOwcc.urlContent);
            IdcClient idcClient = manager.createClient (ClienteOwcc.urlContent);

            IdcContext userContext = new IdcContext (ClienteOwcc.usuarioContent,ClienteOwcc.passwordContent);

            System.out.println("Creando Folder: " + nombreFolder);

            DataBinder dataBinderReq = idcClient.createBinder();

            HdaBinderSerializer serializer = new HdaBinderSerializer ("UTF-8", idcClient.getDataFactory ());

            dataBinderReq.putLocal("IdcService","FLD_CREATE_FOLDER");
            //GUID of the folder under which this new folder is being created . This value can be retrieved from the DB or UI by using IsJava=1 paremeter
            dataBinderReq.putLocal("fParentGUID",idFolderPadreID);
            //Name of the new folder being created
            dataBinderReq.putLocal("fFolderName",nombreFolder);
            dataBinderReq.putLocal("fSecurityGroup",securityGroup); // Security group to which the folder is assigned .
            dataBinderReq.putLocal("fInhibitPropagation","1"); //Inhibit Propagation value . Either 1 or 0
            dataBinderReq.putLocal("fPromptForMetadata","1"); // Prompt for Metadata - used when DIS comes into picture - Values - 0 or 1
           //dataBinderReq.putLocal("fFolderType",duenoFolder); // optional parameter
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXENVIO CREACION CARPETA XXXXXXXXXXXXXXXXXXXXXXX");
            serializer.serializeBinder (System.out, dataBinderReq);

            serviceResponse = idcClient.sendRequest(userContext, dataBinderReq);

            DataBinder dataBinderResp = serviceResponse.getResponseAsBinder();
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXRESPUESTA CREACION CARPETA XXXXXXXXXXXXXXXXXXXXXXX");
            serializer.serializeBinder (System.out, dataBinderResp);

            idFolder = serviceResponse.getResponseAsBinder().getLocal("fFolderGUID").toString();

            System.out.println("Folder " + nombreFolder +" creado satisfactoriamente");
            System.out.println("Folder " + idFolder +" creado satisfactoriamente");

        } catch(Exception ex) {
            System.out.println("Error creando Folder: " + ex.getMessage());
            idFolder = "ERROR";
        } finally {
            if (serviceResponse != null) {
                serviceResponse.close();
            }
        }

        return idFolder;
    }

    /**
     * Toma un stream decodificado en base 64 y lo lleva a un archivo
     *
     * @param archivoFuente el Stream en Base64
     * @param archivoDestino ruta del Archivo destino
     */
    public static void decodificar(byte[] archivoFuente, String archivoDestino){

        try {
        	archivoDestino = tmpDir + "/" + archivoDestino;
            byte[] decodedBytes = Base64.decodeBase64(archivoFuente);
			escribirByteArraysAArchivo(archivoDestino, decodedBytes);
		} catch (IOException e) {
			log.error("Error "  + e.getMessage());
		}
    }

    /**
     * Desbloquea el documento para generar una nueva version.
     *
     * @param vDocName el valor dDocName del documento a desbloquear
     * @return String si se pudo desbloquear el archivo o no, si es si retorna el dID-dDocName
     */
    @SuppressWarnings("rawtypes")
    public static  String desBloquearDocumento ( String vDocName ){

        String respuesta = "";

        IdcClientManager manager = new IdcClientManager ();

        log.debug("Procediendo a generar nueva version de documento con nombre " +  vDocName);
        try{
                // Crea una conexion IdcClient usando protocolo  idc (i.e. socket connection to Content Server)
                IdcClient idcClient = manager.createClient (ClienteOwcc.urlContent);

                // Crea un nuevo contexto usando el usuario 'sysadmin'
                IdcContext userContext = new IdcContext (ClienteOwcc.usuarioContent,ClienteOwcc.passwordContent);

                // Databinder para solicitud de checkin
                DataBinder dataBinder = idcClient.createBinder();
                HdaBinderSerializer serializer = new HdaBinderSerializer ("UTF-8", idcClient.getDataFactory ());
                //indicar la operacion a ejecutar

                dataBinder.putLocal("IdcService", "CHECKOUT_BY_NAME");
                dataBinder.putLocal("dDocName", vDocName);
                dataBinder.putLocal("RevisionSelectionMethod", "Specific");

                // Escribre el data binder de la solicitud
                serializer.serializeBinder (System.out, dataBinder);
                // Envia la peticion al Content Server
                ServiceResponse response = idcClient.sendRequest(userContext,dataBinder);
                // Obtiene el data binder con la respuesta desde el Content Server
                DataBinder responseData = response.getResponseAsBinder();
                // Escribe los datos de respuesta en el stdout
                serializer.serializeBinder (System.out, responseData);

                respuesta = responseData.getLocal("dID") + "-" + responseData.getLocal("dDocName");
              } catch (IdcClientException ice){
                respuesta = ice.getLocalizedMessage();
              } catch (IOException ioe){
                respuesta = ioe.getLocalizedMessage();
        }
        log.debug("Respuesta de Operacion de desbloqueo :" + respuesta);

        return respuesta;

    }


    /**
     * Eliminar archivo temporal del sistema operativo.
     *
     * @param archivo , ruta del archivo a eliminar
     * @return true, si fue satisfactorio
     * @throws IOException Una excepcion de I/O ha ocurrido.
     */
     public static boolean eliminarArchivo(File archivo) throws IOException {
     if (archivo != null) {
         if (archivo.isDirectory()) {
             File[] files = archivo.listFiles();

             for (File f: files) {
                 eliminarArchivo(f);
             }
         }
         return Files.deleteIfExists(archivo.toPath());
     }
     return false;
     }

     /**
      * Enviar archivo content, si ya existe el documento en content, busca generar una nueva version del documento.
      *
      * @param docTitle Titulo del documento
      * @param docName el atributo dDocName
      * @param dDocAuthor Autor del documento
      * @param dDocType Tipo del documento
      * @param dSecurityGroup grupo de seguridad del documento
      * @param dPrimaryFile Archivo
      * @return el resultado de la operacion.
      * @throws IdcClientException the idc client exception
      * @throws IOException Signals that an I/O exception has occurred.
      */
     @SuppressWarnings("rawtypes")
	public static String enviarArchivoContent(String docTitle, String docName, String dDocAuthor, String dDocType,
   		  String dSecurityGroup, File dPrimaryFile) throws IdcClientException,
   		  IOException {

   	  String respuesta = "";
   	  IdcClientManager manager = new IdcClientManager ();

         log.debug("Procediendo a hacer Checkin de Expediente con Operacion Lock Folio");


   	  try{
             // Crea una conexion IdcClient usando protocolo  idc (i.e. socket connection to Content Server)
             IdcClient idcCliente = manager.createClient (ClienteOwcc.urlContent);

             // Crea un nuevo contexto usando el usuario 'sysadmin'
             IdcContext userContext = new IdcContext (ClienteOwcc.usuarioContent,ClienteOwcc.passwordContent);

             // Databinder para solicitud de checkin
             DataBinder dataBinder = idcCliente.createBinder();
             //indicar la operacion a ejecutar

   		  dataBinder.putLocal("IdcService", "CHECKIN_UNIVERSAL");
   		  dataBinder.putLocal("dDocTitle", docTitle);
   		  dataBinder.putLocal("dDocName", docName);
   		  dataBinder.putLocal("dDocAuthor", dDocAuthor);
   		  dataBinder.putLocal("dDocType", dDocType);
   		  dataBinder.putLocal("dSecurityGroup", dSecurityGroup);
   		  dataBinder.putLocal("dDocAccount","SNR/BACHUE");
   		  dataBinder.putLocal("doFileCopy", "0");
   		  dataBinder.putLocal("dPrimaryFile", "ARCHIVE");

   		  if (dPrimaryFile.exists()) {

   		  dataBinder.addFile("primaryFile", dPrimaryFile);
   		  	log.debug("Chequeando Documento Indice Electronico " + dPrimaryFile.getAbsolutePath());
   		  }
   		  ServiceResponse response = idcCliente.sendRequest(userContext, dataBinder);
   		  DataBinder responseBinder = response.getResponseAsBinder();

   		  log.debug("Indice Chequeado y su ID es = :" + responseBinder.getLocalData().get("dID"));
   		  log.debug("Indice Chequeado y su DdocName es = :" + responseBinder.getLocalData().get("dDocName"));
   		  if (response != null) {
   			  response.close();
   		  }

   		  respuesta =  responseBinder.getLocalData().get("dDocName");

         } catch (IdcClientException ice){
             respuesta = ice.getLocalizedMessage();
           } catch (IOException ioe){
             respuesta = ioe.getLocalizedMessage();
     }
     log.debug("Respuesta de Operacion :" + respuesta);

     return respuesta;

     }

     /**
     * Este metodo escribe el contenido de la matriz de bytes en un archivo.
     *
     * @param nombreArchivo El nombre del archivo a escribir
     * @param contenido El contenido del archivo en byte[]
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static void escribirByteArraysAArchivo(String nombreArchivo, byte[] contenido) throws IOException{

        File file = null;
        BufferedOutputStream writer = null;
        FileOutputStream fos = null;
		try {
			file = new File(nombreArchivo);
			fos = new FileOutputStream(file);
			writer = new BufferedOutputStream(fos);
			writer.write(contenido);
	        writer.flush();
		} catch (FileNotFoundException e) {
			log.error("Error "  + e.getMessage());
		} finally {
			if ( writer != null) {
               writer.close();
			}
        }

    }

     /**
      * Genera el indice electronico en formato pdf de un folio y almacena este ultimo en el folio.
      * Es de indicar que si ya existe lo que hace es generar una version o revision de este.
      *
      * @param idDoc el Identificador dID del folio de OWCC a generar el indice electronico
      * @param titulo el titulo a dar al indice electronico, este sale impreso en el pdf generado.
      * @param nombre el nombre a dar al indice electronico.
      * @return String el dDocName del indice generado.
      */
     public static String generarIndiceElectronico(String idDoc , String titulo , String nombre) {

         String dIDDoc = "";
         String vTitulo = titulo +" - " + nombre;
         String vNumDocumento = "0";


     	try {

         	 //Descargar el archivo xml correspondiente al folio
     		 obtenerArchivoDeUCM(idDoc);

             File xmlfile = new File(tmpDir + "/" + idDoc + ".xml");
             File xsltfile = new File("expediente.xsl");
             log.debug("Path Expediente temporalmente : " + xsltfile.getAbsolutePath());


             File pdfDir = new File(tmpDir);
             pdfDir.mkdirs();
             File pdfFile = new File(pdfDir, "IndiceElectronico"+idDoc+".pdf");
             log.debug("Archivo generado temporalmente : " + pdfFile.getAbsolutePath());
             log.debug("Longitud Inicial de Archivo generado temporalmente : " + pdfFile.length());

             reemplazarCadena(xmlfile.toString(), "", " xmlns=\"http://stellent.com/cpd\"");
             // configurar fopFactory
             final FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());

             FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
             // configurar foUserAgent

          // Configurar la salda
             OutputStream out = null;


             try {

            	 out = new FileOutputStream(pdfFile);
                 out = new java.io.BufferedOutputStream(out);
            	     // Constructor fop
                     Fop fop;

                     fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

                     // configurar XSLT
                     TransformerFactory factory = TransformerFactory.newInstance();
                     factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

                     Transformer transformer = factory.newTransformer(new StreamSource(xsltfile));
                     transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                      // asignar el valor del titulo en una variable de la hoja de estilo
                     vTitulo = titulo +" - " + nombre;
                     transformer.setParameter("ExpedienteNombre", "Indice Electronico " + vTitulo );

                     // configurar la transformacion XSLT transformation
                     Source src = new StreamSource(xmlfile);


                     Result res = new SAXResult(fop.getDefaultHandler());

                     // Iniciar la tranformacion XSLT y procesamiento FOP
                     transformer.transform(src, res);

                     log.debug("Longitud Final de Archivo generado temporalmente : " + pdfFile.length());

             } catch (FOPException | TransformerException e) {
                 log.debug("Error aplicando tranformacion" + e);
             } finally {
            	 if ( out != null ) {
                    out.close();
            	 }
             }

             vNumDocumento = ClienteOwcc.existeDocumento("INDICEELECTRONICO" ,  nombre , "IND-");
             log.debug("la Salida de la busqueda del Indice es " + vNumDocumento );
             if ( vNumDocumento.equalsIgnoreCase("0")){
            	//NO EXISTE INDICE
                 try {
              	   log.debug("Creando indice electronico ");
                   dIDDoc = enviarArchivoContent("Indice Electronico " + vTitulo, "IND-" + nombre, usuarioContent , "INDICEELECTRONICO","sgdDocumentoBachue", pdfFile);
                 } catch (IdcClientException ex) {
                     log.debug( ex);
                 } catch (UnsupportedEncodingException ex) {
                     log.debug(ex);
                 }

             }
             else {
             //Existe indice, generar nueva version de documento
                 try {
              	   log.debug("Generando version del documento ");
              	   //desgloquear el documento
              	   String salida = "";
              	   log.debug("documento a desbloquear" + nombre);
              	   salida = desBloquearDocumento("IND-" + nombre);
              	   log.debug("SALIDA DESBLOQUEO" +  salida);
                   dIDDoc = enviarArchivoContent("Indice Electronico " + vTitulo, "IND-" + nombre, usuarioContent , "INDICEELECTRONICO","sgdDocumentoBachue", pdfFile);
                  }catch (UnsupportedEncodingException ex) {
                      log.debug(ex);
                  }
             }
         }catch(IOException exp){
        	 log.error("Error "  + exp.getMessage());
         } catch (IdcClientException e1) {
        	 log.error("Error "  + e1.getMessage());
 		}

         return dIDDoc;
     }


     /**
      * Genera el indice electronico en formato pdf de un folio y almacena este ultimo en el folio.
      * Es de indicar que si ya existe lo que hace es generar una version o revision de este.
      *
      * @param idDoc el Identificador dID del folio de OWCC a generar el indice electronico
      * @param titulo el titulo a dar al indice electronico, este sale impreso en el pdf generado.
      * @param nombre el nombre a dar al indice electronico.
      * @return String el dDocName del indice generado.
      */
     public static String cerrarExpediente(String idDoc , String titulo , String nombre) {

         String dIDDoc = "";
         String vTitulo = titulo +" - " + nombre;
         String vNumDocumento = "0";


     	try {

         	 //Descargar el archivo xml correspondiente al folio
     		 obtenerArchivoDeUCM(idDoc);

             File xmlfile = new File(tmpDir + "/" + idDoc + ".xml");
             File xsltfile = new File("expediente.xsl");
             log.debug("Path Expediente temporalmente : " + xsltfile.getAbsolutePath());


             File pdfDir = new File(tmpDir);
             pdfDir.mkdirs();
             File pdfFile = new File(pdfDir, "IndiceElectronico"+idDoc+".pdf");
             log.debug("Archivo generado temporalmente : " + pdfFile.getAbsolutePath());
             log.debug("Longitud Inicial de Archivo generado temporalmente : " + pdfFile.length());

             reemplazarCadena(xmlfile.toString(), "", " xmlns=\"http://stellent.com/cpd\"");
             // configurar fopFactory
             final FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());

             FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
             // configurar foUserAgent

          // Configurar la salda
             OutputStream out = null;


             try {

            	 out = new FileOutputStream(pdfFile);
                 out = new java.io.BufferedOutputStream(out);
            	     // Constructor fop
                     Fop fop;

                     fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

                     // configurar XSLT
                     TransformerFactory factory = TransformerFactory.newInstance();
                     factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

                     Transformer transformer = factory.newTransformer(new StreamSource(xsltfile));
                     transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                      // asignar el valor del titulo en una variable de la hoja de estilo
                     vTitulo = titulo +" - " + nombre;
                     transformer.setParameter("ExpedienteNombre", "Indice Electronico " + vTitulo );

                     // configurar la transformacion XSLT transformation
                     Source src = new StreamSource(xmlfile);


                     Result res = new SAXResult(fop.getDefaultHandler());

                     // Iniciar la tranformacion XSLT y procesamiento FOP
                     transformer.transform(src, res);

                     log.debug("Longitud Final de Archivo generado temporalmente : " + pdfFile.length());

             } catch (FOPException | TransformerException e) {
                 log.debug("Error aplicando tranformacion" + e);
             } finally {
            	 if ( out != null ) {
                    out.close();
            	 }
             }

             vNumDocumento = ClienteOwcc.existeDocumento("INDICEELECTRONICO" ,  nombre , "IND-");
             log.debug("la Salida de la busqueda del Indice es " + vNumDocumento );
             if ( vNumDocumento.equalsIgnoreCase("0")){
            	//NO EXISTE INDICE
                 try {
              	   log.debug("Creando indice electronico ");
                   dIDDoc = enviarArchivoContent("Indice Electronico " + vTitulo, "IND-" + nombre, usuarioContent , "INDICEELECTRONICO","sgdDocumentoBachue", pdfFile);
                 } catch (IdcClientException ex) {
                     log.debug( ex);
                 } catch (UnsupportedEncodingException ex) {
                     log.debug(ex);
                 }

             }
             else {
             //Existe indice, generar nueva version de documento
                 try {
              	   log.debug("Generando version del documento ");
              	   //desgloquear el documento
              	   String salida = "";
              	   log.debug("documento a desbloquear" + nombre);
              	   salida = desBloquearDocumento("IND-" + nombre);
              	   log.debug("SALIDA DESBLOQUEO" +  salida);
                   dIDDoc = enviarArchivoContent("Indice Electronico " + vTitulo, "IND-" + nombre, usuarioContent , "INDICEELECTRONICO","sgdDocumentoBachue", pdfFile);
                  }catch (UnsupportedEncodingException ex) {
                      log.debug(ex);
                  }
             }
         }catch(IOException exp){
        	 log.error("Error "  + exp.getMessage());
         } catch (IdcClientException e1) {
        	 log.error("Error "  + e1.getMessage());
 		}

         return dIDDoc;
     }



     /**
     * Carga los tipos de contenidos comunes para las busquedas de content.
     */
    private static void loadContentType() {
        types.put(".doc", "application/msword");
        types.put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        types.put("xls", "application/vnd.ms-excel");
        types.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        types.put("ppt", "application/vnd.ms-powerpoint");
        types.put("pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
        types.put("pdf", "application/pdf");
        types.put("jpe", "image/jpeg");
        types.put("jpg", "image/jpeg");
        types.put("jpeg", "image/jpeg");
        types.put("png", "image/png");

    }


     /**
      * Obtener archivo stream de bytes basado en el dId en Content.
      *
      * @param dID el identificador del documento a obtener
      * @return los bytes en salida byte[]
      * @throws IdcClientException Excepciones de conexion del cliente ridc a content
      * @throws IOException Signals that an I/O exception has occurred.
      */
     public static byte[] obtenerArchivo(String dID) throws IdcClientException, IOException {

 		byte[] result = null;

         log.debug("Procediendo a Obtener archivo de Expediente con Operacion Lock Folio");
         try{
                 // Databinder para solicitud de checkin
                 DataBinder dataBinder = idClient.createBinder();

                 //indicar la operacion a ejecutar
 				dataBinder.putLocal("IdcService", "GET_FILE");
 				dataBinder.putLocal("dID", dID );
 				ServiceResponse response = idClient.sendRequest(userContext, dataBinder);
 				//The file is streamed back to us in the response
 				InputStream fstream = response.getResponseStream();
 				ByteArrayOutputStream bos = null;

 				try {
 					int thisLine = 0;
 					bos = new ByteArrayOutputStream();
 					while ((thisLine = fstream.read()) != -1) {
 						bos.write(thisLine);
 					}
 					result = bos.toByteArray();
 				} catch (Exception e) {
 				System.err.println(e);
 				} finally {
 				if (fstream != null)
 					fstream.close();
 				if (bos != null) {
 					bos.flush();
 					bos.close();
 				}
 			}

         } catch (IdcClientException ice){
             log.debug(ice.getLocalizedMessage());
         }
         return result;
 }

     /**
      * Basado en el dID retorna el archivo.
      *
      * @param documentId the document id
      * @throws IdcClientException the idc client exception
      * @throws IOException Signals that an I/O exception has occurred.
      */
     public static void obtenerArchivoDeUCM(String documentId) throws IdcClientException,
       	IOException {

   	    log.debug("Obtener el Archivo");

   	    byte[] bytes = null;
   	    bytes =	obtenerArchivo(documentId);

       	if (bytes !=  null && bytes.length > 0) {
       		BufferedOutputStream bos = null;
       		try {
       			bos = new BufferedOutputStream(new FileOutputStream(tmpDir + "/" + documentId + ".xml"));
		       	log.debug("Archivo Generado");
		       	bos.write(bytes);
		       	bos.flush();
		       	bos.close();
	       	} catch (IOException e) {
	       		log.error("Error "  + e.getMessage());
	       	}finally {
	       		if (bos != null){
	       			bos.close();
	       		}
	       	}
       	}
 }

    /**
     * Obteniente un archivo desde el directorio resources.
     *
     * @param nombreArchivo el nombre del archivo a obtener
     * @return retorna un File con el archivo solicitado
     */
    //obtiene un archivo desde la carpeta recursos en el classpath
     public static File obtenerArchivoDesdeResources(String nombreArchivo) {

         ClassLoader classLoader = ClienteOwcc.class.getClassLoader();

         URL resource = classLoader.getResource(nombreArchivo);
         if (resource == null) {
        	 return new File("expediente.xsl");
         } else {
             return new File(resource.getFile());
         }

     }

     /**
      * Obtener los bytes de un archivo basado en el dID en content.
      *
      * @param id el dID del documento en webcentet content
      * @return byte[] el stream den base 64 del archivo.
      * @throws IOException Signals that an I/O exception has occurred.
      */
    public static byte[] obtenerBytesArchivo(String id) throws IOException {
        try {

            log.debug("Obtener Archivo con Id " + id);
            DataBinder binder = idClient.createBinder();
            binder.putLocal("IdcService", "GET_FILE");
            binder.putLocal("dID", id);
            ServiceResponse response = idClient.sendRequest(userContext, binder);
            InputStream is = null;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            response.toString();
            is = response.getResponseStream();

            int next = is.read();
            while (next > -1) {
                bos.write(next);
                next = is.read();
            }
            bos.flush();
            byte[] result = bos.toByteArray();
            response.close();
            return result;
        } catch (IdcClientException e) {
            log.debug("ERROR] Error En metodo (getBytesFile) para " + e.getMessage());
        }

        return null;
    }

     /**
      * Obtiene la extension archivo pasado por parametro.
      *
      * @param archivo the archivo
      * @return String la extension del archivo, si no tiene extension retorna ""
      */

    private static String obtenerExtensionArchivo(File archivo) {
        String nombreArchivo = archivo.getName();
        if(nombreArchivo.lastIndexOf(".") != -1 && nombreArchivo.lastIndexOf(".") != 0)
        return nombreArchivo.substring(nombreArchivo.lastIndexOf(".")+1);
        else return "";
    }

     /**
     * Obtiene el Global Unique Identifier del path pasado por parametro.
     *
     * @param pathFolder el path del forlder a obtener el guid en Oracle WebCenter Conten
     * @return String el identificador del path. si no existe retorna "NOHAY"
     * @throws IdcClientException the idc client exception
     */
    @SuppressWarnings("rawtypes")
	public static String obtenerFolderGUID(String pathFolder ) throws IdcClientException  {

        IdcClientManager manager = new IdcClientManager ();
        // Create a new IdcClient Connection using idc protocol (i.e. socket connection to Content Server)
        IdcClient idcClient = manager.createClient (ClienteOwcc.urlContent);
        // Create new context using the 'sysadmin' user
        IdcContext userContext = new IdcContext (ClienteOwcc.usuarioContent,ClienteOwcc.passwordContent);
        System.out.println("Invocando Servicio FLD_INFO (path=\"" + pathFolder + "\")");
        // Asignar variables de invocacion
        DataBinder requestData = idcClient.createBinder();
        requestData.putLocal("IdcService", "FLD_INFO");
        requestData.putLocal("path", pathFolder);
        // Ejecutar la invocacion del servicio
        ServiceResponse response;
        String pfolderGUID="";
        try {
            response = idcClient.sendRequest(userContext, requestData);
            DataResultSet result;
            result = response.getResponseAsBinder().getResultSet("FolderInfo");
            pfolderGUID = result.getRows().get(0).get("fFolderGUID");
        } catch (IdcClientException ex) {
            log.debug("Error" + ex);
            pfolderGUID = "NOHAY";
            return pfolderGUID;
        }

        // Obtener el  GUID del folder
        System.out.println("Capturar informacion de folder");

        if (pfolderGUID.isEmpty()) {
            // Si el folder no existe
            System.out.println("Folder " + pathFolder + " no encontrado");

        } else {
            System.out.println("Folder " + pathFolder + " tiene GUID: " + pfolderGUID);
        }
        // Retorna el primer folder.
        return pfolderGUID;
    }

    /**
      * Genera el indice electronico en formato pdf de un folio y retorna los bytes del pdf.
      *
      * @param idDoc el Identificador dID del folio de OWCC a generar el indice electronico
      * @param titulo el titulo a dar al indice electronico, este sale impreso en el pdf generado.
      * @param nombre el nombre a dar al indice electronico.
      * @return byte[] retorna el stream base 64 del documento generado como indice electronico.
      */
    public static byte[] obtenerIndiceElectronico(String idDoc , String titulo , String nombre) {

        String vTitulo = titulo +" - " + nombre;
        String vExpediente = "expediente.xsl";
        byte[] indRetornar = null;

    	try {

        	 //Descargar el archivo xml correspondiente al folio
    		obtenerArchivoDeUCM(idDoc);

            File xmlfile = new File(tmpDir + "/" + idDoc + ".xml");

            ClassLoader classloader = new ClienteOwcc().getClass().getClassLoader();
            URL resource2 = classloader.getResource("expediente.xsl");

            System.out.println(" Archivo conseguido " + resource2.getPath());

            URL resource = ClienteOwcc.class.getResource(vExpediente);
            File xsltfile;

            if ( resource == null ){
           	 System.out.println("Expediente es nulo");
           	 xsltfile = new File("expediente.xsl");
            } else {
           	 xsltfile = new File(resource.getFile());
           	 System.out.println("Expediente no es nulo");
            }

            log.debug("Path Expediente temporalmente : " + xsltfile.getAbsolutePath());
            File pdfDir = new File(tmpDir);
            pdfDir.mkdirs();
            File pdfFile = new File(pdfDir, "IndiceElectronico"+idDoc+".pdf");
            log.debug("Archivo generado temporalmente : " + pdfFile.getAbsolutePath());
            log.debug("Longitud Inicial de Archivo generado temporalmente : " + pdfFile.length());

            reemplazarCadena(xmlfile.toString(), "", " xmlns=\"http://stellent.com/cpd\"");
            // configurar fopFactory
            final FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());

            FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
            // configurar foUserAgent

            // Configurar la salda

            OutputStream out = null;

            try {

	            	out = new FileOutputStream(pdfFile);
	                out = new java.io.BufferedOutputStream(out);
                      // Constructor fop
                    Fop fop;

                    fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

                    // configurar XSLT
                    TransformerFactory factory = TransformerFactory.newInstance();
                    factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

                    Transformer transformer = factory.newTransformer(new StreamSource(xsltfile));
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                     // asignar el valor del titulo en una variable de la hoja de estilo
                    vTitulo = titulo +" - " + nombre;
                    transformer.setParameter("ExpedienteNombre", "Indice Electronico " + vTitulo );

                    // configurar la transformacion XSLT transformation
                    Source src = new StreamSource(xmlfile);
                    Result res = new SAXResult(fop.getDefaultHandler());

                    // Iniciar la tranformacion XSLT y procesamiento FOP
                    transformer.transform(src, res);

                    log.debug("Longitud Final de Archivo generado temporalmente : " + pdfFile.length());

            } catch (FOPException | TransformerException e) {
                log.debug("Error aplicando tranformacion" + e);
            } finally {
            	if (out != null ) {
            		out.close();
            	}
            }

            //retorna el indice
            indRetornar = cargarArchivoComoBytesArray(pdfFile.getPath());

        }catch(IOException exp){
       	 log.error("Error "  + exp.getMessage());
        } catch (IdcClientException e1) {
       	 log.error("Error "  + e1.getMessage());
		}

    	return indRetornar;

    }


     /**
     * Este método calcula el hash md5 de un archivo.
     *
     * @param nombreArchivo ruta del archivo a evaluar el md5
     * @return Una cadena con el md5 del archivo
     * @throws Exception Genera una excepcion
     */
    public static String obtenerMD5Checksum(String nombreArchivo) throws Exception {
        byte[] b = crearChecksum(nombreArchivo);
        String resultado = "";

        for (int i=0; i < b.length; i++) {
            resultado += Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
        }
        return resultado;
    }


     /**
      * Este metodo abre un turno que ya ha sido cerrado
      *
      * @param vDidExpediente el dID del folio o expediente a reabrir
      * @return String si el expediente fue reabierto retorna dID-dDocName
      */
     @SuppressWarnings("rawtypes")
	public static String reAbrirTurno ( String vDidExpediente ){

         String respuesta = "";

         log.debug("Procediendo a Reabrir Expediente con Operacion UnLock Folio, el DID Del Expediente es" + vDidExpediente);

         IdcClientManager manager = new IdcClientManager ();
         try{
                 // Crea una conexion IdcClient usando protocolo  idc (i.e. socket connection to Content Server)
                 IdcClient idcClient = manager.createClient (ClienteOwcc.urlContent);

                 // Crea un nuevo contexto usando el usuario 'sysadmin'
                 IdcContext userContext = new IdcContext (ClienteOwcc.usuarioContent,ClienteOwcc.passwordContent);

                 // Databinder para solicitud de checkin
                 DataBinder dataBinder = idcClient.createBinder();
                 HdaBinderSerializer serializer = new HdaBinderSerializer ("UTF-8", idcClient.getDataFactory ());
                 //indicar la operacion a ejecutar
                 dataBinder.putLocal("IdcService", "UNLOCK_FOLIO");

                 dataBinder.putLocal("dID", vDidExpediente);
                 dataBinder.putLocal("RevisionSelectionMethod", "Specific");


                 // Escribre el data binder de la solicitud
                 serializer.serializeBinder (System.out, dataBinder);
                 // Envia la peticion al Content Server
                 ServiceResponse response = idcClient.sendRequest(userContext,dataBinder);
                 // Obtiene el data binder con la respuesta desde el Content Server
                 DataBinder responseData = response.getResponseAsBinder();
                 // Escribe los datos de respuesta en el stdout
                 serializer.serializeBinder (System.out, responseData);

                 respuesta = responseData.getLocal("dID") + "-" + responseData.getLocal("dDocName");
                 log.debug("Expediente Desbloqueado y con DID " + respuesta );
               } catch (IdcClientException ice){
                 respuesta = ice.getLocalizedMessage();
               } catch (IOException ioe){
                 respuesta = ioe.getLocalizedMessage();
         }

         return respuesta;

     }

     /**
      * Este método carga un contenido de bytes en un archivo temporal en un directorio temporal en el sistema operativo.
      * El directorio temporal, esta asignado en una variable llamada tmpDir.
      *
      * @param nombreArchivo nombre de archivo temporal ( solo el nombre )
      * @param contenidoBytes contenido de bytes a escribir en el archivo temporal
     * @throws IOException problemas en escritura en disco o no encontro el archivo
      */
     public static void recibeArchivo(String nombreArchivo, byte[] contenidoBytes) throws IOException {

         String archivoPath = tmpDir + "/" + nombreArchivo;
         FileOutputStream fos = null;
         BufferedOutputStream outputStream = null;

         try {
             fos = new FileOutputStream(archivoPath);
	             try {
					outputStream = new BufferedOutputStream(fos);
					 outputStream.write(contenidoBytes);
					 outputStream.close();
					 fos.close();
				} catch (Exception e) {
					System.err.println("Error " + e);
				}finally {
					if ( outputStream != null) {
		                  outputStream.close();
						}
					if ( fos != null) {
	                  fos.close();
					}
				}
             System.out.println("Archivo Recibido: " + archivoPath);

         } catch (IOException ex) {
             System.err.println(ex);
         }finally {
        	 if ( fos != null) {
        	    fos.close();
        	 }
         }
     }

     /**
      * Reemplaza un texto por otro en el archivo generado de un folio
      *
      * @param archivo el archivo a modificar
      * @param reemplazarCon la cadena con que se reemplazara
      * @param reemplazar la cadena a reemplazar
     * @throws IOException problemas en escritura en disco o no encontro archivo
      */
     public static void reemplazarCadena(String archivo , String reemplazarCon, String reemplazar ) throws IOException {

    	BufferedReader file = null;
        StringBuffer inputBuffer = null;
        String line = "";
	    String[] lineas = new String[17];
	    String sFolio = "";

	    int vControl = -1;
	    int numeroFolios = 0;
	    int indice1 = 0;
	    int indice2 = 0;

    	 try {
    		 file = new BufferedReader(new FileReader(archivo));
    	     inputBuffer = new StringBuffer();

   	        while ((line = file.readLine()) != null) {


                if (line.contains("</slot>")){
                    vControl = 0;
                }

                if (line.contains("slot nodeId")){
                    vControl = 1;
                }

                if (vControl == 1 && line.contains("/properties")){
                    for ( int i = 0 ; i < lineas.length ; i++){
                            inputBuffer.append(lineas[i]);
                            inputBuffer.append('\n');
                    }
                    vControl = 0;
                }

                if ( vControl == 1 && line.contains("property key=") ){
                        if (line.contains("xcsd:dDocTitle")){
                            lineas[1] = line;
                        }
                        else if (line.contains("xcst:lastmodifieddate")){
                            lineas[4] = line;
                        }
                        else if (line.contains("xcsd:xNRO_FOLIOS")){

                            indice1 = line.indexOf("value=");
                            indice2 = line.indexOf("\"/");
                            sFolio = line.substring(indice1+7, indice2);
                            numeroFolios = numeroFolios + Integer.parseInt(sFolio);
                            lineas[8] = line;
                            lineas[9] = "      <property key=\"xcsd:xTOTAL_FOLIOS\" value=\""+ numeroFolios + "\"/>";
                        }
                        else if (line.contains("xcsd:docURL_encoded")){
                            lineas[14] = line;
                        }
                        else if (line.contains("xcsd:xIdcProfile")){
                            lineas[12] = line;
                        }
                        else if (line.contains("xcst:createdate")){
                            lineas[3] = line;
                        }
                        else if (line.contains("xcsd:dDocAuthor")){
                            lineas[13] = line;
                        }
                        else if (line.contains("xcsd:dDocType")){
                            lineas[2] = line;
                        }
                        else if (line.contains("xcsd:docURL")){
                            lineas[15] = line;
                        }
                        else if (line.contains("xcsd:xTAMANO_ARCHIVO")){
                            lineas[11] = line;
                        }
                        else if (line.contains("xcsd:xVALOR_HUELLA")){
                            lineas[5] = line;
                        }
                        else if (line.contains("xcsd:xFUNCION_RESUMEN")){
                            lineas[6] = line;
                        }
                        else if (line.contains("xcsd:dDocName")){
                            lineas[7] = line;
                        }
                        else if (line.contains("xcsd:dFormat")){
                            lineas[10] = line;
                        }
                        else if (line.contains("xcsd:dID")){
                            lineas[0] = line;
                        }
                        else if (line.contains("xcsd:dOriginalName")){
                            lineas[16] = line;
                        }
                }
                else {
                    inputBuffer.append(line);
                    inputBuffer.append('\n');
                }
   	        }
   	        file.close();
   	        String inputStr = inputBuffer.toString();

   	        inputStr = inputStr.replace(reemplazar, reemplazarCon);

   	        // Escribir sobre el mismo archivo
   	        FileOutputStream fileOut = null;
   	        try {
				fileOut = new FileOutputStream(archivo);
				fileOut.write(inputStr.getBytes());
				fileOut.close();
			} catch (Exception a) {
				log.error("error", a);
			}finally {
				if (fileOut != null) {
					fileOut.close();
				}
			}
   	    } catch (Exception e) {
   	        log.debug("Problema Leyendo el Archivo.");
   	    }finally {
   	    	if ( file != null) {
   	    	  file.close();
   	    	}
   	    }
   	}



     /**
      * Relacionar un documento con un folder existente.
      *
      * @param folderGUID el identificador global del folder
      * @param docName el dDocName del archivo a relacional
      * @return String con la salida dDocName - dID
      * @throws IdcClientException excepcion generada en conectividad al Oracle Webcenter Content
      * @throws IOException Signals that an I/O exception has occurred.
      */
     public static String relacionarDocumentoFolder(String folderGUID, String docName)
             throws IdcClientException, IOException {


         String respuesta = "";

         log.debug("Invocando servicio FLD_CREATE_FILE (folderGUID=\"" + folderGUID + "\")");
         // Set service request parameters
         DataBinder requestData = idClient.createBinder();
         HdaBinderSerializer serializer = new HdaBinderSerializer ("UTF-8", idClient.getDataFactory ());
         requestData.putLocal("IdcService", "FLD_CREATE_FILE");
         requestData.putLocal("fParentGUID", folderGUID);
         requestData.putLocal("fFileType", "soft");
         requestData.putLocal("dDocName", docName);
         // Return the first folder, there should be only one.
         ServiceResponse response = idClient.sendRequest(userContext, requestData);
         // Envia la peticion al Content Server
         // Obtiene el data binder con la respuesta desde el Content Server
         DataBinder responseData = response.getResponseAsBinder();
         // Escribe los datos de respuesta en el stdout
         serializer.serializeBinder (System.out, responseData);

         log.debug("Retornado dDocName " + responseData.getLocal("dDocName" ));
         log.debug("Retornado dId " + responseData.getLocal("dID") );

         respuesta = responseData.getLocal("dDocName") + "-" + responseData.getLocal("dID");

         return respuesta;

     }


     /**
      * Este proceso depura la entrada e invoca el procedimiento relacionarDocumentoFolder,
      * para relacionar un documento con un folder existente
      *
      * @param parametros el arreglo llave-valor donde vienen los datos de xNIR y xTURNO a procesar
      * @param dID el identicador dID documento a incluir
      * @param dDocName el dDocName del documento a incluir
      * @param sistema el sistema que genera la solicitud
      * @return String respuesta en formato dDocName - dID
      * @throws IdcClientException Excepcion en conexion a Oracle WebCenter Content
      * @throws IOException Signals that an I/O exception has occurred.
      */
     public static String relacionDocumento ( Parametros parametros , String dID , String dDocName , String sistema ) throws IdcClientException, IOException{

         //realizar parsing de parametros

         String orip = "";
         String nir = "";
         String turno = "";
         String codOrip = "";
         String respuesta = "";

         Parametros listaMetadatos = new Parametros();

         listaMetadatos = parametros;

         log.debug("Parametros a Procesar 1 " + parametros.getParametro().size());
         log.debug("Parametros a Procesar 2 " + parametros.getParametro().size());

         for (int i = 0; i < listaMetadatos.getParametro().size(); i++) {
                       log.debug("CAPTURANDO METADATO :" + listaMetadatos.getParametro().get(i).getNombre());
                       log.debug("CAPTURANDO VALOR : " + listaMetadatos.getParametro().get(i).getValor());

                       if ( listaMetadatos.getParametro().get(i).getNombre().equals("xORIP") ){
                           orip = listaMetadatos.getParametro().get(i).getValor();
                       }
                       if ( listaMetadatos.getParametro().get(i).getNombre().equals("xCODIGO_ORIP") ){
                           codOrip = listaMetadatos.getParametro().get(i).getValor();
                       }
                       if ( listaMetadatos.getParametro().get(i).getNombre().equals("xNIR") ){
                           nir = listaMetadatos.getParametro().get(i).getValor();
                       }
                       if ( listaMetadatos.getParametro().get(i).getNombre().equals("xTURNO") ){
                           turno = listaMetadatos.getParametro().get(i).getValor();
                       }
                 }

         // obtener los identificadores de los folders

         String nirBasePath = carpetaBaseBachue + codOrip +"-"+ orip.toUpperCase() + "/";
         String nirPath = carpetaBaseBachue + codOrip +"-"+ orip.toUpperCase() + "/" + nir + "/";

         String turnoBasePath = carpetaBaseBachue + codOrip +"-"+ orip.toUpperCase() + "/"  + nir + "/";
         String turnoPath = carpetaBaseBachue + codOrip +"-"+ orip.toUpperCase() + "/" + nir + "/" + turno + "/";

         String guidFolder = "";
         // Si en la trama viene especifcado el turno

         // verificar que el nir no sea vacio

         // Si hay turno y nir
         if ( !nir.isEmpty() && !turno.isEmpty()){
                 try {
                     String folderBaseTurnoID = obtenerFolderGUID(turnoBasePath);
                     log.debug("El UID de " + turnoBasePath+ " es " + folderBaseTurnoID);
                     String folderTurnoID = obtenerFolderGUID ( turnoPath);
                     log.debug("El UID de " + turnoPath+ " es " + folderTurnoID);
                     guidFolder = folderTurnoID;
                 } catch (IdcClientException ex) {
                     log.debug("Error" + ex);
                 }
         }
         else if ( !nir.isEmpty() && turno.isEmpty() ){
             try {
                     String folderBaseNirID = obtenerFolderGUID(nirBasePath);
                     log.debug("El UID de " + nirBasePath+ " es " + folderBaseNirID);
                     String folderNirID = obtenerFolderGUID ( nirPath);
                     log.debug("El UID de " + nirPath+ " es " + folderNirID);
                     guidFolder = folderNirID;
                 } catch (IdcClientException ex) {
                     log.debug("Error" + ex);
                 }
         }
         else{

         }

         //realizar Checkin de Documento
         log.debug("Folder a Crear Enlace " +guidFolder);
         log.debug("Documento a Crear Enlace " +dDocName  );

         respuesta = relacionarDocumentoFolder(guidFolder,dDocName);

       return respuesta;
     }


     /**
     * Metodo que establece conexion al servicio de Oracle Webcenter Content
     *
     * @throws IdcClientException excepcion de conexion a Oracle WebCenter Content
     */
    public static void RIDConnection() throws IdcClientException {
        Integer thead = 30;
        Integer timeOut = 130000;
        loadContentType();
        IdcClientManager manager = new IdcClientManager();
        //idClient = (IdcHttpClient) manager.createClient(BeanBuscar.urlContent);
        idClient =  manager.createClient(ClienteOwcc.urlContent);
        userContext = new IdcContext(ClienteOwcc.usuarioContent,ClienteOwcc.passwordContent);
        idClient.getConfig().setConnectionSize(thead);
        idClient.getConfig().setSocketTimeout(timeOut);
        log.debug("*************************CONFIGURANDO RIDC CONNECTION****************** Url: " + ClienteOwcc.urlContent + " : " + ClienteOwcc.usuarioContent + " : " + ClienteOwcc.passwordContent);
    }


    /**
     * Metodo que realiza validaciones sobre los atributos obligatorios
     *
     * @throws IdcClientException excepcion de conexion a Oracle WebCenter Content
     */
    public static String validarEnvioObligatorios( List<TipoParametro> listaMetadatos , String sistemaOrigen ) {

    	String respuesta = "";
    	String proceso = "";
    	int validos = 0;

    	log.debug("VALIDANDO ATRIBUTOS OBLIGATORIOS EN EL SISTEMA :" + sistemaOrigen );
    	
    	//obtiene el proceso que se esta invocando desde el servicio
    	for (int i = 0; i < listaMetadatos.size(); i++) {
    		
    		if ( listaMetadatos.get(i).getNombre().equalsIgnoreCase("xPROCESO")) {
    			if (!( listaMetadatos.get(i).getValor().equalsIgnoreCase("")  || listaMetadatos.get(i).getValor().isEmpty())) {
		       		 proceso = listaMetadatos.get(i).getValor();
		       	  }
    		}
    		
    	}

    	for (int i = 0; i < listaMetadatos.size(); i++) {


       	  if (sistemaOrigen.equalsIgnoreCase("CORE")) {
       		  
       		  if ( proceso.equalsIgnoreCase("CONCILIACION")){
       			  
       			if ( listaMetadatos.get(i).getNombre().equalsIgnoreCase("xPROCESO")) {
  		      	  if ( listaMetadatos.get(i).getValor().equalsIgnoreCase("")  || listaMetadatos.get(i).getValor().isEmpty() ) {
  		       		 respuesta.concat("El atributo " );
  		       		 respuesta.concat( listaMetadatos.get(i).getNombre());
  		       		 respuesta.concat("	 es Obligatorio ");
  		       	  }
  		      	  else {
  		      		  validos ++;
  		      	  }
       			}  
       		  }
       		  else {
		   		if ( listaMetadatos.get(i).getNombre().equalsIgnoreCase("dDocType")) {
		      	  if ( listaMetadatos.get(i).getValor().equalsIgnoreCase("")  || listaMetadatos.get(i).getValor().isEmpty() ) {
		      		 respuesta.concat("El atributo " );
		      		 respuesta.concat( listaMetadatos.get(i).getNombre());
		      		 respuesta.concat("	 es Obligatorio ");
		      		 log.debug("DDOCTYPE :" + listaMetadatos.get(i).getNombre());
		      	  }
		      	  else {
		      		  log.debug("DDOCTYPE : " + listaMetadatos.get(i).getValor());
		      		  validos ++;
		      	  }
		        } else if ( listaMetadatos.get(i).getNombre().equalsIgnoreCase("xORIP")) {
		      	  if ( listaMetadatos.get(i).getValor().equalsIgnoreCase("")  || listaMetadatos.get(i).getValor().isEmpty() ) {
		       		 respuesta.concat("El atributo " );
		       		 respuesta.concat( listaMetadatos.get(i).getNombre());
		       		 respuesta.concat("	 es Obligatorio y no debe estar vacio");
		       		log.debug("XORIP : " + listaMetadatos.get(i).getValor());
		       	  }
		      	  else {
		      		  validos ++;
		      		  log.debug("XORIP : " + listaMetadatos.get(i).getValor());
		      	  }

		        } else if ( listaMetadatos.get(i).getNombre().equalsIgnoreCase("xNIR")) {
		      	  if ( listaMetadatos.get(i).getValor().equalsIgnoreCase("")  || listaMetadatos.get(i).getValor().isEmpty() ) {
		       		 respuesta.concat("El atributo " );
		       		 respuesta.concat( listaMetadatos.get(i).getNombre());
		       		 respuesta.concat("	 es Obligatorio ");

		       	  }
		      	  else {
		      		  validos ++;
		      	  }
		        } else if ( listaMetadatos.get(i).getNombre().equalsIgnoreCase("xCODIGO_ORIP")){
		      	  if ( listaMetadatos.get(i).getValor().equalsIgnoreCase("")  || listaMetadatos.get(i).getValor().isEmpty() ) {
		       		 respuesta.concat("El atributo " );
		       		 respuesta.concat( listaMetadatos.get(i).getNombre());
		       		 respuesta.concat("	 es Obligatorio ");
		       	  }
		      	  else {
		      		  validos ++;
		      	  }
		        } else if ( listaMetadatos.get(i).getNombre().equalsIgnoreCase("xPROCESO")) {
		      	  if ( listaMetadatos.get(i).getValor().equalsIgnoreCase("")  || listaMetadatos.get(i).getValor().isEmpty() ) {
		       		 respuesta.concat("El atributo " );
		       		 respuesta.concat( listaMetadatos.get(i).getNombre());
		       		 respuesta.concat("	 es Obligatorio ");
		       	  }
		      	  else {
		      		  validos ++;
		      	  }
		        }
       		  }	
       	  }
       	  else if (sistemaOrigen.contentEquals("SEDE_ELECTRONICA")){ // Si no es core.
       		if ( listaMetadatos.get(i).getNombre().equalsIgnoreCase("dDocType")) {
          	  if ( listaMetadatos.get(i).getValor().equalsIgnoreCase("")  || listaMetadatos.get(i).getValor().isEmpty() ) {
          		 respuesta.concat("El atributo " );
          		 respuesta.concat( listaMetadatos.get(i).getNombre());
          		 respuesta.concat("	 es Obligatorio ");
          		 log.debug("DDOCTYPE :" + listaMetadatos.get(i).getNombre());
          	  }
          	  else {
          		  log.debug("DDOCTYPE : " + listaMetadatos.get(i).getValor());
          		  validos ++;
          	  }
            } else if ( listaMetadatos.get(i).getNombre().equalsIgnoreCase("xNIR")) {
          	  if ( listaMetadatos.get(i).getValor().equalsIgnoreCase("")  || listaMetadatos.get(i).getValor().isEmpty() ) {
           		 respuesta.concat("El atributo " );
           		 respuesta.concat( listaMetadatos.get(i).getNombre());
           		 respuesta.concat("	 es Obligatorio ");

           	  }
          	  else {
          		  validos ++;
          	  }
            }
       	  }
       	  else if (sistemaOrigen.contentEquals("CORRESPONDENCIA")) {
       		return respuesta = "VALIDOS";
       	  }
       	/*  ESTO PRUEBAS CORRESPONDENCIA
       	else if (sistemaOrigen.contentEquals("CORRESPONDENCIA")){ // Si es correspondencia.
       		if ( listaMetadatos.get(i).getNombre().equalsIgnoreCase("dDocType")) {
          	  if ( listaMetadatos.get(i).getValor().equalsIgnoreCase("")  || listaMetadatos.get(i).getValor().isEmpty() ) {
          		 respuesta.concat("El atributo " );
          		 respuesta.concat( listaMetadatos.get(i).getNombre());
          		 respuesta.concat("	 es Obligatorio ");
          		 log.debug("DDOCTYPE :" + listaMetadatos.get(i).getNombre());
          	  }
          	  else {
          		  log.debug("DDOCTYPE : " + listaMetadatos.get(i).getValor());
          		  validos ++;
          	  }
            } else if ( listaMetadatos.get(i).getNombre().equalsIgnoreCase("xDESTINATARIO")) {
          	  if ( listaMetadatos.get(i).getValor().equalsIgnoreCase("")  || listaMetadatos.get(i).getValor().isEmpty() ) {
           		 respuesta.concat("El atributo " );
           		 respuesta.concat( listaMetadatos.get(i).getNombre());
           		 respuesta.concat("	 es Obligatorio ");

           	  }
          	  else {
          		  validos ++;
          	  }
            }else if ( listaMetadatos.get(i).getNombre().equalsIgnoreCase("xINTERNO_EXTERNO")) {
            	  if ( listaMetadatos.get(i).getValor().equalsIgnoreCase("")  || listaMetadatos.get(i).getValor().isEmpty() ) {
                		 respuesta.concat("El atributo " );
                		 respuesta.concat( listaMetadatos.get(i).getNombre());
                		 respuesta.concat("	 es Obligatorio ");

                	  }
               	  else {
               		  validos ++;
               	  }
             }else if ( listaMetadatos.get(i).getNombre().equalsIgnoreCase("xNRO_FOLIOS")) {
	           	  if ( listaMetadatos.get(i).getValor().equalsIgnoreCase("")  || listaMetadatos.get(i).getValor().isEmpty() ) {
	         		 respuesta.concat("El atributo " );
	         		 respuesta.concat( listaMetadatos.get(i).getNombre());
	         		 respuesta.concat("	 es Obligatorio ");

	         	  }
	        	  else {
	        		  validos ++;
	        	  }
             }else if ( listaMetadatos.get(i).getNombre().equalsIgnoreCase("xRADICADO")) {
	           	  if ( listaMetadatos.get(i).getValor().equalsIgnoreCase("")  || listaMetadatos.get(i).getValor().isEmpty() ) {
	         		 respuesta.concat("El atributo " );
	         		 respuesta.concat( listaMetadatos.get(i).getNombre());
	         		 respuesta.concat("	 es Obligatorio ");

	         	  }
	        	  else {
	        		  validos ++;
	        	  }
             }else if ( listaMetadatos.get(i).getNombre().equalsIgnoreCase("xREMITENTE")) {
	           	  if ( listaMetadatos.get(i).getValor().equalsIgnoreCase("")  || listaMetadatos.get(i).getValor().isEmpty() ) {
	         		 respuesta.concat("El atributo " );
	         		 respuesta.concat( listaMetadatos.get(i).getNombre());
	         		 respuesta.concat("	 es Obligatorio ");

	         	  }
	        	  else {
	        		  validos ++;
	        	  }
            }
             else if ( listaMetadatos.get(i).getNombre().equalsIgnoreCase("xASUNTO")) {
	           	  if ( listaMetadatos.get(i).getValor().equalsIgnoreCase("")  || listaMetadatos.get(i).getValor().isEmpty() ) {
	         		 respuesta.concat("El atributo " );
	         		 respuesta.concat( listaMetadatos.get(i).getNombre());
	         		 respuesta.concat("	 es Obligatorio ");
	         	  }
	        	  else {
	        		  validos ++;
	        	  }
           }else if ( listaMetadatos.get(i).getNombre().equalsIgnoreCase("xFECHA_RECEPCION")) {
	           	  if ( listaMetadatos.get(i).getValor().equalsIgnoreCase("")  || listaMetadatos.get(i).getValor().isEmpty() ) {
	         		 respuesta.concat("El atributo " );
	         		 respuesta.concat( listaMetadatos.get(i).getNombre());
	         		 respuesta.concat("	 es Obligatorio ");
	         	  }
	        	  else {
	        		  validos ++;
	        	  }
           }

       	 } */


         log.debug("Contador Validos :" + validos );
    	}
    	log.debug("Contador Validos 2:" + validos );
    	if (sistemaOrigen.equalsIgnoreCase("CORE")) {
    		
    		if (proceso.equalsIgnoreCase("CONCILIACION")) {
    			
    			if ( validos == 1) {
	        	    return respuesta = "VALIDOS";
	        	}
	        	else {
	        		if ( respuesta.equalsIgnoreCase("")) {
	        		     respuesta = "FALTA DILIGENCIAR ATRIBUTOS OBLIGATORIOS";
	        		}
	        	    return respuesta;
	        	}
    		}
    		else {
	    		if ( validos == 5) {
	        	    return respuesta = "VALIDOS";
	        	}
	        	else {
	        		if ( respuesta.equalsIgnoreCase("")) {
	        		     respuesta = "FALTA DILIGENCIAR ATRIBUTOS OBLIGATORIOS";
	        		}
	        	    return respuesta;
	        	}
    		}	
    	} else if (sistemaOrigen.equalsIgnoreCase("SEDE_ELECTRONICA")) {
    		if ( validos == 2) {
        	    return respuesta = "VALIDOS";
        	}
        	else {
        		if ( respuesta.equalsIgnoreCase("")) {
        		     respuesta = "FALTA DILIGENCIAR ATRIBUTOS OBLIGATORIOS";
        		}
        	    return respuesta;
        	}

    	}
    	/* else if (sistemaOrigen.equalsIgnoreCase("CORRESPONDENCIA")) {
    		if ( validos == 8) {
        	    return respuesta = "VALIDOS";
        	}
        	else {
        		if ( respuesta.equalsIgnoreCase("")) {
        		     respuesta = "FALTA DILIGENCIAR ATRIBUTOS OBLIGATORIOS";
        		}
        	    return respuesta;
        	}

    	} */

        return respuesta;

}


public static String validarEnvioOpcionales( List<TipoParametro> listaMetadatos ) {

    	String respuesta = "";

    	for (int i = 0; i < listaMetadatos.size(); i++) {
       	  log.debug("CAPTURANDO METADATO :" + listaMetadatos.get(i).getNombre());
       	  log.debug("CAPTURANDO VALOR : " + listaMetadatos.get(i).getValor());
          if ( listaMetadatos.get(i).getValor().equalsIgnoreCase("")  || listaMetadatos.get(i).getValor().isEmpty() ) {
        		 respuesta.concat(" El atributo " );
        		 respuesta.concat( listaMetadatos.get(i).getNombre());
        		 respuesta.concat("	esta Vacio ");
          }
    	}

        return respuesta;

    }


	/**
	 * Obtiene el valor actual de la variable url content.
	 *
	 * @return the url content
	 */
	public static String getUrlContent() {
		return urlContent;
	}

	/**
	 * Asigna valor a la variable url content.
	 *
	 * @param urlContent the new url content
	 */
	public static void setUrlContent(String urlContent) {
		ClienteOwcc.urlContent = urlContent;
	}

	/**
	 * Obtiene el valor actual de la variable url visor.
	 *
	 * @return the url visor
	 */
	public static String getUrlVisor() {
		return urlVisor;
	}

	/**
	 * Asigna valor a la variable url visor.
	 *
	 * @param urlVisor asigna la url del visor de Oracle Webcenter Content
	 */
	public static void setUrlVisor(String urlVisor) {
		ClienteOwcc.urlVisor = urlVisor;
	}

	/**
	 * Obtiene el valor actual de la variable usuario content.
	 *
	 * @return the usuario content
	 */
	public static String getUsuarioContent() {
		return usuarioContent;
	}

	/**
	 * Asigna valor a la variable usuario content.
	 *
	 * @param usuarioContent usuario de conexion a content
	 */
	public static void setUsuarioContent(String usuarioContent) {
		ClienteOwcc.usuarioContent = usuarioContent;
	}

	/**
	 * Obtiene el valor actual de la variable password content.
	 *
	 * @return the password content
	 */
	public static String getPasswordContent() {
		return passwordContent;
	}

	/**
	 * Asigna valor a la variable password content.
	 *
	 * @param passwordContent contraseña de conexion a content
	 */
	public static void setPasswordContent(String passwordContent) {
		ClienteOwcc.passwordContent = passwordContent;
	}


	/**
	 * Obtiene el valor actual de la variable user context.
	 *
	 * @return the user context
	 */
	public static IdcContext getUserContext() {
		return userContext;
	}

	/**
	 * Asigna valor a la variable user context.
	 *
	 * @param userContext el contexto del usuario.
	 */
	public static void setUserContext(IdcContext userContext) {
		ClienteOwcc.userContext = userContext;
	}

	/**
	 * Obtiene el valor actual de la variable types.
	 *
	 * @return the types
	 */
	public static HashMap<String, String> getTypes() {
		return types;
	}

	/**
	 * Asigna valor a la variable types.
	 *
	 * @param types the types
	 */
	public static void setTypes(HashMap<String, String> types) {
		ClienteOwcc.types = types;
	}

	/**
	 * Obtiene el valor actual de la variable context.
	 *
	 * @return the context
	 */
	public static Context getContext() {
		return context;
	}

	/**
	 * Asigna valor a la variable context.
	 *
	 * @param context the new context
	 */
	public static void setContext(Context context) {
		ClienteOwcc.context = context;
	}

	/**
	 * Obtiene el valor actual de la variable conn.
	 *
	 * @return the conn
	 */
	public static java.sql.Connection getConn() {
		return conn;
	}

	/**
	 * Asigna valor a la variable conn.
	 *
	 * @param conn the new conn
	 */
	public static void setConn(java.sql.Connection conn) {
		ClienteOwcc.conn = conn;
	}

	/**
	 * Obtiene el valor actual de la variable rs.
	 *
	 * @return the rs
	 */
	public static ResultSet getRs() {
		return rs;
	}

	/**
	 * Asigna valor a la variable rs.
	 *
	 * @param rs the new rs
	 */
	public static void setRs(ResultSet rs) {
		ClienteOwcc.rs = rs;
	}

	/**
	 * Obtiene el valor actual de la variable tmp dir.
	 *
	 * @return the tmp dir
	 */
	public static String getTmpDir() {
		return tmpDir;
	}

	/**
	 * Asigna valor a la variable tmp dir.
	 *
	 * @param tmpDir the new tmp dir
	 */
	public static void setTmpDir(String tmpDir) {
		ClienteOwcc.tmpDir = tmpDir;
	}

	/**
	 * Obtiene el valor actual de la variable id folder hijo.
	 *
	 * @return the id folder hijo
	 */
	public static String getIdFolderHijo() {
		return idFolderHijo;
	}

	/**
	 * Asigna valor a la variable id folder hijo.
	 *
	 * @param idFolderHijo the new id folder hijo
	 */
	public static void setIdFolderHijo(String idFolderHijo) {
		ClienteOwcc.idFolderHijo = idFolderHijo;
	}

	/**
	 * Obtiene el valor actual de la variable id turno.
	 *
	 * @return the id turno
	 */
	public static String getIdTurno() {
		return idTurno;
	}

	/**
	 * Asigna valor a la variable id turno.
	 *
	 * @param idTurno the new id turno
	 */
	public static void setIdTurno(String idTurno) {
		ClienteOwcc.idTurno = idTurno;
	}

	/**
	 * Obtiene el valor actual de la variable id expediente.
	 *
	 * @return the id expediente
	 */
	public static String getIdExpediente() {
		return idExpediente;
	}

	/**
	 * Asigna valor a la variable id expediente.
	 *
	 * @param idExpediente the new id expediente
	 */
	public static void setIdExpediente(String idExpediente) {
		ClienteOwcc.idExpediente = idExpediente;
	}

	/**
	 * Obtiene el valor actual de la variable id nir.
	 *
	 * @return the id nir
	 */
	public static String getIdNir() {
		return idNir;
	}

	/**
	 * Asigna valor a la variable id nir.
	 *
	 * @param idNir the new id nir
	 */
	public static void setIdNir(String idNir) {
		ClienteOwcc.idNir = idNir;
	}

	/**
	 * Obtiene el valor actual de la variable id folder padre.
	 *
	 * @return the id folder padre
	 */
	public static String getIdFolderPadre() {
		return idFolderPadre;
	}

	/**
	 * Asigna valor a la variable id folder padre.
	 *
	 * @param idFolderPadre the new id folder padre
	 */
	public static void setIdFolderPadre(String idFolderPadre) {
		ClienteOwcc.idFolderPadre = idFolderPadre;
	}

	/**
	 * Obtiene el valor actual de la variable id proceso.
	 *
	 * @return the id proceso
	 */
	public static String getIdProceso() {
		return idProceso;
	}

	/**
	 * Asigna valor a la variable id proceso.
	 *
	 * @param idProceso the new id proceso
	 */
	public static void setIdProceso(String idProceso) {
		ClienteOwcc.idProceso = idProceso;
	}

	/**
	 * Obtiene el valor actual de la variable radicado.
	 *
	 * @return the radicado
	 */
	public static String getRadicado() {
		return radicado;
	}

	/**
	 * Asigna valor a la variable radicado.
	 *
	 * @param radicado the new radicado
	 */
	public static void setRadicado(String radicado) {
		ClienteOwcc.radicado = radicado;
	}

	/**
	 * Obtiene el valor actual de la variable trd.
	 *
	 * @return the trd
	 */
	public static String getTrd() {
		return trd;
	}

	/**
	 * Asigna valor a la variable trd.
	 *
	 * @param trd the new trd
	 */
	public static void setTrd(String trd) {
		ClienteOwcc.trd = trd;
	}

	/**
	 * Obtiene el valor actual de la variable trd prefijo.
	 *
	 * @return the trd prefijo
	 */
	public static String getTrdPrefijo() {
		return trdPrefijo;
	}

	/**
	 * Asigna valor a la variable trd prefijo.
	 *
	 * @param trdPrefijo the new trd prefijo
	 */
	public static void setTrdPrefijo(String trdPrefijo) {
		ClienteOwcc.trdPrefijo = trdPrefijo;
	}

	/**
	 * Obtiene el valor actual de la variable id categoria retencion.
	 *
	 * @return the id categoria retencion
	 */
	public static String getIdCategoriaRetencion() {
		return idCategoriaRetencion;
	}

	/**
	 * Asigna valor a la variable id categoria retencion.
	 *
	 * @param idCategoriaRetencion the new id categoria retencion
	 */
	public static void setIdCategoriaRetencion(String idCategoriaRetencion) {
		ClienteOwcc.idCategoriaRetencion = idCategoriaRetencion;
	}

	/**
	 * Obtiene el valor actual de la variable carpeta base bachue.
	 *
	 * @return the carpeta base bachue
	 */
	public static String getCarpetaBaseBachue() {
		return carpetaBaseBachue;
	}

	/**
	 * Asigna valor a la variable carpeta base bachue.
	 *
	 * @param carpetaBaseBachue the new carpeta base bachue
	 */
	public static void setCarpetaBaseBachue(String carpetaBaseBachue) {
		ClienteOwcc.carpetaBaseBachue = carpetaBaseBachue;
	}

	/**
	 * Obtiene el valor actual de la variable carpeta base correspondencia.
	 *
	 * @return the carpeta base correspondencia
	 */
	public static String getCarpetaBaseCorrespondencia() {
		return carpetaBaseCorrespondencia;
	}

	/**
	 * Asigna valor a la variable carpeta base correspondencia.
	 *
	 * @param carpetaBaseCorrespondencia the new carpeta base correspondencia
	 */
	public static void setCarpetaBaseCorrespondencia(String carpetaBaseCorrespondencia) {
		ClienteOwcc.carpetaBaseCorrespondencia = carpetaBaseCorrespondencia;
	}

	/**
	 * Obtiene el valor actual de la variable carpeta base temporal.
	 *
	 * @return the carpeta base temporal
	 */
	public static String getCarpetaBaseTemporal() {
		return carpetaBaseTemporal;
	}

	/**
	 * Asigna valor a la variable carpeta base temporal.
	 *
	 * @param carpetaBaseTemporal the new carpeta base temporal
	 */
	public static void setCarpetaBaseTemporal(String carpetaBaseTemporal) {
		ClienteOwcc.carpetaBaseTemporal = carpetaBaseTemporal;
	}

	/**
	 * Obtiene el valor actual de la variable carpeta base conciliacion.
	 *
	 * @return the carpeta base conciliacion
	 */
	public static String getCarpetaBaseConciliacion() {
		return carpetaBaseConciliacion;
	}

	/**
	 * Asigna valor a la variable carpeta base conciliacion.
	 *
	 * @param carpetaBaseConciliacion the new carpeta base conciliacion
	 */
	public static void setCarpetaBaseConciliacion(String carpetaBaseConciliacion) {
		ClienteOwcc.carpetaBaseConciliacion = carpetaBaseConciliacion;
	}

	/**
	 * Obtiene el valor actual de la variable carpeta base conciliacion expediente.
	 *
	 * @return the carpeta base conciliacion expediente
	 */
	public static String getCarpetaBaseConciliacionExpediente() {
		return carpetaBaseConciliacionExpediente;
	}

	/**
	 * Asigna valor a la variable carpeta base conciliacion expediente.
	 *
	 * @param carpetaBaseConciliacionExpediente the new carpeta base conciliacion expediente
	 */
	public static void setCarpetaBaseConciliacionExpediente(String carpetaBaseConciliacionExpediente) {
		ClienteOwcc.carpetaBaseConciliacionExpediente = carpetaBaseConciliacionExpediente;
	}

	/**
	 * Obtiene el valor actual de la variable carpeta base correspondencia expediente.
	 *
	 * @return the carpeta base correspondencia expediente
	 */
	public static String getCarpetaBaseCorrespondenciaExpediente() {
		return carpetaBaseCorrespondenciaExpediente;
	}

	/**
	 * Asigna valor a la variable carpeta base correspondencia expediente.
	 *
	 * @param carpetaBaseCorrespondenciaExpediente the new carpeta base correspondencia expediente
	 */
	public static void setCarpetaBaseCorrespondenciaExpediente(String carpetaBaseCorrespondenciaExpediente) {
		ClienteOwcc.carpetaBaseCorrespondenciaExpediente = carpetaBaseCorrespondenciaExpediente;
	}

	/**
	 * Obtiene el valor actual de la variable carpeta base bachue expediente.
	 *
	 * @return the carpeta base bachue expediente
	 */
	public static String getCarpetaBaseBachueExpediente() {
		return carpetaBaseBachueExpediente;
	}

	/**
	 * Asigna valor a la variable carpeta base bachue expediente.
	 *
	 * @param carpetaBaseBachueExpediente the new carpeta base bachue expediente
	 */
	public static void setCarpetaBaseBachueExpediente(String carpetaBaseBachueExpediente) {
		ClienteOwcc.carpetaBaseBachueExpediente = carpetaBaseBachueExpediente;
	}

	/**
	 * Obtiene el valor actual de la variable log.
	 *
	 * @return the log
	 */
	public static Logger getLog() {
		return log;
	}



}
