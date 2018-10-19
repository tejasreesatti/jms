package com.capgemini.consumer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Main {
	public static void main(String[] args) {
		ConnectionFactory connectionFactory;
		Connection connection = null;
	
	try {
		
		InitialContext intialContext = new InitialContext();
		Queue queue = (Queue) intialContext.lookup("JSTopic");
		connectionFactory =  
				(ConnectionFactory) intialContext.lookup("jms/__defaultConnectionFactory");
		
		connection = connectionFactory.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		connection.start();
		MessageConsumer messageConsumer = session.createConsumer(queue);
		TextMessage textMessage = (TextMessage) messageConsumer.receive();
		String body = textMessage.getText();
	
		System.out.println(body);
	}
	catch (NamingException e) {
		e.printStackTrace();
	} catch (JMSException e) {
		e.printStackTrace();
	}finally {
		if (connection != null) try {
			connection.close();
		} catch (JMSException e) {
		e.printStackTrace();
		}
	}
	}
}
	/*public static void main(String[] args) {
		ConnectionFactory connectionfactory;
		Connection connection = null;
		
		try {
			InitialContext intialContext = new InitialContext();
			Queue queue = (Queue) intialContext.lookup("jmsPSTopic");
			connectionfactory =  
					(ConnectionFactory) intialContext.lookup("jms/__defaultConnectionFactory");
			
			connection = connectionfactory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			connection.start();
			MessageConsumer messageconsumer = session.createConsumer(queue);
			TextMessage textmessage = (TextMessage) messageconsumer.receive();
			
			String body = textmessage.getText();
			System.out.println(body);
			
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}finally {
			if (connection != null) try {
				connection.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
	

}
*/