package org.vbchin2.wildfly.simple_remote_messaging_client;

import static org.junit.Assert.*;

import javax.jms.JMSException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.vbchin2.wildfly.simple_remote_messaging_client.RemoteQueueInteractor;

public class RemoteQueueInteratorTest {

    private RemoteQueueInteractor remoteQueueIntegrator;

    @Before
    public void setUp() {
        try {
            remoteQueueIntegrator = new RemoteQueueInteractor();
        } catch (Exception e) {
            fail("Exception in creating an instance of RemoteQueueInteractor. "
                    + "Worthwhile to check if the server is up. "
                    + e.getMessage());
        }
    }

    @After
    public void tearDown() throws Exception {
        remoteQueueIntegrator = null;
    }

    @Test
    public void test() {
        try {
            String msg = "Some garbage of a message!";
            remoteQueueIntegrator.sendTextMessage(msg);
            assertEquals(msg, remoteQueueIntegrator.receiveTextMessage());
        } catch (JMSException jmse) {
            fail("JMS related exception, must be something wrong with the queues. "
                    + jmse.getMessage());
        }
    }

}
