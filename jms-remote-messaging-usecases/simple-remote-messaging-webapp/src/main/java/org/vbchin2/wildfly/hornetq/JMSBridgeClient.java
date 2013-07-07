package org.vbchin2.wildfly.hornetq;

import java.io.IOException;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;

public class JMSBridgeClient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(JMSBridgeClient.class);

	/* The following two lookups are based on how you configured the ConnectionFactory 
	 * and the local "Source Queue" for the JMS Bridge on Server 1. If you have followed 
	 * the installation that was provided as-is then you can go with the below code
	 */
	@Resource(mappedName = "java:/ConnectionFactory")
	private ConnectionFactory cf;

	@Resource(mappedName = "java:/queue/JMSBridgeSourceQ")
	private Queue queue;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {

			if (cf == null) {
				logger.fatal("The look up on java:/ConnectionFactory failed and null is returned");
				return;
			} else if (queue == null) {
				logger.fatal("The look up on java:/queue/JMSBridgeSourceQ failed and null is returned");
				return;
			}

			Connection connection = cf.createConnection();
			Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

			MessageProducer producer = session.createProducer(queue);
			producer.send(session.createTextMessage(request.getParameter("message")));

			session.close();
			connection.close();

		} catch (JMSException jmse) {
			logger.fatal("There has been a JMS exception. "+jmse.getMessage());
			jmse.printStackTrace();
		}
	}
}
