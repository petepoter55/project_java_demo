package com.example.projectTestDemo;

import com.example.projectTestDemo.queue.Receiver;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.springframework.amqp.core.Queue;

@SpringBootApplication
public class ProjectTestDemoApplication extends SpringBootServletInitializer {
	public static final String queueUpdateManage = "queueUpdateManage";

	@Bean
	Queue queueUpdateManage() {
		return new Queue(queueUpdateManage, true, false, false);
	}

	@Bean
	SimpleMessageListenerContainer containerUpdateManageDetail(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapterUpdateManageDetail) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueUpdateManage);
		container.setMessageListener(listenerAdapterUpdateManageDetail);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapterUpdateManageDetail(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveUpdateManageDetail");
	}

	static{
		try {
			System.setProperty("hostName", InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		SpringApplication.run(ProjectTestDemoApplication.class, args);
	}

}
