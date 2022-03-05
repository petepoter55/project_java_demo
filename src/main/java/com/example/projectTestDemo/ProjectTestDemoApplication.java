package com.example.projectTestDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class ProjectTestDemoApplication extends SpringBootServletInitializer {

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
