/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.supernotariado.bachue.snr.clienteowcc;

import java.math.BigInteger;
import org.apache.log4j.Logger;

import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.xml.ws.BindingType;

import https.www_supernotariado_gov_co.schemas.bachue.co.creadorturno.crearturno.v1.TipoEntradaCrearTurno;
import https.www_supernotariado_gov_co.schemas.bachue.co.creadorturno.crearturno.v1.TipoSalidaCrearTurno;

/**
 * Contiene los metodos que permiten hacer la creacion de un turno por parte del core Bachue
 * y otros sistemas de la solucion, en el servidor de Oracle WebCenter Contet
 *
 * @author DataTools
 */
@WebService(serviceName = "SUT_CO_CreadorTurno", portName = "SUT_CO_CreadorTurnoPort", endpointInterface = "https.www_supernotariado_gov_co.services.bachue.co.creadorturno.v1.SUTCOCreadorTurno", targetNamespace = "https://www.supernotariado.gov.co/services/bachue/co/creadorturno/v1", wsdlLocation = "WEB-INF/wsdl/BeanCrearTurno/BS_SUT_CO_CreadorTurno.wsdl")
@BindingType(value = "http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/")
@HandlerChain(file = "handler-chain.xml")
public class BeanCrearTurno {


        /** Gestion del Log. */
    private static final Logger log = Logger.getLogger(BeanCrearTurno.class);

    /**
     * Crear turno. Este metodo invoca la creacion de un turno en un nir especifico,
     * Ambos datos xNIR y xTURNO son pasado como parte del parametro entrada.
     * @param entrada entrada, recibe como parametros obligatorios el xNIR y xTURNO
     * @return TipoSalidaCrearTurno retorna 200 si fue exitoso, 409 si hubo falla tecnica, 500 si hubo error
     */
    @SuppressWarnings("unused")
	public TipoSalidaCrearTurno crearTurno(TipoEntradaCrearTurno entrada) {

        TipoSalidaCrearTurno respuesta = new TipoSalidaCrearTurno();

        String salidaContent = "";
        String vDocName = "";
        String vDid = "";
        String vExpediente = "0";


        log.debug("--------------------------------------------------");
        log.debug("Iniciando nueva operacion de Creacion Turno");
        log.debug("--------------------------------------------------");
        log.debug("Leyendo Propiedades");

        ClienteOwcc.cargarVariables();

        salidaContent = ClienteOwcc.creacionTurno(  entrada.getSistemaOrigen() , entrada.getParametros() );

        if ( salidaContent.contains("-")){
           String[] partes = salidaContent.split("-");
           respuesta.setCodigoMensaje(new BigInteger("200"));
           respuesta.setDescripcionMensaje("Satisfactorio");
        }
        else if ( salidaContent.contains("null") ){
            respuesta.setCodigoMensaje(new BigInteger("409"));
            respuesta.setDescripcionMensaje(salidaContent.toString());
        }
        else if ( salidaContent.contains("Ya Existe Turno") ){
            respuesta.setCodigoMensaje(new BigInteger("409"));
            respuesta.setDescripcionMensaje(salidaContent.toString());
        }
        else if ( salidaContent.contains("No existe NIR") ){
            respuesta.setCodigoMensaje(new BigInteger("409"));
            respuesta.setDescripcionMensaje(salidaContent.toString());
        }
        else {
            respuesta.setCodigoMensaje(new BigInteger("500"));
            respuesta.setDescripcionMensaje("Falla Tecnica");
        }
        return respuesta;
    }

}
