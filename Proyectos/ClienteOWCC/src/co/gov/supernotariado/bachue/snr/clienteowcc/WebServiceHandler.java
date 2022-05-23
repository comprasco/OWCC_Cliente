package co.gov.supernotariado.bachue.snr.clienteowcc;

import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.Set;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.log4j.Logger;

/**
 * Clase que intercepta el Wservice a traves de un handler chain.
 *
 * @author Matby Peralta. <br>
 *         <a>matby.peralta@datatools.com.co</a>.
 */
public class WebServiceHandler implements SOAPHandler<SOAPMessageContext> {

	private static Logger log = Logger.getLogger(WebServiceHandler.class);

	/**
	 * The handleMessage method is invoked for normal processing of inbound and
	 * outbound messages. Refer to the description of the handler framework in the
	 * JAX-WS specification for full details
	 */
	public boolean handleMessage(SOAPMessageContext context) {

		try {
			logToSystemOut(context);
		} catch (SOAPException e) {
			log.error(e.getMessage());
			return false;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return true;
	}

	/**
	 * Metodo encargado de construir la trama para escribor log
	 *
	 * @param context
	 * @throws SOAPException
	 */
	private void logToSystemOut(SOAPMessageContext context) throws SOAPException {

		final SOAPMessage message = context.getMessage();

		final QName operation = (QName) context.get(MessageContext.WSDL_OPERATION);

		final Boolean outboundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		final QName serviceName = (QName) context.get(MessageContext.WSDL_SERVICE);

		try {

			final StringBuilder strBuilder = new StringBuilder();

			if (outboundProperty.booleanValue()) {
				strBuilder.append("\n");
				strBuilder.append(">>> Thread [" + Thread.currentThread().getId() + "] WS-Mensaje Response-: ");

			} else {
				strBuilder.append("\n");
				strBuilder.append(">>> Thread [" + Thread.currentThread().getId() + "] WS - Mensaje Request: ");
			}
			strBuilder.append(serviceName != null ? serviceName.getLocalPart() : null);
			strBuilder.append(".");
			strBuilder.append(operation != null ? operation.getLocalPart() : null);

			strBuilder.append("\n");
			strBuilder.append(SOAPMessageTransformtoString(message));

			log.info(strBuilder.toString());

		} catch (Exception e) {
			log.info(message);
			log.error("Exception in handler: " + e);
		}
	}

	/**
	 * Called at the conclusion of a message exchange pattern just prior to the
	 * JAX-WS runtime dispatching a message, fault or exception. Refer to the
	 * description of the handler framework in the JAX-WS specification for full
	 * details.
	 */
	public void close(MessageContext mc) {
	}

	/**
	 * The handleFault method is invoked for fault message processing. Refer to the
	 * description of the handler framework in the JAX-WS specification for full
	 * details.
	 */
	public boolean handleFault(SOAPMessageContext mc) {
		return true;
	}

	/**
	 * Gets the header blocks that can be processed by this Handler instance
	 */
	@Override
	public Set<QName> getHeaders() {
		return Collections.emptySet();
	}

	/**
	 * Metodo encargado de identar la trama para escribir en el log
	 *
	 * @param soapMessage objeto de mensaje SOAP.
	 * @return String retorna el mensaje SOAP como un string
	 */
	private static String SOAPMessageTransformtoString(SOAPMessage soapMessage) {
		try {
			TransformerFactory tff = TransformerFactory.newInstance();
			tff.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			Transformer tf = tff.newTransformer();

			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			tf.setOutputProperty(OutputKeys.ENCODING, "utf-8");
			tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			tf.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

			Source sc = soapMessage.getSOAPPart().getContent();
			ByteArrayOutputStream streamOut = new ByteArrayOutputStream();
			StreamResult result = new StreamResult(streamOut);
			tf.transform(sc, result);

			String strMessage = streamOut.toString();
			return strMessage;
		} catch (Exception e) {
			return SOAPMessagetoString(soapMessage);
		}
	}

	/**
	 * Metodo encargado de controlar un error durante la identación de la trma a
	 * escribir en el log
	 *
	 * @param soapMessage el mensaje en un objeto
	 * @return String el mensaje a convertido
	 */
	private static String SOAPMessagetoString(SOAPMessage soapMessage) {
		String strMessage = soapMessage.toString();
		ByteArrayOutputStream streamOut = new ByteArrayOutputStream();
		try {
			soapMessage.writeTo(streamOut);
			strMessage = streamOut.toString();
		} catch (Exception e) {
		}

		return strMessage;
	}

}