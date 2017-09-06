package com.example.simplespringbootservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.net.util.IPAddressUtil;

import java.net.InetAddress;

@SpringBootApplication
@RestController
@EnableEurekaClient
public class SimpleSpringbootServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleSpringbootServiceApplication.class, args);
	}

	@GetMapping("/ips")
	public String getIpAddress() throws Exception{
		return InetAddress.getLocalHost().toString();
	}

}
