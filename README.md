HornetQ Remote Messaging Usecases
=================================

This demo shows the various ways of sending JMS messages to remote (JVM) HornetQ system running on JBoss AS a.k.a Wildfly

Prerequisites for the Demo
--------------------------
- Ensure that [Oracle JDK] (http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html) is installed
- Set the JAVA_HOME environment variable pointing to the JDK installation
- Update the PATH environment variable to include the bin directory of the JDK installation
- If in a new command prompt window you are able to run the command: *javac -version* you are all set

Highlights of the Demo
----------------------

The install process will:
- Setup two standlaone instances of Wildfly server. The second server is started with a port offset of 100
- Configure JMS Queues: JMSBridgeSourceQ, LocalServer1Q, JMS Bridge: simple-jms-brdige and a pooled-connection-factory using outbound JCA adapter on server 1 thru CLI scripts
- Configure JMS Queues: JMSBridgeTargetQ, LocalServer2Q on server 2 using the CLI scripts

In this demo you will see how to send messages to a:
- Remote queue running inside JBoss AS using a simple local Java client
- Remote queue running inside JBoss AS from a process running on local JBoss AS
- Local queue which will then forward the message to a remote using a JMS bridge

Pre-Installation Setup
----------------------
 1. [Download RHQ Bundle Deployer 4.8.0] (http://search.maven.org/remotecontent?filepath=org/rhq/rhq-ant-bundle-common/4.8.0/rhq-ant-bundle-common-4.8.0.zip)
 2. Extract the contents of rhq-ant-bundle-common-4.8.0.zip to the root folder of the demo

Command-Line Installation
-------------------------
- Open up Linux terminal or Windows command prompt
- Navigate to the demo root folder 
- Configure runtime properties in the file deployment.properties, including path to the deployment folder
- Execute either install.sh or install.bat based on the OS
- Based on the bind address configured in the deployment properties verify the startup of the server(s)

