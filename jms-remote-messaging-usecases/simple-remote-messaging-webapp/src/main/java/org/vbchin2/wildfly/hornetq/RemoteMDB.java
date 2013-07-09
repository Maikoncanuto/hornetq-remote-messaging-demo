package org.vbchin2.wildfly.hornetq;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.jboss.logging.Logger;

/**
 * Message-Driven Bean implementation class for: RemoteMDB
 */
@MessageDriven(name = "RemoteMDB", activationConfig = { 
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "LocalServer2Q"),
        @ActivationConfigProperty(propertyName = "connectorClassName", propertyValue = "org.hornetq.core.remoting.impl.netty.NettyConnectorFactory"),        
        @ActivationConfigProperty(propertyName = "connectionParameters", propertyValue = "host=192.168.1.20;port=5545")})
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class RemoteMDB implements MessageListener {

	private static Logger logger = Logger.getLogger(RemoteMDB.class);
	
	/**
     * Default constructor. 
     */
    public RemoteMDB() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	TextMessage txtMsg = (TextMessage) message;
    	try {
			logger.info("The message received is : "+txtMsg.getText());
		} catch (JMSException e) {
			logger.error("There has been a JMSException ! "+e.getMessage());
		}
    }

}
