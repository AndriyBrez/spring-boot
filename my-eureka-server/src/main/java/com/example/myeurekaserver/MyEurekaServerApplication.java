package com.example.myeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableEurekaServer
@RestController
public class MyEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyEurekaServerApplication.class, args);
    }

    @GetMapping("/ip")
    public String getIpAdress() throws Exception {
        InetAddress ip = InetAddress.getLocalHost();
        return ip.toString() + "\t\t\t\t\t\t\t"+ ip.getHostName();
    }
}
