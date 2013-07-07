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

public class PooledConnectionFactoryClient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(PooledConnectionFactoryClient.class);

	@Resource(mappedName = "java:/RemoteJmsXA")
	private ConnectionFactory cf;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {

			if (cf == null) {
				logger.fatal("The look up on java:/RemoteJmsXA failed and null is returned");
				return;
			}

			Connection connection = cf.createConnection();
			Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

			/*
			 * Important note here that you shouldn't try to lookup the remote
			 * server queue from here as you do not need to. You are just
			 * creating a queue identity here, not a physical queue.
			 * http://docs.oracle.com/javaee/6/api/javax/jms/QueueSession.html#createQueue(java.lang.String)
			 */
			Queue queue = session.createQueue("LocalServer2Q");

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
